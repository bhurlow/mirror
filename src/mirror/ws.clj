(ns mirror.ws
  (:require [manifold.stream :as s]))

(defonce ws-conns 
  (atom #{}))

(defn close-all! []
  (map s/close! @mirror.ws/ws-conns))

(defn open-conns []
  (filter 
    (complement s/closed?)
    @mirror.ws/ws-conns))

(defn broadcast [msg]
  (doseq [conn (open-conns)]
    (s/put! conn (pr-str msg))))
  
