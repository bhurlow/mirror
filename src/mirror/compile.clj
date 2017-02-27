(ns mirror.compile
  (:require [cljs.env :as env]
            [me.raynes.fs :as fs]
            [cljs.build.api])
  (:import java.security.MessageDigest
           java.math.BigInteger))

;; move to util?
(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

;; this should use .lastModified method on File
;; no need to actually hash the contents
(defn checksum-files [static-path]
  (->> (file-seq (fs/file static-path))
       (filter #(.isFile %))
       (pmap (fn [x] [(str x) (md5 (slurp x))]))
       (set)))

(defonce file-stats 
  (atom nil))

(defn compare-files 
  "returns a suitable callback function for cljs watch
   which returns a set of changed files"
  [static-path callback]
  (fn []
    (let [before @file-stats
          after (checksum-files static-path)]
      (when (nil? before) (reset! file-stats after))
      (when before
        (reset! file-stats after)
        (->> (clojure.set/difference before after)
             (map first)
             (set)
             (callback))))))

(defn watch-js 
  "given a dir of cljs/cljc files,
   watches dir for changes and calls callback
   with a set of changed paths"
  [src static-path callback]
  (cljs.build.api/watch src
    {:optimizations :none
     :verbose false
     :output-dir static-path
     :watch-fn (compare-files static-path callback)}))

;; ===== production js builds 

(def compiler-env (env/default-compiler-env))

(defonce build-cache 
  (atom {}))

(defonce last-build-checksum 
  (atom {}))

(defn rebuild-required? [src]
  (not= (checksum-files src) 
        (get @last-build-checksum src)))

(defn build-js [src static-path build?]
  (println "building js for" src static-path)
  (println "building advanced?" build?)
  (if (not (rebuild-required? src))
    (do
      (println "no js build required")
      (get @build-cache src))
    (do 
      (println "building production js...")
      (swap! last-build-checksum assoc src (checksum-files src))
      (-> (swap! build-cache assoc src
            (cljs.build.api/build 
              src
              (if (not build?)
                 {:optimizations :none
                  :cache-analysis true
                  :compiler-stats true
                  :parallel-build true
                  :output-dir static-path}
                 {:optimizations :advanced
                  :parallel-build true
                  :output-dir static-path})
              compiler-env))
          (get src)))))




