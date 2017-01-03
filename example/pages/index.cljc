(ns pages.index
  (:require [pages.util :as u]
            [mirror.tools :as tools]
   #?(:cljs [reagent.core :as r])
   #?(:cljs [mirror.repl :as repl])))

#?(:cljs (enable-console-print!))
#?(:cljs (js/alert "FOO"))


;; the initial-state fn pre-populates the state atom
(defn initial-state [] 500)
      
;; tools/state-atom provides a special kind of state
;; atom that syncs with server
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

#?(:cljs
    (do
      (aset js/window "testfn"
        (fn [] (println "SUP!")))))

;; this will bootstrap the cljs code on
;; the browser side
;; want to clean this up a bit
(tools/inject state #'render)

; #?(:cljs (repl/mount))
