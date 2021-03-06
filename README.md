# mirror

A mimimal framework for writing isomorphic clj/cljs web applications. Mirror shares rendering code between the backend server and frontend js context allowing for fast pre-rendered templates *and* stateful browser interfaces.

Mirror automatically compiles and includes your cljs code into the 
resulting html page

Every path like `/about` maps to a matching file like `pages/about.cljc` 

HTML is expressed as hiccup style vectors, providing a JSX like experience without introducing new syntax or compile steps

The `:watch` options hot reloads your code as you edit files (a la figwheel) 



### Install CLI

to build the cli command, clone the repo and build the jar:

```
git clone git@github.com/bhurlow/mirror
cd mirror && lein uberjar
ln -s $PWD/bin/mirror ~/bin
```

### Quick Start

```shell
# start fresh
mkdir my-new-project
cd my-new-project 

# make the 'pages' and 'static' directory 
mkdir pages
mkdir static
```

now edit your first file `pages/index.cljc`:

```clj
(ns pages.index)

(defn foo []
  #?(:cljs "hello browser")
  #?(:clj "hello backend"))
  
(defn render []
  [:h1 "hello wrld"]
  	[:h2 (foo)])
```

`render` will be called on the backend, and additionally on the frontend if state changes are requried. `index.cljc` is automatically compiled for the frontend and included into the html page. 

If using the CLI:

```
# will default to dirs name 'pages' and 'static'
mirror 

# but you can specify your own
mirror src public

# all cli flags are passed in as keywords, e.g.
mirror :watch
```

### Supported CLI Flags

- `:watch` 


### In-Project Usage

first download using leiningen
 
in your project.clj deps:

```clj
[mirror "0.1.1"]
```
and in your project, use the middleware:

```clj
(require '[mirror.middleware :refer [wrap-pages])

(defn handler [h]
  (fn [req]
    (response "hello wolrd")))
    
(def app (-> handler (wrap-pages "pages"))

(http/start-server app {:port 3000}))))
```

the `wrap-pages` middleware will look for and render pages in the specified pages directory.

### Rationale

- cljs should be easier to use out of the box 
- no more confusing build steps, gulp etc (have a tool wrap this)
- no backend required if you just want to play with cljs
- no *frontend* required if use simple html rendering

