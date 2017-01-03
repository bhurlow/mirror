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

;; ===== setup ws conn

#?(:cljs
   (defn ws-on-open []
    (println "WS OPEN")))

#?(:cljs
   (defn ws-on-close []
    (println "WS CLOSE!")))

#?(:cljs
   (defn ws-on-message [e]
    (println "WS MESSAGE")
    (println (.-data e))))

#?(:cljs
   (defn new-ws []
      (new js/WebSocket 
        (str "ws://" js/window.location.host "/_ws"))))

#?(:cljs
   (defn- init-ws []
     (let [ws (new-ws)] 
       (aset ws "onopen" ws-on-open)
       (aset ws "onclose" ws-on-close)
       (aset ws "onmessage" ws-on-message))))

;; ==== setup reagent

;; cljs init
#?(:cljs 
   (defn- setup-react [state render-fn]
     (println "frontend init")
     (do
       (let [props (reader/read-string js/__MIRROR_DATA__)]
         (println "initial props" props)
         ;; TODO
         (reset! state props)
         ;; TODO how to derive the ns var??
         (r/render-component
          [render-fn]
          (.getElementById js/document "__mount"))))))

(defn inject [state render-fn]
  #?(:cljs 
     (do
       (setup-react state render-fn)
       (init-ws))))

