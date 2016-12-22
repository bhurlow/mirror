(ns pages.index
  (:require [components.thing]
            [mirror.tools :as tools]
            #?(:clj  [clojure.java.io :as io])
            #?(:cljs [cljs.reader :as reader])
            #?(:cljs [cljsjs.react])
            #?(:cljs [cljsjs.react.dom])
            #?(:cljs [reagent.core :as r])
            #?(:cljs [cljs.pprint])))

#?(:cljs (enable-console-print!))

#?(:clj
   (defn initial-state []
      {:todos [{:text "do laundry"}
               {:text "email dad"}]}))

;; ===== actions

(defonce state (tools/box {}))

;; my ns resolves can't seem to find an atom
;; works on fns tho...
;; (reset! (resolve 'pages.index/state) 123)
(defn reset-state [data]
  (println "setting initial state")
  (reset! state data))

(defn handle-input-change [e]
  #?(:cljs
     (do
      (swap! state assoc :in (.-value (.-target e))))))

(defn handle-submit [e]
  #?(:cljs 
     (do 
       (swap! state update-in [:todos] conj {:text (:in @state)}))))

(defn handle-lisp-change [e]
  #?(:cljs
     (do
      (swap! state assoc :lisp (.-value (.-target e))))))

(defn handle-lisp-submit [e]
  #?(:cljs
     (js/alert "SUP")))

;; ===== rendering

(defn render-todo-item [todo]
  [:p {:key (:text todo)} 
   (str "- " (:text todo))])

(defn render []
  (println "RENDERING")
  (println "STATE AT RENDER" @state)
  [:div 
   [:h1 "Todo List"]
   (for [x (:todos @state)]
     (render-todo-item x))
   [:hr]
   [:input {:type "text"
            :on-change handle-input-change
            :placeholder "make an todo item"}]
   [:input {:type "submit" 
            :on-click handle-submit
            :value "make it!"}]
   [:input {:type :text
            :on-change handle-lisp-change
            :placeholder "insert command"}]
   [:input {:type "submit" 
            :on-click handle-lisp-submit
            :value "eval form"}]])

(tools/inject)


