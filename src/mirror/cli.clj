(ns mirror.cli
  (:require [me.raynes.fs :as fs]
            [aleph.http :as http]
            [mirror.middleware :refer (make-handler)])
  (:gen-class))

(defn check-if-files-exists [pages-path static-path]
  (when 
    (not (or (fs/exists? pages-path)
             (fs/exists? static-path)))
    (throw (Exception. "cant find pages or static dirs"))))

(defn cli-start [args]
  (println "cli" args)
  (let [pages-path  (or (first args) "pages")
        static-path (or (first args) "static")]
    (check-if-files-exists pages-path static-path)
    (let [port (or (System/getenv "PORT") 3000)]
      (println "starting server on port" port)
      (http/start-server 
        (make-handler pages-path static-path) 
        {:port port}))))

(defn -main [& args] 
  (cli-start args))

