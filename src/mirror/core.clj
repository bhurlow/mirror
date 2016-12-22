(ns mirror.core
  (:require [aleph.http :as http]
            [me.raynes.fs :as fs]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [hiccup.form :refer :all]
            [hiccup.def :refer :all]
            [hiccup.element :refer :all]
            [ring.middleware.file :refer (wrap-file)]
            [ring.util.response :refer (response header redirect status)]))

;; ===== Def Protocol Page =====

(require 'cljs.build.api)

(defn build-js [src]
  (cljs.build.api/build src
   {:optimizations :none
    :output-dir "public"}))

(defn- pages-set
  "returns a set of keywords representing
  all existing pages files in the pages dir"
  []
  (->> (fs/list-dir "pages")
       (map fs/base-name)
       (map #(clojure.string/split % #"\."))
       (map first)
       (map keyword)
       (set)))

(defn- extract-path-kw
  "takes a req, returns the keywoard of the page 
  it's looking for"
  [req]
  (if (= "/" (:uri req))
    :index
    (-> (:uri req)
        (fs/split)
        (second)
        (keyword))))

(defn- page-exists? 
  "returns true if page-kw is found
  in pages-set"
  [page-kw]
  (contains? (pages-set) page-kw))

(defn goog-require-str [ns-sym]
  (str "goog.require('" (str ns-sym) "')"))

(defn layout [ns-sym js props & body]
  (html5
    [:body
      ; [:h1 "layout"]
      ; [:p (str props)]
      [:div#__mount body]
      (javascript-tag (str "__MIRROR_DATA__ = " "'" (pr-str props) "'"))
      (include-js "goog/base.js")
      (javascript-tag js)
      (javascript-tag (goog-require-str ns-sym))]))

(defn serve-page 
  "call the render fn in corresponding page-kw file"
  [page-kw]
  (let [path (str "pages/" (name page-kw) ".cljc")
        load-res (load-file path)
        ns-str (str "pages." (name page-kw))
        render-sym (symbol (str ns-str "/render"))
        render-fn (resolve render-sym)
        props-sym (symbol (str ns-str "/get-initial-props"))
        props-fn (resolve props-sym)
        props (when props-fn (props-fn))
        body ((render-fn) (or props {}))
        js (build-js path)]
    (if (nil? render-fn)
      (response (str "could not find var: " render-sym))
      (-> (layout (symbol ns-str) js props body)
          (response)))))

(defn serve-not-found
  []
  (-> (response "not found")
      (status 404)))

(defn wrap-pages [h]
  (fn [req]
    (if (not (page-exists? (extract-path-kw req)))
      (serve-not-found)
      (serve-page (extract-path-kw req)))))

(defn handler [h]
  (fn [req]
    (h req)))

(def app
  (-> handler
      (wrap-pages)
      (wrap-file "public")))





