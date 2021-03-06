(ns mirror.core
  (:require [aleph.http :as http]
            [me.raynes.fs :as fs]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [hiccup.form :refer :all]
            [hiccup.def :refer :all]
            [cljs.env :as env]
            [hiccup.element :refer :all]
            [ring.middleware.file :refer (wrap-file)]
            [ring.util.response :refer (response header redirect status)]
            [manifold.stream :as s]
            [mirror.compile :as compile]
            [mirror.ws :as ws])
  (:import java.security.MessageDigest
           java.math.BigInteger))

;; ===== Def Protocol Page =====

(require 'cljs.build.api)

(defn- pages-set
  "returns a set of keywords representing
  all existing pages files in the pages dir"
  [path]
  (->> (fs/list-dir path)
       (map fs/base-name)
       (map #(clojure.string/split % #"\."))
       (map first)
       (map keyword)
       (set)))

(defn extract-path-kw
  "takes a req, returns the keywoard of the page 
  it's looking for"
  [req]
  (if (= "/" (:uri req))
    :index
    (-> (:uri req)
        (fs/split)
        (second)
        (keyword))))

(defn page-exists? 
  "returns true if page-kw is found
  in pages-set"
  [path page-kw]
  (contains? (pages-set path) page-kw))

(defn goog-require-str [ns-sym]
  (str "goog.require('" (str ns-sym) "')"))

(defn- mobile-viewport-tag []
  [:meta {:name "viewport"
          :content "width=device-width, initial-scale=1, user-scalable=no"}])

(defn layout [ns-sym js build? props & body]
  (html5
    [:head
      (mobile-viewport-tag)]
    [:body
      [:div#__mount body]
      [:div#repl]
      (javascript-tag (str "__MIRROR_DATA__ = " "'" (pr-str props) "'"))
      (include-css "/tachyons.min.css")
      (if (not build?)
        [:div
          (include-js "goog/base.js")
          (javascript-tag js)
          (javascript-tag (goog-require-str ns-sym))]
        [:div
          (javascript-tag js)])]))

(defn serve-page 
  "find a page file matching page-kw. renders and serves
   the found file with compile js"
  [path static-path page-kw build?]
  ;; TODO refactor to have all this happen in 
  ;; bound ns and return data
  (println "serving" page-kw)
  (let [path (str path "/" (name page-kw) ".cljc")
        load-res (load-file path)
        ns-str (str "pages." (name page-kw))
        render-sym (symbol (str ns-str "/render"))
        render-fn (resolve render-sym)
        inital-state-sym (symbol (str ns-str "/initial-state"))
        inital-state-fn (resolve inital-state-sym)
        props (when inital-state-fn
                (reset! (deref (resolve (symbol (str ns-str "/state"))))
                  (inital-state-fn)))
        body (render-fn)
        start (System/currentTimeMillis)
        js (compile/build-js path static-path build?)
        end (System/currentTimeMillis)]
    (println "compiled cljs in" (- end start))
    (if (nil? render-fn)
      (response (str "could not find var: " render-sym))
      (-> (layout (symbol ns-str) js build? props body)
          (response)))))

(defn serve-not-found
  []
  (-> (response "mir not found")
      (status 404)))

(defn relativize [base target]
  (.getPath
    (.relativize (.toURI (fs/file base))
                 (.toURI (fs/file target)))))

(defn watch-reload [src-path static-path]
  (compile/watch-js 
    src-path
    static-path
    (fn [changed]
      (let [to-reload
            (->> changed
                 (map #(relativize static-path %))
                 (filter #(.endsWith % ".js"))
                 (set))]
        (println "to-reload" to-reload)
        (ws/broadcast [:reload to-reload]))))) 
         



