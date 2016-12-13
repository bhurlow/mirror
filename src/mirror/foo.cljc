(ns mirror.foo)

(defn MYTESTFN []
  #?(:cljs (js/alert "BROWSER DFUF")
     :clj (println "SERVER HERE")))

(defprotocol Page
  (render [x])
  (setup [x]))

(def mybar
  (reify Page
    (render [props] 
      (println "INSIDE RENDER")
      [:h1 "sup"])
    (setup [x] 
      (println "IN SETUP")
      {:init "props"})))

