(ns user
  (:require [aleph.http :as http]
            [mirror.core :as mirror]
            [mirror.middleware :refer (make-handler)]))

(defonce server (atom nil))
(defonce handler (make-handler "src/pages" "public"))

(defn start-server []
  (reset! server (http/start-server #'handler {:port 3500})))

(defn go []
  (start-server))


