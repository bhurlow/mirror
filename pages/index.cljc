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
   (defn get-initial-props []
      {:todos [{:text "do laundry"}
               {:text "email dad"}]}))

;; ===== actions

(def state (tools/box {}))

(defn handle-input-change [e]
  (println "ON INPUT CHANGE"))

(defn handle-submit [e]
  #?(:cljs (js/alert "submit!")))

;; ===== rendering

(defn render-todo-item [todo]
  [:p (str "- " (:text todo))])

(defn render [props]
  [:div 
   [:h1 "Todo List"
    (for [x (:todos props)]
      (render-todo-item x))
    [:hr]
    [:input {:type "text"
             :on-change handle-input-change
             :placeholder "make an todo item"}]
    [:input {:type "submit" 
             :on-click handle-submit
             :value "make it!"}]]])

(tools/inject)


