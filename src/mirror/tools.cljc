(ns mirror.tools
  (:require 
    [mirror.util :as util]
    #?(:cljs [reagent.core :as r])
    #?(:cljs [cljs.reader :as reader])
    #?(:cljs [goog.net.jsloader :as jsloader])
    #?(:cljs [goog.Uri :as guri])
    #?(:cljs [cljs.reader :refer (read-string)])))

(defn state-atom 
  "returns an instrumented atom used to represent
  state"
  [data]
  #?(:cljs (r/atom data))
  #?(:clj (atom data)))

;; ===== reloading tools 

#?(:cljs
    (defn- on-reload [path]
    ;; this would trigger HUD
      (println "RELOADED > " path)))

#?(:cljs
    (defn reload-js-file [path]
      (println "attempting reload of" path)
      ;; this is a REQUIRED patch of the goog module loader
      ;; see custom repl docs for details
      (set! (.-isProvided_ js/goog) (fn [name] false))
      (let [deferred (jsloader/load path)]
        (.addCallback deferred #(on-reload path))
        (.addErrback deferred 
                     (fn [x] (println "module load error" x))))))

;; ===== websocket tooling

#?(:cljs
   (defn- handle-ss-cmd [s]
     ;; TODO assert form of cmds
     (let [data (read-string s)] 
       (println data)
       (case (first data)
         :reload (doall (map reload-js-file (second data)))
         (println "dont know the" (first data) "cmd")))))
      
#?(:cljs
   (defn- ws-on-open []
    (println "WS OPEN")))

#?(:cljs
   (defn- ws-on-close []
    (println "WS CLOSE!")))

#?(:cljs
   (defn- ws-on-message [e]
    (println "WS MESSAGE")
    (handle-ss-cmd (.-data e))))

#?(:cljs
   (defn- new-ws []
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

(defonce initialized (atom false))

(defn inject [state render-fn]
  #?(:cljs 
     (do
       (setup-react state render-fn)
       (when-not @initialized
         (init-ws)
         (reset! initialized true)))))

;; for evaluating self-hosted
;; and use in repl
; #?(:cljs
;    (do
;      (def st (cljs.js/empty-state))
;      (def my-eval-fn 
;        (fn [x] 
;          (println "EVAL FN" x)
;          (js/eval (:source x))))
;      (let [eval-fn #(cljs.js/eval-str st % nil {:eval my-eval-fn} println)]
;        (aset js/window (name :myeval) eval-fn))))

