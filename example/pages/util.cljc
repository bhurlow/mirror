(ns pages.util
  (:require [clojure.string]
   #?(:cljs [goog.net.jsloader :as jsloader])
   #?(:cljs [goog.Uri :as guri])))


#?(:cljs (js/alert "IN THIS 60"))

(defn foo []
  [:h1 "HOLAAAA"])
