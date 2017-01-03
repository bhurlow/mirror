// Compiled by ClojureScript 1.9.293 {}
goog.provide('reagent.dom');
goog.require('cljs.core');
goog.require('reagent.impl.util');
goog.require('reagent.interop');
goog.require('reagent.ratom');
goog.require('reagent.impl.template');
goog.require('reagent.impl.batching');
goog.require('cljsjs.react.dom');
goog.require('reagent.debug');
if(typeof reagent.dom.imported !== 'undefined'){
} else {
reagent.dom.imported = null;
}
reagent.dom.module = (function reagent$dom$module(){
if(cljs.core.some_QMARK_.call(null,reagent.dom.imported)){
return reagent.dom.imported;
} else {
if(typeof ReactDOM !== 'undefined'){
return reagent.dom.imported = ReactDOM;
} else {
if(typeof require !== 'undefined'){
var or__8877__auto__ = reagent.dom.imported = require("react-dom");
if(cljs.core.truth_(or__8877__auto__)){
return or__8877__auto__;
} else {
throw (new Error("require('react-dom') failed"));
}
} else {
throw (new Error("js/ReactDOM is missing"));

}
}
}
});
if(typeof reagent.dom.roots !== 'undefined'){
} else {
reagent.dom.roots = cljs.core.atom.call(null,cljs.core.PersistentArrayMap.EMPTY);
}
reagent.dom.unmount_comp = (function reagent$dom$unmount_comp(container){
cljs.core.swap_BANG_.call(null,reagent.dom.roots,cljs.core.dissoc,container);

return (reagent.dom.module.call(null)["unmountComponentAtNode"])(container);
});
reagent.dom.render_comp = (function reagent$dom$render_comp(comp,container,callback){
var _STAR_always_update_STAR_7399 = reagent.impl.util._STAR_always_update_STAR_;
reagent.impl.util._STAR_always_update_STAR_ = true;

try{return (reagent.dom.module.call(null)["render"])(comp.call(null),container,((function (_STAR_always_update_STAR_7399){
return (function (){
var _STAR_always_update_STAR_7400 = reagent.impl.util._STAR_always_update_STAR_;
reagent.impl.util._STAR_always_update_STAR_ = false;

try{cljs.core.swap_BANG_.call(null,reagent.dom.roots,cljs.core.assoc,container,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [comp,container], null));

reagent.impl.batching.flush_after_render.call(null);

if(cljs.core.some_QMARK_.call(null,callback)){
return callback.call(null);
} else {
return null;
}
}finally {reagent.impl.util._STAR_always_update_STAR_ = _STAR_always_update_STAR_7400;
}});})(_STAR_always_update_STAR_7399))
);
}finally {reagent.impl.util._STAR_always_update_STAR_ = _STAR_always_update_STAR_7399;
}});
reagent.dom.re_render_component = (function reagent$dom$re_render_component(comp,container){
return reagent.dom.render_comp.call(null,comp,container,null);
});
/**
 * Render a Reagent component into the DOM. The first argument may be
 *   either a vector (using Reagent's Hiccup syntax), or a React element. The second argument should be a DOM node.
 * 
 *   Optionally takes a callback that is called when the component is in place.
 * 
 *   Returns the mounted component instance.
 */
reagent.dom.render = (function reagent$dom$render(var_args){
var args7401 = [];
var len__9417__auto___7404 = arguments.length;
var i__9418__auto___7405 = (0);
while(true){
if((i__9418__auto___7405 < len__9417__auto___7404)){
args7401.push((arguments[i__9418__auto___7405]));

var G__7406 = (i__9418__auto___7405 + (1));
i__9418__auto___7405 = G__7406;
continue;
} else {
}
break;
}

var G__7403 = args7401.length;
switch (G__7403) {
case 2:
return reagent.dom.render.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
case 3:
return reagent.dom.render.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args7401.length)].join('')));

}
});

reagent.dom.render.cljs$core$IFn$_invoke$arity$2 = (function (comp,container){
return reagent.dom.render.call(null,comp,container,null);
});

reagent.dom.render.cljs$core$IFn$_invoke$arity$3 = (function (comp,container,callback){
reagent.ratom.flush_BANG_.call(null);

var f = (function (){
return reagent.impl.template.as_element.call(null,((cljs.core.fn_QMARK_.call(null,comp))?comp.call(null):comp));
});
return reagent.dom.render_comp.call(null,f,container,callback);
});

reagent.dom.render.cljs$lang$maxFixedArity = 3;

reagent.dom.unmount_component_at_node = (function reagent$dom$unmount_component_at_node(container){
return reagent.dom.unmount_comp.call(null,container);
});
/**
 * Returns the root DOM node of a mounted component.
 */
reagent.dom.dom_node = (function reagent$dom$dom_node(this$){
return (reagent.dom.module.call(null)["findDOMNode"])(this$);
});
/**
 * Force re-rendering of all mounted Reagent components. This is
 *   probably only useful in a development environment, when you want to
 *   update components in response to some dynamic changes to code.
 * 
 *   Note that force-update-all may not update root components. This
 *   happens if a component 'foo' is mounted with `(render [foo])` (since
 *   functions are passed by value, and not by reference, in
 *   ClojureScript). To get around this you'll have to introduce a layer
 *   of indirection, for example by using `(render [#'foo])` instead.
 */
reagent.dom.force_update_all = (function reagent$dom$force_update_all(){
reagent.ratom.flush_BANG_.call(null);

var seq__7412_7416 = cljs.core.seq.call(null,cljs.core.vals.call(null,cljs.core.deref.call(null,reagent.dom.roots)));
var chunk__7413_7417 = null;
var count__7414_7418 = (0);
var i__7415_7419 = (0);
while(true){
if((i__7415_7419 < count__7414_7418)){
var v_7420 = cljs.core._nth.call(null,chunk__7413_7417,i__7415_7419);
cljs.core.apply.call(null,reagent.dom.re_render_component,v_7420);

var G__7421 = seq__7412_7416;
var G__7422 = chunk__7413_7417;
var G__7423 = count__7414_7418;
var G__7424 = (i__7415_7419 + (1));
seq__7412_7416 = G__7421;
chunk__7413_7417 = G__7422;
count__7414_7418 = G__7423;
i__7415_7419 = G__7424;
continue;
} else {
var temp__4657__auto___7425 = cljs.core.seq.call(null,seq__7412_7416);
if(temp__4657__auto___7425){
var seq__7412_7426__$1 = temp__4657__auto___7425;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__7412_7426__$1)){
var c__9263__auto___7427 = cljs.core.chunk_first.call(null,seq__7412_7426__$1);
var G__7428 = cljs.core.chunk_rest.call(null,seq__7412_7426__$1);
var G__7429 = c__9263__auto___7427;
var G__7430 = cljs.core.count.call(null,c__9263__auto___7427);
var G__7431 = (0);
seq__7412_7416 = G__7428;
chunk__7413_7417 = G__7429;
count__7414_7418 = G__7430;
i__7415_7419 = G__7431;
continue;
} else {
var v_7432 = cljs.core.first.call(null,seq__7412_7426__$1);
cljs.core.apply.call(null,reagent.dom.re_render_component,v_7432);

var G__7433 = cljs.core.next.call(null,seq__7412_7426__$1);
var G__7434 = null;
var G__7435 = (0);
var G__7436 = (0);
seq__7412_7416 = G__7433;
chunk__7413_7417 = G__7434;
count__7414_7418 = G__7435;
i__7415_7419 = G__7436;
continue;
}
} else {
}
}
break;
}

return "Updated";
});

//# sourceMappingURL=dom.js.map