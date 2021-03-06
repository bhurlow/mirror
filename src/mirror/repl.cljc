(ns mirror.repl
  (:require [clojure.string]
     #?(:cljs [reagent.core :as r])
     #?(:cljs [cljs.js :as self])))

(defonce state 
  #?(:clj (atom {}))
  #?(:cljs (r/atom {:history []})))

(defn css-str [style-map]
  (->> style-map
      (map (fn [pair]
             (str (name (first pair))
                  ": "
                  (second pair)
                  ";")))
      (interpose " ")
      (reduce str)))

(defn css [m]
  #?(:cljs {:style (clj->js m)})
  #?(:clj {:style (css-str m)}))

(def wrapper-style
  {:width "100%"
   :background "grey"})

(def input-style
  {:fontFamily "monospace"})

(def st 
   #?(:cljs (cljs.js/empty-state)))

(defn ctx-eval [x]
  #?(:cljs (js/eval (:source x))))

(defn handle-eval [form res]
  (swap! state update :history conj {:form form
                                     :res (:value res)})
  (println @state))

(defn process-form [s]
  (println "PROCESS" s)
  #?(:cljs
     (do
      (let [form (:text s)] 
        (println form)
        (cljs.js/eval-str 
          st 
          form
          nil 
          {:eval ctx-eval} 
          (partial handle-eval form))))))

(defn handle-key [e]
  (when (= "Enter" (.-key e))
    (process-form @state)))

(defn handle-change [e]
  (swap! state assoc :text (.-value (.-target e))))

(defn render-repl []
  [:div (css wrapper-style)
    (for [x (:history @state)]
      [:div 
        [:p (:form x)]
        [:p (:res x)]])
    [:input (merge (css input-style) 
                   {:type "text"
                    :on-key-press handle-key
                    :on-change handle-change})]])

(defn mount []
  #?(:cljs 
      (r/render-component
        [render-repl]
        (.getElementById js/document "repl"))))


