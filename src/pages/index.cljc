(ns pages.index
  (:require [components.thing]
            [mirror.tools :as tools]
            ; [mirror.repl :as repl]
            #?(:clj  [clojure.java.io :as io])
            #?(:cljs [cljs.reader :as reader])
            #?(:cljs [cljs.js :as self])
            #?(:cljs [cljsjs.react])
            #?(:cljs [cljsjs.react.dom])
            #?(:cljs [reagent.core :as r])
            #?(:cljs [cljs.pprint])))

#?(:cljs (enable-console-print!))

#?(:clj
   (defn initial-state []
      {:todos [{:text "do laundry"}
               {:text "email dad"}]}))

#?(:cljs
   (do
     (def st (cljs.js/empty-state))
     (def my-eval-fn 
       (fn [x] 
         (println "EVAL FN" x)
         (js/eval (:source x))))
     (let [eval-fn #(cljs.js/eval-str st % nil {:eval my-eval-fn} println)]
       (aset js/window (name :myeval) eval-fn))))

;; ===== actions

(defonce state (tools/box {}))

;; my ns resolves can't seem to find an atom
;; works on fns tho...
;; (reset! (resolve 'pages.index/state) 123)
(defn reset-state [data]
  (reset! state data))

(defn handle-input-change [e]
  #?(:cljs
     (do
      (swap! state assoc :in (.-value (.-target e))))))

(defn handle-submit [e]
  #?(:cljs 
     (do 
       (swap! state update-in [:todos] conj {:text (:in @state)}))))

(defn handle-lisp-change [e]
  #?(:cljs
     (do
      (swap! state assoc :lisp (.-value (.-target e))))))

(defn handle-lisp-submit [e]
  #?(:cljs
     (js/alert "SUP")))

;; ===== rendering

(defn foo []
  (swap! state assoc :foo 123))

(defn render-todo-item [todo]
  [:p {:key (:text todo)} 
   (str "- " (:text todo))])

(defn render []
  [:div 
   [:h1 "Todo List"]
   (for [x (:todos @state)]
     (render-todo-item x))
   [:hr]
   [:input {:type "text"
            :on-change handle-input-change
            :placeholder "make an todo item"}]
   [:input {:type "submit" 
            :on-click handle-submit
            :value "make it!"}]
   [:input {:type :text
            :on-change handle-lisp-change
            :placeholder "insert command"}]
   [:input {:type "submit" 
            :on-click handle-lisp-submit
            :value "eval form"}]])

(tools/inject)
; (repl/repl)


