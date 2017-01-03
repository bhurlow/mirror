// Compiled by ClojureScript 1.9.293 {}
goog.provide('pages.index');
goog.require('cljs.core');
goog.require('pages.util');
goog.require('mirror.tools');
goog.require('reagent.core');
alert("SUP");
mirror.tools.inject.call(null);
pages.index.state = cljs.core.atom.call(null,cljs.core.PersistentArrayMap.EMPTY);
pages.index.handle_click = (function pages$index$handle_click(e){
return null;
});
pages.index.render = (function pages$index$render(){
return new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"h1","h1",-1896887462),"HELLO WORLD!"], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"button","button",1456579943),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"on-click","on-click",1632826543),pages.index.handle_click], null),"click me"], null),pages.util.foo.call(null)], null);
});

//# sourceMappingURL=index.js.map