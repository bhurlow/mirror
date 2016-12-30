(defproject mirror "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :main mirror.core
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.1-beta7"]
                 [cljsjs/react "15.3.1-0"]
                 [cljsjs/react-dom "15.3.1-0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [reagent "0.6.0" :exclusions [cljsjs/react]]
                 [hiccup "1.0.5"]
                 [manifold "0.1.3"]
                 [me.raynes/fs "1.4.6"]
                 [mvxcvi/puget "1.0.1"]
                 [ring "1.4.0"]]
  :profiles {:dev {:source-paths  ["dev" "src" "test"]}})
