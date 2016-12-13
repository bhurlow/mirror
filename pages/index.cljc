(ns pages.index)

#?(:cljs (enable-console-print!))
(println "SUP!")

(defn MYTESTFN []
  #?(:cljs (js/alert "BROWSER DFUF")
     :clj (println "SERVER HERE")))

#?(:cljs (js/alert "HOLA!"))

(defprotocol Page
  (render [x])
  (setup [x]))

(defn page []
  (reify Page
    (render [props] 
      (println "INSIDE RENDER")
      [:h1 "pre rendered htmls!"])
    (setup [x] 
      (println "IN SETUP")
      {:init "props"})))

