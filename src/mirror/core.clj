(ns mirror.core
  (:require [aleph.http :as http]
            [me.raynes.fs :as fs]
            [ring.util.response :refer (response header redirect status)]))

(defn- pages-set
  "returns a set of keywords representing
  all existing pages files in the pages dir"
  []
  (->> (fs/list-dir "pages")
       (map fs/base-name)
       (map #(clojure.string/split % #"\."))
       (map first)
       (map keyword)
       (set)))

(defn- extract-path-kw
  "takes a req, returns the keywoard of the page 
  it's looking for"
  [req]
  (if (= "/" (:uri req))
    :index
    (-> (:uri req)
        (fs/split)
        (second)
        (keyword))))

(defn- page-exists? 
  "returns true if page-kw is found
  in pages-set"
  [page-kw]
  (contains? (pages-set) page-kw))

(defn serve-page 
  "renders the page model found
  at page-kw"
  [page-kw]
  (response (str page-kw)))

(defn serve-not-found
  []
  (-> (response "not found")
      (status 404)))

(defn wrap-pages [h]
  (fn [req]
    (if (not (page-exists? (extract-path-kw req)))
      (serve-not-found)
      (serve-page (extract-path-kw req)))))

(defn handler [h]
  (fn [req]
    (h req)))

(def app
  (-> handler
      (wrap-pages)))





