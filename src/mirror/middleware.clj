(ns mirror.middleware
  (:require [mirror.core :as c]
            [ring.middleware.file :refer (wrap-file)]
            [ring.util.response :refer (response header redirect status)]))

(defn wrap-pages [h path]
  (fn [req]
    (if (not (c/page-exists? path (c/extract-path-kw req)))
      (do (response "PAGE NOT FOUND"))
      (c/serve-page path (c/extract-path-kw req)))))

(defn handler [h]
  (fn [req]
    (h req)))

(defn make-handler [pages-path static-path]
  (-> handler
      (wrap-pages pages-path)
      (wrap-file static-path)))

