(ns mirror.watch
  "utilities for better cljs file watching")

(require 'cljs.build.api)
(require 'clojure.tools.namespace.find)
(require 'clojure.java.classpath)

(defn- root-resource
  "Returns the root directory path for a lib"
  {:tag String}
  [lib]
  (str \/
       (.. (name lib)
           (replace \- \_)
           (replace \. \/))))

(root-resource 'components.thing)

; (io/reOD)

; (clojure.java.classpath/classpath)
; (clojure.tools.namespace.find/find-namespaces (clojure.java.io/as-file "pages/index.cljs"))

(read-string {:read-cond :allow 
              :features #{:cljs}}
             (slurp "pages/index.cljc"))
