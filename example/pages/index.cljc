(ns pages.index
  (:require [pages.util :as u]))

(defn render []
  [:div 
    [:h1 "RE!"]
    (u/foo)])
