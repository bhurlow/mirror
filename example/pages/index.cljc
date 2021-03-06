(ns pages.index
  (:require [pages.util :as u]
            [mirror.tools :as tools]))

#?(:cljs (enable-console-print!))

;; the initial-state fn pre-populates the state atom
(defn initial-state [] 88)
      
;; tools/state-atom provides a special kind of state
;; atom that syncs with server
(defonce state (tools/state-atom {})) 

(defn handle-click [e]
  (swap! state (fn [x] (- x 10))))

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
