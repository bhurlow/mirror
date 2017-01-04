(ns pages.util
  (:require [clojure.string]
   #?(:cljs [goog.net.jsloader :as jsloader])
   #?(:cljs [goog.Uri :as guri])))

(defn foo []
  [:h1 "HI!"])
