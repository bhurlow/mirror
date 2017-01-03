(ns pages.index
  (:require [pages.util :as u]
            [mirror.tools :as tools]
   #?(:cljs [reagent.core :as r])))

#?(:cljs (enable-console-print!))

;; the initial-state fn pre-populates
#?(:clj
   (defn initial-state [] 500))
      

(def state (tools/state-atom 0)) 

(defn handle-click [e]
  (swap! state inc))

;; render is called on both backend 
;; and frontend
(defn render []
  [:div 
    [:h1 "HELLO WORLD!"]
    [:p (str "clicks ->" @state)] 
    [:button {:on-click handle-click} "click me"]
    (u/foo)])


;; this will bootstrap the cljs code on
;; the browser side
;; want to clean this up a bit
(tools/inject state #'render)
