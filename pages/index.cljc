(ns pages.index
  (:require [components.thing]
            #?(:cljs [cljs.reader :as reader])
            #?(:cljs [cljsjs.react])
            #?(:cljs [om.dom :as dom])
            #?(:cljs [cljs.pprint])))

#?(:cljs (enable-console-print!))

#?(:cljs 
   (do
     (let [props (reader/read-string js/__MIRROR_DATA__)]
       (println "props" (keys props)))
       (println "OMDOM" (type (dom/h1 nil "foo")))
       (js/console.log (dom/h1 nil "FOO"))))

(defn MYTESTFN []
  #?(:cljs (js/alert "BROWSER DFUF")
     :clj (println "SERVER HERE")))

(defprotocol Page
  (render [x])
  (setup [x]))

(defn page []
  (reify Page
    (render [props] 
      (println "INSIDE RENDER")
      [:div
        [:h1 "pre rendered htmls!"]
        (components.thing/widget 123)])
    (setup [x] 
      (println "IN SETUP")
      {:init "props"
       :values '(123 33 44 55)})))

