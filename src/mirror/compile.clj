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

(def compiler-env (env/default-compiler-env))

(defn build-js [src static-path]
  (cljs.build.api/build src
   {:optimizations :none
    :cache-analysis true
    :compiler-stats true
    :parallel-build true
    ; :asset-path "STATIC"
    :output-dir static-path}
   compiler-env))

