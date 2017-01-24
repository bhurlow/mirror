(ns pages.taco
  (:require [pages.util :as u]
            [mirror.tools :as tools]))

#?(:cljs (enable-console-print!))

(defonce state (tools/state-atom {:x 0 :y 0})) 

(defn handle-mouse-move [e]
  #?(:cljs
      (do
        ; (js/console.log (clj->js e))
        (reset! state
                {:x (.-screenX e)
                 :y (.-screenY e)}))))

#?(:cljs
   (aset js/window "onmousemove" handle-mouse-move)) 

(defn render []
  [:div
    [:h1 "HI MOM"]
    [:p "x:" (:x @state)]
    [:p "y:" (:y @state)]])

(tools/inject state #'render)
