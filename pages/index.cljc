(ns pages.index
  (:require [components.thing]
            #?(:cljs [cljs.reader :as reader])
            #?(:cljs [cljsjs.react])
            #?(:cljs [cljsjs.react.dom])
            #?(:cljs [reagent.core :as r])
            #?(:cljs [cljs.pprint])))

#?(:cljs (enable-console-print!))


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
     (let [props (reader/read-string js/__MIRROR_DATA__)]
       (println "props" (keys props)))))

; #?(:cljs
;    (do
;     (defonce thing (r/atom 0))
;     (defn handle-click [e]
;       (swap! thing inc)
;       (println e))
;     (defn foo-component []
;       [:div {:on-click handle-click} (str "SUP" @thing)])
;     (r/render-component 
;       [foo-component] 
;       (.getElementById js/document "__mount"))))

    ; (defn state-ful-with-atom []
    ;   [:div {:on-click #(swap! click-count inc)}
    ;    "I have been clicked " @click-count " times."]))




