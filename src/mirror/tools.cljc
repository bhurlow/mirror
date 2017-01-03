(ns mirror.tools
  (:require 
    [mirror.util :as util]
    #?(:cljs [reagent.core :as r])
    #?(:cljs [cljs.reader :as reader])))

(defn state-atom 
  "returns an instrumented atom used to represent
  state"
  [data]
  #?(:cljs (r/atom data))
  #?(:clj (atom data)))

;; ==== setup reagent

;; cljs init
#?(:cljs 
   (defn- setup [state render-fn]
     (println "setting up frontend")
     (do
       (let [props (reader/read-string js/__MIRROR_DATA__)]
         (println "props" props)
         ;; TODO
         (println "state is right now" @state)
         ;; TODO how to derive the ns var??
         (r/render-component
          [render-fn]
          (.getElementById js/document "__mount"))))))

(defn inject [state render-fn]
  #?(:cljs (setup state render-fn)))
