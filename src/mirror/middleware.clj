(ns mirror.middleware
  (:require [mirror.core :as c]
            [mirror.ws :as ws]
            [aleph.http :as http]
            [manifold.stream :as s]
            [ring.middleware.params :refer (wrap-params)]
            [ring.middleware.keyword-params :refer (wrap-keyword-params)]
            [ring.middleware.file :refer (wrap-file)]
            [ring.middleware.resource :refer (wrap-resource)]
            [ring.util.response :refer (response header redirect status)]
            [ring-image-crop.core :refer (wrap-image-crop)]))

(defn wrap-pages [h pages-path static-path]
  (fn [req]
    (if (not (c/page-exists? pages-path (c/extract-path-kw req)))
      (h req)
      (c/serve-page pages-path static-path (c/extract-path-kw req)))))

(defn handler [req]
  (if (= "/_ws" (:uri req))
    (do
      (let [s @(http/websocket-connection req)]
        (println "websocket connection")
        (swap! ws/ws-conns conj s))))
  (response "page not found"))

(defn make-handler [pages-path static-path]
  (-> #'handler
      (wrap-pages pages-path static-path)
      (wrap-file static-path)
      (wrap-image-crop static-path)
      (wrap-keyword-params)
      (wrap-params)
      (wrap-resource "/")))

