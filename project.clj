(defproject mirror "0.1.1"
  :description "isomorphic clojure/script web apps"
  :main mirror.core
  :url "https://github.com/bhurlow/mirror"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["releases" {:url "https://clojars.org/repo" :creds :gpg}]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.1-beta7"]
                 ;; https://github.com/bhurlow/mirror/issues/14
                 ;; reagent 0.6.0 breaks stuff... ?? 
                 [reagent "0.5.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [hiccup "1.0.5"]
                 [manifold "0.1.5"]
                 [me.raynes/fs "1.4.6"]
                 [mvxcvi/puget "1.0.1"]
                 [ring "1.4.0"]]
  :uberjar-name "mirror.jar"
  :profiles {:dev {:source-paths  ["dev" "src" "test" "example"]}
             :uberjar {:main mirror.cli}})
