(ns pages.index
  (:require [pages.util :as u]))

(println "HELLO INDEX")

(defn render []
  [:div 
    [:h1 "RE!"]
    (u/foo)])
