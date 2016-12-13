(ns user
  (:require [aleph.http :as http]
            [mirror.core :as mirror]))

(defonce server (atom nil))

(defn start-server []
  (reset! server (http/start-server #'mirror/app {:port 3500})))

(defn go []
  (start-server))


