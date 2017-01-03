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
var G__5237__delegate = function (args){
return cljs.core.swap_BANG_.call(null,reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"warn","warn",-436710552)], null),cljs.core.conj,cljs.core.apply.call(null,cljs.core.str,args));
};
var G__5237 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__5241__i = 0, G__5241__a = new Array(arguments.length -  0);
while (G__5241__i < G__5241__a.length) {G__5241__a[G__5241__i] = arguments[G__5241__i + 0]; ++G__5241__i;}
  args = new cljs.core.IndexedSeq(G__5241__a,0);
} 
return G__5237__delegate.call(this,args);};
G__5237.cljs$lang$maxFixedArity = 0;
G__5237.cljs$lang$applyTo = (function (arglist__5244){
var args = cljs.core.seq(arglist__5244);
return G__5237__delegate(args);
});
G__5237.cljs$core$IFn$_invoke$arity$variadic = G__5237__delegate;
return G__5237;
})()
;})(o))
;

o.error = ((function (o){
return (function() { 
var G__5248__delegate = function (args){
return cljs.core.swap_BANG_.call(null,reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"error","error",-978969032)], null),cljs.core.conj,cljs.core.apply.call(null,cljs.core.str,args));
};
var G__5248 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__5254__i = 0, G__5254__a = new Array(arguments.length -  0);
while (G__5254__i < G__5254__a.length) {G__5254__a[G__5254__i] = arguments[G__5254__i + 0]; ++G__5254__i;}
  args = new cljs.core.IndexedSeq(G__5254__a,0);
} 
return G__5248__delegate.call(this,args);};
G__5248.cljs$lang$maxFixedArity = 0;
G__5248.cljs$lang$applyTo = (function (arglist__5258){
var args = cljs.core.seq(arglist__5258);
return G__5248__delegate(args);
});
G__5248.cljs$core$IFn$_invoke$arity$variadic = G__5248__delegate;
return G__5248;
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