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
            [ring.util.response :refer (response header redirect status)]))

;; ===== Def Protocol Page =====

(require 'cljs.build.api)

(def watches (atom #{}))

(defn watch-fn []
  (println "WATCH FN DONE"))

(defn watch-js [src]
  (cljs.build.api/watch src
    {:optimizations :none
     :output-dir "public"
     :watch-fn watch-fn}))

(def compiler-env (env/default-compiler-env))

(defn build-js [src]
  (cljs.build.api/build src
   {:optimizations :none
    :cache-analysis true
    :compiler-stats true
    :parallel-build true
    :output-dir "public"}
   compiler-env))

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

(defn layout [ns-sym js props & body]
  (html5
    [:body
      [:div#__mount body]
      [:div#repl]
      (javascript-tag (str "__MIRROR_DATA__ = " "'" (pr-str props) "'"))
      (include-js "goog/base.js")
      (javascript-tag js)
      (javascript-tag (goog-require-str ns-sym))]))

(defn serve-page 
  "find a page file matching page-kw. renders and serves
   the found file with compile js"
  [path page-kw]
  ;; TODO refactor to have all this happen in 
  ;; bound ns and return data
  (let [path (str path "/" (name page-kw) ".cljc")
        load-res (load-file path)
        ns-str (str "pages." (name page-kw))
        render-sym (symbol (str ns-str "/render"))
        render-fn (resolve render-sym)
        ;; this is breaking! why can't I look it up!
        ;; gettinb back a 'var not an atom
        ;; found workaround with fn but still confused
        ; state-sym (symbol (str ns-str "/state"))
        ; state-var (resolve state-sym)
        inital-state-sym (symbol (str ns-str "/initial-state"))
        inital-state-fn (resolve inital-state-sym)
        reset-state-fn (resolve (symbol (str ns-str "/reset-state")))
        props (when (and inital-state-fn reset-state-fn)
                (reset-state-fn (inital-state-fn)))
        body (render-fn)
        start (System/currentTimeMillis)
        js (build-js path)
        end (System/currentTimeMillis)]
    (println "compiled cljs in" (- end start))
    (if (nil? render-fn)
      (response (str "could not find var: " render-sym))
      (-> (layout (symbol ns-str) js props body)
          (response)))))

(defn serve-not-found
  []
  (-> (response "mir not found")
      (status 404)))


