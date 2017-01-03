(ns pages.util
  (:require [clojure.string]
   #?(:cljs [goog.net.jsloader :as jsloader])
   #?(:cljs [goog.Uri :as guri])))

#?(:cljs (js/alert "CHANGE13"))

#?(:cljs
    (do
      (set! (.-isProvided_ js/goog) (fn [name] false))
      (aset js/window "doreq"
        (fn [] 
          (let [deferred (jsloader/load "pages/util.js")]
            (.addCallback deferred println))))))

(defn foo []
  [:h1 "HI!"])
