# mirror

A mimimal framework for writing isomorphic clj/cljs web applications. Mirror shares rendering code between the backend server and frontend js context allowing for fast pre-rendered templates *and* stateful browser interfaces.

### Usage

first download using leiningen
 
in your project.clj deps:

```
[mirror "0.0.1"]
```

### CLI usage

```
git clone bhurlow/mirror
ln -s $PWD/bin/mirror ~/bin
```

### Rationale

- getting start with cljs is actually pretty hard
- no more confusing build steps, gulp etc (have a tool wrap this)

