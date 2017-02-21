(ns pages.taco
  (:require [mirror.tools :as tools]))

#?(:cljs (enable-console-print!))

(defn initial-state [] 88)

(defonce state (tools/state-atom {})) 

(defn nav []
  [:ul
   [:li "i am link"]])

(defn handle-click [e]
  #?(:cljs (js/alert "HI MOM")))

(defn render []
  [:div
   [:h1 {:on-click handle-click} "hello taco"]
   (nav)])

(tools/inject state #'render)
