(ns pages.index
  (:require [pages.util :as u]
            [mirror.tools :as tools]
   #?(:cljs [reagent.core :as r])))

#?(:cljs (js/alert "SUP"))

;; this will bootstrap the cljs code on
;; the browser side
(tools/inject)

; (def state (tools/state-atom {})) 
(def state (atom {}))

(defn handle-click [e])
  

;; render is called on both backend 
;; and frontend
(defn render []
  [:div 
    [:h1 "HELLO WORLD!"]
    ; [:p (str "clicks ->" (:count @state))] 
    [:button {:on-click handle-click} "click me"]
    (u/foo)])

