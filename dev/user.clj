(ns user
  (:require [aleph.http :as http]
            [mirror.core :as mirror]
            [mirror.middleware :refer (make-handler)]))

(defonce server (atom nil))

(defn start-server [build?]
  (let [handler (make-handler "example/pages" "example/static" build?)]
    (reset! server (http/start-server handler {:port 3000}))))

(defn go [build?]
  (start-server build?))

(defn stop []
  (when @server (.close @server))
  (require 'mirror.middleware :reload-all))


