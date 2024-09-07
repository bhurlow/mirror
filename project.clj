(defproject mirror "0.3.0"
  :description "isomorphic clojure/script web apps"
  :main mirror.core
  :url "https://github.com/bhurlow/mirror"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories [["releases" {:url "https://clojars.org/repo" :creds :gpg}]]
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [aleph "0.8.1"]
                 ;; https://github.com/bhurlow/mirror/issues/14
                 ;; reagent 0.6.0 breaks stuff... ?? 
                 [reagent "0.5.0"]
                 [org.clojure/clojurescript "1.11.132"]
                 [hiccup "2.0.0-RC3"]
                 [me.raynes/fs "1.4.6"]
                 [mvxcvi/puget "1.0.1"]
                 [ring/ring-core "1.12.2"]]
  :uberjar-name "mirror.jar"
  :profiles {:dev {:source-paths  ["dev" "src" "test" "example"]}
             :uberjar {:main mirror.cli}})
