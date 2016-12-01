(ns mirror.core
  (:require [aleph.http :as http]
            [ring.util.response :refer (response header redirect status)]))

(defn wrap-pages [h]
  (fn [req]
    (response "FOO")))
