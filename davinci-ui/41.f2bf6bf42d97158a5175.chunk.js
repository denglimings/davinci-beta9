(window.webpackJsonp=window.webpackJsonp||[]).push([[41],{"1d6c4d2a7bdc74efd1d7":function(e,t,a){"use strict";a.r(t);var n=a("8af190b70a6bc55c6f1b"),r=a.n(n),c=a("e95a63b25fb92ed15721"),i=a("811a4999285c4f950f0a"),d=a("fe25efaa8d6f45e3f5f9"),o=a("499d577663719e9512ef"),u=a("6104aceff0328eacbce8"),E=a("ca37a65e274e0b8f5aaa"),l=r.a.createElement(c.Switch,null,r.a.createElement(c.Route,{exact:!0,path:"/project/:projectId/widget/:widgetId?",component:E.c}),r.a.createElement(c.Route,{exact:!0,path:"/project/:projectId/widgets",component:E.b}));t.default=function(){return Object(i.b)({key:"widget",reducer:o.a}),Object(d.b)({key:"widget",saga:u.a}),l}},"499d577663719e9512ef":function(e,t,a){"use strict";a.d(t,"b",function(){return d});var n=a("7edf83707012a871cdfb"),r=a("be3ae9b489ceb948e586"),c=a("6b414ce5e780d1c58f66"),i=a("88a09aae288902a34b11"),d={widgets:[],currentWidget:null,loading:!1,dataLoading:!1,columnValueLoading:!1,distinctColumnValues:null};t.a=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:d,t=arguments.length>1?arguments[1]:void 0;return Object(n.a)(e,function(e){switch(t.type){case r.a.LOAD_WIDGETS:e.loading=!0,e.widgets=null;break;case r.a.LOAD_WIDGETS_SUCCESS:e.loading=!1,e.widgets=t.payload.widgets;break;case r.a.LOAD_WIDGETS_FAILURE:e.loading=!1;break;case r.a.ADD_WIDGET:e.loading=!0;break;case r.a.ADD_WIDGET_SUCCESS:e.widgets?(e.widgets.push(t.payload.result),e.loading=!1):(e.loading=!1,e.widgets=[t.payload.result]);break;case r.a.ADD_WIDGET_FAILURE:e.loading=!1;break;case r.a.DELETE_WIDGET:e.loading=!0;break;case r.a.DELETE_WIDGET_SUCCESS:e.widgets=e.widgets.filter(function(e){return e.id!==t.payload.id}),e.loading=!1;break;case r.a.DELETE_WIDGET_FAILURE:e.loading=!1;break;case r.a.LOAD_WIDGET_DETAIL:e.loading=!0,e.currentWidget=null;break;case r.a.LOAD_WIDGET_DETAIL_SUCCESS:e.loading=!1,e.currentWidget=t.payload.detail;break;case r.a.LOAD_WIDGET_DETAIL_FAILURE:e.loading=!1;break;case r.a.EDIT_WIDGET:e.loading=!0;break;case r.a.EDIT_WIDGET_SUCCESS:case r.a.EDIT_WIDGET_FAILURE:e.loading=!1;break;case r.a.COPY_WIDGET:e.loading=!0;break;case r.a.COPY_WIDGET_SUCCESS:var a=t.payload.fromWidgetId;e.widgets.splice(e.widgets.findIndex(function(e){return e.id===a})+1,0,t.payload.result),e.loading=!1;break;case r.a.COPY_WIDGET_FAILURE:e.loading=!1;case i.a.LOAD_VIEW_DATA:e.dataLoading=!0;break;case i.a.LOAD_VIEW_DATA_SUCCESS:case i.a.LOAD_VIEW_DATA_FAILURE:e.dataLoading=!1;break;case c.s:e.widgets=t.payload.widgets;break;case i.a.LOAD_VIEW_DISTINCT_VALUE:e.columnValueLoading=!0,e.distinctColumnValues=null;break;case i.a.LOAD_VIEW_DISTINCT_VALUE_SUCCESS:e.columnValueLoading=!1,e.distinctColumnValues=t.payload.data.slice(0,100);break;case i.a.LOAD_VIEW_DISTINCT_VALUE_FAILURE:e.columnValueLoading=!1;break;case r.a.CLEAR_CURRENT_WIDGET:e.currentWidget=null}})}},"6104aceff0328eacbce8":function(e,t,a){"use strict";a.d(t,"a",function(){return A});var n=a("d782b72bc5b680c7122c"),r=a("be3ae9b489ceb948e586"),c=a("4633b5891e83a9df58b7"),i=a.n(c),d=a("6ba0a35486f5e23ac5ae"),o=a("95066b9b78a83cfbe91a"),u=a("9adba983ceae6f089ff0"),E=a("55c69f0ea731e712b8f3"),l=regeneratorRuntime.mark(T),D=regeneratorRuntime.mark(g),s=regeneratorRuntime.mark(W),p=regeneratorRuntime.mark(L),_=regeneratorRuntime.mark(y),f=regeneratorRuntime.mark(O),b=regeneratorRuntime.mark(S),I=regeneratorRuntime.mark(A);function T(e){var t,a;return regeneratorRuntime.wrap(function(c){for(;;)switch(c.prev=c.next){case 0:if(e.type===r.a.LOAD_WIDGETS){c.next=2;break}return c.abrupt("return");case 2:return t=e.payload.projectId,c.prev=3,c.next=6,Object(n.call)(o.a,"".concat(u.a.widget,"?projectId=").concat(t));case 6:return a=c.sent,c.next=9,Object(n.put)(d.a.widgetsLoaded(a.payload));case 9:c.next=16;break;case 11:return c.prev=11,c.t0=c.catch(3),c.next=15,Object(n.put)(d.a.widgetsLoadedFail());case 15:Object(E.a)(c.t0);case 16:case"end":return c.stop()}},l,null,[[3,11]])}function g(e){var t,a,c,i;return regeneratorRuntime.wrap(function(l){for(;;)switch(l.prev=l.next){case 0:if(e.type===r.a.ADD_WIDGET){l.next=2;break}return l.abrupt("return");case 2:return t=e.payload,a=t.widget,c=t.resolve,l.prev=3,l.next=6,Object(n.call)(o.a,{method:"post",url:u.a.widget,data:a});case 6:return i=l.sent,l.next=9,Object(n.put)(d.a.widgetAdded(i.payload));case 9:c(),l.next=17;break;case 12:return l.prev=12,l.t0=l.catch(3),l.next=16,Object(n.put)(d.a.addWidgetFail());case 16:Object(E.a)(l.t0);case 17:case"end":return l.stop()}},D,null,[[3,12]])}function W(e){var t;return regeneratorRuntime.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:if(e.type===r.a.DELETE_WIDGET){a.next=2;break}return a.abrupt("return");case 2:return t=e.payload.id,a.prev=3,a.next=6,Object(n.call)(o.a,{method:"delete",url:"".concat(u.a.widget,"/").concat(t)});case 6:return a.next=8,Object(n.put)(d.a.widgetDeleted(t));case 8:a.next=15;break;case 10:return a.prev=10,a.t0=a.catch(3),a.next=14,Object(n.put)(d.a.deleteWidgetFail());case 14:Object(E.a)(a.t0);case 15:case"end":return a.stop()}},s,null,[[3,10]])}function L(e){var t,a,c,i;return regeneratorRuntime.wrap(function(l){for(;;)switch(l.prev=l.next){case 0:if(e.type===r.a.LOAD_WIDGET_DETAIL){l.next=2;break}return l.abrupt("return");case 2:return t=e.payload.id,l.prev=3,l.next=6,Object(n.call)(o.a,"".concat(u.a.widget,"/").concat(t));case 6:return a=l.sent,c=a.payload.viewId,l.next=10,Object(n.call)(o.a,"".concat(u.a.view,"/").concat(c));case 10:return i=l.sent,l.next=13,Object(n.put)(d.a.widgetDetailLoaded(a.payload,i.payload));case 13:l.next=20;break;case 15:return l.prev=15,l.t0=l.catch(3),l.next=19,Object(n.put)(d.a.loadWidgetDetailFail(l.t0));case 19:Object(E.a)(l.t0);case 20:case"end":return l.stop()}},p,null,[[3,15]])}function y(e){var t,a,c;return regeneratorRuntime.wrap(function(i){for(;;)switch(i.prev=i.next){case 0:if(e.type===r.a.EDIT_WIDGET){i.next=2;break}return i.abrupt("return");case 2:return t=e.payload,a=t.widget,c=t.resolve,i.prev=3,i.next=6,Object(n.call)(o.a,{method:"put",url:"".concat(u.a.widget,"/").concat(a.id),data:a});case 6:return i.next=8,Object(n.put)(d.a.widgetEdited());case 8:c(),i.next=16;break;case 11:return i.prev=11,i.t0=i.catch(3),i.next=15,Object(n.put)(d.a.editWidgetFail());case 15:Object(E.a)(i.t0);case 16:case"end":return i.stop()}},_,null,[[3,11]])}function O(e){var t,a,c,l;return regeneratorRuntime.wrap(function(D){for(;;)switch(D.prev=D.next){case 0:if(e.type===r.a.COPY_WIDGET){D.next=2;break}return D.abrupt("return");case 2:return t=e.payload,a=t.widget,c=t.resolve,D.prev=3,D.next=6,Object(n.call)(o.a,{method:"post",url:u.a.widget,data:i()(a,"id")});case 6:return l=D.sent,D.next=9,Object(n.put)(d.a.widgetCopied(a.id,l.payload));case 9:c(),D.next=17;break;case 12:return D.prev=12,D.t0=D.catch(3),D.next=16,Object(n.put)(d.a.copyWidgetFail());case 16:Object(E.a)(D.t0);case 17:case"end":return D.stop()}},f,null,[[3,12]])}function S(e){var t;return regeneratorRuntime.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:if(e.type===r.a.EXECUTE_COMPUTED_SQL){a.next=2;break}return a.abrupt("return");case 2:return t=e.payload.sql,a.prev=3,a.next=6,Object(n.call)(o.a,{method:"post",data:t});case 6:a.sent,a.next=12;break;case 9:a.prev=9,a.t0=a.catch(3),Object(E.a)(a.t0);case 12:case"end":return a.stop()}},b,null,[[3,9]])}function A(){return regeneratorRuntime.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(n.all)([Object(n.takeLatest)(r.a.LOAD_WIDGETS,T),Object(n.takeEvery)(r.a.ADD_WIDGET,g),Object(n.takeEvery)(r.a.DELETE_WIDGET,W),Object(n.takeLatest)(r.a.LOAD_WIDGET_DETAIL,L),Object(n.takeEvery)(r.a.EDIT_WIDGET,y),Object(n.takeEvery)(r.a.COPY_WIDGET,O),Object(n.takeEvery)(r.a.EXECUTE_COMPUTED_SQL,S)]);case 2:case"end":return e.stop()}},I)}},"6ba0a35486f5e23ac5ae":function(e,t,a){"use strict";a.d(t,"a",function(){return c});var n=a("be3ae9b489ceb948e586"),r=a("fdb445ec61a8d515506e"),c={loadWidgets:function(e){return{type:n.a.LOAD_WIDGETS,payload:{projectId:e}}},widgetsLoaded:function(e){return{type:n.a.LOAD_WIDGETS_SUCCESS,payload:{widgets:e}}},widgetsLoadedFail:function(){return{type:n.a.LOAD_WIDGETS_FAILURE,payload:{}}},addWidget:function(e,t){return{type:n.a.ADD_WIDGET,payload:{widget:e,resolve:t}}},widgetAdded:function(e){return{type:n.a.ADD_WIDGET_SUCCESS,payload:{result:e}}},addWidgetFail:function(){return{type:n.a.ADD_WIDGET_FAILURE,payload:{}}},loadWidgetDetail:function(e){return{type:n.a.LOAD_WIDGET_DETAIL,payload:{id:e}}},widgetDetailLoaded:function(e,t){return{type:n.a.LOAD_WIDGET_DETAIL_SUCCESS,payload:{detail:e,view:t}}},loadWidgetDetailFail:function(e){return{type:n.a.LOAD_WIDGET_DETAIL_FAILURE,payload:{error:e}}},editWidget:function(e,t){return{type:n.a.EDIT_WIDGET,payload:{widget:e,resolve:t}}},widgetEdited:function(){return{type:n.a.EDIT_WIDGET_SUCCESS,payload:{}}},editWidgetFail:function(){return{type:n.a.EDIT_WIDGET_FAILURE,payload:{}}},copyWidget:function(e,t){return{type:n.a.COPY_WIDGET,payload:{widget:e,resolve:t}}},widgetCopied:function(e,t){return{type:n.a.COPY_WIDGET_SUCCESS,payload:{fromWidgetId:e,result:t}}},copyWidgetFail:function(){return{type:n.a.COPY_WIDGET_FAILURE,payload:{}}},deleteWidget:function(e){return{type:n.a.DELETE_WIDGET,payload:{id:e}}},widgetDeleted:function(e){return{type:n.a.DELETE_WIDGET_SUCCESS,payload:{id:e}}},deleteWidgetFail:function(){return{type:n.a.DELETE_WIDGET_FAILURE,payload:{}}},clearCurrentWidget:function(){return{type:n.a.CLEAR_CURRENT_WIDGET,payload:{}}},executeComputed:function(e){return{type:n.a.EXECUTE_COMPUTED_SQL,payload:{sql:e}}}};Object(r.b)(c)},"811a4999285c4f950f0a":function(e,t,a){"use strict";var n=a("8af190b70a6bc55c6f1b"),r=a.n(n),c=a("5ef9de3df8d92ea0e41c"),i=a.n(c),d=a("d7dd51e1bf6bfc2c9c3d"),o=a("5fa3f8487e09c6182715"),u=a.n(o),E=a("f3b0ff1628e56352bcbf"),l=a.n(E),D=a("a1cf5d6fa4ed65a6ddd5"),s=a.n(D),p=a("6a4f9c383785f9168266"),_=a.n(p),f=a("4838bba2c29015c6b85a"),b=a("864d653faa82e264b5f9");function I(e,t){return function(a,n){t||Object(f.a)(e),_()(u()(a)&&!s()(a)&&l()(n),"(app/utils...) injectReducer: Expected `reducer` to be a reducer function"),Reflect.has(e.injectedReducers,a)&&e.injectedReducers[a]===n||(e.injectedReducers[a]=n,e.replaceReducer(Object(b.a)(e.injectedReducers)))}}function T(e){return Object(f.a)(e),{injectReducer:I(e,!0)}}function g(e){return(g="function"===typeof Symbol&&"symbol"===typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"===typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function W(e,t){for(var a=0;a<t.length;a++){var n=t[a];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function L(e,t){return!t||"object"!==g(t)&&"function"!==typeof t?function(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e}(e):t}function y(e){return(y=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)})(e)}function O(e,t){return(O=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e})(e,t)}function S(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}a.d(t,"b",function(){return A});t.a=function(e){var t=e.key,a=e.reducer;return function(e){var n=function(n){function c(e,n){var r;return function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,c),r=L(this,y(c).call(this,e,n)),T(n.store).injectReducer(t,a),r}var i,d,o;return function(e,t){if("function"!==typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&O(e,t)}(c,r.a.Component),i=c,(d=[{key:"render",value:function(){return r.a.createElement(e,this.props)}}])&&W(i.prototype,d),o&&W(i,o),c}();return S(n,"WrappedComponent",e),S(n,"contextType",d.ReactReduxContext),S(n,"displayName","withReducer(".concat(e.displayName||e.name||"Component",")")),i()(n,e)}};var A=function(e){var t=e.key,a=e.reducer,n=r.a.useContext(d.ReactReduxContext);r.a.useEffect(function(){T(n.store).injectReducer(t,a)},[])}},be3ae9b489ceb948e586:function(e,t,a){"use strict";a.d(t,"a",function(){return c});var n,r=a("fdb445ec61a8d515506e");!function(e){e.LOAD_WIDGETS="davinci/Widget/LOAD_WIDGETS",e.LOAD_WIDGETS_SUCCESS="davinci/Widget/LOAD_WIDGETS_SUCCESS",e.LOAD_WIDGETS_FAILURE="davinci/Widget/LOAD_WIDGETS_FAILURE",e.ADD_WIDGET="davinci/Widget/ADD_WIDGET",e.ADD_WIDGET_SUCCESS="davinci/Widget/ADD_WIDGET_SUCCESS",e.ADD_WIDGET_FAILURE="davinci/Widget/ADD_WIDGET_FAILURE",e.LOAD_WIDGET_DETAIL="davinci/Widget/LOAD_WIDGET_DETAIL",e.LOAD_WIDGET_DETAIL_SUCCESS="davinci/Widget/LOAD_WIDGET_DETAIL_SUCCESS",e.LOAD_WIDGET_DETAIL_FAILURE="davinci/Widget/LOAD_WIDGET_DETAIL_FAILURE",e.EDIT_WIDGET="davinci/Widget/EDIT_WIDGET",e.EDIT_WIDGET_SUCCESS="davinci/Widget/EDIT_WIDGET_SUCCESS",e.EDIT_WIDGET_FAILURE="davinci/Widget/EDIT_WIDGET_FAILURE",e.COPY_WIDGET="davinci/Widget/COPY_WIDGET",e.COPY_WIDGET_SUCCESS="davinci/Widget/COPY_WIDGET_SUCCESS",e.COPY_WIDGET_FAILURE="davinci/Widget/COPY_WIDGET_FAILURE",e.DELETE_WIDGET="davinci/Widget/DELETE_WIDGET",e.DELETE_WIDGET_SUCCESS="davinci/Widget/DELETE_WIDGET_SUCCESS",e.DELETE_WIDGET_FAILURE="davinci/Widget/DELETE_WIDGET_FAILURE",e.CLEAR_CURRENT_WIDGET="davinci/Widget/CLEAR_CURRENT_WIDGET",e.EXECUTE_COMPUTED_SQL="davinci/Widget/EXECUTE_COMPUTED_SQL"}(n||(n={}));var c=Object(r.a)(n)}}]);