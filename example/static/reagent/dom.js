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
var _STAR_always_update_STAR_5855 = reagent.impl.util._STAR_always_update_STAR_;
reagent.impl.util._STAR_always_update_STAR_ = true;

try{return (reagent.dom.module.call(null)["render"])(comp.call(null),container,((function (_STAR_always_update_STAR_5855){
return (function (){
var _STAR_always_update_STAR_5857 = reagent.impl.util._STAR_always_update_STAR_;
reagent.impl.util._STAR_always_update_STAR_ = false;

try{cljs.core.swap_BANG_.call(null,reagent.dom.roots,cljs.core.assoc,container,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [comp,container], null));

reagent.impl.batching.flush_after_render.call(null);

if(cljs.core.some_QMARK_.call(null,callback)){
return callback.call(null);
} else {
return null;
}
}finally {reagent.impl.util._STAR_always_update_STAR_ = _STAR_always_update_STAR_5857;
}});})(_STAR_always_update_STAR_5855))
);
}finally {reagent.impl.util._STAR_always_update_STAR_ = _STAR_always_update_STAR_5855;
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
var args5858 = [];
var len__9417__auto___5861 = arguments.length;
var i__9418__auto___5862 = (0);
while(true){
if((i__9418__auto___5862 < len__9417__auto___5861)){
args5858.push((arguments[i__9418__auto___5862]));

var G__5863 = (i__9418__auto___5862 + (1));
i__9418__auto___5862 = G__5863;
continue;
} else {
}
break;
}

var G__5860 = args5858.length;
switch (G__5860) {
case 2:
return reagent.dom.render.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
case 3:
return reagent.dom.render.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args5858.length)].join('')));

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

var seq__5869_5873 = cljs.core.seq.call(null,cljs.core.vals.call(null,cljs.core.deref.call(null,reagent.dom.roots)));
var chunk__5870_5874 = null;
var count__5871_5875 = (0);
var i__5872_5876 = (0);
while(true){
if((i__5872_5876 < count__5871_5875)){
var v_5877 = cljs.core._nth.call(null,chunk__5870_5874,i__5872_5876);
cljs.core.apply.call(null,reagent.dom.re_render_component,v_5877);

var G__5878 = seq__5869_5873;
var G__5879 = chunk__5870_5874;
var G__5880 = count__5871_5875;
var G__5881 = (i__5872_5876 + (1));
seq__5869_5873 = G__5878;
chunk__5870_5874 = G__5879;
count__5871_5875 = G__5880;
i__5872_5876 = G__5881;
continue;
} else {
var temp__4657__auto___5882 = cljs.core.seq.call(null,seq__5869_5873);
if(temp__4657__auto___5882){
var seq__5869_5883__$1 = temp__4657__auto___5882;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__5869_5883__$1)){
var c__9263__auto___5884 = cljs.core.chunk_first.call(null,seq__5869_5883__$1);
var G__5885 = cljs.core.chunk_rest.call(null,seq__5869_5883__$1);
var G__5886 = c__9263__auto___5884;
var G__5887 = cljs.core.count.call(null,c__9263__auto___5884);
var G__5888 = (0);
seq__5869_5873 = G__5885;
chunk__5870_5874 = G__5886;
count__5871_5875 = G__5887;
i__5872_5876 = G__5888;
continue;
} else {
var v_5889 = cljs.core.first.call(null,seq__5869_5883__$1);
cljs.core.apply.call(null,reagent.dom.re_render_component,v_5889);

var G__5890 = cljs.core.next.call(null,seq__5869_5883__$1);
var G__5891 = null;
var G__5892 = (0);
var G__5893 = (0);
seq__5869_5873 = G__5890;
chunk__5870_5874 = G__5891;
count__5871_5875 = G__5892;
i__5872_5876 = G__5893;
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