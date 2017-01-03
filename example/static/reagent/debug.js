// Compiled by ClojureScript 1.9.293 {}
goog.provide('reagent.debug');
goog.require('cljs.core');
reagent.debug.has_console = typeof console !== 'undefined';
reagent.debug.tracking = false;
if(typeof reagent.debug.warnings !== 'undefined'){
} else {
reagent.debug.warnings = cljs.core.atom.call(null,null);
}
if(typeof reagent.debug.track_console !== 'undefined'){
} else {
reagent.debug.track_console = (function (){var o = ({});
o.warn = ((function (o){
return (function() { 
var G__6837__delegate = function (args){
return cljs.core.swap_BANG_.call(null,reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"warn","warn",-436710552)], null),cljs.core.conj,cljs.core.apply.call(null,cljs.core.str,args));
};
var G__6837 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__6839__i = 0, G__6839__a = new Array(arguments.length -  0);
while (G__6839__i < G__6839__a.length) {G__6839__a[G__6839__i] = arguments[G__6839__i + 0]; ++G__6839__i;}
  args = new cljs.core.IndexedSeq(G__6839__a,0);
} 
return G__6837__delegate.call(this,args);};
G__6837.cljs$lang$maxFixedArity = 0;
G__6837.cljs$lang$applyTo = (function (arglist__6840){
var args = cljs.core.seq(arglist__6840);
return G__6837__delegate(args);
});
G__6837.cljs$core$IFn$_invoke$arity$variadic = G__6837__delegate;
return G__6837;
})()
;})(o))
;

o.error = ((function (o){
return (function() { 
var G__6841__delegate = function (args){
return cljs.core.swap_BANG_.call(null,reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"error","error",-978969032)], null),cljs.core.conj,cljs.core.apply.call(null,cljs.core.str,args));
};
var G__6841 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__6842__i = 0, G__6842__a = new Array(arguments.length -  0);
while (G__6842__i < G__6842__a.length) {G__6842__a[G__6842__i] = arguments[G__6842__i + 0]; ++G__6842__i;}
  args = new cljs.core.IndexedSeq(G__6842__a,0);
} 
return G__6841__delegate.call(this,args);};
G__6841.cljs$lang$maxFixedArity = 0;
G__6841.cljs$lang$applyTo = (function (arglist__6843){
var args = cljs.core.seq(arglist__6843);
return G__6841__delegate(args);
});
G__6841.cljs$core$IFn$_invoke$arity$variadic = G__6841__delegate;
return G__6841;
})()
;})(o))
;

return o;
})();
}
reagent.debug.track_warnings = (function reagent$debug$track_warnings(f){
reagent.debug.tracking = true;

cljs.core.reset_BANG_.call(null,reagent.debug.warnings,null);

f.call(null);

var warns = cljs.core.deref.call(null,reagent.debug.warnings);
cljs.core.reset_BANG_.call(null,reagent.debug.warnings,null);

reagent.debug.tracking = false;

return warns;
});

//# sourceMappingURL=debug.js.map