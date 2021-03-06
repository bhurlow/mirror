(ns mirror.cli
  (:require [me.raynes.fs :as fs]
            [aleph.http :as http]
            [mirror.middleware :refer (make-handler)]
            [mirror.core :as core])
  (:gen-class))

(def known-flags
  #{:watch})

(defn check-if-files-exists [pages-path static-path]
  (when 
    (not (or (fs/exists? pages-path)
             (fs/exists? static-path)))
    (throw (Exception. "cant find pages or static dirs"))))

(defn check-if-flags-valid [flags])

(defn cli-start [args]
  (println "cli args ->" args)
  (let [parsed (map read-string args)
        flags (set (filter keyword? parsed))
        args (filter (complement keyword?) parsed)
        watch? (contains? flags :watch)
        build? (contains? flags :build)
        pages-path  (or (some-> (first args) str) "pages")
        static-path (or (some-> (second args) str) "static/out")]
    (check-if-flags-valid flags)
    (check-if-files-exists pages-path static-path)
    (let [port (or (System/getenv "PORT") 3000)]
      (println "starting server on port" port)
      (http/start-server 
        (make-handler 
          pages-path 
          static-path 
          (or build? false))
        {:port port}))
    ;; this blocks
    (when watch?
      (println "watching for changes in" pages-path)
      (core/watch-reload pages-path static-path))))

(defn -main [& args] 
  (cli-start args))



