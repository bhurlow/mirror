(ns mirror.middleware
  (:require [mirror.core :as c]
            [ring.middleware.file :refer (wrap-file)]
            [ring.middleware.resource :refer (wrap-resource)]
            [ring.util.response :refer (response header redirect status)]))

(defn wrap-pages [h pages-path static-path]
  (fn [req]
    (if (not (c/page-exists? pages-path (c/extract-path-kw req)))
      (do (response "PAGE NOT FOUND"))
      (c/serve-page pages-path static-path (c/extract-path-kw req)))))

(defn handler [h]
  (fn [req]
    (h req)))

(defn make-handler [pages-path static-path]
  (-> handler
      (wrap-pages pages-path static-path)
      (wrap-file static-path)
      (wrap-resource "/")))

