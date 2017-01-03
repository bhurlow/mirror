// Compiled by ClojureScript 1.9.293 {}
goog.provide('mirror.tools');
goog.require('cljs.core');
goog.require('mirror.util');
goog.require('reagent.core');
goog.require('cljs.reader');
/**
 * returns an instrumented atom used to represent
 *   state
 */
mirror.tools.state_atom = (function mirror$tools$state_atom(data){
return reagent.core.atom.call(null,data);
});
mirror.tools.setup = (function mirror$tools$setup(){
cljs.core.println.call(null,"setting up frontend");

var props = cljs.reader.read_string.call(null,__MIRROR_DATA__);
cljs.core.println.call(null,"props",props);

pages.index.reset_state.call(null,props);

cljs.core.println.call(null,"state is right now",cljs.core.deref.call(null,pages.index.state));

return reagent.core.render_component.call(null,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [pages.index.render], null),document.getElementById("__mount"));
});
mirror.tools.inject = (function mirror$tools$inject(){
return mirror.tools.setup.call(null);
});

//# sourceMappingURL=tools.js.map