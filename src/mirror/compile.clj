(ns mirror.compile
  (:require [cljs.env :as env]
            [cljs.build.api]))

(def watches (atom #{}))

(defn watch-fn []
  (println "WATCH FN DONE"))

(defn watch-js [src]
  (cljs.build.api/watch src
    {:optimizations :none
     :output-dir "public"
     :watch-fn watch-fn}))

(def compiler-env (env/default-compiler-env))

(defn build-js [src static-path]
  (println "BUILD" static-path)
  (cljs.build.api/build src
   {:optimizations :none
    :cache-analysis true
    :compiler-stats true
    :parallel-build true
    ; :asset-path "STATIC"
    :output-dir static-path}
   compiler-env))
