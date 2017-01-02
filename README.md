# mirror

A mimimal framework for writing isomorphic clj/cljs web applications. Mirror shares rendering code between the backend server and frontend js context allowing for fast pre-rendered templates *and* stateful browser interfaces.

### Usage

first download using leiningen
 
in your project.clj deps:

```
[mirror "0.0.1"]
```

### CLI usage

to build the cli command, clone the repo and build the jar:

```
git clone git@github.com/bhurlow/mirror
cd mirror && lein uberjar
ln -s $PWD/bin/mirror ~/bin
```

### Rationale

- cljs should be easier to use out of the box 
- no more confusing build steps, gulp etc (have a tool wrap this)
- no backend required if you just want to play with cljs
- no *frontend* required if use simple html rendering

