(ns user
  (:require [aleph.http :as http]
            [mirror.core :as mirror]
            [mirror.middleware :refer (make-handler)]))

(defonce server (atom nil))

(defn start-server []
  (let [handler (make-handler "example/pages" "example/static")]
    (reset! server (http/start-server handler {:port 3000}))))

(defn go []
  (start-server))

(defn restart []
  (when @server (.close @server))
  (require 'mirror.middleware :reload-all)
  (start-server))


