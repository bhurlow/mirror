(ns mirror.cli
  (:require [me.raynes.fs :as fs]
            [aleph.http :as http]
            [mirror.middleware :refer (make-handler)])
  (:gen-class))

(defn check-if-files-exists []
  (println "cwd" fs/*cwd*)
  (when 
    (not (or (fs/exists? "pages")
             (fs/exists? "static")))
    (throw (Exception. "cant find pages or static dirs"))))

(defn cli-start [args]
  (check-if-files-exists)
  (http/start-server 
    (make-handler 
      (or (first args) "pages") 
      (or (second args) "static")) 
    {:port 3000}))

(defn -main [& args] (cli-start args))

