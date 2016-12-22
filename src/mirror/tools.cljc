(ns mirror.tools
  (:require 
    [mirror.util :as util]
    #?(:cljs [reagent.core :as r])
    #?(:cljs [cljs.reader :as reader])))

(defn box [data]
  #?(:cljs (r/atom data))
  #?(:clj (atom data)))

;; ==== setup reagent

;; cljs init
#?(:cljs 
   (defn- setup []
     (println "SETIP CALLED")
     (do
       (let [props (reader/read-string js/__MIRROR_DATA__)]
         (println "props" props)
         ;; TODO how to derive the ns var??
         (r/render-component
          ((pages.index/render) props)
          (.getElementById js/document "__mount"))))))

(defn inject []
  #?(:cljs (setup)))
