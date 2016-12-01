(ns user
  (:require [aleph.http :as http]
            [mirror.core :as mirror]))

(defonce server (atom nil))

(defn handler [h]
  (fn [req]
    (h req)))

(def app
  (-> handler
      (mirror/wrap-pages)))

(defn start-server []
  (reset! server (http/start-server #'app {:port 3500})))

(defn go []
  (start-server))


