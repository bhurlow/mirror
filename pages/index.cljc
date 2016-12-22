(ns pages.index
  (:require [components.thing]
            #?(:cljs [cljs.reader :as reader])
            #?(:cljs [cljsjs.react])
            #?(:cljs [cljsjs.react.dom])
            #?(:cljs [reagent.core :as r])
            #?(:cljs [cljs.pprint])))

#?(:cljs (enable-console-print!))

(defonce foo 555)

(defprotocol Page
  (render [props state])
  (setup [x]))

(defn page []
  (reify Page
    (render [props state] 
      (println "INSIDE RENDER")
      [:div
        [:h1 (str "click!" (:clicks state))]])
      ; [:div
      ;   [:h1 "pre rendered htmls!"]
      ;   (components.thing/widget 123)])
    (setup [x] 
      (println "IN SETUP")
      {:init "props"
       :values '(123 33 44 55)})))

;; cljs init
#?(:cljs 
   (do
     (let [props (reader/read-string js/__MIRROR_DATA__)
           state (r/atom {:clicks 0})]
       (println "props" (keys props))
       (aset js/window "onclick" #(swap! state update :clicks inc))
      (defn foo-component []
        [:div (str "SUP" (:clicks @state))])
      (r/render-component 
        [foo-component] 
        (.getElementById js/document "__mount")))))




