/*
 * Selecter Plugin [Formstone Library]
 * @author Ben Plum
 * @version 2.1.2
 *
 * Copyright © 2013 Ben Plum <mr@benplum.com>
 * Released under the MIT License <http://www.opensource.org/licenses/mit-license.php>
 */
 
if(jQuery)(function(h){function u(a){a=h.extend({},v,a||{});for(var b=h(this),e=0,d=b.length;e<d;e++){var g=b.eq(e),f=a;if(!g.data("selecter")){f.externalLinks&&(f.links=!0);h.extend(f,g.data("selecter-options"));var j=g.find("option, optgroup"),m=j.filter("option"),k=m.filter(":selected"),s=f.defaultLabel?-1:m.index(k),t=j.length-1,r=f.links?"nav":"div",w=f.links?"a":"span";f.multiple=g.prop("multiple");f.disabled=g.is(":disabled");var c="<"+r+' class="selecter '+f.customClass;l?c+=" mobile":f.cover&&(c+= " cover");c=f.multiple?c+" multiple":c+" closed";f.disabled&&(c+=" disabled");c+='">';f.multiple||(c+='<span class="selecter-selected">',c+=x(f.trimOptions,!1!=f.defaultLabel?f.defaultLabel:k.text()),c+="</span>");for(var c=c+'<div class="selecter-options">',k=0,n=null,p=0,u=j.length;p<u;p++)n=h(j[p]),"OPTGROUP"==n[0].tagName?c+='<span class="selecter-group">'+n.attr("label")+"</span>":(c+="<"+w+' class="selecter-item',n.is(":selected")&&!f.defaultLabel&&(c+=" selected"),0==p&&(c+=" first"),p==t&& (c+=" last"),c+='" ',c=f.links?c+('href="'+n.val()+'"'):c+('data-value="'+n.val()+'"'),c+=">"+x(f.trimOptions,n.text())+"</"+w+">",k++);c+="</div>";c+="</"+r+">";g.addClass("selecter-element").after(c);j=g.next(".selecter");f=h.extend({$selectEl:g,$optionEls:m,$selecter:j,$selected:j.find(".selecter-selected"),$itemsWrapper:j.find(".selecter-options"),$items:j.find(".selecter-item"),index:s,guid:y},f);void 0!=h.fn.scroller&&f.$itemsWrapper.scroller();j.on("click.selecter",".selecter-selected",f,z).on("click.selecter", ".selecter-item",f,A).on("selecter-close",f,q).data("selecter",f);if(!f.links&&!l||l){if(g.on("change",f,B).on("blur.selecter",f,C),!l)g.on("focus.selecter",f,D)}else g.hide();y++}}return b}function z(a){a.preventDefault();a.stopPropagation();var b=a.data;if(!b.$selectEl.is(":disabled"))if(h(".selecter").not(b.$selecter).trigger("selecter-close",[b]),l)a=b.$selectEl[0],document.createEvent?(b=document.createEvent("MouseEvents"),b.initMouseEvent("mousedown",!0,!0,window,0,0,0,0,0,!1,!1,!1,!1,0,null), a.dispatchEvent(b)):element.fireEvent&&a.fireEvent("onmousedown");else if(b.$selecter.hasClass("closed")){if(a.preventDefault(),a.stopPropagation(),a=a.data,!a.$selecter.hasClass("open")){var b=a.$selecter.offset(),e=h("body").outerHeight(),d=a.$itemsWrapper.outerHeight(!0);b.top+d>e&&l?a.$selecter.addClass("bottom"):a.$selecter.removeClass("bottom");a.$itemsWrapper.show();a.$selecter.removeClass("closed").addClass("open");h("body").on("click.selecter-"+a.guid,":not(.selecter-options)",a,E).on("keydown.selecter-"+ a.guid,a,s);void 0!=h.fn.scroller&&a.$itemsWrapper.scroller("reset")}}else b.$selecter.hasClass("open")&&q(a)}function q(a){a.preventDefault();a.stopPropagation();a=a.data;a.$selecter.hasClass("open")&&(a.$itemsWrapper.hide(),a.$selecter.removeClass("open").addClass("closed"),h("body").off(".selecter-"+a.guid))}function E(a){a.preventDefault();a.stopPropagation();0==h(a.currentTarget).parents(".selecter").length&&q(a)}function A(a){a.preventDefault();a.stopPropagation();var b=h(this),e=a.data;e.$selectEl.is(":disabled")|| (e.links?l?m(b.val(),e.externalLinks):m(b.attr("href"),e.externalLinks):e.$itemsWrapper.is(":visible")&&(b=e.$items.index(b),k(b,e,!1)),e.multiple||q(a))}function B(a,b){if(!b){var e=h(this),d=a.data;d.links?l?m(e.val(),d.externalLinks):m(e.attr("href"),d.externalLinks):(e=d.$optionEls.index(d.$optionEls.filter("[value="+e.val()+"]")),k(e,d,!1))}}function D(a){a.preventDefault();a.stopPropagation();a=a.data;!a.$selectEl.is(":disabled")&&!a.multiple&&(a.$selecter.addClass("focus"),h(".selecter").not(a.$selecter).trigger("selecter-close", [a]),h("body").on("keydown.selecter-"+a.guid,a,s))}function C(a){a.preventDefault();a.stopPropagation();a=a.data;a.$selecter.removeClass("focus");h(".selecter").not(a.$selecter).trigger("selecter-close",[a]);h("body").off(".selecter-"+a.guid)}function s(a){var b=a.data;if(b.$selecter.hasClass("open")&&13==a.keyCode)k(b.index,b,!1),q(a);else if(9!=a.keyCode&&!a.metaKey&&!a.altKey&&!a.ctrlKey&&!a.shiftKey){a.preventDefault();a.stopPropagation();var e=b.$items.length-1,d=-1;if(-1<h.inArray(a.keyCode, t?[38,40,37,39]:[38,40]))d=b.index+(38==a.keyCode||t&&37==a.keyCode?-1:1),0>d&&(d=0),d>e&&(d=e);else{a=String.fromCharCode(a.keyCode).toUpperCase();for(i=b.index+1;i<=e;i++){var g=b.$optionEls.eq(i).text().charAt(0).toUpperCase();if(g==a){d=i;break}}if(0>d)for(i=0;i<=e;i++)if(g=b.$optionEls.eq(i).text().charAt(0).toUpperCase(),g==a){d=i;break}}0<=d&&k(d,b,!b.$selecter.hasClass("open"))}}function k(a,b,e){var d=b.$items.eq(a),g=d.hasClass("selected");if(g)b.multiple&&(b.$optionEls.eq(a).prop("selected", null),d.removeClass("selected"));else{var f=d.html();d.data("value");b.multiple?b.$optionEls.eq(a).prop("selected",!0):(b.$selected.html(f),b.$items.filter(".selected").removeClass("selected"),e||(b.$selectEl[0].selectedIndex=a));b.$selectEl.trigger("change",[!0]);d.addClass("selected")}if(!g||b.multiple)b.callback.call(b.$selecter,b.$selectEl.val(),a),b.index=a}function x(a,b){return!1===a?b:b.length>a?b.substring(0,a)+"...":b}function m(a,b){b?window.open(a):window.location.href=a}var t=-1<navigator.userAgent.toLowerCase().indexOf("firefox"), l=/Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent||navigator.vendor||window.opera),v={callback:function(){},cover:!1,customClass:"",defaultLabel:!1,externalLinks:!1,links:!1,trimOptions:!1},y=0,r={defaults:function(a){v=h.extend(v,a||{});return h(this)},disable:function(){for(var a=h(this),b=0,e=a.length;b<e;b++){var d=a.eq(b),g=d.next(".selecter");g.hasClass("open")&&g.find(".selecter-selected").trigger("click");d.prop("disabled",!0);g.addClass("disabled")}return a},enable:function(){for(var a= h(this),b=0,e=a.length;b<e;b++){var d=a.eq(b),g=d.next(".selecter");d.prop("disabled",null);g.removeClass("disabled")}return a},destroy:function(){for(var a=h(this),b=0,e=a.length;b<e;b++){var d=a.eq(b),g=d.next(".selecter");g.hasClass("open")&&g.find(".selecter-selected").trigger("click");void 0!=h.fn.scroller&&g.find(".selecter-options").scroller("destroy");d.off(".selecter").removeClass("selecter-element").show();g.off(".selecter").remove()}return a}};h.fn.selecter=function(a){return r[a]?r[a].apply(this, Array.prototype.slice.call(arguments,1)):"object"===typeof a||!a?u.apply(this,arguments):this}})(jQuery);