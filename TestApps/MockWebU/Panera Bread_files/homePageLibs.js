/*
 * Copyright 1997-2010 Day Management AG
 * Barfuesserplatz 6, 4001 Basel, Switzerland
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Day Management AG, ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Day.
 */

// map $CQ to Granite jQuery
window.$CQ = _g.$;

/*! jQuery UI - v1.10.3 - 2013-07-01
* http://jqueryui.com
* Includes: jquery.ui.core.js, jquery.ui.datepicker.js
* Copyright 2013 jQuery Foundation and other contributors Licensed MIT */

(function(e,t){function i(t,i){var a,n,r,o=t.nodeName.toLowerCase();return"area"===o?(a=t.parentNode,n=a.name,t.href&&n&&"map"===a.nodeName.toLowerCase()?(r=e("img[usemap=#"+n+"]")[0],!!r&&s(r)):!1):(/input|select|textarea|button|object/.test(o)?!t.disabled:"a"===o?t.href||i:i)&&s(t)}function s(t){return e.expr.filters.visible(t)&&!e(t).parents().addBack().filter(function(){return"hidden"===e.css(this,"visibility")}).length}var a=0,n=/^ui-id-\d+$/;e.ui=e.ui||{},e.extend(e.ui,{version:"1.10.3",keyCode:{BACKSPACE:8,COMMA:188,DELETE:46,DOWN:40,END:35,ENTER:13,ESCAPE:27,HOME:36,LEFT:37,NUMPAD_ADD:107,NUMPAD_DECIMAL:110,NUMPAD_DIVIDE:111,NUMPAD_ENTER:108,NUMPAD_MULTIPLY:106,NUMPAD_SUBTRACT:109,PAGE_DOWN:34,PAGE_UP:33,PERIOD:190,RIGHT:39,SPACE:32,TAB:9,UP:38}}),e.fn.extend({focus:function(t){return function(i,s){return"number"==typeof i?this.each(function(){var t=this;setTimeout(function(){e(t).focus(),s&&s.call(t)},i)}):t.apply(this,arguments)}}(e.fn.focus),scrollParent:function(){var t;return t=e.ui.ie&&/(static|relative)/.test(this.css("position"))||/absolute/.test(this.css("position"))?this.parents().filter(function(){return/(relative|absolute|fixed)/.test(e.css(this,"position"))&&/(auto|scroll)/.test(e.css(this,"overflow")+e.css(this,"overflow-y")+e.css(this,"overflow-x"))}).eq(0):this.parents().filter(function(){return/(auto|scroll)/.test(e.css(this,"overflow")+e.css(this,"overflow-y")+e.css(this,"overflow-x"))}).eq(0),/fixed/.test(this.css("position"))||!t.length?e(document):t},zIndex:function(i){if(i!==t)return this.css("zIndex",i);if(this.length)for(var s,a,n=e(this[0]);n.length&&n[0]!==document;){if(s=n.css("position"),("absolute"===s||"relative"===s||"fixed"===s)&&(a=parseInt(n.css("zIndex"),10),!isNaN(a)&&0!==a))return a;n=n.parent()}return 0},uniqueId:function(){return this.each(function(){this.id||(this.id="ui-id-"+ ++a)})},removeUniqueId:function(){return this.each(function(){n.test(this.id)&&e(this).removeAttr("id")})}}),e.extend(e.expr[":"],{data:e.expr.createPseudo?e.expr.createPseudo(function(t){return function(i){return!!e.data(i,t)}}):function(t,i,s){return!!e.data(t,s[3])},focusable:function(t){return i(t,!isNaN(e.attr(t,"tabindex")))},tabbable:function(t){var s=e.attr(t,"tabindex"),a=isNaN(s);return(a||s>=0)&&i(t,!a)}}),e("<a>").outerWidth(1).jquery||e.each(["Width","Height"],function(i,s){function a(t,i,s,a){return e.each(n,function(){i-=parseFloat(e.css(t,"padding"+this))||0,s&&(i-=parseFloat(e.css(t,"border"+this+"Width"))||0),a&&(i-=parseFloat(e.css(t,"margin"+this))||0)}),i}var n="Width"===s?["Left","Right"]:["Top","Bottom"],r=s.toLowerCase(),o={innerWidth:e.fn.innerWidth,innerHeight:e.fn.innerHeight,outerWidth:e.fn.outerWidth,outerHeight:e.fn.outerHeight};e.fn["inner"+s]=function(i){return i===t?o["inner"+s].call(this):this.each(function(){e(this).css(r,a(this,i)+"px")})},e.fn["outer"+s]=function(t,i){return"number"!=typeof t?o["outer"+s].call(this,t):this.each(function(){e(this).css(r,a(this,t,!0,i)+"px")})}}),e.fn.addBack||(e.fn.addBack=function(e){return this.add(null==e?this.prevObject:this.prevObject.filter(e))}),e("<a>").data("a-b","a").removeData("a-b").data("a-b")&&(e.fn.removeData=function(t){return function(i){return arguments.length?t.call(this,e.camelCase(i)):t.call(this)}}(e.fn.removeData)),e.ui.ie=!!/msie [\w.]+/.exec(navigator.userAgent.toLowerCase()),e.support.selectstart="onselectstart"in document.createElement("div"),e.fn.extend({disableSelection:function(){return this.bind((e.support.selectstart?"selectstart":"mousedown")+".ui-disableSelection",function(e){e.preventDefault()})},enableSelection:function(){return this.unbind(".ui-disableSelection")}}),e.extend(e.ui,{plugin:{add:function(t,i,s){var a,n=e.ui[t].prototype;for(a in s)n.plugins[a]=n.plugins[a]||[],n.plugins[a].push([i,s[a]])},call:function(e,t,i){var s,a=e.plugins[t];if(a&&e.element[0].parentNode&&11!==e.element[0].parentNode.nodeType)for(s=0;a.length>s;s++)e.options[a[s][0]]&&a[s][1].apply(e.element,i)}},hasScroll:function(t,i){if("hidden"===e(t).css("overflow"))return!1;var s=i&&"left"===i?"scrollLeft":"scrollTop",a=!1;return t[s]>0?!0:(t[s]=1,a=t[s]>0,t[s]=0,a)}})})(jQuery);(function(t,e){function i(){this._curInst=null,this._keyEvent=!1,this._disabledInputs=[],this._datepickerShowing=!1,this._inDialog=!1,this._mainDivId="ui-datepicker-div",this._inlineClass="ui-datepicker-inline",this._appendClass="ui-datepicker-append",this._triggerClass="ui-datepicker-trigger",this._dialogClass="ui-datepicker-dialog",this._disableClass="ui-datepicker-disabled",this._unselectableClass="ui-datepicker-unselectable",this._currentClass="ui-datepicker-current-day",this._dayOverClass="ui-datepicker-days-cell-over",this.regional=[],this.regional[""]={closeText:"Done",prevText:"Prev",nextText:"Next",currentText:"Today",monthNames:["January","February","March","April","May","June","July","August","September","October","November","December"],monthNamesShort:["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],dayNames:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],dayNamesShort:["Sun","Mon","Tue","Wed","Thu","Fri","Sat"],dayNamesMin:["Su","Mo","Tu","We","Th","Fr","Sa"],weekHeader:"Wk",dateFormat:"mm/dd/yy",firstDay:0,isRTL:!1,showMonthAfterYear:!1,yearSuffix:""},this._defaults={showOn:"focus",showAnim:"fadeIn",showOptions:{},defaultDate:null,appendText:"",buttonText:"...",buttonImage:"",buttonImageOnly:!1,hideIfNoPrevNext:!1,navigationAsDateFormat:!1,gotoCurrent:!1,changeMonth:!1,changeYear:!1,yearRange:"c-10:c+10",showOtherMonths:!1,selectOtherMonths:!1,showWeek:!1,calculateWeek:this.iso8601Week,shortYearCutoff:"+10",minDate:null,maxDate:null,duration:"fast",beforeShowDay:null,beforeShow:null,onSelect:null,onChangeMonthYear:null,onClose:null,numberOfMonths:1,showCurrentAtPos:0,stepMonths:1,stepBigMonths:12,altField:"",altFormat:"",constrainInput:!0,showButtonPanel:!1,autoSize:!1,disabled:!1},t.extend(this._defaults,this.regional[""]),this.dpDiv=s(t("<div id='"+this._mainDivId+"' class='ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all'></div>"))}function s(e){var i="button, .ui-datepicker-prev, .ui-datepicker-next, .ui-datepicker-calendar td a";return e.delegate(i,"mouseout",function(){t(this).removeClass("ui-state-hover"),-1!==this.className.indexOf("ui-datepicker-prev")&&t(this).removeClass("ui-datepicker-prev-hover"),-1!==this.className.indexOf("ui-datepicker-next")&&t(this).removeClass("ui-datepicker-next-hover")}).delegate(i,"mouseover",function(){t.datepicker._isDisabledDatepicker(a.inline?e.parent()[0]:a.input[0])||(t(this).parents(".ui-datepicker-calendar").find("a").removeClass("ui-state-hover"),t(this).addClass("ui-state-hover"),-1!==this.className.indexOf("ui-datepicker-prev")&&t(this).addClass("ui-datepicker-prev-hover"),-1!==this.className.indexOf("ui-datepicker-next")&&t(this).addClass("ui-datepicker-next-hover"))})}function n(e,i){t.extend(e,i);for(var s in i)null==i[s]&&(e[s]=i[s]);return e}t.extend(t.ui,{datepicker:{version:"1.10.3"}});var a,r="datepicker";t.extend(i.prototype,{markerClassName:"hasDatepicker",maxRows:4,_widgetDatepicker:function(){return this.dpDiv},setDefaults:function(t){return n(this._defaults,t||{}),this},_attachDatepicker:function(e,i){var s,n,a;s=e.nodeName.toLowerCase(),n="div"===s||"span"===s,e.id||(this.uuid+=1,e.id="dp"+this.uuid),a=this._newInst(t(e),n),a.settings=t.extend({},i||{}),"input"===s?this._connectDatepicker(e,a):n&&this._inlineDatepicker(e,a)},_newInst:function(e,i){var n=e[0].id.replace(/([^A-Za-z0-9_\-])/g,"\\\\$1");return{id:n,input:e,selectedDay:0,selectedMonth:0,selectedYear:0,drawMonth:0,drawYear:0,inline:i,dpDiv:i?s(t("<div class='"+this._inlineClass+" ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all'></div>")):this.dpDiv}},_connectDatepicker:function(e,i){var s=t(e);i.append=t([]),i.trigger=t([]),s.hasClass(this.markerClassName)||(this._attachments(s,i),s.addClass(this.markerClassName).keydown(this._doKeyDown).keypress(this._doKeyPress).keyup(this._doKeyUp),this._autoSize(i),t.data(e,r,i),i.settings.disabled&&this._disableDatepicker(e))},_attachments:function(e,i){var s,n,a,r=this._get(i,"appendText"),o=this._get(i,"isRTL");i.append&&i.append.remove(),r&&(i.append=t("<span class='"+this._appendClass+"'>"+r+"</span>"),e[o?"before":"after"](i.append)),e.unbind("focus",this._showDatepicker),i.trigger&&i.trigger.remove(),s=this._get(i,"showOn"),("focus"===s||"both"===s)&&e.focus(this._showDatepicker),("button"===s||"both"===s)&&(n=this._get(i,"buttonText"),a=this._get(i,"buttonImage"),i.trigger=t(this._get(i,"buttonImageOnly")?t("<img/>").addClass(this._triggerClass).attr({src:a,alt:n,title:n}):t("<button type='button'></button>").addClass(this._triggerClass).html(a?t("<img/>").attr({src:a,alt:n,title:n}):n)),e[o?"before":"after"](i.trigger),i.trigger.click(function(){return t.datepicker._datepickerShowing&&t.datepicker._lastInput===e[0]?t.datepicker._hideDatepicker():t.datepicker._datepickerShowing&&t.datepicker._lastInput!==e[0]?(t.datepicker._hideDatepicker(),t.datepicker._showDatepicker(e[0])):t.datepicker._showDatepicker(e[0]),!1}))},_autoSize:function(t){if(this._get(t,"autoSize")&&!t.inline){var e,i,s,n,a=new Date(2009,11,20),r=this._get(t,"dateFormat");r.match(/[DM]/)&&(e=function(t){for(i=0,s=0,n=0;t.length>n;n++)t[n].length>i&&(i=t[n].length,s=n);return s},a.setMonth(e(this._get(t,r.match(/MM/)?"monthNames":"monthNamesShort"))),a.setDate(e(this._get(t,r.match(/DD/)?"dayNames":"dayNamesShort"))+20-a.getDay())),t.input.attr("size",this._formatDate(t,a).length)}},_inlineDatepicker:function(e,i){var s=t(e);s.hasClass(this.markerClassName)||(s.addClass(this.markerClassName).append(i.dpDiv),t.data(e,r,i),this._setDate(i,this._getDefaultDate(i),!0),this._updateDatepicker(i),this._updateAlternate(i),i.settings.disabled&&this._disableDatepicker(e),i.dpDiv.css("display","block"))},_dialogDatepicker:function(e,i,s,a,o){var h,l,c,u,d,p=this._dialogInst;return p||(this.uuid+=1,h="dp"+this.uuid,this._dialogInput=t("<input type='text' id='"+h+"' style='position: absolute; top: -100px; width: 0px;'/>"),this._dialogInput.keydown(this._doKeyDown),t("body").append(this._dialogInput),p=this._dialogInst=this._newInst(this._dialogInput,!1),p.settings={},t.data(this._dialogInput[0],r,p)),n(p.settings,a||{}),i=i&&i.constructor===Date?this._formatDate(p,i):i,this._dialogInput.val(i),this._pos=o?o.length?o:[o.pageX,o.pageY]:null,this._pos||(l=document.documentElement.clientWidth,c=document.documentElement.clientHeight,u=document.documentElement.scrollLeft||document.body.scrollLeft,d=document.documentElement.scrollTop||document.body.scrollTop,this._pos=[l/2-100+u,c/2-150+d]),this._dialogInput.css("left",this._pos[0]+20+"px").css("top",this._pos[1]+"px"),p.settings.onSelect=s,this._inDialog=!0,this.dpDiv.addClass(this._dialogClass),this._showDatepicker(this._dialogInput[0]),t.blockUI&&t.blockUI(this.dpDiv),t.data(this._dialogInput[0],r,p),this},_destroyDatepicker:function(e){var i,s=t(e),n=t.data(e,r);s.hasClass(this.markerClassName)&&(i=e.nodeName.toLowerCase(),t.removeData(e,r),"input"===i?(n.append.remove(),n.trigger.remove(),s.removeClass(this.markerClassName).unbind("focus",this._showDatepicker).unbind("keydown",this._doKeyDown).unbind("keypress",this._doKeyPress).unbind("keyup",this._doKeyUp)):("div"===i||"span"===i)&&s.removeClass(this.markerClassName).empty())},_enableDatepicker:function(e){var i,s,n=t(e),a=t.data(e,r);n.hasClass(this.markerClassName)&&(i=e.nodeName.toLowerCase(),"input"===i?(e.disabled=!1,a.trigger.filter("button").each(function(){this.disabled=!1}).end().filter("img").css({opacity:"1.0",cursor:""})):("div"===i||"span"===i)&&(s=n.children("."+this._inlineClass),s.children().removeClass("ui-state-disabled"),s.find("select.ui-datepicker-month, select.ui-datepicker-year").prop("disabled",!1)),this._disabledInputs=t.map(this._disabledInputs,function(t){return t===e?null:t}))},_disableDatepicker:function(e){var i,s,n=t(e),a=t.data(e,r);n.hasClass(this.markerClassName)&&(i=e.nodeName.toLowerCase(),"input"===i?(e.disabled=!0,a.trigger.filter("button").each(function(){this.disabled=!0}).end().filter("img").css({opacity:"0.5",cursor:"default"})):("div"===i||"span"===i)&&(s=n.children("."+this._inlineClass),s.children().addClass("ui-state-disabled"),s.find("select.ui-datepicker-month, select.ui-datepicker-year").prop("disabled",!0)),this._disabledInputs=t.map(this._disabledInputs,function(t){return t===e?null:t}),this._disabledInputs[this._disabledInputs.length]=e)},_isDisabledDatepicker:function(t){if(!t)return!1;for(var e=0;this._disabledInputs.length>e;e++)if(this._disabledInputs[e]===t)return!0;return!1},_getInst:function(e){try{return t.data(e,r)}catch(i){throw"Missing instance data for this datepicker"}},_optionDatepicker:function(i,s,a){var r,o,h,l,c=this._getInst(i);return 2===arguments.length&&"string"==typeof s?"defaults"===s?t.extend({},t.datepicker._defaults):c?"all"===s?t.extend({},c.settings):this._get(c,s):null:(r=s||{},"string"==typeof s&&(r={},r[s]=a),c&&(this._curInst===c&&this._hideDatepicker(),o=this._getDateDatepicker(i,!0),h=this._getMinMaxDate(c,"min"),l=this._getMinMaxDate(c,"max"),n(c.settings,r),null!==h&&r.dateFormat!==e&&r.minDate===e&&(c.settings.minDate=this._formatDate(c,h)),null!==l&&r.dateFormat!==e&&r.maxDate===e&&(c.settings.maxDate=this._formatDate(c,l)),"disabled"in r&&(r.disabled?this._disableDatepicker(i):this._enableDatepicker(i)),this._attachments(t(i),c),this._autoSize(c),this._setDate(c,o),this._updateAlternate(c),this._updateDatepicker(c)),e)},_changeDatepicker:function(t,e,i){this._optionDatepicker(t,e,i)},_refreshDatepicker:function(t){var e=this._getInst(t);e&&this._updateDatepicker(e)},_setDateDatepicker:function(t,e){var i=this._getInst(t);i&&(this._setDate(i,e),this._updateDatepicker(i),this._updateAlternate(i))},_getDateDatepicker:function(t,e){var i=this._getInst(t);return i&&!i.inline&&this._setDateFromField(i,e),i?this._getDate(i):null},_doKeyDown:function(e){var i,s,n,a=t.datepicker._getInst(e.target),r=!0,o=a.dpDiv.is(".ui-datepicker-rtl");if(a._keyEvent=!0,t.datepicker._datepickerShowing)switch(e.keyCode){case 9:t.datepicker._hideDatepicker(),r=!1;break;case 13:return n=t("td."+t.datepicker._dayOverClass+":not(."+t.datepicker._currentClass+")",a.dpDiv),n[0]&&t.datepicker._selectDay(e.target,a.selectedMonth,a.selectedYear,n[0]),i=t.datepicker._get(a,"onSelect"),i?(s=t.datepicker._formatDate(a),i.apply(a.input?a.input[0]:null,[s,a])):t.datepicker._hideDatepicker(),!1;case 27:t.datepicker._hideDatepicker();break;case 33:t.datepicker._adjustDate(e.target,e.ctrlKey?-t.datepicker._get(a,"stepBigMonths"):-t.datepicker._get(a,"stepMonths"),"M");break;case 34:t.datepicker._adjustDate(e.target,e.ctrlKey?+t.datepicker._get(a,"stepBigMonths"):+t.datepicker._get(a,"stepMonths"),"M");break;case 35:(e.ctrlKey||e.metaKey)&&t.datepicker._clearDate(e.target),r=e.ctrlKey||e.metaKey;break;case 36:(e.ctrlKey||e.metaKey)&&t.datepicker._gotoToday(e.target),r=e.ctrlKey||e.metaKey;break;case 37:(e.ctrlKey||e.metaKey)&&t.datepicker._adjustDate(e.target,o?1:-1,"D"),r=e.ctrlKey||e.metaKey,e.originalEvent.altKey&&t.datepicker._adjustDate(e.target,e.ctrlKey?-t.datepicker._get(a,"stepBigMonths"):-t.datepicker._get(a,"stepMonths"),"M");break;case 38:(e.ctrlKey||e.metaKey)&&t.datepicker._adjustDate(e.target,-7,"D"),r=e.ctrlKey||e.metaKey;break;case 39:(e.ctrlKey||e.metaKey)&&t.datepicker._adjustDate(e.target,o?-1:1,"D"),r=e.ctrlKey||e.metaKey,e.originalEvent.altKey&&t.datepicker._adjustDate(e.target,e.ctrlKey?+t.datepicker._get(a,"stepBigMonths"):+t.datepicker._get(a,"stepMonths"),"M");break;case 40:(e.ctrlKey||e.metaKey)&&t.datepicker._adjustDate(e.target,7,"D"),r=e.ctrlKey||e.metaKey;break;default:r=!1}else 36===e.keyCode&&e.ctrlKey?t.datepicker._showDatepicker(this):r=!1;r&&(e.preventDefault(),e.stopPropagation())},_doKeyPress:function(i){var s,n,a=t.datepicker._getInst(i.target);return t.datepicker._get(a,"constrainInput")?(s=t.datepicker._possibleChars(t.datepicker._get(a,"dateFormat")),n=String.fromCharCode(null==i.charCode?i.keyCode:i.charCode),i.ctrlKey||i.metaKey||" ">n||!s||s.indexOf(n)>-1):e},_doKeyUp:function(e){var i,s=t.datepicker._getInst(e.target);if(s.input.val()!==s.lastVal)try{i=t.datepicker.parseDate(t.datepicker._get(s,"dateFormat"),s.input?s.input.val():null,t.datepicker._getFormatConfig(s)),i&&(t.datepicker._setDateFromField(s),t.datepicker._updateAlternate(s),t.datepicker._updateDatepicker(s))}catch(n){}return!0},_showDatepicker:function(e){if(e=e.target||e,"input"!==e.nodeName.toLowerCase()&&(e=t("input",e.parentNode)[0]),!t.datepicker._isDisabledDatepicker(e)&&t.datepicker._lastInput!==e){var i,s,a,r,o,h,l;i=t.datepicker._getInst(e),t.datepicker._curInst&&t.datepicker._curInst!==i&&(t.datepicker._curInst.dpDiv.stop(!0,!0),i&&t.datepicker._datepickerShowing&&t.datepicker._hideDatepicker(t.datepicker._curInst.input[0])),s=t.datepicker._get(i,"beforeShow"),a=s?s.apply(e,[e,i]):{},a!==!1&&(n(i.settings,a),i.lastVal=null,t.datepicker._lastInput=e,t.datepicker._setDateFromField(i),t.datepicker._inDialog&&(e.value=""),t.datepicker._pos||(t.datepicker._pos=t.datepicker._findPos(e),t.datepicker._pos[1]+=e.offsetHeight),r=!1,t(e).parents().each(function(){return r|="fixed"===t(this).css("position"),!r}),o={left:t.datepicker._pos[0],top:t.datepicker._pos[1]},t.datepicker._pos=null,i.dpDiv.empty(),i.dpDiv.css({position:"absolute",display:"block",top:"-1000px"}),t.datepicker._updateDatepicker(i),o=t.datepicker._checkOffset(i,o,r),i.dpDiv.css({position:t.datepicker._inDialog&&t.blockUI?"static":r?"fixed":"absolute",display:"none",left:o.left+"px",top:o.top+"px"}),i.inline||(h=t.datepicker._get(i,"showAnim"),l=t.datepicker._get(i,"duration"),i.dpDiv.zIndex(t(e).zIndex()+1),t.datepicker._datepickerShowing=!0,t.effects&&t.effects.effect[h]?i.dpDiv.show(h,t.datepicker._get(i,"showOptions"),l):i.dpDiv[h||"show"](h?l:null),t.datepicker._shouldFocusInput(i)&&i.input.focus(),t.datepicker._curInst=i))}},_updateDatepicker:function(e){this.maxRows=4,a=e,e.dpDiv.empty().append(this._generateHTML(e)),this._attachHandlers(e),e.dpDiv.find("."+this._dayOverClass+" a").mouseover();var i,s=this._getNumberOfMonths(e),n=s[1],r=17;e.dpDiv.removeClass("ui-datepicker-multi-2 ui-datepicker-multi-3 ui-datepicker-multi-4").width(""),n>1&&e.dpDiv.addClass("ui-datepicker-multi-"+n).css("width",r*n+"em"),e.dpDiv[(1!==s[0]||1!==s[1]?"add":"remove")+"Class"]("ui-datepicker-multi"),e.dpDiv[(this._get(e,"isRTL")?"add":"remove")+"Class"]("ui-datepicker-rtl"),e===t.datepicker._curInst&&t.datepicker._datepickerShowing&&t.datepicker._shouldFocusInput(e)&&e.input.focus(),e.yearshtml&&(i=e.yearshtml,setTimeout(function(){i===e.yearshtml&&e.yearshtml&&e.dpDiv.find("select.ui-datepicker-year:first").replaceWith(e.yearshtml),i=e.yearshtml=null},0))},_shouldFocusInput:function(t){return t.input&&t.input.is(":visible")&&!t.input.is(":disabled")&&!t.input.is(":focus")},_checkOffset:function(e,i,s){var n=e.dpDiv.outerWidth(),a=e.dpDiv.outerHeight(),r=e.input?e.input.outerWidth():0,o=e.input?e.input.outerHeight():0,h=document.documentElement.clientWidth+(s?0:t(document).scrollLeft()),l=document.documentElement.clientHeight+(s?0:t(document).scrollTop());return i.left-=this._get(e,"isRTL")?n-r:0,i.left-=s&&i.left===e.input.offset().left?t(document).scrollLeft():0,i.top-=s&&i.top===e.input.offset().top+o?t(document).scrollTop():0,i.left-=Math.min(i.left,i.left+n>h&&h>n?Math.abs(i.left+n-h):0),i.top-=Math.min(i.top,i.top+a>l&&l>a?Math.abs(a+o):0),i},_findPos:function(e){for(var i,s=this._getInst(e),n=this._get(s,"isRTL");e&&("hidden"===e.type||1!==e.nodeType||t.expr.filters.hidden(e));)e=e[n?"previousSibling":"nextSibling"];return i=t(e).offset(),[i.left,i.top]},_hideDatepicker:function(e){var i,s,n,a,o=this._curInst;!o||e&&o!==t.data(e,r)||this._datepickerShowing&&(i=this._get(o,"showAnim"),s=this._get(o,"duration"),n=function(){t.datepicker._tidyDialog(o)},t.effects&&(t.effects.effect[i]||t.effects[i])?o.dpDiv.hide(i,t.datepicker._get(o,"showOptions"),s,n):o.dpDiv["slideDown"===i?"slideUp":"fadeIn"===i?"fadeOut":"hide"](i?s:null,n),i||n(),this._datepickerShowing=!1,a=this._get(o,"onClose"),a&&a.apply(o.input?o.input[0]:null,[o.input?o.input.val():"",o]),this._lastInput=null,this._inDialog&&(this._dialogInput.css({position:"absolute",left:"0",top:"-100px"}),t.blockUI&&(t.unblockUI(),t("body").append(this.dpDiv))),this._inDialog=!1)},_tidyDialog:function(t){t.dpDiv.removeClass(this._dialogClass).unbind(".ui-datepicker-calendar")},_checkExternalClick:function(e){if(t.datepicker._curInst){var i=t(e.target),s=t.datepicker._getInst(i[0]);(i[0].id!==t.datepicker._mainDivId&&0===i.parents("#"+t.datepicker._mainDivId).length&&!i.hasClass(t.datepicker.markerClassName)&&!i.closest("."+t.datepicker._triggerClass).length&&t.datepicker._datepickerShowing&&(!t.datepicker._inDialog||!t.blockUI)||i.hasClass(t.datepicker.markerClassName)&&t.datepicker._curInst!==s)&&t.datepicker._hideDatepicker()}},_adjustDate:function(e,i,s){var n=t(e),a=this._getInst(n[0]);this._isDisabledDatepicker(n[0])||(this._adjustInstDate(a,i+("M"===s?this._get(a,"showCurrentAtPos"):0),s),this._updateDatepicker(a))},_gotoToday:function(e){var i,s=t(e),n=this._getInst(s[0]);this._get(n,"gotoCurrent")&&n.currentDay?(n.selectedDay=n.currentDay,n.drawMonth=n.selectedMonth=n.currentMonth,n.drawYear=n.selectedYear=n.currentYear):(i=new Date,n.selectedDay=i.getDate(),n.drawMonth=n.selectedMonth=i.getMonth(),n.drawYear=n.selectedYear=i.getFullYear()),this._notifyChange(n),this._adjustDate(s)},_selectMonthYear:function(e,i,s){var n=t(e),a=this._getInst(n[0]);a["selected"+("M"===s?"Month":"Year")]=a["draw"+("M"===s?"Month":"Year")]=parseInt(i.options[i.selectedIndex].value,10),this._notifyChange(a),this._adjustDate(n)},_selectDay:function(e,i,s,n){var a,r=t(e);t(n).hasClass(this._unselectableClass)||this._isDisabledDatepicker(r[0])||(a=this._getInst(r[0]),a.selectedDay=a.currentDay=t("a",n).html(),a.selectedMonth=a.currentMonth=i,a.selectedYear=a.currentYear=s,this._selectDate(e,this._formatDate(a,a.currentDay,a.currentMonth,a.currentYear)))},_clearDate:function(e){var i=t(e);this._selectDate(i,"")},_selectDate:function(e,i){var s,n=t(e),a=this._getInst(n[0]);i=null!=i?i:this._formatDate(a),a.input&&a.input.val(i),this._updateAlternate(a),s=this._get(a,"onSelect"),s?s.apply(a.input?a.input[0]:null,[i,a]):a.input&&a.input.trigger("change"),a.inline?this._updateDatepicker(a):(this._hideDatepicker(),this._lastInput=a.input[0],"object"!=typeof a.input[0]&&a.input.focus(),this._lastInput=null)},_updateAlternate:function(e){var i,s,n,a=this._get(e,"altField");a&&(i=this._get(e,"altFormat")||this._get(e,"dateFormat"),s=this._getDate(e),n=this.formatDate(i,s,this._getFormatConfig(e)),t(a).each(function(){t(this).val(n)}))},noWeekends:function(t){var e=t.getDay();return[e>0&&6>e,""]},iso8601Week:function(t){var e,i=new Date(t.getTime());return i.setDate(i.getDate()+4-(i.getDay()||7)),e=i.getTime(),i.setMonth(0),i.setDate(1),Math.floor(Math.round((e-i)/864e5)/7)+1},parseDate:function(i,s,n){if(null==i||null==s)throw"Invalid arguments";if(s="object"==typeof s?""+s:s+"",""===s)return null;var a,r,o,h,l=0,c=(n?n.shortYearCutoff:null)||this._defaults.shortYearCutoff,u="string"!=typeof c?c:(new Date).getFullYear()%100+parseInt(c,10),d=(n?n.dayNamesShort:null)||this._defaults.dayNamesShort,p=(n?n.dayNames:null)||this._defaults.dayNames,f=(n?n.monthNamesShort:null)||this._defaults.monthNamesShort,m=(n?n.monthNames:null)||this._defaults.monthNames,g=-1,v=-1,_=-1,b=-1,y=!1,x=function(t){var e=i.length>a+1&&i.charAt(a+1)===t;return e&&a++,e},k=function(t){var e=x(t),i="@"===t?14:"!"===t?20:"y"===t&&e?4:"o"===t?3:2,n=RegExp("^\\d{1,"+i+"}"),a=s.substring(l).match(n);if(!a)throw"Missing number at position "+l;return l+=a[0].length,parseInt(a[0],10)},w=function(i,n,a){var r=-1,o=t.map(x(i)?a:n,function(t,e){return[[e,t]]}).sort(function(t,e){return-(t[1].length-e[1].length)});if(t.each(o,function(t,i){var n=i[1];return s.substr(l,n.length).toLowerCase()===n.toLowerCase()?(r=i[0],l+=n.length,!1):e}),-1!==r)return r+1;throw"Unknown name at position "+l},D=function(){if(s.charAt(l)!==i.charAt(a))throw"Unexpected literal at position "+l;l++};for(a=0;i.length>a;a++)if(y)"'"!==i.charAt(a)||x("'")?D():y=!1;else switch(i.charAt(a)){case"d":_=k("d");break;case"D":w("D",d,p);break;case"o":b=k("o");break;case"m":v=k("m");break;case"M":v=w("M",f,m);break;case"y":g=k("y");break;case"@":h=new Date(k("@")),g=h.getFullYear(),v=h.getMonth()+1,_=h.getDate();break;case"!":h=new Date((k("!")-this._ticksTo1970)/1e4),g=h.getFullYear(),v=h.getMonth()+1,_=h.getDate();break;case"'":x("'")?D():y=!0;break;default:D()}if(s.length>l&&(o=s.substr(l),!/^\s+/.test(o)))throw"Extra/unparsed characters found in date: "+o;if(-1===g?g=(new Date).getFullYear():100>g&&(g+=(new Date).getFullYear()-(new Date).getFullYear()%100+(u>=g?0:-100)),b>-1)for(v=1,_=b;;){if(r=this._getDaysInMonth(g,v-1),r>=_)break;v++,_-=r}if(h=this._daylightSavingAdjust(new Date(g,v-1,_)),h.getFullYear()!==g||h.getMonth()+1!==v||h.getDate()!==_)throw"Invalid date";return h},ATOM:"yy-mm-dd",COOKIE:"D, dd M yy",ISO_8601:"yy-mm-dd",RFC_822:"D, d M y",RFC_850:"DD, dd-M-y",RFC_1036:"D, d M y",RFC_1123:"D, d M yy",RFC_2822:"D, d M yy",RSS:"D, d M y",TICKS:"!",TIMESTAMP:"@",W3C:"yy-mm-dd",_ticksTo1970:1e7*60*60*24*(718685+Math.floor(492.5)-Math.floor(19.7)+Math.floor(4.925)),formatDate:function(t,e,i){if(!e)return"";var s,n=(i?i.dayNamesShort:null)||this._defaults.dayNamesShort,a=(i?i.dayNames:null)||this._defaults.dayNames,r=(i?i.monthNamesShort:null)||this._defaults.monthNamesShort,o=(i?i.monthNames:null)||this._defaults.monthNames,h=function(e){var i=t.length>s+1&&t.charAt(s+1)===e;return i&&s++,i},l=function(t,e,i){var s=""+e;if(h(t))for(;i>s.length;)s="0"+s;return s},c=function(t,e,i,s){return h(t)?s[e]:i[e]},u="",d=!1;if(e)for(s=0;t.length>s;s++)if(d)"'"!==t.charAt(s)||h("'")?u+=t.charAt(s):d=!1;else switch(t.charAt(s)){case"d":u+=l("d",e.getDate(),2);break;case"D":u+=c("D",e.getDay(),n,a);break;case"o":u+=l("o",Math.round((new Date(e.getFullYear(),e.getMonth(),e.getDate()).getTime()-new Date(e.getFullYear(),0,0).getTime())/864e5),3);break;case"m":u+=l("m",e.getMonth()+1,2);break;case"M":u+=c("M",e.getMonth(),r,o);break;case"y":u+=h("y")?e.getFullYear():(10>e.getYear()%100?"0":"")+e.getYear()%100;break;case"@":u+=e.getTime();break;case"!":u+=1e4*e.getTime()+this._ticksTo1970;break;case"'":h("'")?u+="'":d=!0;break;default:u+=t.charAt(s)}return u},_possibleChars:function(t){var e,i="",s=!1,n=function(i){var s=t.length>e+1&&t.charAt(e+1)===i;return s&&e++,s};for(e=0;t.length>e;e++)if(s)"'"!==t.charAt(e)||n("'")?i+=t.charAt(e):s=!1;else switch(t.charAt(e)){case"d":case"m":case"y":case"@":i+="0123456789";break;case"D":case"M":return null;case"'":n("'")?i+="'":s=!0;break;default:i+=t.charAt(e)}return i},_get:function(t,i){return t.settings[i]!==e?t.settings[i]:this._defaults[i]},_setDateFromField:function(t,e){if(t.input.val()!==t.lastVal){var i=this._get(t,"dateFormat"),s=t.lastVal=t.input?t.input.val():null,n=this._getDefaultDate(t),a=n,r=this._getFormatConfig(t);try{a=this.parseDate(i,s,r)||n}catch(o){s=e?"":s}t.selectedDay=a.getDate(),t.drawMonth=t.selectedMonth=a.getMonth(),t.drawYear=t.selectedYear=a.getFullYear(),t.currentDay=s?a.getDate():0,t.currentMonth=s?a.getMonth():0,t.currentYear=s?a.getFullYear():0,this._adjustInstDate(t)}},_getDefaultDate:function(t){return this._restrictMinMax(t,this._determineDate(t,this._get(t,"defaultDate"),new Date))},_determineDate:function(e,i,s){var n=function(t){var e=new Date;return e.setDate(e.getDate()+t),e},a=function(i){try{return t.datepicker.parseDate(t.datepicker._get(e,"dateFormat"),i,t.datepicker._getFormatConfig(e))}catch(s){}for(var n=(i.toLowerCase().match(/^c/)?t.datepicker._getDate(e):null)||new Date,a=n.getFullYear(),r=n.getMonth(),o=n.getDate(),h=/([+\-]?[0-9]+)\s*(d|D|w|W|m|M|y|Y)?/g,l=h.exec(i);l;){switch(l[2]||"d"){case"d":case"D":o+=parseInt(l[1],10);break;case"w":case"W":o+=7*parseInt(l[1],10);break;case"m":case"M":r+=parseInt(l[1],10),o=Math.min(o,t.datepicker._getDaysInMonth(a,r));break;case"y":case"Y":a+=parseInt(l[1],10),o=Math.min(o,t.datepicker._getDaysInMonth(a,r))}l=h.exec(i)}return new Date(a,r,o)},r=null==i||""===i?s:"string"==typeof i?a(i):"number"==typeof i?isNaN(i)?s:n(i):new Date(i.getTime());return r=r&&"Invalid Date"==""+r?s:r,r&&(r.setHours(0),r.setMinutes(0),r.setSeconds(0),r.setMilliseconds(0)),this._daylightSavingAdjust(r)},_daylightSavingAdjust:function(t){return t?(t.setHours(t.getHours()>12?t.getHours()+2:0),t):null},_setDate:function(t,e,i){var s=!e,n=t.selectedMonth,a=t.selectedYear,r=this._restrictMinMax(t,this._determineDate(t,e,new Date));t.selectedDay=t.currentDay=r.getDate(),t.drawMonth=t.selectedMonth=t.currentMonth=r.getMonth(),t.drawYear=t.selectedYear=t.currentYear=r.getFullYear(),n===t.selectedMonth&&a===t.selectedYear||i||this._notifyChange(t),this._adjustInstDate(t),t.input&&t.input.val(s?"":this._formatDate(t))},_getDate:function(t){var e=!t.currentYear||t.input&&""===t.input.val()?null:this._daylightSavingAdjust(new Date(t.currentYear,t.currentMonth,t.currentDay));return e},_attachHandlers:function(e){var i=this._get(e,"stepMonths"),s="#"+e.id.replace(/\\\\/g,"\\");e.dpDiv.find("[data-handler]").map(function(){var e={prev:function(){t.datepicker._adjustDate(s,-i,"M")},next:function(){t.datepicker._adjustDate(s,+i,"M")},hide:function(){t.datepicker._hideDatepicker()},today:function(){t.datepicker._gotoToday(s)},selectDay:function(){return t.datepicker._selectDay(s,+this.getAttribute("data-month"),+this.getAttribute("data-year"),this),!1},selectMonth:function(){return t.datepicker._selectMonthYear(s,this,"M"),!1},selectYear:function(){return t.datepicker._selectMonthYear(s,this,"Y"),!1}};t(this).bind(this.getAttribute("data-event"),e[this.getAttribute("data-handler")])})},_generateHTML:function(t){var e,i,s,n,a,r,o,h,l,c,u,d,p,f,m,g,v,_,b,y,x,k,w,D,T,C,M,S,N,I,P,A,z,H,E,F,O,W,j,R=new Date,L=this._daylightSavingAdjust(new Date(R.getFullYear(),R.getMonth(),R.getDate())),Y=this._get(t,"isRTL"),B=this._get(t,"showButtonPanel"),J=this._get(t,"hideIfNoPrevNext"),K=this._get(t,"navigationAsDateFormat"),Q=this._getNumberOfMonths(t),V=this._get(t,"showCurrentAtPos"),U=this._get(t,"stepMonths"),q=1!==Q[0]||1!==Q[1],X=this._daylightSavingAdjust(t.currentDay?new Date(t.currentYear,t.currentMonth,t.currentDay):new Date(9999,9,9)),G=this._getMinMaxDate(t,"min"),$=this._getMinMaxDate(t,"max"),Z=t.drawMonth-V,te=t.drawYear;if(0>Z&&(Z+=12,te--),$)for(e=this._daylightSavingAdjust(new Date($.getFullYear(),$.getMonth()-Q[0]*Q[1]+1,$.getDate())),e=G&&G>e?G:e;this._daylightSavingAdjust(new Date(te,Z,1))>e;)Z--,0>Z&&(Z=11,te--);for(t.drawMonth=Z,t.drawYear=te,i=this._get(t,"prevText"),i=K?this.formatDate(i,this._daylightSavingAdjust(new Date(te,Z-U,1)),this._getFormatConfig(t)):i,s=this._canAdjustMonth(t,-1,te,Z)?"<a class='ui-datepicker-prev ui-corner-all' data-handler='prev' data-event='click' title='"+i+"'><span class='ui-icon ui-icon-circle-triangle-"+(Y?"e":"w")+"'>"+i+"</span></a>":J?"":"<a class='ui-datepicker-prev ui-corner-all ui-state-disabled' title='"+i+"'><span class='ui-icon ui-icon-circle-triangle-"+(Y?"e":"w")+"'>"+i+"</span></a>",n=this._get(t,"nextText"),n=K?this.formatDate(n,this._daylightSavingAdjust(new Date(te,Z+U,1)),this._getFormatConfig(t)):n,a=this._canAdjustMonth(t,1,te,Z)?"<a class='ui-datepicker-next ui-corner-all' data-handler='next' data-event='click' title='"+n+"'><span class='ui-icon ui-icon-circle-triangle-"+(Y?"w":"e")+"'>"+n+"</span></a>":J?"":"<a class='ui-datepicker-next ui-corner-all ui-state-disabled' title='"+n+"'><span class='ui-icon ui-icon-circle-triangle-"+(Y?"w":"e")+"'>"+n+"</span></a>",r=this._get(t,"currentText"),o=this._get(t,"gotoCurrent")&&t.currentDay?X:L,r=K?this.formatDate(r,o,this._getFormatConfig(t)):r,h=t.inline?"":"<button type='button' class='ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all' data-handler='hide' data-event='click'>"+this._get(t,"closeText")+"</button>",l=B?"<div class='ui-datepicker-buttonpane ui-widget-content'>"+(Y?h:"")+(this._isInRange(t,o)?"<button type='button' class='ui-datepicker-current ui-state-default ui-priority-secondary ui-corner-all' data-handler='today' data-event='click'>"+r+"</button>":"")+(Y?"":h)+"</div>":"",c=parseInt(this._get(t,"firstDay"),10),c=isNaN(c)?0:c,u=this._get(t,"showWeek"),d=this._get(t,"dayNames"),p=this._get(t,"dayNamesMin"),f=this._get(t,"monthNames"),m=this._get(t,"monthNamesShort"),g=this._get(t,"beforeShowDay"),v=this._get(t,"showOtherMonths"),_=this._get(t,"selectOtherMonths"),b=this._getDefaultDate(t),y="",k=0;Q[0]>k;k++){for(w="",this.maxRows=4,D=0;Q[1]>D;D++){if(T=this._daylightSavingAdjust(new Date(te,Z,t.selectedDay)),C=" ui-corner-all",M="",q){if(M+="<div class='ui-datepicker-group",Q[1]>1)switch(D){case 0:M+=" ui-datepicker-group-first",C=" ui-corner-"+(Y?"right":"left");break;case Q[1]-1:M+=" ui-datepicker-group-last",C=" ui-corner-"+(Y?"left":"right");break;default:M+=" ui-datepicker-group-middle",C=""}M+="'>"}for(M+="<div class='ui-datepicker-header ui-widget-header ui-helper-clearfix"+C+"'>"+(/all|left/.test(C)&&0===k?Y?a:s:"")+(/all|right/.test(C)&&0===k?Y?s:a:"")+this._generateMonthYearHeader(t,Z,te,G,$,k>0||D>0,f,m)+"</div><table class='ui-datepicker-calendar'><thead>"+"<tr>",S=u?"<th class='ui-datepicker-week-col'>"+this._get(t,"weekHeader")+"</th>":"",x=0;7>x;x++)N=(x+c)%7,S+="<th"+((x+c+6)%7>=5?" class='ui-datepicker-week-end'":"")+">"+"<span title='"+d[N]+"'>"+p[N]+"</span></th>";for(M+=S+"</tr></thead><tbody>",I=this._getDaysInMonth(te,Z),te===t.selectedYear&&Z===t.selectedMonth&&(t.selectedDay=Math.min(t.selectedDay,I)),P=(this._getFirstDayOfMonth(te,Z)-c+7)%7,A=Math.ceil((P+I)/7),z=q?this.maxRows>A?this.maxRows:A:A,this.maxRows=z,H=this._daylightSavingAdjust(new Date(te,Z,1-P)),E=0;z>E;E++){for(M+="<tr>",F=u?"<td class='ui-datepicker-week-col'>"+this._get(t,"calculateWeek")(H)+"</td>":"",x=0;7>x;x++)O=g?g.apply(t.input?t.input[0]:null,[H]):[!0,""],W=H.getMonth()!==Z,j=W&&!_||!O[0]||G&&G>H||$&&H>$,F+="<td class='"+((x+c+6)%7>=5?" ui-datepicker-week-end":"")+(W?" ui-datepicker-other-month":"")+(H.getTime()===T.getTime()&&Z===t.selectedMonth&&t._keyEvent||b.getTime()===H.getTime()&&b.getTime()===T.getTime()?" "+this._dayOverClass:"")+(j?" "+this._unselectableClass+" ui-state-disabled":"")+(W&&!v?"":" "+O[1]+(H.getTime()===X.getTime()?" "+this._currentClass:"")+(H.getTime()===L.getTime()?" ui-datepicker-today":""))+"'"+(W&&!v||!O[2]?"":" title='"+O[2].replace(/'/g,"&#39;")+"'")+(j?"":" data-handler='selectDay' data-event='click' data-month='"+H.getMonth()+"' data-year='"+H.getFullYear()+"'")+">"+(W&&!v?"&#xa0;":j?"<span class='ui-state-default'>"+H.getDate()+"</span>":"<a class='ui-state-default"+(H.getTime()===L.getTime()?" ui-state-highlight":"")+(H.getTime()===X.getTime()?" ui-state-active":"")+(W?" ui-priority-secondary":"")+"' href='#'>"+H.getDate()+"</a>")+"</td>",H.setDate(H.getDate()+1),H=this._daylightSavingAdjust(H);M+=F+"</tr>"}Z++,Z>11&&(Z=0,te++),M+="</tbody></table>"+(q?"</div>"+(Q[0]>0&&D===Q[1]-1?"<div class='ui-datepicker-row-break'></div>":""):""),w+=M}y+=w}return y+=l,t._keyEvent=!1,y},_generateMonthYearHeader:function(t,e,i,s,n,a,r,o){var h,l,c,u,d,p,f,m,g=this._get(t,"changeMonth"),v=this._get(t,"changeYear"),_=this._get(t,"showMonthAfterYear"),b="<div class='ui-datepicker-title'>",y="";if(a||!g)y+="<span class='ui-datepicker-month'>"+r[e]+"</span>";else{for(h=s&&s.getFullYear()===i,l=n&&n.getFullYear()===i,y+="<select class='ui-datepicker-month' data-handler='selectMonth' data-event='change'>",c=0;12>c;c++)(!h||c>=s.getMonth())&&(!l||n.getMonth()>=c)&&(y+="<option value='"+c+"'"+(c===e?" selected='selected'":"")+">"+o[c]+"</option>");y+="</select>"}if(_||(b+=y+(!a&&g&&v?"":"&#xa0;")),!t.yearshtml)if(t.yearshtml="",a||!v)b+="<span class='ui-datepicker-year'>"+i+"</span>";else{for(u=this._get(t,"yearRange").split(":"),d=(new Date).getFullYear(),p=function(t){var e=t.match(/c[+\-].*/)?i+parseInt(t.substring(1),10):t.match(/[+\-].*/)?d+parseInt(t,10):parseInt(t,10);
return isNaN(e)?d:e},f=p(u[0]),m=Math.max(f,p(u[1]||"")),f=s?Math.max(f,s.getFullYear()):f,m=n?Math.min(m,n.getFullYear()):m,t.yearshtml+="<select class='ui-datepicker-year' data-handler='selectYear' data-event='change'>";m>=f;f++)t.yearshtml+="<option value='"+f+"'"+(f===i?" selected='selected'":"")+">"+f+"</option>";t.yearshtml+="</select>",b+=t.yearshtml,t.yearshtml=null}return b+=this._get(t,"yearSuffix"),_&&(b+=(!a&&g&&v?"":"&#xa0;")+y),b+="</div>"},_adjustInstDate:function(t,e,i){var s=t.drawYear+("Y"===i?e:0),n=t.drawMonth+("M"===i?e:0),a=Math.min(t.selectedDay,this._getDaysInMonth(s,n))+("D"===i?e:0),r=this._restrictMinMax(t,this._daylightSavingAdjust(new Date(s,n,a)));t.selectedDay=r.getDate(),t.drawMonth=t.selectedMonth=r.getMonth(),t.drawYear=t.selectedYear=r.getFullYear(),("M"===i||"Y"===i)&&this._notifyChange(t)},_restrictMinMax:function(t,e){var i=this._getMinMaxDate(t,"min"),s=this._getMinMaxDate(t,"max"),n=i&&i>e?i:e;return s&&n>s?s:n},_notifyChange:function(t){var e=this._get(t,"onChangeMonthYear");e&&e.apply(t.input?t.input[0]:null,[t.selectedYear,t.selectedMonth+1,t])},_getNumberOfMonths:function(t){var e=this._get(t,"numberOfMonths");return null==e?[1,1]:"number"==typeof e?[1,e]:e},_getMinMaxDate:function(t,e){return this._determineDate(t,this._get(t,e+"Date"),null)},_getDaysInMonth:function(t,e){return 32-this._daylightSavingAdjust(new Date(t,e,32)).getDate()},_getFirstDayOfMonth:function(t,e){return new Date(t,e,1).getDay()},_canAdjustMonth:function(t,e,i,s){var n=this._getNumberOfMonths(t),a=this._daylightSavingAdjust(new Date(i,s+(0>e?e:n[0]*n[1]),1));return 0>e&&a.setDate(this._getDaysInMonth(a.getFullYear(),a.getMonth())),this._isInRange(t,a)},_isInRange:function(t,e){var i,s,n=this._getMinMaxDate(t,"min"),a=this._getMinMaxDate(t,"max"),r=null,o=null,h=this._get(t,"yearRange");return h&&(i=h.split(":"),s=(new Date).getFullYear(),r=parseInt(i[0],10),o=parseInt(i[1],10),i[0].match(/[+\-].*/)&&(r+=s),i[1].match(/[+\-].*/)&&(o+=s)),(!n||e.getTime()>=n.getTime())&&(!a||e.getTime()<=a.getTime())&&(!r||e.getFullYear()>=r)&&(!o||o>=e.getFullYear())},_getFormatConfig:function(t){var e=this._get(t,"shortYearCutoff");return e="string"!=typeof e?e:(new Date).getFullYear()%100+parseInt(e,10),{shortYearCutoff:e,dayNamesShort:this._get(t,"dayNamesShort"),dayNames:this._get(t,"dayNames"),monthNamesShort:this._get(t,"monthNamesShort"),monthNames:this._get(t,"monthNames")}},_formatDate:function(t,e,i,s){e||(t.currentDay=t.selectedDay,t.currentMonth=t.selectedMonth,t.currentYear=t.selectedYear);var n=e?"object"==typeof e?e:this._daylightSavingAdjust(new Date(s,i,e)):this._daylightSavingAdjust(new Date(t.currentYear,t.currentMonth,t.currentDay));return this.formatDate(this._get(t,"dateFormat"),n,this._getFormatConfig(t))}}),t.fn.datepicker=function(e){if(!this.length)return this;t.datepicker.initialized||(t(document).mousedown(t.datepicker._checkExternalClick),t.datepicker.initialized=!0),0===t("#"+t.datepicker._mainDivId).length&&t("body").append(t.datepicker.dpDiv);var i=Array.prototype.slice.call(arguments,1);return"string"!=typeof e||"isDisabled"!==e&&"getDate"!==e&&"widget"!==e?"option"===e&&2===arguments.length&&"string"==typeof arguments[1]?t.datepicker["_"+e+"Datepicker"].apply(t.datepicker,[this[0]].concat(i)):this.each(function(){"string"==typeof e?t.datepicker["_"+e+"Datepicker"].apply(t.datepicker,[this].concat(i)):t.datepicker._attachDatepicker(this,e)}):t.datepicker["_"+e+"Datepicker"].apply(t.datepicker,[this[0]].concat(i))},t.datepicker=new i,t.datepicker.initialized=!1,t.datepicker.uuid=(new Date).getTime(),t.datepicker.version="1.10.3"})(jQuery);
/*!
 * jQuery Cycle2 - Version: 20130502
 * http://malsup.com/jquery/cycle2/
 * Copyright (c) 2012 M. Alsup; Dual licensed: MIT/GPL
 * Requires: jQuery v1.7 or later
 */
;(function($) {
"use strict";

var version = '20130409';

$.fn.cycle = function( options ) {
    // fix mistakes with the ready state
    var o;
    if ( this.length === 0 && !$.isReady ) {
        o = { s: this.selector, c: this.context };
        $.fn.cycle.log('requeuing slideshow (dom not ready)');
        $(function() {
            $( o.s, o.c ).cycle(options);
        });
        return this;
    }

    return this.each(function() {
        var data, opts, shortName, val;
        var container = $(this);
        var log = $.fn.cycle.log;

        if ( container.data('cycle.opts') )
            return; // already initialized

        if ( container.data('cycle-log') === false || 
            ( options && options.log === false ) ||
            ( opts && opts.log === false) ) {
            log = $.noop;
        }

        log('--c2 init--');
        data = container.data();
        for (var p in data) {
            // allow props to be accessed sans 'cycle' prefix and log the overrides
            if (data.hasOwnProperty(p) && /^cycle[A-Z]+/.test(p) ) {
                val = data[p];
                shortName = p.match(/^cycle(.*)/)[1].replace(/^[A-Z]/, lowerCase);
                log(shortName+':', val, '('+typeof val +')');
                data[shortName] = val;
            }
        }

        opts = $.extend( {}, $.fn.cycle.defaults, data, options || {});

        opts.timeoutId = 0;
        opts.paused = opts.paused || false; // #57
        opts.container = container;
        opts._maxZ = opts.maxZ;

        opts.API = $.extend ( { _container: container }, $.fn.cycle.API );
        opts.API.log = log;
        opts.API.trigger = function( eventName, args ) {
            opts.container.trigger( eventName, args );
            return opts.API;
        };

        container.data( 'cycle.opts', opts );
        container.data( 'cycle.API', opts.API );

        // opportunity for plugins to modify opts and API
        opts.API.trigger('cycle-bootstrap', [ opts, opts.API ]);

        opts.API.addInitialSlides();
        opts.API.preInitSlideshow();

        if ( opts.slides.length )
            opts.API.initSlideshow();
    });
};

$.fn.cycle.API = {
    opts: function() {
        return this._container.data( 'cycle.opts' );
    },
    addInitialSlides: function() {
        var opts = this.opts();
        var slides = opts.slides;
        opts.slideCount = 0;
        opts.slides = $(); // empty set
        
        // add slides that already exist
        slides = slides.jquery ? slides : opts.container.find( slides );

        if ( opts.random ) {
            slides.sort(function() {return Math.random() - 0.5;});
        }

        opts.API.add( slides );
    },

    preInitSlideshow: function() {
        var opts = this.opts();
        opts.API.trigger('cycle-pre-initialize', [ opts ]);
        var tx = $.fn.cycle.transitions[opts.fx];
        if (tx && $.isFunction(tx.preInit))
            tx.preInit( opts );
        opts._preInitialized = true;
    },

    postInitSlideshow: function() {
        var opts = this.opts();
        opts.API.trigger('cycle-post-initialize', [ opts ]);
        var tx = $.fn.cycle.transitions[opts.fx];
        if (tx && $.isFunction(tx.postInit))
            tx.postInit( opts );
    },

    initSlideshow: function() {
        var opts = this.opts();
        var pauseObj = opts.container;
        var slideOpts;
        opts.API.calcFirstSlide();

        if ( opts.container.css('position') == 'static' )
            opts.container.css('position', 'relative');

        $(opts.slides[opts.currSlide]).css('opacity',1).show();
        opts.API.stackSlides( opts.slides[opts.currSlide], opts.slides[opts.nextSlide], !opts.reverse );

        if ( opts.pauseOnHover ) {
            // allow pauseOnHover to specify an element
            if ( opts.pauseOnHover !== true )
                pauseObj = $( opts.pauseOnHover );

            pauseObj.hover(
                function(){ opts.API.pause( true ); }, 
                function(){ opts.API.resume( true ); }
            );
        }

        // stage initial transition
        if ( opts.timeout ) {
            slideOpts = opts.API.getSlideOpts( opts.nextSlide );
            opts.API.queueTransition( slideOpts, slideOpts.timeout + opts.delay );
        }

        opts._initialized = true;
        opts.API.updateView( true );
        opts.API.trigger('cycle-initialized', [ opts ]);
        opts.API.postInitSlideshow();
    },

    pause: function( hover ) {
        var opts = this.opts(),
            slideOpts = opts.API.getSlideOpts(),
            alreadyPaused = opts.hoverPaused || opts.paused;

        if ( hover )
            opts.hoverPaused = true; 
        else
            opts.paused = true;

        if ( ! alreadyPaused ) {
            opts.container.addClass('cycle-paused');
            opts.API.trigger('cycle-paused', [ opts ]).log('cycle-paused');

            if ( slideOpts.timeout ) {
                clearTimeout( opts.timeoutId );
                opts.timeoutId = 0;
                
                // determine how much time is left for the current slide
                opts._remainingTimeout -= ( $.now() - opts._lastQueue );
                if ( opts._remainingTimeout < 0 || isNaN(opts._remainingTimeout) )
                    opts._remainingTimeout = undefined;
            }
        }
    },

    resume: function( hover ) {
        var opts = this.opts(),
            alreadyResumed = !opts.hoverPaused && !opts.paused,
            remaining;

        if ( hover )
            opts.hoverPaused = false; 
        else
            opts.paused = false;

        if ( ! alreadyResumed ) {
            opts.container.removeClass('cycle-paused');
            opts.API.queueTransition( opts.API.getSlideOpts(), opts._remainingTimeout );
            opts.API.trigger('cycle-resumed', [ opts, opts._remainingTimeout ] ).log('cycle-resumed');
        }
    },

    add: function( slides, prepend ) {
        var opts = this.opts();
        var oldSlideCount = opts.slideCount;
        var startSlideshow = false;
        var len;

        if ( $.type(slides) == 'string')
            slides = $.trim( slides );

        $( slides ).each(function(i) {
            var slideOpts;
            var slide = $(this);

            if ( prepend )
                opts.container.prepend( slide );
            else
                opts.container.append( slide );

            opts.slideCount++;
            slideOpts = opts.API.buildSlideOpts( slide );

            if ( prepend )
                opts.slides = $( slide ).add( opts.slides );
            else
                opts.slides = opts.slides.add( slide );

            opts.API.initSlide( slideOpts, slide, --opts._maxZ );

            slide.data('cycle.opts', slideOpts);
            opts.API.trigger('cycle-slide-added', [ opts, slideOpts, slide ]);
        });

        opts.API.updateView( true );

        startSlideshow = opts._preInitialized && (oldSlideCount < 2 && opts.slideCount >= 1);
        if ( startSlideshow ) {
            if ( !opts._initialized )
                opts.API.initSlideshow();
            else if ( opts.timeout ) {
                len = opts.slides.length;
                opts.nextSlide = opts.reverse ? len - 1 : 1;
                if ( !opts.timeoutId ) {
                    opts.API.queueTransition( opts );
                }
            }
        }
    },

    calcFirstSlide: function() {
        var opts = this.opts();
        var firstSlideIndex;
        firstSlideIndex = parseInt( opts.startingSlide || 0, 10 );
        if (firstSlideIndex >= opts.slides.length || firstSlideIndex < 0)
            firstSlideIndex = 0;

        opts.currSlide = firstSlideIndex;
        if ( opts.reverse ) {
            opts.nextSlide = firstSlideIndex - 1;
            if (opts.nextSlide < 0)
                opts.nextSlide = opts.slides.length - 1;
        }
        else {
            opts.nextSlide = firstSlideIndex + 1;
            if (opts.nextSlide == opts.slides.length)
                opts.nextSlide = 0;
        }
    },

    calcNextSlide: function() {
        var opts = this.opts();
        var roll;
        if ( opts.reverse ) {
            roll = (opts.nextSlide - 1) < 0;
            opts.nextSlide = roll ? opts.slideCount - 1 : opts.nextSlide-1;
            opts.currSlide = roll ? 0 : opts.nextSlide+1;
        }
        else {
            roll = (opts.nextSlide + 1) == opts.slides.length;
            opts.nextSlide = roll ? 0 : opts.nextSlide+1;
            opts.currSlide = roll ? opts.slides.length-1 : opts.nextSlide-1;
        }
    },

    calcTx: function( slideOpts, manual ) {
        var opts = slideOpts;
        var tx;
        if ( manual && opts.manualFx )
            tx = $.fn.cycle.transitions[opts.manualFx];
        if ( !tx )
            tx = $.fn.cycle.transitions[opts.fx];

        if (!tx) {
            tx = $.fn.cycle.transitions.fade;
            opts.API.log('Transition "' + opts.fx + '" not found.  Using fade.');
        }
        return tx;
    },

    prepareTx: function( manual, fwd ) {
        var opts = this.opts();
        var after, curr, next, slideOpts, tx;

        if ( opts.slideCount < 2 ) {
            opts.timeoutId = 0;
            return;
        }
        if ( manual && ( !opts.busy || opts.manualTrump ) ) {
            opts.API.stopTransition();
            opts.busy = false;
            clearTimeout(opts.timeoutId);
            opts.timeoutId = 0;
        }
        if ( opts.busy )
            return;
        if ( opts.timeoutId === 0 && !manual )
            return;

        curr = opts.slides[opts.currSlide];
        next = opts.slides[opts.nextSlide];
        slideOpts = opts.API.getSlideOpts( opts.nextSlide );
        tx = opts.API.calcTx( slideOpts, manual );

        opts._tx = tx;

        if ( manual && slideOpts.manualSpeed !== undefined )
            slideOpts.speed = slideOpts.manualSpeed;

        // if ( opts.nextSlide === opts.currSlide )
        //     opts.API.calcNextSlide();

        // ensure that:
        //      1. advancing to a different slide
        //      2. this is either a manual event (prev/next, pager, cmd) or 
        //              a timer event and slideshow is not paused
        if ( opts.nextSlide != opts.currSlide && 
            (manual || (!opts.paused && !opts.hoverPaused && opts.timeout) )) { // #62

            opts.API.trigger('cycle-before', [ slideOpts, curr, next, fwd ]);
            if ( tx.before )
                tx.before( slideOpts, curr, next, fwd );

            after = function() {
                opts.busy = false;
                // #76; bail if slideshow has been destroyed
                if (! opts.container.data( 'cycle.opts' ) )
                    return;

                if (tx.after)
                    tx.after( slideOpts, curr, next, fwd );
                opts.API.trigger('cycle-after', [ slideOpts, curr, next, fwd ]);
                opts.API.queueTransition( slideOpts);
                opts.API.updateView( true );
            };

            opts.busy = true;
            if (tx.transition)
                tx.transition(slideOpts, curr, next, fwd, after);
            else
                opts.API.doTransition( slideOpts, curr, next, fwd, after);

            opts.API.calcNextSlide();
            opts.API.updateView();
        } else {
            opts.API.queueTransition( slideOpts );
        }
    },

    // perform the actual animation
    doTransition: function( slideOpts, currEl, nextEl, fwd, callback) {
        var opts = slideOpts;
        var curr = $(currEl), next = $(nextEl);
        var fn = function() {
            // make sure animIn has something so that callback doesn't trigger immediately
            next.animate(opts.animIn || { opacity: 1}, opts.speed, opts.easeIn || opts.easing, callback);
        };
		
        next.css(opts.cssBefore || {});
        curr.animate(opts.animOut || {}, opts.speed, opts.easeOut || opts.easing, function() {
            curr.css(opts.cssAfter || {});
            if (!opts.sync) {
                fn();
            }
        });
        if (opts.sync) {
            fn();
        }
    },

    queueTransition: function( slideOpts, specificTimeout ) {
        var opts = this.opts();
        var timeout = specificTimeout !== undefined ? specificTimeout : slideOpts.timeout;
        if (opts.nextSlide === 0 && --opts.loop === 0) {
            opts.API.log('terminating; loop=0');
            opts.timeout = 0;
            if ( timeout ) {
                setTimeout(function() {
                    opts.API.trigger('cycle-finished', [ opts ]);
                }, timeout);
            }
            else {
                opts.API.trigger('cycle-finished', [ opts ]);
            }
            // reset nextSlide
            opts.nextSlide = opts.currSlide;
            return;
        }
        if ( timeout ) {
            opts._lastQueue = $.now();
            if ( specificTimeout === undefined )
                opts._remainingTimeout = slideOpts.timeout;

            if ( !opts.paused && ! opts.hoverPaused ) {
                opts.timeoutId = setTimeout(function() { 
                    opts.API.prepareTx( false, !opts.reverse ); 
                }, timeout );
            }
        }
    },

    stopTransition: function() {
        var opts = this.opts();
        if ( opts.slides.filter(':animated').length ) {
            opts.slides.stop(false, true);
            opts.API.trigger('cycle-transition-stopped', [ opts ]);
        }

        if ( opts._tx && opts._tx.stopTransition )
            opts._tx.stopTransition( opts );
    },

    // advance slide forward or back
    advanceSlide: function( val ) {
        var opts = this.opts();
        clearTimeout(opts.timeoutId);
        opts.timeoutId = 0;
        opts.nextSlide = opts.currSlide + val;
        
        if (opts.nextSlide < 0)
            opts.nextSlide = opts.slides.length - 1;
        else if (opts.nextSlide >= opts.slides.length)
            opts.nextSlide = 0;

        opts.API.prepareTx( true,  val >= 0 );
        return false;
    },

    buildSlideOpts: function( slide ) {
        var opts = this.opts();
        var val, shortName;
        var slideOpts = slide.data() || {};
        for (var p in slideOpts) {
            // allow props to be accessed sans 'cycle' prefix and log the overrides
            if (slideOpts.hasOwnProperty(p) && /^cycle[A-Z]+/.test(p) ) {
                val = slideOpts[p];
                shortName = p.match(/^cycle(.*)/)[1].replace(/^[A-Z]/, lowerCase);
                opts.API.log('['+(opts.slideCount-1)+']', shortName+':', val, '('+typeof val +')');
                slideOpts[shortName] = val;
            }
        }

        slideOpts = $.extend( {}, $.fn.cycle.defaults, opts, slideOpts );
        slideOpts.slideNum = opts.slideCount;

        try {
            // these props should always be read from the master state object
            delete slideOpts.API;
            delete slideOpts.slideCount;
            delete slideOpts.currSlide;
            delete slideOpts.nextSlide;
            delete slideOpts.slides;
        } catch(e) {
            // no op
        }
        return slideOpts;
    },

    getSlideOpts: function( index ) {
        var opts = this.opts();
        if ( index === undefined )
            index = opts.currSlide;

        var slide = opts.slides[index];
        var slideOpts = $(slide).data('cycle.opts');
        return $.extend( {}, opts, slideOpts );
    },
    
    initSlide: function( slideOpts, slide, suggestedZindex ) {
        var opts = this.opts();
        slide.css( slideOpts.slideCss || {} );
        if ( suggestedZindex > 0 )
            slide.css( 'zIndex', suggestedZindex );

        // ensure that speed settings are sane
        if ( isNaN( slideOpts.speed ) )
            slideOpts.speed = $.fx.speeds[slideOpts.speed] || $.fx.speeds._default;
        if ( !slideOpts.sync )
            slideOpts.speed = slideOpts.speed / 2;

        slide.addClass( opts.slideClass );
    },

    updateView: function( isAfter ) {
        var opts = this.opts();
        if ( !opts._initialized )
            return;
        var slideOpts = opts.API.getSlideOpts();
        var currSlide = opts.slides[ opts.currSlide ];

        if ( ! isAfter ) {
            opts.API.trigger('cycle-update-view-before', [ opts, slideOpts, currSlide ]);
            if ( opts.updateView < 0 )
                return;
        }

        if ( opts.slideActiveClass ) {
            opts.slides.removeClass( opts.slideActiveClass )
                .eq( opts.currSlide ).addClass( opts.slideActiveClass );
        }

        if ( isAfter && opts.hideNonActive )
            opts.slides.filter( ':not(.' + opts.slideActiveClass + ')' ).hide();

        opts.API.trigger('cycle-update-view', [ opts, slideOpts, currSlide, isAfter ]);
        opts.API.trigger('cycle-update-view-after', [ opts, slideOpts, currSlide ]);
    },

    getComponent: function( name ) {
        var opts = this.opts();
        var selector = opts[name];
        if (typeof selector === 'string') {
            // if selector is a child, sibling combinator, adjancent selector then use find, otherwise query full dom
            return (/^\s*[\>|\+|~]/).test( selector ) ? opts.container.find( selector ) : $( selector );
        }
        if (selector.jquery)
            return selector;
        
        return $(selector);
    },

    stackSlides: function( curr, next, fwd ) {
        var opts = this.opts();
        if ( !curr ) {
            curr = opts.slides[opts.currSlide];
            next = opts.slides[opts.nextSlide];
            fwd = !opts.reverse;
        }

        // reset the zIndex for the common case:
        // curr slide on top,  next slide beneath, and the rest in order to be shown
        $(curr).css('zIndex', opts.maxZ);

        var i;
        var z = opts.maxZ - 2;
        var len = opts.slideCount;
        if (fwd) {
            for ( i = opts.currSlide + 1; i < len; i++ )
                $( opts.slides[i] ).css( 'zIndex', z-- );
            for ( i = 0; i < opts.currSlide; i++ )
                $( opts.slides[i] ).css( 'zIndex', z-- );
        }
        else {
            for ( i = opts.currSlide - 1; i >= 0; i-- )
                $( opts.slides[i] ).css( 'zIndex', z-- );
            for ( i = len - 1; i > opts.currSlide; i-- )
                $( opts.slides[i] ).css( 'zIndex', z-- );
        }

        $(next).css('zIndex', opts.maxZ - 1);
    },

    getSlideIndex: function( el ) {
        return this.opts().slides.index( el );
    }

}; // API

// default logger
$.fn.cycle.log = function log() {
    /*global console:true */
    if (window.console && console.log)
        console.log('[cycle2] ' + Array.prototype.join.call(arguments, ' ') );
};

$.fn.cycle.version = function() { return 'Cycle2: ' + version; };

// helper functions

function lowerCase(s) {
    return (s || '').toLowerCase();
}

// expose transition object
$.fn.cycle.transitions = {
    custom: {
    },
    none: {
        before: function( opts, curr, next, fwd ) {
            opts.API.stackSlides( next, curr, fwd );
            opts.cssBefore = { opacity: 1, display: 'block' };
        }
    },
    fade: {
        before: function( opts, curr, next, fwd ) {
            var css = opts.API.getSlideOpts( opts.nextSlide ).slideCss || {};
            opts.API.stackSlides( curr, next, fwd );
            opts.cssBefore = $.extend(css, { opacity: 0, display: 'block' });
            opts.animIn = { opacity: 1 };
            opts.animOut = { opacity: 0 };
        }
    },
    fadeout: {
        before: function( opts , curr, next, fwd ) {
            var css = opts.API.getSlideOpts( opts.nextSlide ).slideCss || {};
            opts.API.stackSlides( curr, next, fwd );
            opts.cssBefore = $.extend(css, { opacity: 1, display: 'block' });
            opts.animOut = { opacity: 0 };
        }
    },
    scrollHorz: {
        before: function( opts, curr, next, fwd ) {
            opts.API.stackSlides( curr, next, fwd );
            var w = opts.container.css('overflow','hidden').width();
            opts.cssBefore = { left: fwd ? w : - w, top: 0, opacity: 1, display: 'block' };
            opts.cssAfter = { zIndex: opts._maxZ - 2, left: 0 };
            opts.animIn = { left: 0 };
            opts.animOut = { left: fwd ? -w : w };
        }
    }
};

// @see: http://jquery.malsup.com/cycle2/api
$.fn.cycle.defaults = {
    allowWrap:        true,
    autoSelector:     '.cycle-slideshow[data-cycle-auto-init!=false]',
    delay:            0,
    easing:           null,
    fx:              'fade',
    hideNonActive:    true,
    loop:             0,
    manualFx:         undefined,
    manualSpeed:      undefined,
    manualTrump:      true,
    maxZ:             100,
    pauseOnHover:     false,
    reverse:          false,
    slideActiveClass: 'cycle-slide-active',
    slideClass:       'cycle-slide',
    slideCss:         { position: 'absolute', top: 0, left: 0 },
    slides:          '> img',
    speed:            500,
    startingSlide:    0,
    sync:             true,
    timeout:          4000,
    updateView:       -1
};

// automatically find and run slideshows
$(document).ready(function() {
    $( $.fn.cycle.defaults.autoSelector ).cycle();
});

})(jQuery);

/*! Cycle2 autoheight plugin; Copyright (c) M.Alsup, 2012; version: 20130304 */
(function($) {
"use strict";

$.extend($.fn.cycle.defaults, {
    autoHeight: 0 // setting this option to false disables autoHeight logic
});    

$(document).on( 'cycle-initialized', function( e, opts ) {
    var autoHeight = opts.autoHeight;
    var t = $.type( autoHeight );
    var resizeThrottle = null;
    var ratio;

    if ( t !== 'string' && t !== 'number' )
        return;

    // bind events
    opts.container.on( 'cycle-slide-added cycle-slide-removed', initAutoHeight );
    opts.container.on( 'cycle-destroyed', onDestroy );

    if ( autoHeight == 'container' ) {
        opts.container.on( 'cycle-before', onBefore );
    }
    else if ( t === 'string' && /\d+\:\d+/.test( autoHeight ) ) { 
        // use ratio
        ratio = autoHeight.match(/(\d+)\:(\d+)/);
        ratio = ratio[1] / ratio[2];
        opts._autoHeightRatio = ratio;
    }

    // if autoHeight is a number then we don't need to recalculate the sentinel
    // index on resize
    if ( t !== 'number' ) {
        // bind unique resize handler per slideshow (so it can be 'off-ed' in onDestroy)
        opts._autoHeightOnResize = function () {
            clearTimeout( resizeThrottle );
            resizeThrottle = setTimeout( onResize, 50 );
        };

        $(window).on( 'resize orientationchange', opts._autoHeightOnResize );
    }

    setTimeout( onResize, 30 );

    function onResize() {
        initAutoHeight( e, opts );
    }
});

function initAutoHeight( e, opts ) {
    var clone, height, sentinelIndex;
    var autoHeight = opts.autoHeight;

    if ( autoHeight == 'container' ) {
        height = $( opts.slides[ opts.currSlide ] ).outerHeight();
        opts.container.height( height );
    }
    else if ( opts._autoHeightRatio ) { 
        opts.container.height( opts.container.width() / opts._autoHeightRatio );
    }
    else if ( autoHeight === 'calc' || ( $.type( autoHeight ) == 'number' && autoHeight >= 0 ) ) {
        if ( autoHeight === 'calc' )
            sentinelIndex = calcSentinelIndex( e, opts );
        else if ( autoHeight >= opts.slides.length )
            sentinelIndex = 0;
        else 
            sentinelIndex = autoHeight;

        // only recreate sentinel if index is different
        if ( sentinelIndex == opts._sentinelIndex )
            return;

        opts._sentinelIndex = sentinelIndex;
        if ( opts._sentinel )
            opts._sentinel.remove();

        // clone existing slide as sentinel
        clone = $( opts.slides[ sentinelIndex ].cloneNode(true) );
        
        // #50; remove special attributes from cloned content
        clone.removeAttr( 'id name rel' ).find( '[id],[name],[rel]' ).removeAttr( 'id name rel' );

        clone.css({
            position: 'static',
            visibility: 'hidden',
            display: 'block'
        }).prependTo( opts.container ).addClass('cycle-sentinel cycle-slide').removeClass('cycle-slide-active');
        clone.find( '*' ).css( 'visibility', 'hidden' );

        opts._sentinel = clone;
    }
}    

function calcSentinelIndex( e, opts ) {
    var index = 0, max = -1;

    // calculate tallest slide index
    opts.slides.each(function(i) {
        var h = $(this).height();
        if ( h > max ) {
            max = h;
            index = i;
        }
    });
    return index;
}

function onBefore( e, opts, outgoing, incoming, forward ) {
    var h = $(incoming).outerHeight();
    var duration = opts.sync ? opts.speed / 2 : opts.speed;
    opts.container.animate( { height: h }, duration );
}

function onDestroy( e, opts ) {
    if ( opts._autoHeightOnResize ) {
        $(window).off( 'resize orientationchange', opts._autoHeightOnResize );
        opts._autoHeightOnResize = null;
    }
    opts.container.off( 'cycle-slide-added cycle-slide-removed', initAutoHeight );
    opts.container.off( 'cycle-destroyed', onDestroy );
    opts.container.off( 'cycle-before', onBefore );

    if ( opts._sentinel ) {
        opts._sentinel.remove();
        opts._sentinel = null;
    }
}

})(jQuery);

/*! caption plugin for Cycle2;  version: 20130306 */
(function($) {
"use strict";

$.extend($.fn.cycle.defaults, {
    caption:          '> .cycle-caption',
    captionTemplate:  '{{slideNum}} / {{slideCount}}',
    overlay:          '> .cycle-overlay',
    overlayTemplate:  '<div>{{title}}</div><div>{{desc}}</div>',
    captionModule:    'caption'
});    

$(document).on( 'cycle-update-view', function( e, opts, slideOpts, currSlide ) {
    if ( opts.captionModule !== 'caption' )
        return;
    var el;
    $.each(['caption','overlay'], function() {
        var name = this; 
        var template = slideOpts[name+'Template'];
        var el = opts.API.getComponent( name );
        if( el.length && template ) {
            el.html( opts.API.tmpl( template, slideOpts, opts, currSlide ) );
            el.show();
        }
        else {
            el.hide();
        }
    });
});

$(document).on( 'cycle-destroyed', function( e, opts ) {
    var el;
    $.each(['caption','overlay'], function() {
        var name = this, template = opts[name+'Template'];
        if ( opts[name] && template ) {
            el = opts.API.getComponent( 'caption' );
            el.empty();
        }
    });
});

})(jQuery);

/*! command plugin for Cycle2;  version: 20130323 */
(function($) {
"use strict";

var c2 = $.fn.cycle;

$.fn.cycle = function( options ) {
    var cmd, cmdFn, opts;
    var args = $.makeArray( arguments );

    if ( $.type( options ) == 'number' ) {
        return this.cycle( 'goto', options );
    }

    if ( $.type( options ) == 'string' ) {
        return this.each(function() {
            var cmdArgs;
            cmd = options;
            opts = $(this).data('cycle.opts');

            if ( opts === undefined ) {
                c2.log('slideshow must be initialized before sending commands; "' + cmd + '" ignored');
                return;
            }
            else {
                cmd = cmd == 'goto' ? 'jump' : cmd; // issue #3; change 'goto' to 'jump' internally
                cmdFn = opts.API[ cmd ];
                if ( $.isFunction( cmdFn )) {
                    cmdArgs = $.makeArray( args );
                    cmdArgs.shift();
                    return cmdFn.apply( opts.API, cmdArgs );
                }
                else {
                    c2.log( 'unknown command: ', cmd );
                }
            }
        });
    }
    else {
        return c2.apply( this, arguments );
    }
};

// copy props
$.extend( $.fn.cycle, c2 );

$.extend( c2.API, {
    next: function() {
        var opts = this.opts();
        if ( opts.busy && ! opts.manualTrump )
            return;
        
        var count = opts.reverse ? -1 : 1;
        if ( opts.allowWrap === false && ( opts.currSlide + count ) >= opts.slideCount )
            return;

        opts.API.advanceSlide( count );
        opts.API.trigger('cycle-next', [ opts ]).log('cycle-next');
    },

    prev: function() {
        var opts = this.opts();
        if ( opts.busy && ! opts.manualTrump )
            return;
        var count = opts.reverse ? 1 : -1;
        if ( opts.allowWrap === false && ( opts.currSlide + count ) < 0 )
            return;

        opts.API.advanceSlide( count );
        opts.API.trigger('cycle-prev', [ opts ]).log('cycle-prev');
    },

    destroy: function() {
        var opts = this.opts();
        clearTimeout(opts.timeoutId);
        opts.timeoutId = 0;
        opts.API.stop();
        opts.API.trigger( 'cycle-destroyed', [ opts ] ).log('cycle-destroyed');
        opts.container.removeData( 'cycle.opts' );

        // #75; remove inline styles
        if ( ! opts.retainStylesOnDestroy ) {
            opts.container.removeAttr( 'style' );
            opts.slides.removeAttr( 'style' );
            opts.slides.removeClass( 'cycle-slide-active' );
        }
    },

    jump: function( index ) {
        // go to the requested slide
        var fwd;
        var opts = this.opts();
        if ( opts.busy && ! opts.manualTrump )
            return;
        var num = parseInt( index, 10 );
        if (isNaN(num) || num < 0 || num >= opts.slides.length) {
            opts.API.log('goto: invalid slide index: ' + num);
            return;
        }
        if (num == opts.currSlide) {
            opts.API.log('goto: skipping, already on slide', num);
            return;
        }
        opts.nextSlide = num;
        clearTimeout(opts.timeoutId);
        opts.timeoutId = 0;
        opts.API.log('goto: ', num, ' (zero-index)');
        fwd = opts.currSlide < opts.nextSlide;
        opts.API.prepareTx( true, fwd );
    },

    stop: function() {
        var opts = this.opts();
        var pauseObj = opts.container;
        clearTimeout(opts.timeoutId);
        opts.timeoutId = 0;
        opts.API.stopTransition();
        if ( opts.pauseOnHover ) {
            if ( opts.pauseOnHover !== true )
                pauseObj = $( opts.pauseOnHover );
            pauseObj.off('mouseenter mouseleave');
        }
        opts.API.trigger('cycle-stopped', [ opts ]).log('cycle-stopped');
    },

    reinit: function() {
        var opts = this.opts();
        opts.API.destroy();
        opts.container.cycle();
    },

    remove: function( index ) {
        var opts = this.opts();
        var slide, slideToRemove, slides = [], slideNum = 1;
        for ( var i=0; i < opts.slides.length; i++ ) {
            slide = opts.slides[i];
            if ( i == index ) {
                slideToRemove = slide;
            }
            else {
                slides.push( slide );
                $( slide ).data('cycle.opts').slideNum = slideNum;
                slideNum++;
            }
        }
        if ( slideToRemove ) {
            opts.slides = $( slides );
            opts.slideCount--;
            $( slideToRemove ).remove();
            if (index == opts.currSlide) {
                opts.API.advanceSlide( 1 );
            }

            opts.API.trigger('cycle-slide-removed', [ opts, index, slideToRemove ]).log('cycle-slide-removed');
            opts.API.updateView();
        }
    }

});

// listen for clicks on elements with data-cycle-cmd attribute

$(document).on('click.cycle', '[data-cycle-cmd]', function(e) {
    // issue cycle command
    e.preventDefault();
    var el = $(this);
    var command = el.data('cycle-cmd');
    var context = el.data('cycle-context') || '.cycle-slideshow';
    $(context).cycle(command, el.data('cycle-arg'));
});


})(jQuery);

/*! hash plugin for Cycle2;  version: 20121120 */
(function($) {
"use strict";

$(document).on( 'cycle-pre-initialize', function( e, opts ) {
    onHashChange( opts, true );

    opts._onHashChange = function() {
        onHashChange( opts, false );
    };

    $( window ).on( 'hashchange', opts._onHashChange);
});

$(document).on( 'cycle-update-view', function( e, opts, slideOpts ) {
    if ( slideOpts.hash ) {
        opts._hashFence = true;
        window.location.hash = slideOpts.hash;
    }
});

$(document).on( 'cycle-destroyed', function( e, opts) {
    if ( opts._onHashChange ) {
        $( window ).off( 'hashchange', opts._onHashChange );
    }
});

function onHashChange( opts, setStartingSlide ) {
    var hash;
    if ( opts._hashFence ) {
        opts._hashFence = false;
        return;
    }
    
    hash = window.location.hash.substring(1);

    opts.slides.each(function(i) {
        if ( $(this).data( 'cycle-hash' ) == hash ) {
            if ( setStartingSlide === true ) {
                opts.startingSlide = i;
            }
            else {
                opts.nextSlide = i;
                opts.API.prepareTx( true, false );
            }
            return false;
        }
    });
}

})(jQuery);

/*! loader plugin for Cycle2;  version: 20130307 */
(function($) {
"use strict";

$.extend($.fn.cycle.defaults, {
    loader: false
});

$(document).on( 'cycle-bootstrap', function( e, opts ) {
    var addFn;

    if ( !opts.loader )
        return;

    // override API.add for this slideshow
    addFn = opts.API.add;
    opts.API.add = add;

    function add( slides, prepend ) {
        var slideArr = [];
        if ( $.type( slides ) == 'string' )
            slides = $.trim( slides );
        else if ( $.type( slides) === 'array' ) {
            for (var i=0; i < slides.length; i++ )
                slides[i] = $(slides[i])[0];
        }

        slides = $( slides );
        var slideCount = slides.length;

        if ( ! slideCount )
            return;

        slides.hide().appendTo('body').each(function(i) { // appendTo fixes #56
            var count = 0;
            var slide = $(this);
            var images = slide.is('img') ? slide : slide.find('img');
            slide.data('index', i);
            // allow some images to be marked as unimportant (and filter out images w/o src value)
            images = images.filter(':not(.cycle-loader-ignore)').filter(':not([src=""])');
            if ( ! images.length ) {
                --slideCount;
                slideArr.push( slide );
                return;
            }

            count = images.length;
            images.each(function() {
                // add images that are already loaded
                if ( this.complete ) {
                    imageLoaded();
                }
                else {
                    $(this).load(function() {
                        imageLoaded();
                    }).error(function() {
                        if ( --count === 0 ) {
                            // ignore this slide
                            opts.API.log('slide skipped; img not loaded:', this.src);
                            if ( --slideCount === 0 && opts.loader == 'wait') {
                                addFn.apply( opts.API, [ slideArr, prepend ] );
                            }
                        }
                    });
                }
            });

            function imageLoaded() {
                if ( --count === 0 ) {
                    --slideCount;
                    addSlide( slide );
                }
            }
        });

        if ( slideCount )
            opts.container.addClass('cycle-loading');
        

        function addSlide( slide ) {
            var curr;
            if ( opts.loader == 'wait' ) {
                slideArr.push( slide );
                if ( slideCount === 0 ) {
                    // #59; sort slides into original markup order
                    slideArr.sort( sorter );
                    addFn.apply( opts.API, [ slideArr, prepend ] );
                    opts.container.removeClass('cycle-loading');
                }
            }
            else {
                curr = $(opts.slides[opts.currSlide]);
                addFn.apply( opts.API, [ slide, prepend ] );
                curr.show();
                opts.container.removeClass('cycle-loading');
            }
        }

        function sorter(a, b) {
            return a.data('index') - b.data('index');
        }
    }
});

})(jQuery);

/*! pager plugin for Cycle2;  version: 20130203 */
(function($) {
"use strict";

$.extend($.fn.cycle.defaults, {
    pager:            '> .cycle-pager',
    pagerActiveClass: 'cycle-pager-active',
    pagerEvent:       'click.cycle',
    pagerTemplate:    '<span>&bull;</span>'
});    

$(document).on( 'cycle-bootstrap', function( e, opts, API ) {
    // add method to API
    API.buildPagerLink = buildPagerLink;
});

$(document).on( 'cycle-slide-added', function( e, opts, slideOpts, slideAdded ) {
    if ( opts.pager ) {
        opts.API.buildPagerLink ( opts, slideOpts, slideAdded );
        opts.API.page = page;
    }
});

$(document).on( 'cycle-slide-removed', function( e, opts, index, slideRemoved ) {
    if ( opts.pager ) {
        var pagers = opts.API.getComponent( 'pager' );
        pagers.each(function() {
            var pager = $(this);
            $( pager.children()[index] ).remove();
        });
    }
});

$(document).on( 'cycle-update-view', function( e, opts, slideOpts ) {
    var pagers;

    if ( opts.pager ) {
        pagers = opts.API.getComponent( 'pager' );
        pagers.each(function() {
           $(this).children().removeClass( opts.pagerActiveClass )
            .eq( opts.currSlide ).addClass( opts.pagerActiveClass );
        });
    }
});

$(document).on( 'cycle-destroyed', function( e, opts ) {
    var pagers;
    if (opts.pager && opts.pagerTemplate) {
        pagers = opts.API.getComponent( 'pager' );
        pagers.empty();
    }
});

function buildPagerLink( opts, slideOpts, slide ) {
    var pagerLink;
    var pagers = opts.API.getComponent( 'pager' );
    pagers.each(function() {
        var pager = $(this);
        if ( slideOpts.pagerTemplate ) {
            var markup = opts.API.tmpl( slideOpts.pagerTemplate, slideOpts, opts, slide[0] );
            pagerLink = $( markup ).appendTo( pager );
        }
        else {
            pagerLink = pager.children().eq( opts.slideCount - 1 );
        }
        pagerLink.on( opts.pagerEvent, function(e) {
            e.preventDefault();
            opts.API.page( pager, e.currentTarget);
        });
    });
}

function page( pager, target ) {
    /*jshint validthis:true */
    var opts = this.opts();
    if ( opts.busy && ! opts.manualTrump )
        return;

    var index = pager.children().index( target );
    var nextSlide = index;
    var fwd = opts.currSlide < nextSlide;
    if (opts.currSlide == nextSlide) {
        return; // no op, clicked pager for the currently displayed slide
    }
    opts.nextSlide = nextSlide;
    opts.API.prepareTx( true, fwd );
    opts.API.trigger('cycle-pager-activated', [opts, pager, target ]);
}

})(jQuery);


/*! prevnext plugin for Cycle2;  version: 20130307 */
(function($) {
"use strict";

$.extend($.fn.cycle.defaults, {
    next:           '> .cycle-next',
    nextEvent:      'click.cycle',
    disabledClass:  'disabled',
    prev:           '> .cycle-prev',
    prevEvent:      'click.cycle',
    swipe:          false
});    

$(document).on( 'cycle-initialized', function( e, opts ) {
	//Modified to have same component several times on the same page - Aditya
    /*opts.API.getComponent( 'next' ).on( opts.nextEvent, function(e) {
        e.preventDefault();
        opts.API.next();
    });

    opts.API.getComponent( 'prev' ).on( opts.prevEvent, function(e) {
        e.preventDefault();
        opts.API.prev();
    });*/
	opts.container.parent().find(opts.API.getComponent( 'next' )).on( opts.nextEvent, function(e) {
        e.preventDefault();
        opts.API.next();
    });
    opts.container.parent().find(opts.API.getComponent( 'prev' )).on( opts.prevEvent, function(e) {
        e.preventDefault();
        opts.API.prev();
    });

    if ( opts.swipe ) {
        var nextEvent = opts.swipeVert ? 'swipeUp.cycle' : 'swipeLeft.cycle swipeleft.cycle';
        var prevEvent = opts.swipeVert ? 'swipeDown.cycle' : 'swipeRight.cycle swiperight.cycle';
        opts.container.on( nextEvent, function(e) {
            opts.API.next();
        });
        opts.container.on( prevEvent, function() {
            opts.API.prev();
        });
    }
});

$(document).on( 'cycle-update-view', function( e, opts, slideOpts, currSlide ) {
    if ( opts.allowWrap )
        return;

    var cls = opts.disabledClass;
    var next = opts.API.getComponent( 'next' );
    var prev = opts.API.getComponent( 'prev' );
    var prevBoundry = opts._prevBoundry || 0;
    var nextBoundry = opts._nextBoundry || opts.slideCount - 1;

    if ( opts.currSlide == nextBoundry )
        next.addClass( cls ).prop( 'disabled', true );
    else
        next.removeClass( cls ).prop( 'disabled', false );

    if ( opts.currSlide === prevBoundry )
        prev.addClass( cls ).prop( 'disabled', true );
    else
        prev.removeClass( cls ).prop( 'disabled', false );
});


$(document).on( 'cycle-destroyed', function( e, opts ) {
    opts.API.getComponent( 'prev' ).off( opts.nextEvent );
    opts.API.getComponent( 'next' ).off( opts.prevEvent );
    opts.container.off( 'swipeleft.cycle swiperight.cycle swipeLeft.cycle swipeRight.cycle swipeUp.cycle swipeDown.cycle' );
});

})(jQuery);

/*! progressive loader plugin for Cycle2;  version: 20130315 */
(function($) {
"use strict";

$.extend($.fn.cycle.defaults, {
    progressive: false
});

$(document).on( 'cycle-pre-initialize', function( e, opts ) {
    if ( !opts.progressive )
        return;

    var API = opts.API;
    var nextFn = API.next;
    var prevFn = API.prev;
    var prepareTxFn = API.prepareTx;
    var type = $.type( opts.progressive );
    var slides, scriptEl;

    if ( type == 'array' ) {
        slides = opts.progressive;
    }
    else if ($.isFunction( opts.progressive ) ) {
        slides = opts.progressive( opts );
    }
    else if ( type == 'string' ) {
        scriptEl = $( opts.progressive );
        slides = $.trim( scriptEl.html() );
        if ( !slides )
            return;
        // is it json array?
        if ( /^(\[)/.test( slides ) ) {
            try {
                slides = $.parseJSON( slides );
            }
            catch(err) {
                API.log( 'error parsing progressive slides', err );
                return;
            }
        }
        else {
            // plain text, split on delimeter
            slides = slides.split( new RegExp( scriptEl.data('cycle-split') || '\n') );
            
            // #95; look for empty slide
            if ( ! slides[ slides.length - 1 ] )
                slides.pop();
        }
    }



    if ( prepareTxFn ) {
        API.prepareTx = function( manual, fwd ) {
            var index, slide;

            if ( manual || slides.length === 0 ) {
                prepareTxFn.apply( opts.API, [ manual, fwd ] );
                return;
            }

            if ( fwd && opts.currSlide == ( opts.slideCount-1) ) {
                slide = slides[ 0 ];
                slides = slides.slice( 1 );
                opts.container.one('cycle-slide-added', function(e, opts ) {
                    setTimeout(function() {
                        opts.API.advanceSlide( 1 );
                    },50);
                });
                opts.API.add( slide );
            }
            else if ( !fwd && opts.currSlide === 0 ) {
                index = slides.length-1;
                slide = slides[ index ];
                slides = slides.slice( 0, index );
                opts.container.one('cycle-slide-added', function(e, opts ) {
                    setTimeout(function() {
                        opts.currSlide = 1;
                        opts.API.advanceSlide( -1 );
                    },50);
                });
                opts.API.add( slide, true );
            }
            else {
                prepareTxFn.apply( opts.API, [ manual, fwd ] );
            }
        };
    }

    if ( nextFn ) {
        API.next = function() {
            var opts = this.opts();
            if ( slides.length && opts.currSlide == ( opts.slideCount - 1 ) ) {
                var slide = slides[ 0 ];
                slides = slides.slice( 1 );
                opts.container.one('cycle-slide-added', function(e, opts ) {
                    nextFn.apply( opts.API );
                    opts.container.removeClass('cycle-loading');
                });
                opts.container.addClass('cycle-loading');
                opts.API.add( slide );
            }
            else {
                nextFn.apply( opts.API );    
            }
        };
    }
    
    if ( prevFn ) {
        API.prev = function() {
            var opts = this.opts();
            if ( slides.length && opts.currSlide === 0 ) {
                var index = slides.length-1;
                var slide = slides[ index ];
                slides = slides.slice( 0, index );
                opts.container.one('cycle-slide-added', function(e, opts ) {
                    opts.currSlide = 1;
                    opts.API.advanceSlide( -1 );
                    opts.container.removeClass('cycle-loading');
                });
                opts.container.addClass('cycle-loading');
                opts.API.add( slide, true );
            }
            else {
                prevFn.apply( opts.API );
            }
        };
    }
});

})(jQuery);

/*! tmpl plugin for Cycle2;  version: 20121227 */
(function($) {
"use strict";

$.extend($.fn.cycle.defaults, {
    tmplRegex: '{{((.)?.*?)}}'
});

$.extend($.fn.cycle.API, {
    tmpl: function( str, opts /*, ... */) {
        var regex = new RegExp( opts.tmplRegex || $.fn.cycle.defaults.tmplRegex, 'g' );
        var args = $.makeArray( arguments );
        args.shift();
        return str.replace(regex, function(_, str) {
            var i, j, obj, prop, names = str.split('.');
            for (i=0; i < args.length; i++) {
                obj = args[i];
                if ( ! obj )
                    continue;
                if (names.length > 1) {
                    prop = obj;
                    for (j=0; j < names.length; j++) {
                        obj = prop;
                        prop = prop[ names[j] ] || str;
                    }
                } else {
                    prop = obj[str];
                }

                if ($.isFunction(prop))
                    return prop.apply(obj, args);
                if (prop !== undefined && prop !== null && prop != str)
                    return prop;
            }
            return str;
        });
    }
});    

})(jQuery);
/*!
 * jQuery Validation Plugin 1.11.1
 *
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * http://docs.jquery.com/Plugins/Validation
 *
 * Copyright 2013 Jörn Zaefferer
 * Released under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 */

(function($) {

$.extend($.fn, {
	// http://docs.jquery.com/Plugins/Validation/validate
	validate: function( options ) {

		// if nothing is selected, return nothing; can't chain anyway
		if ( !this.length ) {
			if ( options && options.debug && window.console ) {
				//console.warn( "Nothing selected, can't validate, returning nothing." );
			}
			return;
		}

		// check if a validator for this form was already created
		var validator = $.data( this[0], "validator" );
		if ( validator ) {
			return validator;
		}

		// Add novalidate tag if HTML5.
		this.attr( "novalidate", "novalidate" );

		validator = new $.validator( options, this[0] );
		$.data( this[0], "validator", validator );

		if ( validator.settings.onsubmit ) {

			this.validateDelegate( ":submit", "click", function( event ) {
				if ( validator.settings.submitHandler ) {
					validator.submitButton = event.target;
				}
				// allow suppressing validation by adding a cancel class to the submit button
				if ( $(event.target).hasClass("cancel") ) {
					validator.cancelSubmit = true;
				}

				// allow suppressing validation by adding the html5 formnovalidate attribute to the submit button
				if ( $(event.target).attr("formnovalidate") !== undefined ) {
					validator.cancelSubmit = true;
				}
			});

			// validate the form on submit
			this.submit( function( event ) {
				if ( validator.settings.debug ) {
					// prevent form submit to be able to see console output
					event.preventDefault();
				}
				function handle() {
					var hidden;
					if ( validator.settings.submitHandler ) {
						if ( validator.submitButton ) {
							// insert a hidden input as a replacement for the missing submit button
							hidden = $("<input type='hidden'/>").attr("name", validator.submitButton.name).val( $(validator.submitButton).val() ).appendTo(validator.currentForm);
						}
						validator.settings.submitHandler.call( validator, validator.currentForm, event );
						if ( validator.submitButton ) {
							// and clean up afterwards; thanks to no-block-scope, hidden can be referenced
							hidden.remove();
						}
						return false;
					}
					return true;
				}

				// prevent submit for invalid forms or custom submit handlers
				if ( validator.cancelSubmit ) {
					validator.cancelSubmit = false;
					return handle();
				}
				if ( validator.form() ) {
					if ( validator.pendingRequest ) {
						validator.formSubmitted = true;
						return false;
					}
					return handle();
				} else {
					validator.focusInvalid();
					return false;
				}
			});
		}

		return validator;
	},
	// http://docs.jquery.com/Plugins/Validation/valid
	valid: function() {
		if ( $(this[0]).is("form")) {
			return this.validate().form();
		} else {
			var valid = true;
			var validator = $(this[0].form).validate();
			this.each(function() {
				valid = valid && validator.element(this);
			});
			return valid;
		}
	},
	// attributes: space seperated list of attributes to retrieve and remove
	removeAttrs: function( attributes ) {
		var result = {},
			$element = this;
		$.each(attributes.split(/\s/), function( index, value ) {
			result[value] = $element.attr(value);
			$element.removeAttr(value);
		});
		return result;
	},
	// http://docs.jquery.com/Plugins/Validation/rules
	rules: function( command, argument ) {
		var element = this[0];

		if ( command ) {
			var settings = $.data(element.form, "validator").settings;
			var staticRules = settings.rules;
			var existingRules = $.validator.staticRules(element);
			switch(command) {
			case "add":
				$.extend(existingRules, $.validator.normalizeRule(argument));
				// remove messages from rules, but allow them to be set separetely
				delete existingRules.messages;
				staticRules[element.name] = existingRules;
				if ( argument.messages ) {
					settings.messages[element.name] = $.extend( settings.messages[element.name], argument.messages );
				}
				break;
			case "remove":
				if ( !argument ) {
					delete staticRules[element.name];
					return existingRules;
				}
				var filtered = {};
				$.each(argument.split(/\s/), function( index, method ) {
					filtered[method] = existingRules[method];
					delete existingRules[method];
				});
				return filtered;
			}
		}

		var data = $.validator.normalizeRules(
		$.extend(
			{},
			$.validator.classRules(element),
			$.validator.attributeRules(element),
			$.validator.dataRules(element),
			$.validator.staticRules(element)
		), element);

		// make sure required is at front
		if ( data.required ) {
			var param = data.required;
			delete data.required;
			data = $.extend({required: param}, data);
		}

		return data;
	}
});

// Custom selectors
$.extend($.expr[":"], {
	// http://docs.jquery.com/Plugins/Validation/blank
	blank: function( a ) { return !$.trim("" + $(a).val()); },
	// http://docs.jquery.com/Plugins/Validation/filled
	filled: function( a ) { return !!$.trim("" + $(a).val()); },
	// http://docs.jquery.com/Plugins/Validation/unchecked
	unchecked: function( a ) { return !$(a).prop("checked"); }
});

// constructor for validator
$.validator = function( options, form ) {
	this.settings = $.extend( true, {}, $.validator.defaults, options );
	this.currentForm = form;
	this.init();
};

$.validator.format = function( source, params ) {
	if ( arguments.length === 1 ) {
		return function() {
			var args = $.makeArray(arguments);
			args.unshift(source);
			return $.validator.format.apply( this, args );
		};
	}
	if ( arguments.length > 2 && params.constructor !== Array  ) {
		params = $.makeArray(arguments).slice(1);
	}
	if ( params.constructor !== Array ) {
		params = [ params ];
	}
	$.each(params, function( i, n ) {
		source = source.replace( new RegExp("\\{" + i + "\\}", "g"), function() {
			return n;
		});
	});
	return source;
};

$.extend($.validator, {

	defaults: {
		messages: {},
		groups: {},
		rules: {},
		errorClass: "input-error",
		validClass: "",
		errorElement: "div",
		focusInvalid: true,
		errorContainer: $([]),
		errorLabelContainer: $([]),
		onsubmit: true,
		ignore: ":hidden",
		ignoreTitle: false,
		onfocusin: function( element, event ) {
			this.lastActive = element;

			// hide error label and remove error class on focus if enabled
			if ( this.settings.focusCleanup && !this.blockFocusCleanup ) {
				if ( this.settings.unhighlight ) {
					this.settings.unhighlight.call( this, element, this.settings.errorClass, this.settings.validClass );
				}
				this.addWrapper(this.errorsFor(element)).hide();
			}
		},
		onfocusout: function( element, event ) {
			if ( !this.checkable(element) && (element.name in this.submitted || !this.optional(element)) ) {
				this.element(element);
			}
		},
		onkeyup: function( element, event ) {
			if ( event.which === 9 && this.elementValue(element) === "" ) {
				return;
			} else if ( element.name in this.submitted || element === this.lastElement ) {
				this.element(element);
			}
		},
		onclick: function( element, event ) {
			// click on selects, radiobuttons and checkboxes
			if ( element.name in this.submitted ) {
				this.element(element);
			}
			// or option elements, check parent select in that case
			else if ( element.parentNode.name in this.submitted ) {
				this.element(element.parentNode);
			}
		},
		highlight: function( element, errorClass, validClass ) {
			if ( element.type === "radio" ) {
				this.findByName(element.name).addClass(errorClass).removeClass(validClass);
			} else {
				$(element).parent("td").find("img").remove();
				$(element).addClass(errorClass);
				/*$(element).addClass(errorClass).removeClass(validClass);*/
			}
		},
		unhighlight: function( element, errorClass, validClass ) {
			if ( element.type === "radio" ) {
				this.findByName(element.name).removeClass(errorClass).addClass(validClass);
			} else {
				/*$(element).removeClass(errorClass).addClass(validClass);*/
				$(element).removeClass(errorClass);
				if($(element).parent().has("img").length>0){
				}
				else{
					$(element).parent("td").find("div").html("&nbsp;");
					$(element).after("<img src=\"../library/images/icons/correct-input.png\" alt=\"valid\" class=\"valid-input\" />");
				}
			}
		}
	},

	// http://docs.jquery.com/Plugins/Validation/Validator/setDefaults
	setDefaults: function( settings ) {
		$.extend( $.validator.defaults, settings );
	},

	messages: {
		required: "This field is required.",
		remote: "Please fix this field.",
		email: "Please enter a valid email address.",
		url: "Please enter a valid URL.",
		date: "Please enter a valid date.",
		dateISO: "Please enter a valid date (ISO).",
		number: "Please enter a valid number.",
		digits: "Please enter only digits.",
		creditcard: "Please enter a valid credit card number.",
		equalTo: "Please enter the same value again.",
		maxlength: $.validator.format("Please enter no more than {0} characters."),
		minlength: $.validator.format("Please enter at least {0} characters."),
		rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
		range: $.validator.format("Please enter a value between {0} and {1}."),
		max: $.validator.format("Please enter a value less than or equal to {0}."),
		min: $.validator.format("Please enter a value greater than or equal to {0}.")
	},

	autoCreateRanges: false,

	prototype: {

		init: function() {
			this.labelContainer = $(this.settings.errorLabelContainer);
			this.errorContext = this.labelContainer.length && this.labelContainer || $(this.currentForm);
			this.containers = $(this.settings.errorContainer).add( this.settings.errorLabelContainer );
			this.submitted = {};
			this.valueCache = {};
			this.pendingRequest = 0;
			this.pending = {};
			this.invalid = {};
			this.reset();

			var groups = (this.groups = {});
			$.each(this.settings.groups, function( key, value ) {
				if ( typeof value === "string" ) {
					value = value.split(/\s/);
				}
				$.each(value, function( index, name ) {
					groups[name] = key;
				});
			});
			var rules = this.settings.rules;
			$.each(rules, function( key, value ) {
				rules[key] = $.validator.normalizeRule(value);
			});

			function delegate(event) {
				var validator = $.data(this[0].form, "validator"),
					eventType = "on" + event.type.replace(/^validate/, "");
				if ( validator.settings[eventType] ) {
					validator.settings[eventType].call(validator, this[0], event);
				}
			}
			$(this.currentForm)
				.validateDelegate(":text, [type='password'], [type='file'], select, textarea, " +
					"[type='number'], [type='search'] ,[type='tel'], [type='url'], " +
					"[type='email'], [type='datetime'], [type='date'], [type='month'], " +
					"[type='week'], [type='time'], [type='datetime-local'], " +
					"[type='range'], [type='color'] ",
					"focusin focusout keyup", delegate)
				.validateDelegate("[type='radio'], [type='checkbox'], select, option", "click", delegate);

			if ( this.settings.invalidHandler ) {
				$(this.currentForm).bind("invalid-form.validate", this.settings.invalidHandler);
			}
		},

		// http://docs.jquery.com/Plugins/Validation/Validator/form
		form: function() {
			this.checkForm();
			$.extend(this.submitted, this.errorMap);
			this.invalid = $.extend({}, this.errorMap);
			if ( !this.valid() ) {
				$(this.currentForm).triggerHandler("invalid-form", [this]);
			}
			this.showErrors();
			return this.valid();
		},

		checkForm: function() {
			this.prepareForm();
			for ( var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++ ) {
				this.check( elements[i] );
			}
			return this.valid();
		},

		// http://docs.jquery.com/Plugins/Validation/Validator/element
		element: function( element ) {
			element = this.validationTargetFor( this.clean( element ) );
			this.lastElement = element;
			this.prepareElement( element );
			this.currentElements = $(element);
			var result = this.check( element ) !== false;
			if ( result ) {
				delete this.invalid[element.name];
			} else {
				this.invalid[element.name] = true;
			}
			if ( !this.numberOfInvalids() ) {
				// Hide error containers on last error
				this.toHide = this.toHide.add( this.containers );
			}
			this.showErrors();
			return result;
		},

		// http://docs.jquery.com/Plugins/Validation/Validator/showErrors
		showErrors: function( errors ) {
			if ( errors ) {
				// add items to error list and map
				$.extend( this.errorMap, errors );
				this.errorList = [];
				for ( var name in errors ) {
					this.errorList.push({
						message: errors[name],
						element: this.findByName(name)[0]
					});
				}
				// remove items from success list
				this.successList = $.grep( this.successList, function( element ) {
					return !(element.name in errors);
				});
			}
			if ( this.settings.showErrors ) {
				this.settings.showErrors.call( this, this.errorMap, this.errorList );
			} else {
				this.defaultShowErrors();
			}
		},

		// http://docs.jquery.com/Plugins/Validation/Validator/resetForm
		resetForm: function() {
			if ( $.fn.resetForm ) {
				$(this.currentForm).resetForm();
			}
			this.submitted = {};
			this.lastElement = null;
			this.prepareForm();
			this.hideErrors();
			this.elements().removeClass( this.settings.errorClass ).removeData( "previousValue" );
		},

		numberOfInvalids: function() {
			return this.objectLength(this.invalid);
		},

		objectLength: function( obj ) {
			var count = 0;
			for ( var i in obj ) {
				count++;
			}
			return count;
		},

		hideErrors: function() {
			this.addWrapper( this.toHide ).hide();
		},

		valid: function() {
			return this.size() === 0;
		},

		size: function() {
			return this.errorList.length;
		},

		focusInvalid: function() {
			if ( this.settings.focusInvalid ) {
				try {
					$(this.findLastActive() || this.errorList.length && this.errorList[0].element || [])
					.filter(":visible")
					.focus()
					// manually trigger focusin event; without it, focusin handler isn't called, findLastActive won't have anything to find
					.trigger("focusin");
				} catch(e) {
					// ignore IE throwing errors when focusing hidden elements
				}
			}
		},

		findLastActive: function() {
			var lastActive = this.lastActive;
			return lastActive && $.grep(this.errorList, function( n ) {
				return n.element.name === lastActive.name;
			}).length === 1 && lastActive;
		},

		elements: function() {
			var validator = this,
				rulesCache = {};

			// select all valid inputs inside the form (no submit or reset buttons)
			return $(this.currentForm)
			.find("input, select, textarea")
			.not(":submit, :reset, :image, [disabled]")
			.not( this.settings.ignore )
			.filter(function() {
				if ( !this.name && validator.settings.debug && window.console ) {
					console.error( "%o has no name assigned", this);
				}

				// select only the first element for each name, and only those with rules specified
				if ( this.name in rulesCache || !validator.objectLength($(this).rules()) ) {
					return false;
				}

				rulesCache[this.name] = true;
				return true;
			});
		},

		clean: function( selector ) {
			return $(selector)[0];
		},

		errors: function() {
			var errorClass = this.settings.errorClass.replace(" ", ".");
			return $(this.settings.errorElement + "." + errorClass, this.errorContext);
		},

		reset: function() {
			this.successList = [];
			this.errorList = [];
			this.errorMap = {};
			this.toShow = $([]);
			this.toHide = $([]);
			this.currentElements = $([]);
		},

		prepareForm: function() {
			this.reset();
			this.toHide = this.errors().add( this.containers );
		},

		prepareElement: function( element ) {
			this.reset();
			this.toHide = this.errorsFor(element);
		},

		elementValue: function( element ) {
			var type = $(element).attr("type"),
				val = $(element).val();

			if ( type === "radio" || type === "checkbox" ) {
				return $("input[name='" + $(element).attr("name") + "']:checked").val();
			}

			if ( typeof val === "string" ) {
				return val.replace(/\r/g, "");
			}
			return val;
		},

		check: function( element ) {
			element = this.validationTargetFor( this.clean( element ) );

			var rules = $(element).rules();
			var dependencyMismatch = false;
			var val = this.elementValue(element);
			var result;

			for (var method in rules ) {
				var rule = { method: method, parameters: rules[method] };
				try {

					result = $.validator.methods[method].call( this, val, element, rule.parameters );

					// if a method indicates that the field is optional and therefore valid,
					// don't mark it as valid when there are no other rules
					if ( result === "dependency-mismatch" ) {
						dependencyMismatch = true;
						continue;
					}
					dependencyMismatch = false;

					if ( result === "pending" ) {
						this.toHide = this.toHide.not( this.errorsFor(element) );
						return;
					}

					if ( !result ) {
						this.formatAndAdd( element, rule );
						return false;
					}
				} catch(e) {
					if ( this.settings.debug && window.console ) {
						console.log( "Exception occurred when checking element " + element.id + ", check the '" + rule.method + "' method.", e );
					}
					throw e;
				}
			}
			if ( dependencyMismatch ) {
				return;
			}
			if ( this.objectLength(rules) ) {
				this.successList.push(element);
			}
			return true;
		},

		// return the custom message for the given element and validation method
		// specified in the element's HTML5 data attribute
		customDataMessage: function( element, method ) {
			return $(element).data("msg-" + method.toLowerCase()) || (element.attributes && $(element).attr("data-msg-" + method.toLowerCase()));
		},

		// return the custom message for the given element name and validation method
		customMessage: function( name, method ) {
			var m = this.settings.messages[name];
			return m && (m.constructor === String ? m : m[method]);
		},

		// return the first defined argument, allowing empty strings
		findDefined: function() {
			for(var i = 0; i < arguments.length; i++) {
				if ( arguments[i] !== undefined ) {
					return arguments[i];
				}
			}
			return undefined;
		},

		defaultMessage: function( element, method ) {
			return this.findDefined(
				this.customMessage( element.name, method ),
				this.customDataMessage( element, method ),
				// title is never undefined, so handle empty string as undefined
				!this.settings.ignoreTitle && element.title || undefined,
				$.validator.messages[method],
				"<strong>Warning: No message defined for " + element.name + "</strong>"
			);
		},

		formatAndAdd: function( element, rule ) {
			var message = this.defaultMessage( element, rule.method ),
				theregex = /\$?\{(\d+)\}/g;
			if ( typeof message === "function" ) {
				message = message.call(this, rule.parameters, element);
			} else if (theregex.test(message)) {
				message = $.validator.format(message.replace(theregex, "{$1}"), rule.parameters);
			}
			this.errorList.push({
				message: message,
				element: element
			});

			this.errorMap[element.name] = message;
			this.submitted[element.name] = message;
		},

		addWrapper: function( toToggle ) {
			if ( this.settings.wrapper ) {
				toToggle = toToggle.add( toToggle.parent( this.settings.wrapper ) );
			}
			return toToggle;
		},

		defaultShowErrors: function() {
			var i, elements;
			for ( i = 0; this.errorList[i]; i++ ) {
				var error = this.errorList[i];
				if ( this.settings.highlight ) {
					this.settings.highlight.call( this, error.element, this.settings.errorClass, this.settings.validClass );
				}
				this.showLabel( error.element, error.message );
			}
			if ( this.errorList.length ) {
				this.toShow = this.toShow.add( this.containers );
			}
			if ( this.settings.success ) {
				for ( i = 0; this.successList[i]; i++ ) {
					this.showLabel( this.successList[i] );
				}
			}
			if ( this.settings.unhighlight ) {
				for ( i = 0, elements = this.validElements(); elements[i]; i++ ) {
					this.settings.unhighlight.call( this, elements[i], this.settings.errorClass, this.settings.validClass );
				}
			}
			this.toHide = this.toHide.not( this.toShow );
			this.hideErrors();
			this.addWrapper( this.toShow ).show();
		},

		validElements: function() {
			return this.currentElements.not(this.invalidElements());
		},

		invalidElements: function() {
			return $(this.errorList).map(function() {
				return this.element;
			});
		},

		showLabel: function( element, message ) {
			var label = this.errorsFor( element );
			if ( label.length ) {
				// refresh error/success class
				label.removeClass( this.settings.validClass ).addClass( this.settings.errorClass );
				// replace message on existing label
				label.html(message);
			} else {
				// create label
				label = $("<" + this.settings.errorElement + ">")
					.attr("for", this.idOrName(element))
					.addClass(this.settings.errorClass)
					.html(message || "");
				if ( this.settings.wrapper ) {
					// make sure the element is visible, even in IE
					// actually showing the wrapped element is handled elsewhere
					label = label.hide().show().wrap("<" + this.settings.wrapper + "/>").parent();
				}
				if ( !this.labelContainer.append(label).length ) {
					if ( this.settings.errorPlacement ) {
						this.settings.errorPlacement(label, $(element) );
					} else {
						//label.insertAfter(element);
						$(element).parents("td").find("div").html(label);
					}
				}
			}
			if ( !message && this.settings.success ) {
				label.text("");
				if ( typeof this.settings.success === "string" ) {
					label.addClass( this.settings.success );
				} else {
					this.settings.success( label, element );
				}
			}
			this.toShow = this.toShow.add(label);
		},

		errorsFor: function( element ) {
			var name = this.idOrName(element);
			return this.errors().filter(function() {
				return $(this).attr("for") === name;
			});
		},

		idOrName: function( element ) {
			return this.groups[element.name] || (this.checkable(element) ? element.name : element.id || element.name);
		},

		validationTargetFor: function( element ) {
			// if radio/checkbox, validate first element in group instead
			if ( this.checkable(element) ) {
				element = this.findByName( element.name ).not(this.settings.ignore)[0];
			}
			return element;
		},

		checkable: function( element ) {
			return (/radio|checkbox/i).test(element.type);
		},

		findByName: function( name ) {
			return $(this.currentForm).find("[name='" + name + "']");
		},

		getLength: function( value, element ) {
			switch( element.nodeName.toLowerCase() ) {
			case "select":
				return $("option:selected", element).length;
			case "input":
				if ( this.checkable( element) ) {
					return this.findByName(element.name).filter(":checked").length;
				}
			}
			return value.length;
		},

		depend: function( param, element ) {
			return this.dependTypes[typeof param] ? this.dependTypes[typeof param](param, element) : true;
		},

		dependTypes: {
			"boolean": function( param, element ) {
				return param;
			},
			"string": function( param, element ) {
				return !!$(param, element.form).length;
			},
			"function": function( param, element ) {
				return param(element);
			}
		},

		optional: function( element ) {
			var val = this.elementValue(element);
			return !$.validator.methods.required.call(this, val, element) && "dependency-mismatch";
		},

		startRequest: function( element ) {
			if ( !this.pending[element.name] ) {
				this.pendingRequest++;
				this.pending[element.name] = true;
			}
		},

		stopRequest: function( element, valid ) {
			this.pendingRequest--;
			// sometimes synchronization fails, make sure pendingRequest is never < 0
			if ( this.pendingRequest < 0 ) {
				this.pendingRequest = 0;
			}
			delete this.pending[element.name];
			if ( valid && this.pendingRequest === 0 && this.formSubmitted && this.form() ) {
				$(this.currentForm).submit();
				this.formSubmitted = false;
			} else if (!valid && this.pendingRequest === 0 && this.formSubmitted) {
				$(this.currentForm).triggerHandler("invalid-form", [this]);
				this.formSubmitted = false;
			}
		},

		previousValue: function( element ) {
			return $.data(element, "previousValue") || $.data(element, "previousValue", {
				old: null,
				valid: true,
				message: this.defaultMessage( element, "remote" )
			});
		}

	},

	classRuleSettings: {
		required: {required: true},
		email: {email: true},
		url: {url: true},
		date: {date: true},
		dateISO: {dateISO: true},
		number: {number: true},
		digits: {digits: true},
		creditcard: {creditcard: true}
	},

	addClassRules: function( className, rules ) {
		if ( className.constructor === String ) {
			this.classRuleSettings[className] = rules;
		} else {
			$.extend(this.classRuleSettings, className);
		}
	},

	classRules: function( element ) {
		var rules = {};
		var classes = $(element).attr("class");
		if ( classes ) {
			$.each(classes.split(" "), function() {
				if ( this in $.validator.classRuleSettings ) {
					$.extend(rules, $.validator.classRuleSettings[this]);
				}
			});
		}
		return rules;
	},

	attributeRules: function( element ) {
		var rules = {};
		var $element = $(element);
		var type = $element[0].getAttribute("type");

		for (var method in $.validator.methods) {
			var value;

			// support for <input required> in both html5 and older browsers
			if ( method === "required" ) {
				value = $element.get(0).getAttribute(method);
				// Some browsers return an empty string for the required attribute
				// and non-HTML5 browsers might have required="" markup
				if ( value === "" ) {
					value = true;
				}
				// force non-HTML5 browsers to return bool
				value = !!value;
			} else {
				value = $element.attr(method);
			}

			// convert the value to a number for number inputs, and for text for backwards compability
			// allows type="date" and others to be compared as strings
			if ( /min|max/.test( method ) && ( type === null || /number|range|text/.test( type ) ) ) {
				value = Number(value);
			}

			if ( value ) {
				rules[method] = value;
			} else if ( type === method && type !== 'range' ) {
				// exception: the jquery validate 'range' method
				// does not test for the html5 'range' type
				rules[method] = true;
			}
		}

		// maxlength may be returned as -1, 2147483647 (IE) and 524288 (safari) for text inputs
		if ( rules.maxlength && /-1|2147483647|524288/.test(rules.maxlength) ) {
			delete rules.maxlength;
		}

		return rules;
	},

	dataRules: function( element ) {
		var method, value,
			rules = {}, $element = $(element);
		for (method in $.validator.methods) {
			value = $element.data("rule-" + method.toLowerCase());
			if ( value !== undefined ) {
				rules[method] = value;
			}
		}
		return rules;
	},

	staticRules: function( element ) {
		var rules = {};
		var validator = $.data(element.form, "validator");
		if ( validator.settings.rules ) {
			rules = $.validator.normalizeRule(validator.settings.rules[element.name]) || {};
		}
		return rules;
	},

	normalizeRules: function( rules, element ) {
		// handle dependency check
		$.each(rules, function( prop, val ) {
			// ignore rule when param is explicitly false, eg. required:false
			if ( val === false ) {
				delete rules[prop];
				return;
			}
			if ( val.param || val.depends ) {
				var keepRule = true;
				switch (typeof val.depends) {
				case "string":
					keepRule = !!$(val.depends, element.form).length;
					break;
				case "function":
					keepRule = val.depends.call(element, element);
					break;
				}
				if ( keepRule ) {
					rules[prop] = val.param !== undefined ? val.param : true;
				} else {
					delete rules[prop];
				}
			}
		});

		// evaluate parameters
		$.each(rules, function( rule, parameter ) {
			rules[rule] = $.isFunction(parameter) ? parameter(element) : parameter;
		});

		// clean number parameters
		$.each(['minlength', 'maxlength'], function() {
			if ( rules[this] ) {
				rules[this] = Number(rules[this]);
			}
		});
		$.each(['rangelength', 'range'], function() {
			var parts;
			if ( rules[this] ) {
				if ( $.isArray(rules[this]) ) {
					rules[this] = [Number(rules[this][0]), Number(rules[this][1])];
				} else if ( typeof rules[this] === "string" ) {
					parts = rules[this].split(/[\s,]+/);
					rules[this] = [Number(parts[0]), Number(parts[1])];
				}
			}
		});

		if ( $.validator.autoCreateRanges ) {
			// auto-create ranges
			if ( rules.min && rules.max ) {
				rules.range = [rules.min, rules.max];
				delete rules.min;
				delete rules.max;
			}
			if ( rules.minlength && rules.maxlength ) {
				rules.rangelength = [rules.minlength, rules.maxlength];
				delete rules.minlength;
				delete rules.maxlength;
			}
		}

		return rules;
	},

	// Converts a simple string to a {string: true} rule, e.g., "required" to {required:true}
	normalizeRule: function( data ) {
		if ( typeof data === "string" ) {
			var transformed = {};
			$.each(data.split(/\s/), function() {
				transformed[this] = true;
			});
			data = transformed;
		}
		return data;
	},

	// http://docs.jquery.com/Plugins/Validation/Validator/addMethod
	addMethod: function( name, method, message ) {
		$.validator.methods[name] = method;
		$.validator.messages[name] = message !== undefined ? message : $.validator.messages[name];
		if ( method.length < 3 ) {
			$.validator.addClassRules(name, $.validator.normalizeRule(name));
		}
	},

	methods: {

		// http://docs.jquery.com/Plugins/Validation/Methods/required
		required: function( value, element, param ) {
			// check if dependency is met
			if ( !this.depend(param, element) ) {
				return "dependency-mismatch";
			}
			if ( element.nodeName.toLowerCase() === "select" ) {
				// could be an array for select-multiple or a string, both are fine this way
				var val = $(element).val();
				return val && val.length > 0;
			}
			if ( this.checkable(element) ) {
				return this.getLength(value, element) > 0;
			}
			return $.trim(value).length > 0;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/email
		email: function( value, element ) {
			// contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
			return this.optional(element) || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(value);
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/url
		url: function( value, element ) {
			// contributed by Scott Gonzalez: http://projects.scottsplayground.com/iri/
			return this.optional(element) || /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/date
		date: function( value, element ) {
			return this.optional(element) || !/Invalid|NaN/.test(new Date(value).toString());
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/dateISO
		dateISO: function( value, element ) {
			return this.optional(element) || /^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/.test(value);
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/number
		number: function( value, element ) {
			return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/digits
		digits: function( value, element ) {
			return this.optional(element) || /^\d+$/.test(value);
		},

		passwordStrength: function( value, element ) {
			return this.optional(element) || /^\d+$/.test(value);
		},
		// http://docs.jquery.com/Plugins/Validation/Methods/creditcard
		// based on http://en.wikipedia.org/wiki/Luhn
		creditcard: function( value, element ) {
			if ( this.optional(element) ) {
				return "dependency-mismatch";
			}
			// accept only spaces, digits and dashes
			if ( /[^0-9 \-]+/.test(value) ) {
				return false;
			}
			var nCheck = 0,
				nDigit = 0,
				bEven = false;

			value = value.replace(/\D/g, "");

			for (var n = value.length - 1; n >= 0; n--) {
				var cDigit = value.charAt(n);
				nDigit = parseInt(cDigit, 10);
				if ( bEven ) {
					if ( (nDigit *= 2) > 9 ) {
						nDigit -= 9;
					}
				}
				nCheck += nDigit;
				bEven = !bEven;
			}

			return (nCheck % 10) === 0;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/minlength
		minlength: function( value, element, param ) {
			var length = $.isArray( value ) ? value.length : this.getLength($.trim(value), element);
			return this.optional(element) || length >= param;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/maxlength
		maxlength: function( value, element, param ) {
			var length = $.isArray( value ) ? value.length : this.getLength($.trim(value), element);
			return this.optional(element) || length <= param;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/rangelength
		rangelength: function( value, element, param ) {
			var length = $.isArray( value ) ? value.length : this.getLength($.trim(value), element);
			return this.optional(element) || ( length >= param[0] && length <= param[1] );
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/min
		min: function( value, element, param ) {
			return this.optional(element) || value >= param;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/max
		max: function( value, element, param ) {
			return this.optional(element) || value <= param;
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/range
		range: function( value, element, param ) {
			return this.optional(element) || ( value >= param[0] && value <= param[1] );
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/equalTo
		equalTo: function( value, element, param ) {
			// bind to the blur event of the target in order to revalidate whenever the target field is updated
			// TODO find a way to bind the event just once, avoiding the unbind-rebind overhead
			var target = $(param);
			if ( this.settings.onfocusout ) {
				target.unbind(".validate-equalTo").bind("blur.validate-equalTo", function() {
					$(element).valid();
				});
			}
			return value === target.val();
		},

		// http://docs.jquery.com/Plugins/Validation/Methods/remote
		remote: function( value, element, param ) {
			if ( this.optional(element) ) {
				return "dependency-mismatch";
			}

			var previous = this.previousValue(element);
			if (!this.settings.messages[element.name] ) {
				this.settings.messages[element.name] = {};
			}
			previous.originalMessage = this.settings.messages[element.name].remote;
			this.settings.messages[element.name].remote = previous.message;

			param = typeof param === "string" && {url:param} || param;

			if ( previous.old === value ) {
				return previous.valid;
			}

			previous.old = value;
			var validator = this;
			this.startRequest(element);
			var data = {};
			data[element.name] = value;
			$.ajax($.extend(true, {
				url: param,
				mode: "abort",
				port: "validate" + element.name,
				dataType: "json",
				data: data,
				success: function( response ) {
					validator.settings.messages[element.name].remote = previous.originalMessage;
					var valid = response === true || response === "true";
					if ( valid ) {
						var submitted = validator.formSubmitted;
						validator.prepareElement(element);
						validator.formSubmitted = submitted;
						validator.successList.push(element);
						delete validator.invalid[element.name];
						validator.showErrors();
					} else {
						var errors = {};
						var message = response || validator.defaultMessage( element, "remote" );
						errors[element.name] = previous.message = $.isFunction(message) ? message(value) : message;
						validator.invalid[element.name] = true;
						validator.showErrors(errors);
					}
					previous.valid = valid;
					validator.stopRequest(element, valid);
				}
			}, param));
			return "pending";
		}

	}

});

// deprecated, use $.validator.format instead
$.format = $.validator.format;

}(jQuery));

// ajax mode: abort
// usage: $.ajax({ mode: "abort"[, port: "uniqueport"]});
// if mode:"abort" is used, the previous request on that port (port can be undefined) is aborted via XMLHttpRequest.abort()
(function($) {
	var pendingRequests = {};
	// Use a prefilter if available (1.5+)
	if ( $.ajaxPrefilter ) {
		$.ajaxPrefilter(function( settings, _, xhr ) {
			var port = settings.port;
			if ( settings.mode === "abort" ) {
				if ( pendingRequests[port] ) {
					pendingRequests[port].abort();
				}
				pendingRequests[port] = xhr;
			}
		});
	} else {
		// Proxy ajax
		var ajax = $.ajax;
		$.ajax = function( settings ) {
			var mode = ( "mode" in settings ? settings : $.ajaxSettings ).mode,
				port = ( "port" in settings ? settings : $.ajaxSettings ).port;
			if ( mode === "abort" ) {
				if ( pendingRequests[port] ) {
					pendingRequests[port].abort();
				}
				pendingRequests[port] = ajax.apply(this, arguments);
				return pendingRequests[port];
			}
			return ajax.apply(this, arguments);
		};
	}
}(jQuery));

// provides delegate(type: String, delegate: Selector, handler: Callback) plugin for easier event delegation
// handler is only called when $(event.target).is(delegate), in the scope of the jquery-object for event.target
(function($) {
	$.extend($.fn, {
		validateDelegate: function( delegate, type, handler ) {
			return this.bind(type, function( event ) {
				var target = $(event.target);
				if ( target.is(delegate) ) {
					return handler.apply(target, arguments);
				}
			});
		}
	});
}(jQuery));
/*
 * jQuery validate.password plug-in 1.0
 *
 * http://bassistance.de/jquery-plugins/jquery-plugin-validate.password/
 *
 * Copyright (c) 2009 Jörn Zaefferer
 *
 * $Id$
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */
(function($) {
	
	var LOWER = /[a-z]/,
		UPPER = /[A-Z]/,
		DIGIT = /[0-9]/,
		DIGITS = /[0-9].*[0-9]/,
		SPECIAL = /[^a-zA-Z0-9]/,
		SAME = /^(.)\1+$/;
		
	function rating(rate, message) {
		return {
			rate: rate,
			messageKey: message
		};
	}
	
	function uncapitalize(str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	
	$.validator.passwordRating = function(password, username) {
		if (!password || password.length < 8)
			return rating(0, "too-short");
		if (username && password.toLowerCase().match(username.toLowerCase()))
			return rating(0, "similar-to-username");
		if (SAME.test(password))
			return rating(1, "very-weak");
		
		var lower = LOWER.test(password),
			upper = UPPER.test(uncapitalize(password)),
			digit = DIGIT.test(password),
			digits = DIGITS.test(password),
			special = SPECIAL.test(password);
		
		if (lower && upper && digit || lower && digits || upper && digits || special)
			return rating(4, "strong");
		if (lower && upper || lower && digit || upper && digit)
			return rating(3, "good");
		return rating(2, "weak");
	}
	
	$.validator.passwordRating.messages = {
		"similar-to-username": "Too similar to username",
		"too-short": "Too short",
		"very-weak": "Very weak",
		"weak": "Weak",
		"good": "Good",
		"strong": "Strong"
	}
	
	$.validator.addMethod("password", function(value, element, usernameField) {
		// use untrimmed value
		var password = element.value,
		// get username for comparison, if specified
			username = $(typeof usernameField != "boolean" ? usernameField : []);
			
		var rating = $.validator.passwordRating(password, username.val());
		// update message for this field
		
		var meter = $(".password-meter", element.form);
		
		meter.find(".password-meter-bar").removeClass().addClass("password-meter-bar").addClass("password-meter-" + rating.messageKey);
		meter.find(".password-meter-message")
		.removeClass()
		.addClass("password-meter-message")
		.addClass("password-meter-message-" + rating.messageKey)
		.text($.validator.passwordRating.messages[rating.messageKey]);
		// display process bar instead of error message
		
		return rating.rate > 2;
	}, "&nbsp;");
	// manually add class rule, to make username param optional
	$.validator.classRuleSettings.password = { password: true };
	
})(jQuery);
// usage: log('inside coolFunc', this, arguments);
// paulirish.com/2009/log-a-lightweight-wrapper-for-consolelog/
window.log = function f(){ log.history = log.history || []; log.history.push(arguments); if(this.console) { var args = arguments, newarr; args.callee = args.callee.caller; newarr = [].slice.call(args); if (typeof console.log === 'object') log.apply.call(console.log, console, newarr); else console.log.apply(console, newarr);}};

// make it safe to use console.log always
(function(a){function b(){}for(var c="assert,count,debug,dir,dirxml,error,exception,group,groupCollapsed,groupEnd,info,log,markTimeline,profile,profileEnd,time,timeEnd,trace,warn".split(","),d;!!(d=c.pop());){a[d]=a[d]||b;}})
(function(){try{console.log();return window.console;}catch(a){return (window.console={});}}());


// place any jQuery/helper plugins in here, instead of separate, slower script files.

/*!
 * hoverIntent r7 // 2013.03.11 // jQuery 1.9.1+
 * http://cherne.net/brian/resources/jquery.hoverIntent.html
 *
 * You may use hoverIntent under the terms of the MIT license.
 * Copyright 2007, 2013 Brian Cherne
 */
(function(e){e.fn.hoverIntent=function(t,n,r){var i={interval:100,sensitivity:7,timeout:0};if(typeof t==="object"){i=e.extend(i,t)}else if(e.isFunction(n)){i=e.extend(i,{over:t,out:n,selector:r})}else{i=e.extend(i,{over:t,out:t,selector:n})}var s,o,u,a;var f=function(e){s=e.pageX;o=e.pageY};var l=function(t,n){n.hoverIntent_t=clearTimeout(n.hoverIntent_t);if(Math.abs(u-s)+Math.abs(a-o)<i.sensitivity){e(n).off("mousemove.hoverIntent",f);n.hoverIntent_s=1;return i.over.apply(n,[t])}else{u=s;a=o;n.hoverIntent_t=setTimeout(function(){l(t,n)},i.interval)}};var c=function(e,t){t.hoverIntent_t=clearTimeout(t.hoverIntent_t);t.hoverIntent_s=0;return i.out.apply(t,[e])};var h=function(t){var n=jQuery.extend({},t);var r=this;if(r.hoverIntent_t){r.hoverIntent_t=clearTimeout(r.hoverIntent_t)}if(t.type=="mouseenter"){u=n.pageX;a=n.pageY;e(r).on("mousemove.hoverIntent",f);if(r.hoverIntent_s!=1){r.hoverIntent_t=setTimeout(function(){l(n,r)},i.interval)}}else{e(r).off("mousemove.hoverIntent",f);if(r.hoverIntent_s==1){r.hoverIntent_t=setTimeout(function(){c(n,r)},i.timeout)}}};return this.on({"mouseenter.hoverIntent":h,"mouseleave.hoverIntent":h},i.selector)}})(jQuery)


/**
 * jQuery Unveil
 * A very lightweight jQuery plugin to lazy load images
 * http://luis-almeida.github.com/unveil
 *
 * Licensed under the MIT license.
 * Copyright 2013 Lu�s Almeida
 * https://github.com/luis-almeida
 */

;(function($) {

  $.fn.unveil = function(threshold) {
    var $w = $(window),
        th = threshold || 0,
        retina = window.devicePixelRatio > 1,
        attrib = retina? "data-hisrc" : "data-lowbandwidth",
        images = this,
        loaded,
        inview,
        source;

    this.one("unveil", function() {
      source = this.getAttribute(attrib);
      source = source || this.getAttribute("data-lowbandwidth");
      if (source) this.setAttribute("src", source);
    });

    function unveil() {
      inview = images.filter(function() {
        var $e = $(this),
            wt = $w.scrollTop(),
            wb = wt + $w.height(),
            et = $e.offset().top,
            eb = et + $e.height();

        return eb >= wt - th && et <= wb + th;
      });

      loaded = inview.trigger("unveil");
      images = images.not(loaded);
    }

    $w.scroll(unveil);
    $w.resize(unveil);

    unveil();

    return this;

  };

})(jQuery);

$(document).ready(function() {
  /* set initial article position */
  // $('.home-page section:first-child article').css('top','40%');
  /* Scroll event handler */
  /* $(window).bind('scroll',function(e){
    parallaxScroll();
  }); */
  $(".main-content img").unveil(200);
});
/*!
 * jQuery Cookie Plugin v1.3.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2013 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD. Register as anonymous module.
		define(['jquery'], factory);
	} else {
		// Browser globals.
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function raw(s) {
		return s;
	}

	function decoded(s) {
		return decodeURIComponent(s.replace(pluses, ' '));
	}

	function converted(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}
		try {
			return config.json ? JSON.parse(s) : s;
		} catch(er) {}
	}

	var config = $.cookie = function (key, value, options) {

		// write
		if (value !== undefined) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setDate(t.getDate() + days);
			}

			value = config.json ? JSON.stringify(value) : String(value);

			return (document.cookie = [
				config.raw ? key : encodeURIComponent(key),
				'=',
				config.raw ? value : encodeURIComponent(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// read
		var decode = config.raw ? raw : decoded;
		var cookies = document.cookie.split('; ');
		var result = key ? undefined : {};
		for (var i = 0, l = cookies.length; i < l; i++) {
			var parts = cookies[i].split('=');
			var name = decode(parts.shift());
			var cookie = decode(parts.join('='));

			if (key && key === name) {
				result = converted(cookie);
				break;
			}

			if (!key) {
				result[name] = converted(cookie);
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		if ($.cookie(key) !== undefined) {
			// Must not alter options, thus extending a fresh object...
			$.cookie(key, '', $.extend({}, options, { expires: -1 }));
			return true;
		}
		return false;
	};

}));
/*!
 * form-validation.js
 * This file contains the Scripts required for form validation in client side
 * @require   jquery.js, jquery.validation.js
 *
 * @project   Panera Web Unification
 * @date      2013-06-17
 * @author    Vijay Thangavel, SapientNitro <vthangavel@sapient.com>
 * @licensor  Panera Bread
 * @site      panerabread.com
 *
 * Usage:
 * var formHandler = new FormHandler('#form_id', {})
 */
 function FormHandler(form_name_id, options)
 {
 	var options = options || {};
 	var that = this;

 	this.formObject = $(form_name_id);
 	if((this.formObject.data('form-handler') !== null) && (this.formObject.data('form-handler') !== undefined)){
 		return this.formObject.data('form-handler');
 	}
 	this.formObject.data('form-handler', this);
 	this.validatorObject = null;
 	this.ruleSet = options.ruleSet || {};
 	this.messageSet = options.messages || {};
	this.errorBlockSet = {};
	this.boolAjaxJsonErrorHandling = true;
	if(options.disableDefaultSubmit === undefined){
		options.disableDefaultSubmit = true;
	}
	if(options.disableDefaultAjaxErrorHandling !== undefined){
		this.boolAjaxJsonErrorHandling = options.disableDefaultAjaxErrorHandling;
	}
	this.disableDefaultSubmit = options.disableDefaultSubmit;
	this.handlerSuccess = options.success || null;
	this.handlerError = options.failure || null;

	this.debugEnabled = false;

	if(this.instanceCount == 0){
		setUp.call(this);
	}
	this.instanceCount++;
	this.hasMultiFields = false;
	this.isInputSpanWrap = true;//(true && $('#overlay-response:visible').length <= 0);

 	this.init = function(){
 		//Form not found, return false;
 		if(this.formObject.length <= 0){
 			this.log('Form Not found');
 			this.log(this.formParam);
 			return false;
 		}
	 	//QuickFix for missed visit & refer friends;
	 	this.formObjectHtmlId = this.formObject.prop('id').toString();

		if(this.formObjectHtmlId !== undefined && $.inArray(this.formObjectHtmlId, ['form_missed_visit', 'refer_friend_form']) >=0 ){
			this.isInputSpanWrap = false;
		}

 		//this.isInputSpanWrap = (this.formObject.find('.modal-mid-form').length <= 0);
 		this.hasMultiFields = (false && this.formObject.find('table textarea').length > 0);

 		if(this.disableDefaultSubmit){
	 		this.formObject.on('submit', function(event){
	 			event.preventDefault();
	 		});
	 	}
	 	this.fetchRulesAndMessages();

	 	if($.fn.isDefined(this.formObject.data('manual-validation'))){
	 		//this.formObject.trigger('manualVerification');
	 	}else{
	 		if(this.isInputSpanWrap){
	 			this.formObject.removeClass('sd-form-validation').addClass('sd-form-validation');
	 		}
	 		this.validateForm();
	 	}
	 	if(this.getAjaxActionUrl() !== undefined){
	 		this.formObject.off('submitHandlerStart').on('submitHandlerStart', function(event, thisCopy){
	 			thisCopy.formObject.addClass('form-ajax-submit-in-progress');
	 		});

	 		this.formObject.off('submitHandlerEnd').on('submitHandlerEnd', function(event, thisCopy){
	 			thisCopy.formObject.removeClass('form-ajax-submit-in-progress');
	 			thisCopy.formObject.css('cursor', 'default');
	 		});
	 	}
 	}

 	this.log = function(cont){
 		if(this.debugEnabled){
 			if(typeof console !== 'undefined'){
 				console.log(cont);
 			}else{
 				//alert(cont);
 			}
 		}
 	}

 	this.validateForm = function(){
		this.validatorObject = this.formObject.validate({ rules:this.ruleSet, messages: this.messageSet} );
		if(this.handlerError !== null){
			this.setInvalidHandler(this.handlerError);
		}

		if(this.handlerSuccess !== null){
			this.setSuccessHandler(this.handlerSuccess);
		}
 	};

	this.isValid = function(){
		return this.formObject.validate().valid();
	};

	this.setInvalidHandler = function(invalid_handler_callback){
		this.formObject.on('invalid-form.validate', invalid_handler_callback);
	}

	this.setSuccessHandler = function(success_callback){
		this.formObject.on('submitHandler', success_callback);
	}

 	this.submitHandler = function(form){
		if(this.disableDefaultSubmit){
			this.clearError();
			this.formObject.trigger('submitHandler', [this]);
		}else{
			form.submit();
		}
 	}

	this.resetForm = function(){
		this.clearErrorSuccess();
		this.formObject[0].reset();
	}

	this.clearErrorSuccess = function(){
		this.clearError();
		this.formObject.find('.form-success-column').removeClass('form-success-column');
	}
 	this.clearError = function(){
		this.formObject.find('.input-error').removeClass('input-error');
		this.formObject.find('.part-error').removeClass('part-error');
		this.formObject.find('div.inline-message').html('&nbsp;');
		this.hideGenericMessage();
	}

	this.showGenericSuccessMessage = function(){
		this.showSuccessMessage(this.formObject.data('msg-form-success'), this.formObject.data('msg-form-id'));
	}

	this.showGenericErrorMessage = function(){
		this.showErrorMessage(this.formObject.data('msg-form-error'), this.formObject.data('msg-form-id'));
	}

	this.hideGenericMessage = function(){
		this.hideFormMessage(this.formObject, this.formObject.data('msg-form-id'));
	}

	this.hideFormMessage = function(form_obj, msg_id){
		$.fn.formHideInlineMessage(form_obj, msg_id);
	}
	this.showErrorMessage = function(message, msg_id){
		$.fn.formShowInlineMessage(that.formObject, message, false, msg_id);
	}

	this.showSuccessMessage = function(message, msg_id){
		$.fn.formShowInlineMessage(that.formObject, message, true, msg_id);
	}

	this.addFieldError = function(element, message){
		this.validatorObject.showErrors({element: message});
	}

	this.addGroupFieldError = function(element, group_container, show_message){
		var message = '';
		if(show_message){
			message = $(group_container).data('group-error');
		}
		this.addRowMessage(group_container, message, false);
	}

 	this.fetchContainers = function(input_obj){
		var inputObj = $(input_obj);
		//if(inputObj.data('rule-none') !== undefined){return;}
		var input_name = inputObj.attr('name');
		//Field inline message holder containers;
		//Collect it so that we can avoid finding this on every events (keyup, blur, focus)
		var errObject = null;
		var addErrObjectAfter = null;
		var hideByDefault = false;
		var isInputBox = true;
		var isWrapRequired = true;
		var isGroupFields = false;
		var inputObjTagName = inputObj.prop('tagName');
		var inputObjAttrType = inputObj.attr('type');
		var tickMarkHolder = null;
		if(inputObjAttrType !== undefined){
			inputObjAttrType = inputObjAttrType.toString().toUpperCase();
		}

		if(inputObjAttrType === 'HIDDEN'){
			return;
		}
		if((inputObjAttrType === 'RADIO') || (inputObjAttrType === 'CHECKBOX')){
			if(inputObj.parent().prop('tagName') == 'LABEL'){
				errObject = inputObj.parent().next();
				addErrObjectAfter = inputObj.parent();
			}else{
				errObject = inputObj.parent();
				if(inputObj.next().hasClass('inline-message')){
					errObject = inputObj.next();
				}
				addErrObjectAfter = inputObj;
			}
			hideByDefault = true;
			isInputBox = false;
			if(inputObj.closest('.checkbox-label').length > 0){
				addErrObjectAfter = inputObj.parent().next();
				tickMarkHolder = inputObj.parent().next();
			}
		}else{
			errObject = inputObj.next();
			addErrObjectAfter = inputObj;
			if(inputObj.hasClass('form-select')){
				errObject = inputObj.parent().next();
				addErrObjectAfter = inputObj.parent();
			}
			if(inputObj.hasClass('ignore')){
				addErrObjectAfter = inputObj.parent();
			}
			if(inputObj.next().hasClass('help-text')){
				//addErrObjectAfter = inputObj.next();
			}
		}			
		
		//if(inputObj.prop('tagName') == 'SELECT'){
			if(inputObj.closest('.group-fields').length > 0){
				isWrapRequired = false;
				addErrObjectAfter = inputObj.closest('.group-fields');
				if(addErrObjectAfter.prop('tagName') === 'TR'){
					addErrObjectAfter = this.getFormErrorObject();
				}
				isGroupFields = true;
			}
		//}
		if(inputObj.prop('tagName') == 'TEXTAREA'){
			isInputBox = false;
		}
		if(inputObj.parent().prop('tagName') == 'TD'){
			inputObj.parent().addClass(inputObj.prop('tagName').toString().toLowerCase());
		}
		if(inputObj.parent().hasClass('form-select-label')){
			inputObj.parent().parent().addClass(inputObj.prop('tagName').toString().toLowerCase());
		}


		if($.fn.isFullObj(errObject) && errObject.hasClass('inline-message')){
			this.errorBlockSet[input_name] = errObject;
			if(typeof errObject.attr('id') === 'undefined'){
				errObject.attr('id', input_name + '_err_id');
			}
		}else{
			var idVal = '';
			if(typeof input_name === 'string' && input_name.length > 0){
				idVal = ' id="' + input_name + '_err_id" ';
			}
			if(addErrObjectAfter.find('div.inline-message').length <= 0){
				if(isGroupFields){
					addErrObjectAfter.append("<div class='inline-message' "+ idVal +">&nbsp;</div>");
					errObject = $(addErrObjectAfter).find('div.inline-message');
				}else{
					addErrObjectAfter.after("<div class='inline-message' "+ idVal +">&nbsp;</div>");
					errObject = $(addErrObjectAfter).next();
				}
			}
			this.errorBlockSet[input_name] = errObject;
		}

			if(this.isInputSpanWrap && isWrapRequired){
				var tickMarker = null;
				var spanWrapper = null;
				//var tickMarkIcon = '<img src="../library/css/images/correct-input.png"  class="sd-correct-input" alt="" />';
				var tickMarkIcon = '<img src="/etc/designs/panerabread/clientLibs/css/images/correct-input.png"  class="sd-correct-input" alt="" />';
				
				if(isInputBox){
					if(inputObj.parent().prop('tagName') == 'LABEL'){
						spanWrapper = inputObj.parent();
					}else{
						spanWrapper = inputObj;
					}
					spanWrapper.wrap('<span class="sd-validation"></span>');
					spanWrapper.after(tickMarkIcon);
					tickMarker = spanWrapper.parent();
				}else{
					if(inputObj.parent().prop('tagName') == 'LABEL'){
						inputObj.parent().wrap('<span class="sd-validation"/>');
						inputObj.parent().after(tickMarkIcon);
						tickMarker = inputObj.parent().parent();
					}else{
						tickMarker = inputObj.parent();
						if(tickMarkHolder != null){
							tickMarker = tickMarkHolder;
						}
					}
				}
				inputObj.data('tick-marker', tickMarker);
			}else{
				inputObj.data('tick-marker', inputObj.parent());
			}

		if(hideByDefault){
			errObject.hide();
		}
	}

 	this.fetchRulesAndMessages = function(){
		var that = this;
		var rule, message, thisObject, ruleName, ruleValue, dataGroup;
 		var inputElements = this.formObject.find('input,select,textarea').each(function(){
 			message = '';
 			thisObject = $(this);
			rule = thisObject.data('rule');
			ruleGroup = thisObject.data('rule-group');
			var isGroupRule = false;

			if($.fn.isDefined(ruleGroup)){
				var groupContainer = thisObject.closest('.group-fields');
				if(groupContainer.length > 0){
					message = $(groupContainer).data('group-error');
					if(groupContainer.prop('tagName') === 'TR'){
						groupContainer = this.getFormErrorObject();
					}
					that.errorBlockSet[thisObject.attr('name')] = groupContainer;
					isGroupRule = true;
				}
			}
 			if($.fn.isFullObj(rule)){
 				
 				rule = rule.split('|');
 				for(i = 0, n = rule.length; i < n; i++){
					var ruleFound = false, msgId = ruleValue = '';
					ruleName = rule[i];
					if(/^[a-z]*$/.test(ruleName.toLowerCase())){
						ruleFound = true;
						msgId = 'msg-'+ruleName;
						ruleValue = true;//Pre-defined validators like required, email, etc,
					}
					if(!ruleFound && ruleName.indexOf('maxlength') >= 0){
						ruleFound = true;
						ruleValue = ruleName.substring(10);
						ruleName = 'maxlength';
						msgId = 'msg-maxlength';
					}
					if(!ruleFound && ruleName.indexOf('minlength') >= 0){
						ruleFound = true;
						ruleValue = ruleName.substring(10);
						ruleName = 'minlength';
						msgId = 'msg-minlength';
					}
					if(!ruleFound && ruleName.indexOf('mandatorylength') >= 0){
						ruleFound = true;
						ruleValue = ruleName.substring(16);
						ruleName = 'mandatorylength';
						msgId = 'msg-mandatorylength';
					}
					if(!ruleFound && ruleName.indexOf('equalTo') >= 0){
						ruleFound = true;
						ruleValue = ruleName.substring(8, ruleName.indexOf(')'));
						ruleName = 'equalTo';
						msgId = 'msg-equalto';
					}
					if(!ruleFound && ruleName.indexOf('min') >= 0){
						ruleFound = true;
						ruleValue = parseInt(ruleName.substring(4));
						ruleName = 'min';
						msgId = 'msg-min';
					}
					if(!isGroupRule){
						message = thisObject.data(msgId);
					}

					if($.fn.isFullObj(message) || ruleGroup){
						that.addRuleAndMessage(thisObject, ruleName, ruleValue, message);
					}else{
						that.log('rule=' + rule[i]);
						that.log('msgId=' + msgId);
						that.log(msgId + ' data attribute is not found');
						that.log("\n\n");
					}
				}
			}else{
				thisObject.data('rule-none', 'none');
			}

			if(!ruleGroup){
				that.fetchContainers(thisObject);
			}
		});
	};

	this.addRuleAndMessage = function(input_obj, rule_name, rule_value, message){
		//this.formObject.validate(inputObj, {rules:rule, messages:message});
		var inputObj = $(input_obj);
		var ruleDef = {req:"required"};
		var ruleName = rule_name;
		var ruleValue = rule_value;
		this.log(ruleName);this.log(inputObj);
		if($.fn.isDefined(ruleDef[rule_name])){
			ruleName = ruleDef[rule_name];
		}

		var inputName = inputObj.attr('name');
		if(!$.fn.isDefined(this.ruleSet[inputName])){
			this.ruleSet[inputName] = {};
			this.messageSet[inputName] = {};
		}
		this.ruleSet[inputName][ruleName] = ruleValue;
		this.messageSet[inputName][ruleName] = message;
	}

	this.fieldShowMessage = function(input_obj, message, message_class){
		var cls = message_class || 'error';
		try{
			this.errorBlockSet[$(input_obj).attr('name')].show().html("<span class='" + cls + "'>" + message + "</span>");
		}catch(e){
			this.log(this.errorBlockSet);
			this.log($(input_obj).attr('name'))
			this.log(this.errorBlockSet[$(input_obj).attr('name')]);
		}
	}

	this.fieldHideMessage = function(input_obj){
		try{
			this.errorBlockSet[$(input_obj).attr('name')].html("&nbsp;");
		}catch(e){
			this.log(this.errorBlockSet);
			this.log($(input_obj).attr('name'))
			this.log(this.errorBlockSet[$(input_obj).attr('name')]);
		}
	}

	this.doQuickAjaxPostJson = function(response_handler){
		doAjaxSubmit(this.getAjaxActionUrl(), this.getSerializedData(), response_handler, {requestType: 'POST', dataType: 'json'});
	}

 	this.doAjaxSubmitGetJson = function(url_value, data_string, response_handler){
 		doAjaxSubmit(url_value, data_string, response_handler, {requestType: 'GET', dataType: 'json'});
 	}

	this.doAjaxSubmitPostJson = function(url_value, data_string, response_handler){
		doAjaxSubmit(url_value, data_string, response_handler, {requestType: 'POST', dataType: 'json'});
	}

	this.doAjaxSubmitPostText = function(url_value, data_string, response_handler){
		doAjaxSubmit(url_value, data_string, response_handler, {requestType: 'POST', dataType: 'text'});
	}
 	
 	this.getFormObject = function(){
 		return this.formObject;
 	}

	this.getAjaxActionUrl = function(){
		return this.formObject.data('ajax-action-url');
	}

	this.setSerializedData = function(data_serialized){
		this.datSerializedFormData = data_serialized;
	}

	this.getSerializedData = function(){
		var returnData = this.datSerializedFormData;
		if(returnData === undefined){
			returnData = this.formObject.serialize();
		}
		return returnData;
	}

	this.clearFetchedRules = function(){
		this.ruleSet = {};
		this.messageSet = {};
	}

	this.addManualRuleAndMessages = function(rule_set, message_set){
		this.ruleSet = rule_set;
		this.messageSet = message_set;
	}

 	this.getDataMsg = function(elem, data_suffix){
 		return ($(elem).data(data_suffix) || '');
 	}

	this.destroyObject = function(){
		for(var i in this){
			//delete this[i];
		}
	}

	this.enableValidation = function(){
		var fields = arguments;
		for(var i = 0, n = arguments.length; i < n; i++){
		//	this.fieldHideMessage($(fields[i]));
		$('#' + fields[i]).removeClass('ignore');
		}
	}

	this.disableValidation = function(){
		var fields = arguments;
		for(var i = 0, n = arguments.length; i < n; i++){
			this.fieldHideMessage($('#' + fields[i]));
			$('#' + fields[i]).addClass('ignore');
		}
	}
	this.triggerSubmitDone = function(){
		this.getFormObject().trigger('submitDone');
	}

	this.clearRowMessage = function(row){
		var rowObject = $(row);
		if(rowObject.length > 0){
			rowObject.find('.inline-message').remove();
			rowObject.find('.input-error').removeClass('input-error')
		}
	}
	this.addRowMessage = function(row, message, is_success){
		var rowObject = $(row);
		if(rowObject.length > 0){
			rowObject.removeClass('form-success-column');
			var no_errors = (rowObject.find('.input-error').length <= 0);
			var msgContainer = rowObject.find('.inline-message');
			var msg = '&nbsp;';
			if(message.length > 0){
				var cls = is_success?'success':'error';
				msg = '<span class="'+ cls + '">' + message + '</span>';
				if(msgContainer.length > 0){
					msgContainer.html(msg);
					msgContainer.removeClass('inline-message').addClass('inline-message is-group-message');
				}else{
					rowObject.append("<div class='inline-message is-group-message'>" + msg + "</div>");
				}
			}
			if(is_success && no_errors){
				rowObject.addClass('form-success-column');
				rowObject.find('.is-group-message').removeClass('is-group-message');
			}
			
		}
	}

	this.showGroupError = function(element, message){
		this.addRowMessage(this.errorBlockSet[$(element).attr('name')], message.html(), false);
	}

	this.clearGroupError = function(element){
		this.addRowMessage(this.errorBlockSet[$(element).attr('name')], '', true);
		$(element).removeClass('input-error');
	}

	this.getFormErrorObject = function(){
		var messageHolderId =  this.formObject.attr('id')+'_msg';
		var messageContainer = $('#' + messageHolderId);
		var classVal = 'message-box';
		var defBox = this.formObject.find('.message-box');
		if(defBox.length > 0){
			messageContainer = defBox;
		}

		if(messageContainer.length > 0 ){
			messageContainer.attr('class', classVal);
		}else{
			this.formObject.prepend($('<div id="' + messageHolderId +'" class="message-box">'));
			messageContainer = $('#' + messageHolderId);
		}
		return messageContainer;
	}

	this.ajaxJsonErrorHandling = function(){
		return this.boolAjaxJsonErrorHandling;
	}

	this.disableAjaxJsonErrorHandling = function(){
		this.boolAjaxJsonErrorHandling = false;
	}

 	function doAjaxSubmit(url_value, data_string, response_handler, options){
 			that.clearError();
 			var options = options || {};
 			var reqType = options.requestType || 'GET';
 			var dataType = options.dataType || 'json';
			var isJsonErrorHandlingEnabled = that.ajaxJsonErrorHandling();
 			var urlValue = url_value;
 			var dataString = data_string;
 			var responseHandler = response_handler;

			//if(typeof sd_devLocalEnv !== 'undefined'){urlValue = 'json/account.php';}
			$.ajax( {
				type: reqType,
				dataType: dataType,
				url: urlValue,
				data: dataString,
				cache:false,
				beforeSend:function(){
					that.formObject.trigger('submitHandlerStart', [that]);
					that.formObject.addClass('sd-form-submitting');
				},
				success: function( response ){
					if(isJsonErrorHandlingEnabled && (typeof response === 'object') && response.hasOwnProperty('errors')){
						var formErrorMsg = that.getFormErrorObject();
						var errMessage = errAttr = errConfigMessage = '';
					    formErrorMsg.html('');
					    $.each(response.errors, function (i, data) {
						errClass = 'error';
						errMessage = data.message;
						try{
							if(data['error-code'].length > 0){
								errAttr = 'error-' + data['error-code'];
								errConfigMessage= that.formObject.data(errAttr);
								if(typeof errConfigMessage === 'string' && errConfigMessage.length > 0){
									errMessage = errConfigMessage;
									errClass = 'error error-config';
								}
							}
						}catch(e){SiteUtils.log(e.message);}
					        formErrorMsg.append($('<p class="' + errClass + '"/>').text(errMessage).attr('id', 'err_' + data['error-code']));
					    });
					    formErrorMsg.removeClass('hide');
					}else{
						responseHandler(response);
					}
					that.formObject.trigger('submitHandlerEnd', [that]);
					that.formObject.removeClass('sd-form-submitting');
				}, 
				error: function() {
					that.clearErrorSuccess();
					var errMessage = 'Unable to process your request. Please try again';
					try{
						var formError = that.getFormObject().data('msg-error');
						if((formError !== undefined) && (typeof formError === 'string') && (formError.length > 0)){
							errMessage = formError;
						}
					}catch(e){SiteUtils.log(e.message);}
					that.showErrorMessage(errMessage);
					that.formObject.trigger('submitHandlerEnd', [that]);
					that.formObject.removeClass('sd-form-submitting');
				}
			});
		}

	function setUp(){
		// check if a formHandler for this form was already created
		/*
		var formHandler = $.data(this.formObject, "formHandler");
		if ( formHandler ) {
			this.log('Another constructor call');
			return formHandler;
		}
		$.data( this.formObject, "formHandler", this);
		*/
		//Custom validator for US phone of format (nnn nnn nnnn)
		$.validator.addMethod("usphone", function(value, element, options) {
			var formatIndex = [3, 3, 4];
			var groupContainer = $(element).closest('.group-fields');
			var errorHolder = that.getDataMsg(element, 'msg-pholder');
			var errorMessage = that.getDataMsg(element, 'msg-usphone');

			that.errorBlockSet[$(element).attr('name')] = {};
			that.errorBlockSet[$(element).attr('name')] = $(errorHolder);

			var inputs = groupContainer.find('input');
			inputs.removeClass('input-error');
			var validPhoneUS334Format = true;
			if(inputs.length === 3){
				var index = 0;
				inputs.each(function(){
					$(this).removeClass('part-error');
					var result = $(this).val().match(new  RegExp("^\\d{" + formatIndex[index++] + "}$"));
					if(result === null){
						$(this).addClass('part-error');
						validPhoneUS334Format = false;
					}
				});
			}
			groupContainer.addClass('form-success-column');
			that.fieldHideMessage(element);
			if(!validPhoneUS334Format){
				groupContainer.removeClass('form-success-column');
				that.fieldShowMessage(element, errorMessage, "error");
			}
			return validPhoneUS334Format;
		});
		
		//US Zip
		$.validator.addMethod("uszip", function(value, element, options){
			return this.optional(element) || /\d{5}-\d{4}$|^\d{5}$/.test(value);
		});
		//Name
		$.validator.addMethod("legalname", function(value, element, options){
			var regex = /^[a-zA-Z0-9.\s\-,’&()]+$/;
			return (regex.test(value));
		});

		//Age Range 10-15
		$.validator.addMethod("agerange", function(value, element, options){
			var isValid = false;
			var rangePattern = /\d{1,2}-\d{1,2}$/;
			if(rangePattern.test(value)){
				var nums = value.split('-');
				if(parseInt(nums[0], 10) <= parseInt(nums[1], 10)){
					isValid = true;
				}
			}else{
				isValid = /^\d+$/.test(value);
			}
			return this.optional(element) || isValid;
		});

		//Age Range 10-15
		$.validator.addMethod("usphoneformat", function(phone_number, element) {
			phone_number = phone_number.replace(/\s+/g, "");
			return this.optional(element) || phone_number.length > 9 &&
				phone_number.match(/^(\+?1-?)?(\([2-9]\d{2}\)|[2-9]\d{2})-?[2-9]\d{2}-?\d{4}$/);
		});

		$.validator.addMethod("filterwords", function(value, element, options){
			var restrictedWords = SiteUtils.CONSTANTS.RESTRICTED_WORDS;
			var stringToValidate = value.toString();
			var stringSplitted = stringToValidate.split(' ');
			var isAllowable = true;
			var pattern = new RegExp('^' + restrictedWords.join("$|^" ) + '$', "gi");
			for(var i = 0, n = stringSplitted.length; (isAllowable && i < n); i++){
				if($.trim('' + stringSplitted[i]).length > 0){
					isAllowable = !(pattern.test(stringSplitted[i]));
				}
			}
			if(isAllowable && (n > 1)){
				pattern = new RegExp('^' + SiteUtils.CONSTANTS.RESTRICTED_WORDS_MULTI.join("$|^" ) + '$', "gi");
				isAllowable = !(pattern.test(stringToValidate));
			}
			return isAllowable;
		});
		$.validator.addMethod("restrictemails", function(value, element, options){
			var stringToValidate = value.toString();
			var stringSplitted = stringToValidate.split('@');//email should be already validated.
			var isAllowable = true;
			if(stringSplitted.length == 2){
				var emailValue = stringToValidate;
				var emailName = stringSplitted[0];
				var emailDomain = stringSplitted[1];
				if(emailValue == 'noemail@panerabread.com' || emailDomain == 'panera.net'){
					isAllowable = false;
				}
			}
			return isAllowable;
		});

		//Email-Legal
		$.validator.addMethod("legalemail", function(value, element, options){
			var regex = /^.+@+.+\.+.+$/;
			return regex.test(value);
		});

		$.validator.addMethod("alphanumeric", function(value, element, options){
			var regex = /^[A-Za-z0-9]+$/i;
			return regex.test(value);
		});

		$.validator.addMethod("requestname", function(value, element, options){
			var regex = /^[A-Za-z0-9\s]+$/i;
			return regex.test(value);
		});

		$.validator.addMethod("alphabets", function(value, element, options){
			var regex = /^[A-Za-z]+$/i;
			return regex.test(value);
		});


		$.validator.addMethod("mandatorylength", function(value, element, param){
			var length = $.isArray( value ) ? value.length : this.getLength($.trim('' + value), element);
			return this.optional(element) || length == param;
		});

		$.validator.addMethod("xrequired", function(value, element, options){
			var regex = /^.+@+.+\.+.+$/;
			var returnStatus = regex.test(value)  || (value.length == 0);
			if(returnStatus){
				$(element).removeClass('ignore').addClass('ignore');
			}
			return returnStatus;
		});


		this.passwordStrength = function(input_obj){
			var ratingMessageData = {'0': 'info-pwd-weak', '1': 'info-pwd-medium', '2': 'info-pwd-strong'};
			var rating = 0;
			var pwd = $(input_obj).val();
			var pwdLen = pwd.length;
			var minVowels = 2;
			if(pwdLen < 10){ rating += 1; }
			if(pwdLen < 15){ rating += 1; minVowels = 4 }
			if(pwdLen < 20){ rating += 1; minVowels = 6	}

			rating += /[a-z]/.test(pwd)?2:0;// lower case
			rating += /[A-Z]/.test(pwd)?2:0;// UPPER CASE
			rating += /[0-9]/.test(pwd)?2:0;// Digits
			rating += /[$*_@()]/.test(pwd)?4:0;// Special chars

			for(var i = 0, totVowel = 0 ; i < pwdLen; i++){
				k=pwd[i];
				if(k = (/[aeiouAEIOU]/.test(k))){
					totVowel++;//Vowel count;
				}
			}
			if(totVowel <= minVowels){
				rating += 2;
			}
			var ratingStatus = 0;
			if(rating > 8){
				ratingStatus = 1;
			}
			if(rating > 12){
				ratingStatus = 2;
			}
			this.fieldShowMessage(input_obj, this.getDataMsg(input_obj, ratingMessageData[ratingStatus]), 'success');
			return ratingStatus;
		}

		//Over-ride password validator added by any other plugin.
		$.validator.addMethod("password", function(value, element){
			$(element).on('showStrength', function(){
				that.passwordStrength($(element));
			});
			return true;
		});

		//Require from group
		$.validator.addMethod("group-usphone", function(value, element, options) {
		    var numberRequired = options[0];
		    var selector = options[1];
		    //Look for our selector within the parent form
		    var validOrNot = $(selector, element.form).filter(function() {
		         // Each field is kept if it has a value
		         return $(this).val();
		         // Set to true if there are enough, else to false
		      }).length >= numberRequired;

		    if(!$(element).data('being_validated')) {
		    var fields = $(selector, element.form);
		    fields.data('being_validated', true);
		    // .valid() means "validate using all applicable rules" (which
		    // includes this one)
		    fields.valid();
		    fields.data('being_validated', false);
		    }
		    return validOrNot;
		});

		//$.validator.addClassRules('usphone_group', {usphone_group: [3,".phone-fields-3"]});

		$.validator.setDefaults({
			focusInvalid : ((navigator.userAgent.match(/iPad/i)) === null),
			errorPlacement: function( error, element ){
				var elementObj = $(element);
				if(elementObj.data('rule-group')){
					that.showGroupError(elementObj, error);
					return;
				}
				if(elementObj.hasClass('ignore') || (typeof elementObj.data('rule-none') !== 'undefined')){
					return;
				}
				if(element.attr('type') === 'radio'){
					$.fn.showInlineMessage(element.closest('form').get(), error.html(), true);
				}else{
					if(that.hasMultiFields){
						var tickIcons = elementObj.next('.input-success-icon');
						tickIcons.remove();
					}else{
						elementObj.data('tick-marker').removeClass('form-success-column');
					}
					var pHolder = element.data('msg-pholder');
					if($.fn.isDefined(pHolder)){
						$(pHolder).html(error.html());
							element.addClass('input-error');
					}else{
						//element.next().html(error.html());
						that.fieldShowMessage(element, error.html(), 'error');
					}
				}
			},
			unhighlight: function( element, errorClass, validClass ) {
				var elementObj = $(element);
				if(elementObj.data('rule-group')){
					that.clearGroupError(element);
					return;
				}
				if(elementObj.hasClass('ignore') || (typeof elementObj.data('rule-none') !== 'undefined')){
					return;
				}
				if ( element.type === "radio" ) {
					this.findByName(element.name).removeClass(errorClass).addClass(validClass);
				} else {
					if(that.hasMultiFields){
						var tickIcons = elementObj.next('.input-success-icon');
						if(tickIcons.length <= 0){
							elementObj.after('<img alt="" title="" class="input-success-icon" src="../library/css/images/correct-input.png" />');
						}
					}else{
						elementObj.data('tick-marker').addClass('form-success-column');
					}
				/*$(element).removeClass(errorClass).addClass(validClass);*/
					elementObj.removeClass(errorClass);
					var pHolder = elementObj.data('msg-pholder');
					if($.fn.isDefined(pHolder)){
						$(pHolder).empty();
					}
					that.fieldHideMessage(element);
					if($(element).data('trigger') !== undefined){
						$(element).triggerHandler(elementObj.data('trigger'));
					}
				}
			},
			submitHandler: function(form){
				that.submitHandler(form);
			}
		});

	}
 }

 FormHandler.prototype.instanceCount = 0;



(function($){
	$.fn.extend({
		isDefined: function(obj){
			return (obj !== undefined);
		},
		isFullObj: function(obj){
			return (($.fn.isDefined(obj)) && ($.fn.isDefined(obj.length)) && (obj.length > 0));
		}
	});

})(jQuery);
 
(function($) {
	$.extend($.fn, {
		formHideInlineMessage: function(form_obj, msg_id){
			var formObj = $(form_obj);
			var messageHolderId =  msg_id || formObj.attr('id')+'_msg';
			var messageContainer = $('#' + messageHolderId);
			if(messageContainer.length > 0){
				messageContainer.addClass('hide');
			}
		},
		/*
		* @desc Handles Messaging for Individual HTML FORM 
		* @param Object formObj{jQuery form object or Form Identifier}
		* @param String message {Message needs to be displayed in the form}
		* @param isError Boolean to indicate whether the form has invalid data or not.
		* @param msg_id Container in which the message needs to be shown. Defaulted to <FORM_ID>_msg;
		*/
		formShowInlineMessage: function( form_obj, message, is_success, msg_id){
			var formObj = $(form_obj);
			var classVal = is_success?'inlineMessage success': 'inlineMessage error';

			var messageHolderId =  msg_id || formObj.attr('id')+'_msg';
			var messageContainer = $('#' + messageHolderId);

			var defBox = formObj.find('.message-box p:first');
			if(defBox.length > 0){
				messageContainer = defBox;
				var classVal = is_success?'success': 'error';
			}
			
			if(messageContainer.length > 0 ){
				messageContainer.removeClass('hide');
				messageContainer.attr('class', classVal);
				messageContainer.html(message);
			}else{
				$('<p class="' + classVal + '" id="' + messageHolderId +'">' + message + '</p>').insertBefore(formObj.parent());
			}
		}

	});
}(jQuery));








/*
jQuery.validator.addMethod("math", function(value, element, params) {
 return this.optional(element) || value == params[0] + params[1];
}, jQuery.validator.format("Please enter the correct value for {0} + {1}"));
*/
/*!
 * site-utils.js
 * This file contains the Common Scripts required across the pages. 
 *
 * @project   Panera Web Unification
 * @date      2013-06-26
 * @author    Vijay Thangavel, SapientNitro <vthangavel@sapient.com>
 * @licensor  Panera Bread
 * @site      panerabread.com
 *
 */
SiteUtils = {};
SiteUtils.Is = {};
SiteUtils.App = {};
SiteUtils.Track = {};
SiteUtils.UI = {};
SiteUtils.CONSTANTS = {};
SiteUtils.PAGE = {};

SiteUtils.Is.defined = function(obj){
	return (obj !== undefined);
}

SiteUtils.Is.elemExisting = function(o){
	var obj = $(o);
	return ($(obj) && SiteUtils.Is.defined(obj.length) && obj.length > 0);
}

SiteUtils.Is.ipad = function(){
	var isDeviceIpad = ((navigator.userAgent.match(/iPad/i)) !== null);
	return isDeviceIpad;
};

SiteUtils.log = function(msg, line_no){
	//return;
	if((typeof sd_devLocalEnv === 'undefined')){return;}
	if(console !== undefined){
		console.log(msg);
		if(line_no !== undefined){
			console.log('at line #' + line_no);
		}
	}
};

SiteUtils.validateForm = function(form_name, options){
	var formObj = null;
	if(typeof form_name === 'string'){
		formObj = $('#' + form_name);
	}else{
		formObj = $(form_name);
	}

	if(formObj.length <= 0){
		SiteUtils.log(form_name + ' not found :: SiteUtils.validateForm');
	}
	var options = options || {};
	var formHandler = new FormHandler(formObj, options);
	formHandler.init();
	return formHandler;
};

SiteUtils.setupInlineForm = function(forms_place_holder, with_validator){
		var formsContainer = $(forms_place_holder);
		var initValidator = with_validator || false;
		if(formsContainer.length < 1){
			SiteUtils.log(forms_place_holder)
			return false;
		}

		formsContainer.find('a.edit-link').each(function(){
			//Gather edit link, fetch input box container id thru href attribute
			//Add listeners for edit link, form & cancel button.
			var inputBlock = $($(this).attr('href'));
			var inputBlockParent = inputBlock.parent();
			if(inputBlockParent.length <= 0){
				inputBlockParent = {addClass:function(){}, removeClass: function(){}};
			}else{
				if(typeof inputBlockParent.attr('id') === 'undefined'){
					try{
						inputBlockParent.attr('id', 'sd-par-' + ($(this).attr('href')).toString().replace('#', ''));
					}catch(e){SiteUtils.log(e.message);}
				}
			}
			var formBlock = inputBlock.find('form:first');
			var cancelButton = formBlock.find('a.cancel:first');
			var editButton = $(this);
			var messageHolderId = formBlock.attr('id')+'_msg';

			editButton.on('click', function(event, show){
				var showBlock = show || false;
				event.preventDefault();
				if(inputBlock.is(':visible') || showBlock){
					$(this).removeClass('link-active');
					inputBlock.removeClass('opened');
					inputBlockParent.removeClass('opened');
					inputBlock.slideUp(600);
					formBlock.triggerHandler('formClearError');
					SiteUtils.App.Forms.reStoreFormFieldStatus(formBlock, '.editable-field');
				}else{
					$(this).addClass('link-active');
					inputBlock.addClass('opened');
					inputBlockParent.addClass('opened');
					inputBlock.slideDown(800);
					$('#' + messageHolderId).remove();//Have to find a better way to handle messages.
					SiteUtils.App.Forms.storeFormFieldStatus(formBlock, '.editable-field');
				}
			}
			);
			formBlock.data('edit-layer', editButton);
			
			if(initValidator){
				formHandler = new FormHandler(formBlock);
				formHandler.init();
				//After validation formHandler will trigger 'submitHandler', by passing formHandler itself,
				//whereas in the below else part, it will pass the formObject only (Intentional, to avoid validation & creation of formHandler object)
				//
				formBlock.on('formClearError', function(event){
					var formHandler = $(this).data('form-handler');
					formHandler.clearError();
					formHandler.clearErrorSuccess();
				});
				SiteUtils.App.Forms.storeFormFieldStatus(formBlock, '.editable-field');

			}else{
				formBlock.on('submit', function(event){
					event.preventDefault();
					formBlock.triggerHandler('submitHandler', [formBlock]);
				});
			}
			formBlock.on('cancelSubmit', function(event){
				event.preventDefault();
				editButton.triggerHandler('click', [true]);
			});
			formBlock.on('submitDone', function(event){
				event.preventDefault();
				editButton.triggerHandler('click', [true]);
			});
			cancelButton.on('click', function(event){
				event.preventDefault();
				formBlock.triggerHandler('cancelSubmit');
			});
		});
};

SiteUtils.populateListBox = function(html_select_box, data_list, options){
	var options = options || {};
	var selBox 		= $(html_select_box);
	if(options.valueAsText === undefined){options.valueAsText = true};
	if(options.enableFinally === undefined){options.enableFinally = true};
	if(options.keepFirstOption === undefined){options.keepFirstOption = true};
	var valueAsText = options.valueAsText;
	var keepFirstOption = options.keepFirstOption;
	var firstOption = selBox.find('option:first');
	var enableFinally = options.enableFinally;
	//var callback = options.callback || function(){};
	selBox.empty();
	var val = txt = '';
	$.each(data_list, function(index, value){
		if(valueAsText){
			val = value; txt = value;
		}else{
			val = index; txt = value;
		}
		selBox.append($("<option />").val(val).text(txt));
	});
	if(keepFirstOption && firstOption.length > 0){
		selBox.prepend(firstOption);
	}
	if(enableFinally){
		selBox.removeAttr('disabled');
		selBox.removeClass('disabled');
		if(selBox.parent().hasClass('form-select-label')){
            selBox.parent().removeClass('disabled-dropdown');
        }
		if(selBox.hasClass('disabled-dropdown')){
			selBox.removeClass('disabled-dropdown');
		}
	}
	//callback();
}

SiteUtils.enableNumericInput = function(){
	var pattern = new RegExp('[^0-9]+', 'g');
	$('input.numeric-input').on('keyup', function(){
		this.value = this.value.toString().replace(pattern, '');
	});
}

SiteUtils.autoTab = function(group_id, options){
	var keys = [9, 16, 17, 18, 19, 20, 27, 33, 34, 35, 36, 37, 38, 39, 40, 45, 46, 144, 145];
		if(typeof group_id === 'object'){
			var groupContainer = $(group_id);
		}else{
			var groupContainer = $('#' + group_id);
		}
	if(groupContainer.length <= 0){
		SiteUtils.log(group_id + ' container not exist::SiteUtils.autoTab');
		return;
	}

	function acceptNum(str){
		var pattern = new RegExp('[^0-9]+', 'g');
		return str.replace(pattern, '');
	}

	var inputs = groupContainer.find('input');
	var total = inputs.length;
	inputs.each(function(index, obj){
		 var defaults = {prevInput:{focus:function(){}}, nextInput:{focus:function(){}}, maxlength:20};
		if(index > 0){
			defaults.prevInput = $(inputs[index - 1]);
		}
		if((index + 1) < total){
			defaults.nextInput = $(inputs[index + 1]);
		}
		defaults.maxlength = this.getAttribute('maxlength');
		$(this).data('defaults', defaults);

		$(this).on('keyup', function(event){
			this.value = acceptNum(this.value);
			var keyCode = event.which;
			if($.inArray(keyCode, keys) == -1){
				var defaults = $(this).data('defaults');
				if(this.value.length >= defaults['maxlength']){
					defaults['nextInput'].focus();
				}
				if(this.value.length == 0 && keyCode === 8){
					defaults['prevInput'].focus();
					try{$(this).removeClass('input-error').addClass('input-error');}catch(e){SiteUtils.log(e.message);}
				}
			}
		});
	});
}

SiteUtils.PrintControl = function(control_id, container_id){
	var containerId = container_id || '';
	if($('#' + control_id).length > 0){
		$('#' + control_id).on('click', function(event){
			event.preventDefault();
			window.print();
		});
	}
}

SiteUtils.parseNVP = function(nvp_string, options){
	var options = options || {};
	var parsedObject = options.defaults || {};
	var separator = options.separator || '&';
	var nvpString = nvp_string || '';
	nvpString.replace(
	new RegExp( "(\\w+)=([^,]*)", "gi" ),
		function($0, $1, $2){
			parsedObject[$1] = $2;
		}
	);
	return parsedObject;
}

SiteUtils.updateNVP = function(nvp_string, update_field, update_value){
	var nvpString = nvp_string || '';
	nvpString = nvpString.replace(
	new RegExp( "(\\w+)=([^,]*)", "gi" ),
		function($0, fieldName, fieldValue){
			if(fieldName === update_field){
				fieldValue = update_value;
			}
			return fieldName + '=' + fieldValue;
		}
	);
	return nvpString;
}

/*--- Define app specific functionalities under SiteUtils.App -->*/
SiteUtils.App = {
	boolPtPage: false,
	boolLoggedInUser: false,
	init: function(){
		this.boolPtPage = ($('#content-outer-wrapper').data('pt') === true);
		this.boolLoggedInUser = (getCookie(tokenCookieName) != null);
	},
	isUserLoggedIn: function(){
		return this.boolLoggedInUser;
	},
	isPtPage: function(){
		return this.boolPtPage;
	},
	loadPtPage: function() {
		if(!this.isPtPage()){return;}
        var pt = document.createElement('script'); pt.type = 'text/javascript'; pt.async = true;
        pt.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'static.punchtab.com/js/pt.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(pt, s);
    }
}
SiteUtils.App.Forms = {
	storeFormFieldStatus: function(form_id_object, identifier){
		var formObject = $(form_id_object);
		if(formObject.length > 0){
			var fieldSets = formObject.find(identifier);
			$.each(fieldSets, function(){
				if($.inArray($(this).attr('type'), ['radio', 'checkbox']) >= 0){
					$(this).data('original-value', $(this).is(':checked')?'XTRUE':'XFALSE');
				}else{
					$(this).data('original-value', $(this).val());
				}
			});
		}else{SiteUtils.log('Form not found in SiteUtils.App.Forms.storeFormFieldStatus');}
	},
	reStoreFormFieldStatus: function(form_id_object, identifier){
		var formObject = $(form_id_object);
		if(formObject.length > 0){
			var fieldSets = formObject.find(identifier);
			$.each(fieldSets, function(){
				if($.inArray($(this).attr('type'), ['radio', 'checkbox']) >=0 ){
					$(this).prop('checked', ($(this).data('original-value') === 'XTRUE'));
				}else{
					$(this).val($(this).data('original-value'));
				}
			});
		}else{SiteUtils.log('Form not found in SiteUtils.App.Forms.reStoreFormFieldStatus');}
	},
	updatePartialDobIfAny: function(formHandler, dob_field, dob_year_default){
		if(formHandler.getFormObject().data('has-optional-birthyear') == 'true'){
			var params = formHandler.getFormObject().serializeArray();
			for(var i = 0, n = params.length; i < n; i++){
				if(params[i].name === dob_field){
					params[i].value = dob_year_default;
				}
			}
			var newSerializedData = jQuery.param(params);
			formHandler.setSerializedData(newSerializedData);
		}
	},
	doValidateDob: function(form_handler){
		var formHandler = form_handler,
			dobContainer = formHandler.getFormObject().find('.dob-fields'),
			dobYearField = formHandler.getFormObject().find('#dob_year');
		if(dobContainer.length <= 0){
			SiteUtils.log('doValidateDob Error: DOB Container is not found');
			return true;
		}
		var selects = dobContainer.find('select'), boolDobIsValid = true, counts = 0;
		$.each(selects, function(index, element){
			counts += ($(element).val().toString().length > 0)?1 : 0;
		});
		var birthYearIsEmpty = (dobYearField.val().toString().length == 0);
		boolDobIsValid = ((counts == 0) || (counts == 3) || ((counts == 2) && birthYearIsEmpty));
		formHandler.getFormObject().data('has-optional-birthyear', ((counts == 2) && birthYearIsEmpty)?'true': 'false');
		if(!boolDobIsValid){
			formHandler.addGroupFieldError('dob_month', dobContainer, true);
		}else{
			formHandler.clearRowMessage(dobContainer);
		}
		return boolDobIsValid;//give proper naming in future.
	}


}

SiteUtils.App.Tooltip = {
	init: function(){
		$('.tooltip a').hover(function(){
                var descr = $(this).data('description');
                var closeButton = '';
                if(SiteUtils.Is.ipad()){
					closeButton = '<p class="closeTooltipButton"><a href="#" class="closeTooltipAnchor">Hide</a></p>';
                }
                $('<p class="tooltipLayer"></p>').text(descr).append(closeButton).appendTo('body').fadeIn(300);
        }, function() {
                $('.tooltipLayer').remove();
        }).mousemove(function(e) {
                var mousex = e.pageX + 20;
                var mousey = e.pageY + 10;
                $('.tooltipLayer').css({ top: mousey, left: mousex })
        }).click(function(e){
        	e.stopPropagation();
        	e.preventDefault();
        	if(SiteUtils.Is.ipad()){
        		$('.tooltipLayer').toggle();
        	}
        });
        $('a.closeTooltipAnchor').on('click', function(){
        	$('.tooltipLayer').remove();
        });		
	}
};

SiteUtils.UI.maskProgress = function(container_id, remove_mask){
	var containerObj = $(container_id);
	var containerId = containerObj.prop('id');
	if(typeof containerId === 'string' && containerId.length <= 0){
		containerId = container_id.replace('.', '');
	}
	var maskId = containerId + '_progressing';
	var removeMask = remove_mask || false;	
	var dObj = $('#' + maskId);
	if(removeMask){
		if(dObj.length > 0){
			dObj.remove();
		}
		return;
	}

	if(dObj.length <= 0){
		$(container_id).prepend('<div id="'+ maskId +'"></div>')
		dObj = $('#' + maskId)
	}
	try{
		dObj.addClass('block-mask');
		var offsets	= containerObj.position();
		dObj.css('width', containerObj.width() + 'px');
		dObj.css('height', containerObj.height() + 'px');
		dObj.css('paddingTop', parseInt((containerObj.height() / 2), 10) + 'px');
		dObj.html('<p>&nbsp;</p>');
	}catch(e){
		SiteUtils.log(e.message);
		dObj.destroy();
	}
}

SiteUtils.UI.enableCustomDropDowns = function( arg_selector ){
	var selector = arg_selector || '.main-content select';
    if($('html').hasClass('csstransitions')){
        if($(selector).length > 0){
            $(selector).each(function(index, value){
                if($(value).hasClass('no-customization')){return;}
                if($(value).prop('tagName') !== 'SELECT'){return;}
                if(!$(value).hasClass('form-select')){
                   $(value).addClass('form-select').wrap('<label class="form-select-label" />');
                }
            });
        }
    }
}

SiteUtils.UI.createModalWindow = function(html_content, options_param) {
		var options = options_param || {};
		var fadeInTimer = options.timer || 'slow';
		var handleForm = options.handleForm || 'NO';
		function closeModal(){
			$('#overlay-wrapper').trigger('event-modal-closed');
			$('body').removeClass('modal-window-in-progress');
			$('.modal-mask,.overlay-wrap').remove();
			$("body").removeClass("overlay-on hide-overflow");
		}

		function handleForms(){
			if(handleForm == 'NO'){return;}
			var formElement = $("#overlay-response").find("form:first");
			if(formElement.length > 0){
				formHandler = new FormHandler(formElement);
				formHandler.clearError();
				formHandler.init();
				formHandler.submitHandler = function(){
					formElement.trigger('submitHandler', [formHandler]);
				}
				try{
					if(!SiteUtils.Is.ipad()){
						formElement.find('input[type=text]:first').focus();
					}
					formElement.find('input:password').val('');
				}catch(e){SiteUtils.log(e.message);}

				formHandler.getFormObject().off('submitHandlerStart').on('submitHandlerStart', function(){
					$('body').addClass('modal-window-in-progress');
				});
				formHandler.getFormObject().off('submitHandlerEnd').on('submitHandlerEnd', function(){
					$('body').removeClass('modal-window-in-progress');
				});
		    }
		}
		function adjustIt(already_opened){
		    $(".overlay-wrap .inner").css("padding", "0");
		    $(".overlay-wrap .inner").css("margin", "0 auto");
		    if(already_opened){
				$(".overlay-wrap").hide().promise().done($(".overlay-wrap").fadeIn(fadeInTimer).promise().done(handleForms()));
		    }else{
				$(".modal-mask,.overlay-wrap").fadeIn(fadeInTimer).promise().done(handleForms());
			}

			$(window).on('keydown', function(event){
				if(event.keyCode == 27){
					if($('#overlay-wrapper').is(":visible")){
						closeModal();
						event.preventDefault();
					}
				}
			});
			$('.overlay-close').off("click").on("click", function(event) {
				event.preventDefault();
				$(".modal-mask,.overlay-wrap").fadeOut("slow", function() {
					closeModal();
				});
			});
			$('.overlay-close').off("touchstart").on("touchstart", function(event) {
				event.preventDefault();
				$(".modal-mask,.overlay-wrap").fadeOut("slow", function() {
					closeModal();
				});
			});
			$('.close-logon-modal').off("click").on("click", function(event) {
				event.preventDefault();
				$(".modal-mask,.overlay-wrap").fadeOut("slow", function() {
					closeModal();
				});
			});
			$('#overlay-wrapper').on('close-modal', function(){
				$(".modal-mask,.overlay-wrap").fadeOut("slow", function() {
					closeModal();
				});
			});

		}
	var alreadyOpened = true;
    if(!$('#overlay-response').length > 0){
		alreadyOpened = false;
        $('body').addClass("overlay-on hide-overflow").prepend("<div class='modal-mask' style='display:none'></div><div class='overlay-wrap' style='display:none'  id='overlay-wrapper-root'><div class='inner'><div class='overlay' id='overlay-wrapper'><a href='#' class='overlay-close'>&nbsp;</a><div id='overlay-response'></div></div></div></div>");
    }
	$('#overlay-response').html(html_content);
	adjustIt(alreadyOpened);
}

SiteUtils.UI.replaceWordInHtml = function(elem_obj, find_string, replace_string){
	var str;
	var htmlContent = $(elem_obj).html(function(){
		str  = $(this).html().replace(find_string, replace_string);
		return str;
	})
	return str;
}

SiteUtils.UI.getMaxWidthOf = function(parentObj, selector){
	var maxWidth = 0;
	$(parentObj).find(selector).each(function(i){
	if(this.offsetWidth > maxWidth)
		maxWidth = this.offsetWidth;
	});
	return maxWidth;
}

SiteUtils.UI.mScrollBar = function(selector, options){
	var scrollObj = $(selector);
	var onShow = function(){};
	var onHide = function(){};

	var confScrollShownClass = 'scroll-shown';
 	var curHeight = scrollObj.height();
    var curWidth = scrollObj.width();
    var confTitleWidth = 100;
    try{
		confTitleWidth = scrollObj.parent().find('.first').width();
	}catch(e){SiteUtils.log(e.message)}

	return {
		isShown: function(){
			return scrollObj.hasClass(confScrollShownClass);
		},
		callOnShow: function(shown_callback){
			onShow = shown_callback;
		},
		callOnHide: function(hide_callback){
			onHide = hide_callback;
		},
		showIt: function(options, callback_params){
			var options = options || {};
			scrollObj.height(150);
			scrollObj.mCustomScrollbar(options);
			scrollObj.addClass(confScrollShownClass);
			onShow(callback_params);
		},
		resetIt: function(){
			scrollObj.height(curHeight);
			scrollObj.width(curWidth);
		},
		hideIt: function(callback_params){
			scrollObj.mCustomScrollbar('destroy');
			scrollObj.removeClass(confScrollShownClass);
			onHide(callback_params);
		},
		getObj: function(){
			return scrollObj;
		},
		curHeight: function(){
			return curHeight;
		},
		curWidth: function(){
			return curWidth;
		},
		curTitleWidth: function(){
			return confTitleWidth;
		},
		updateHtml: function(html_content){
			scrollObj.html(html_content);
			scrollObj.mCustomScrollbar('update');
		}
	}
}

/* Page Analytics Trackers */
SiteUtils.Track = {
	separator: '||',
	_executeTrack: function(event_raiser, track_data){//do not call this method directly. Use SiteUtils.Track.executeTracl('trackEvents', ['a', 'b', 'c'])
		var trackData = track_data;
		if((trackData !== null) && (typeof _gaq !== 'undefined')){
			_gaq.push(['_trackEvent', trackData.eCategory, trackData.eAction, trackData.eLabel]);
		}
	},
	_trackEvent: function(html_object, data_attr){
		var htmlObj = $(html_object);
		if(htmlObj.length <= 0){
			SiteUtils.log(html_object + 'does not exist');
			return;
		}
		var trackData = SiteUtils.Track.parseData(htmlObj.data(data_attr));
		SiteUtils.Track._executeTrack(htmlObj, trackData);
	},	
	parseData: function(track_data){
		var returnObj = null;
		var trackData = track_data;
		if(trackData !== undefined){
			trackData = trackData.split(SiteUtils.Track.separator);
			if(trackData.length == 3){
				eCategory = trackData[0] || '';
				eAction = trackData[1] || '';
				eLabel = trackData[2] || '';
				returnObj = {'eCategory': eCategory, 'eAction': eAction, 'eLabel' : eLabel};
			}
		}
	return returnObj;
	},
	enableAllTrackers: function(){
		$(document).on('click', '.gatrack', function(event){
			SiteUtils.doTrackStart($(this));
		});
	},
	doTrackIt: function(html_elem_object, data_attr){
		this._trackEvent(html_elem_object, data_attr);
	},
	doTrackStart: function(html_elem_object){
		this._trackEvent(html_elem_object, 'ga-track');
	},
	doTrackEnd: function(html_elem_object){
		this._trackEvent(html_elem_object, 'ga-track-end');
	},
	doExecuteTrack: function(track_data){
		this._executeTrack(track_data);
	},
	doPtHeaderLogin: function(){
		try{
			_ptq.push(['login', {points: 0, ref_id: "pan_header_login", referrer:{ points: 0, ref_id:"pan_header_login"}}]);
		}catch(e){SiteUtils.log(e.message);}
	},
	doPtHeaderRegiser: function(){
		try{
			_ptq.push(['register', {points: 0, ref_id: "pan_header_register", referrer:{ points: 0, ref_id:"pan_header_register"}}]);
		}catch(e){SiteUtils.log(e.message);}
	},
	doPtWidgetLogin: function(){
		try{
			_ptq.push(['login', {points: 0, ref_id: "pan_login", referrer:{ points: 0, ref_id:"pan_login"}}]);
		}catch(e){SiteUtils.log(e.message);}
	},
	doPtWidgetRegister: function(){
		try{
			_ptq.push(['register', {points: 0, ref_id: "pan_register", referrer:{ points: 0, ref_id:"pan_register"}}]);
		}catch(e){SiteUtils.log(e.message);}
	}
}
/* PAGE Related Stuff */
SiteUtils.PAGE = {
	init: function(){
		this.widthScreen = screen.availWidth || 1024;
		this.widthViewport = $(window).width();
		this.htmlObjBody = $(document.body);
		this.isDeviceIpad = SiteUtils.Is.ipad();

		//init functions
		this.setupZoomProperties();
	},
	setupZoomProperties: function(){
		if(this.isDeviceIpad){
			return;
		}
		this.widthViewport = $(window).width();
		var zLevel = 1;
		try{
			zLevel = parseFloat(this.widthScreen / this.widthViewport).toFixed(2);
		}catch(e){SiteUtils.log(e.message);}
		if(zLevel >= 2){
			this.htmlObjBody.addClass('no-fixed-elements');
		}else{
			this.htmlObjBody.removeClass('no-fixed-elements');
		}
	},
	isPrimaryNavKeyboardAccessible: function(){
		var boolReturn = false;
		if($('#nav-wrapper').length > 0){
			boolReturn = $('#nav-wrapper').hasClass('keyboard-access');
		}
		return boolReturn;
	}

}

/* CONSTANTS */

SiteUtils.CONSTANTS.RESTRICTED_WORDS = ['anal', 'anus', 'arse', 'ass', 'ballsack', 'balls', 'bastard', 'bitch', 'biatch', 'bloody', 'blowjob', 'bollock', 'bollok', 'boner', 'boob', 'bugger', 'bum', 'butt', 'buttplug', 'clitoris', 'cock', 'coon', 'crap', 'cunt', 'damn', 'dick', 'dildo', 'dyke', 'fag', 'feck', 'fellate', 'fellatio', 'felching', 'fuck', 'fudgepacker', 'flange', 'Goddamn', 'hell', 'homo', 'jerk', 'jizz', 'knobend', 'labia', 'lmao', 'lmfao', 'muff', 'nigger', 'nigga', 'omg', 'penis', 'piss', 'poop', 'prick', 'pube', 'pussy', 'queer', 'scrotum', 'sex', 'shit', 'sh1t', 'slut', 'smegma', 'spunk', 'tit', 'tosser', 'turd', 'twat', 'vagina', 'wank', 'whore', 'wtf'];
SiteUtils.CONSTANTS.RESTRICTED_WORDS_MULTI = ['blow job', 'f u c k', 'fudge packer', 'God damn', 'knob end', 's hit'];
SiteUtils.CONSTANTS.BIRTHYEAR_DEFAULT = '1804';
SiteUtils.CONSTANTS.CLASS_LOSE_IT_REGISTRATION = 'reg-lose-it';

$(document).ready(function(){
	if(!$('body').hasClass('mobile-page')){
		SiteUtils.App.init();
		SiteUtils.PAGE.init();
		$(window).on('resize', function(){
			SiteUtils.PAGE.setupZoomProperties();
		});
	}
});

$.fn.visible = function() {
    return this.css('visibility', 'visible');
};

$.fn.inVisible = function() {
    return this.css('visibility', 'hidden');
};
function isiPad(){
	var reuturnVal=false;
	(function(a) {
    if(/android|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(ad|hone|od)|iris|kindle|lge |maemo|meego.+mobile|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino|playbook|silk/i.test(a)
    ||
    /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4)))
    {reuturnVal=true;}
	else{reuturnVal=false;}
})(navigator.userAgent||navigator.vendor||window.opera);
if(reuturnVal==="undefined") return true;
else return reuturnVal;

}
/*
 * Viewport - jQuery selectors for finding elements in viewport
 *
 * Copyright (c) 2008-2009 Mika Tuupola
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 *
 * Project home:
 *  http://www.appelsiini.net/projects/viewport
 *
 */
(function($) {

    $.belowthefold = function(element, settings) {
        var fold = $(window).height() + $(window).scrollTop();
        return fold <= $(element).offset().top - settings.threshold;
    };

    $.abovethetop = function(element, settings) {
        var top = $(window).scrollTop();
        return top >= $(element).offset().top + $(element).height() - settings.threshold;
    };

    $.rightofscreen = function(element, settings) {
        var fold = $(window).width() + $(window).scrollLeft();
        return fold <= $(element).offset().left - settings.threshold;
    };

    $.leftofscreen = function(element, settings) {
        var left = $(window).scrollLeft();
        return left >= $(element).offset().left + $(element).width() - settings.threshold;
    };

    $.inviewport = function(element, settings) {
        return !$.rightofscreen(element, settings) && !$.leftofscreen(element, settings) && !$.belowthefold(element, settings) && !$.abovethetop(element, settings);
    };

    $.extend($.expr[':'], {
        "below-the-fold": function(a, i, m) {
            return $.belowthefold(a, {threshold : 0});
        },
        "above-the-top": function(a, i, m) {
            return $.abovethetop(a, {threshold : 0});
        },
        "left-of-screen": function(a, i, m) {
            return $.leftofscreen(a, {threshold : 0});
        },
        "right-of-screen": function(a, i, m) {
            return $.rightofscreen(a, {threshold : 0});
        },
        "in-viewport": function(a, i, m) {
            return $.inviewport(a, {threshold : 0});
        }
    });
})(jQuery);

/**
 * Home Page Parallax
 * adjust the height of homepage banners to show a peek of the next banner at bottom of display
 *
 */
var parallaxEvents = false,
    inAnimation = [],
    outAnimation = [],
    demoDone = false;

// demo run

function initialNavSetup(){
    //console.log('do the animation');
    var duration = 300.
        initDelay = 1500;
    // loop through our elements and make it work
    $('.homepage-nav').find('a').each(function(index, el){
        inAnimation[index] = window.setTimeout(function(){
        $(el).addClass('demo');
      }, initDelay+(duration*index));

    // setup exit animation
      outAnimation[index] = window.setTimeout(function(){
        $(el).removeClass('demo');
      }, initDelay+2000+(duration*(index+1)));
    });
}
//attaches parallax events
function attachParallaxEvents() {
     $('.homepage-nav').on('click', '.nextSection', function(e) {
        e.preventDefault();
        // variables
        var scrollToVal = $($(e.currentTarget).attr('href')).offset().top;
        // check if this is the top button
        if (e.currentTarget === $('.homepage-nav').find('.nextSection:first-child')[0]) {
            scrollToVal = 0;
        }
        // animate body
        $('body,html').stop().animate({
            scrollTop: scrollToVal
        }, 800);
        clearDemo();
    });
    // scrolling  main navigation
    $(window).scroll(function () {
      var inview = '#' + $('#skrollr-body section > article > .home:in-viewport:first').parent().parent().attr('id'),
          $link = $('.homepage-nav').find('a').filter('[href=' + inview + ']');
      if ($link.length && !$link.is('.active')) {
        $('.homepage-nav a').removeClass('active');
        $link.addClass('active').addClass('demo');
        window.setTimeout(function($link){
            $('.homepage-nav a').removeClass('demo');
        }, 500);
      }
    });
}
function clearDemo(){
    var len1 = inAnimation.length,
        len2 = outAnimation.length,
        i;

    if (demoDone === false){
        for (i = 0; i < len1; i++){
            window.clearTimeout(inAnimation[i]);
        }
        for (i = 0; i < len2; i++){
            window.clearTimeout(outAnimation[i]);
        }
        $('.homepage-nav').find('.demo').removeClass('demo');
        demoDone = true;
    }
}
/**
 	* Home Page Parallax
 	* adjust the height of homepage banners to show a peek of the next banner at bottom of display
 	*
 	*/
function setParallaxHeight() {
	//console.log('setting parallax height');
    var headerHeight = $('#page-header').height(),
        windowHeight = $(window).height(),
        windowWidth = $(window).width(),
        parallaxHeight = $(window).height() - (50 + headerHeight),
        defParallaxHeight = 1100;

    if(SiteUtils.Is.ipad()){
        defParallaxHeight = 724;
    }


    // check if parallaxHeight is smaller than min height that we have setup
    if (parallaxHeight < defParallaxHeight){
        parallaxHeight = defParallaxHeight;
    }

    // is the viewport horizontal
    if(windowHeight >= windowWidth) {
        // add class to skrollr to adjust content for parallax to work correctly
        $('#skrollr-body').addClass('portrait');
        } else {
            $('#skrollr-body').removeClass('portrait');
        }

    $(".error-page section, .home-page section").height(parallaxHeight).css('opacity','1');

   if (!parallaxEvents){
        attachParallaxEvents();
        initialNavSetup();
        parallaxEvents = true;
   }
	alignNavVertically();
	if(!isiPad()){
		stickyFooter();
	}
}



function getModalVideo() {
    $(document).on("click", ".modal-video", function(event) {
        event.preventDefault();
        var dataUrl = $(this).attr("data-video");
        var response = "<div class='video-container'><iframe width=\"640\" height=\"360\" src=\"" + dataUrl + "\" frameborder=\"0\" allowfullscreen></iframe></div>";
		if($(this).find(".video-caption-markup").length>0)
			response+=$(this).find(".video-caption-markup").html();
        createModalWindow(response);
        $(".modal-mask,.overlay-wrap").fadeIn("fast");
        $('.overlay-close').off("click").on("click", function() {
			$(".overlay-wrap").find("iframe").hide();
            $(".modal-mask,.overlay-wrap").fadeOut("fast", function() {
				$('.modal-mask,.overlay-wrap').remove();
                $("body").removeClass("overlay-on");
            });
        });
        adjustModalHeight();
    });
}

function createModalWindow(response) {
    $('body').addClass("overlay-on").append("<div class='modal-mask' style='display:none'></div><div class='overlay-wrap' style='display:none'><div class='inner'><div class='overlay'><a href='javascript:void(0)' class='overlay-close'></a>" + response + "</div></div></div>");
}

function adjustModalHeight() {
    var winHeight = $(window).height();
    var overlayHeight = $(".overlay-wrap .overlay").height();
    if (overlayHeight <= winHeight) {
        $(".overlay-wrap .inner").css("padding", "0");
    }
}
  	function initializeHomePrimaryNav(){
        if(SiteUtils.PAGE.isPrimaryNavKeyboardAccessible()){
            return;
        }
		$(".pushdown-close").click(function(e){
			e.preventDefault();
			$('#primary-nav li a').removeClass("nav-active");$('#pushdown-container,#content-outer-wrapper,footer,.stuck').removeClass('nav-active');
		});
	/**
	 * Primary Navigation Pushdown
	 * show the appropreate sub nav when primary is interacted with
	 *
	 */
	$('#primary-nav li').each(function(index, element) {
		$(this).click(function(event){event.preventDefault();});
		$(this).hoverIntent(function(){
			$('#primary-nav li a').removeClass("nav-active");
			$(this).not(".order").find("a").addClass("nav-active");
			$('#pushdown-container nav').removeClass("nav-active");
			$('#pushdown-container').find("nav[data-role=column_"+(index+1)+"]").addClass("nav-active");
			$('#pushdown-container,#content-outer-wrapper,footer,.stuck').addClass("nav-active");
		},function(){});
    });
	$("#nav-wrapper").hoverIntent(function(){},function(){$('#primary-nav li a').removeClass("nav-active");$('#pushdown-container,#content-outer-wrapper,footer,.stuck').removeClass('nav-active');});
	}

	function stickyFooter() {
		if(!isiPad()){
			var windowHeight = $(window).height(),
			footerDisplayHeight = 40,
			footerHeight = $('.home-page footer').height(),
			stuckFooterStart = (windowHeight - footerDisplayHeight),
			activeFooterStart = (windowHeight - footerHeight);
		
			// stick the home page footer to the bottom and show a bit of it
			$(".home-page footer").css('top',stuckFooterStart).addClass('stuck');
			// add some padding the to bottom of skrollr div so nothing gets cut off
			$('#skrollr-body').css('padding-bottom', (footerDisplayHeight - 3));
		}
	}
function alignNavVertically(){
	$(".link-holder").css("opacity",1);
}
function initializeParallax(){
	// Banner Color
	$("section").each(function() {
		var bannerColor = $(this).data("bannerfontcolor");
		if((typeof bannerColor !== 'undefined') && (bannerColor.length > 0)){
			$(this).css('color', bannerColor).find("h3,h2,p,ul").css("color", bannerColor);
			$(".wifi-overlay h3").css("color", bannerColor);
			$(".modal-video *").css("color", "#4c3327");
		}
	});
	if(isiPad()){$(".home-page footer").css("position","relative").css("top","0");}
}
function goToParallax(){
	$("footer").find(".footer-wrapper h4:eq(0)").addClass("first").parent().find("ul:eq(0)").addClass("first");
	$("footer").find(".footer-wrapper ul.social-icons").parents("li.column-header").addClass("last");
	if(!isiPad()){
	   /**
		* Home Page Sticky Footer
		* stick the footer to the bottom of the home page
		*
		*/
		var windowHeight = $(window).height(),
		footerDisplayHeight = 40,
		footerHeight = $('.home-page footer').height(),
		stuckFooterStart = (windowHeight - footerDisplayHeight),
		activeFooterStart = (windowHeight - footerHeight);
	
		// slide up footer when we know users intent (requires hoverIntent jquery plugin)
		//$(".home-page footer").hoverIntent({over: makeActive, out: makeInActive});
		$(".home-page footer").hoverIntent(function(){
			// redeclare vars due to resizing of browser
			var windowHeight = $(window).height(),
			footerDisplayHeight = 40,
			footerHeight = $('.home-page footer').height(),
			stuckFooterStart = (windowHeight - footerDisplayHeight),
			activeFooterStart = (windowHeight - footerHeight);
			$('footer.stuck').css('top',activeFooterStart).addClass('active');
		},function(){
			// redeclare vars due to resizing of browser
			var windowHeight = $(window).height(),
			footerDisplayHeight = 40,
			footerHeight = $('.home-page footer').height(),
			stuckFooterStart = (windowHeight - footerDisplayHeight),
			activeFooterStart = (windowHeight - footerHeight);
			$('footer.stuck').css('top',stuckFooterStart).removeClass('active');
		});
   }
   	var relatedLinks="";
	 $('.banner').each(function(index) {
        var bannerBackground;
		if(!$(this).parents("div.bannergenerator").hasClass("hide"))
			relatedLinks=relatedLinks+"<li><a href='#"+$(this).attr("id")+"' class='nextSection'><span>"+$(this).find(".nextSectionInfo").text()+"</span></a></li>";
        // since .hiSRC doesnt work for background images
        // check to see if we are on a retina device (pixel ratio of 2)
        // serve up the appropreate image
        if (window.devicePixelRatio === 2) {
            bannerBackground = $(this).data('hisrc');
        } else {
			bannerBackground = $(this).data('background');
        }
        $(this).css('backgroundImage', 'url(' + bannerBackground + ')');
    });
	$(".link-holder").html(relatedLinks);
	$(".link-holder").find("a:eq(0)").addClass("active");
	getModalVideo();
    setParallaxHeight();
  	initializeParallax();
	var $homepageBanners=$(".home-page #skrollr-body .bannergenerator").not(".hide");
	$(".home-page section").css("opacity","1");
	initializeHomePrimaryNav();
	$(window).resize(function() { setParallaxHeight(); });
}
function startHomepage(){
	validateOrderBanner();
}

// Cookie Creation
function createCookie(name, value, days) {
    if(days){
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    }else{
        expires = "";
    }
    document.cookie = name+"="+value+expires+"; path=/";

}

// Read Cookie
function readCookie(name) {
    var nameEQ = name + "=";
    var cookieArray = document.cookie.split(';');
    for ( var i = 0; i < cookieArray.length; i++) {
        var cookieObj = cookieArray[i];
        while (cookieObj.charAt(0) == ' ') {
            cookieObj = cookieObj.substring(1, cookieObj.length);
        }
        if (cookieObj.indexOf(nameEQ) === 0) {
            return decodeURIComponent(cookieObj.substring(nameEQ.length, cookieObj.length));
        }
    }
    return null;
};

//Delete Cookie
function deleteCookie(name) {
    createCookie(name, "", -1);
}
//Function to validate order banner


function validateOrderBanner(){
    var cookieLocation = readCookie("location");
    var latcode=0;
	var longcode=0;
    if (cookieLocation != null) {
		var objects = SiteUtils.parseNVP(cookieLocation);
		latcode = objects['latcode'];
        longcode = objects['longcode'];
    } 

	var delivery="NO";
	var dine="NO";
	var pickup="NO";
	var json;
	var orderDistance=$("#order_distance").val();
	//goToParallax();
	
	var jqxhr = $.getJSON('/pbstat/location/getpaneralocationinfo.json', function(data) {
            var map = new Object();
			var arr = [];   
			json=data;
			iterateJson(json,map,arr,latcode,longcode);
			arr.sort(function(a,b){return a-b});
		
			if((arr[0]/1.6)<orderDistance){
				var nearestCafeId=getKeyByValue(map,arr[0]);
				arr=getProperties(json,nearestCafeId);
				delivery=arr[0];
				dine=arr[1];
				pickup=arr[2];
		
				if(delivery!='NO' || dine!='NO' || pickup!='NO'){ $("#orderbanner").removeClass('hide'); }
				if(delivery!='NO'){ $("#delivery").removeClass('hide'); }
				if(dine!='NO'){ $("#dine").removeClass('hide'); }
				if(pickup!='NO'){ $("#pick").removeClass('hide'); }  
			}
        }).done(function() {
			// To Test order-banner this line can be un-commented
			// $("#orderbanner,#delivery,#dine,#pick").removeClass('hide');
           goToParallax();
        }).fail(function(xhr, ajaxOptions, thrownError) { goToParallax();/*alert(xhr.status + " " + thrownError);*/
        });
}

//Function to create map with cafeId and distance from current location
function iterateJson(json,map,arr,latcode,longcode){
	for(var i in json){
		for(var j in json[i]){
			for(var k=0;k< json[i][j].length;k++){
				var cafeId=(json[i][j][k]["cafeID"]);
				var lat2=(json[i][j][k]["coordinates"][1]);
				var lon2=(json[i][j][k]["coordinates"][0]);
				var distance=getDistance(latcode,longcode,lat2,lon2);
				 map[cafeId] = distance;
				 arr.push(distance);
			}
		}
	}
}    

//Function to get the required properties for the nearest cafe
function getProperties(json,nearestCafeId){
	var propertyArray = [];
	for(var i in json){
		for(var j in json[i]){
			for(var k in json[i][j]){
				if(json[i][j][k]["cafeID"]==nearestCafeId){
					propertyArray.push(json[i][j][k]["hasDelivery"]);
					propertyArray.push(json[i][j][k]["hasDineIn"]);
					propertyArray.push(json[i][j][k]["hasPickup"]);
					break;
				}	
			}
		}
	}
	return propertyArray;
}


//Function to calculate distance between two coordinates
function getDistance(lat1,lon1,lat2,lon2) {
var R = 6371; // km
var dLat = toRad(lat2-lat1);
var dLon = toRad(lon2-lon1);
var lat1 = toRad(lat1);
var lat2 = toRad(lat2);

			var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
			var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
			var d = R * c;
return d;
}

function toRad(degree) {
return (degree * Math.PI/ 180);
}


//Function to get cafeId of the nearest Cafe 
function getKeyByValue(map, value ) {

for( var prop in map ) {
	if( map.hasOwnProperty( prop ) ) {
		if( map[ prop ] === value )
			 return prop;
	}
}
}

$(document).ready(function(e) {
	startHomepage();
});
var enableLocation=false;
var tokenCookieName="ssoToken";
(function($) {
	$(document).ready(function () {
		if($("#ssocookiename").size()>0){
			tokenCookieName=""+$("#ssocookiename").data("value")+"";
		}
		if($(".loggedInView").is(":visible") == true || getCookie(tokenCookieName) != null){
            createIdleTimeCookie("idleTime","0,"+new Date().getTime());
		}
		$('.loggedInView').on('click', 'a', function (e){
			e.preventDefault();
			userForceLogOut();
		});
		// Back To List Check Point
		var craftsmanpage="craftsmanship.html";
		var localevents="local-events.html";
		var newshappenings="news-and-happenings.html";
		var newsnoteworthy="new-and-noteworthy.html";
		var searchpage="search.html";
		var recipespage="recipes.html";
		
		if($("#craftsmanpage").size()>0){
			craftsmanpage=""+$("#craftsmanpage").data("value")+"";
		}
		if($("#localevents").size()>0){
			localevents=""+$("#localevents").data("value")+"";
		}
		if($("#newshappenings").size()>0){
			newshappenings=""+$("#newshappenings").data("value")+"";
		}
		if($("#newsnoteworthy").size()>0){
			newsnoteworthy=""+$("#newsnoteworthy").data("value")+"";
		}
		if($("#searchpage").size()>0){
			searchpage=""+$("#searchpage").data("value")+"";
		}
		if($("#recipespage").size()>0){
			recipespage=""+$("#recipespage").data("value")+"";
		}
		var currentLoc=location.href;
		if(currentLoc.indexOf(recipespage)!=-1 
				  || currentLoc.indexOf(searchpage)!=-1 
				  || currentLoc.indexOf(craftsmanpage)!=-1) {
			deleteCookie("back_to_link");
		}else if (currentLoc.indexOf(newshappenings)!=-1 
				  ||  currentLoc.indexOf(localevents)!=-1 
				  ||  currentLoc.indexOf(newsnoteworthy)!=-1) {
			createCookie("back_to_link",window.location.href, 0);	
		}
		
	    //Increment the idle time counter every minute.
	    var idleInterval = setInterval("timerIncrement()", 60000); // 1 minute

	    //Zero the idle timer on mouse movement.
	    $(this).mousemove(function (e) {
            if(readCookie("idleTime")!=null){
            	updateCookieTimer("0,"+new Date().getTime());
            }
            else{
                refreshPage();
            }
	    });
        $(this).click(function (e){
            if(readCookie("idleTime")!=null){
            	updateCookieTimer("0,"+new Date().getTime());
            }else{
				if($(".loggedInView").is(":visible") == true && getCookie(tokenCookieName) != null){
					userForceLogOut();
				}else{
                	refreshPage();
                }
            }
	    });
	    $(this).keypress(function (e){
	    	if(readCookie("idleTime")!=null){
	    		updateCookieTimer("0,"+new Date().getTime());
            }else{
				if($(".loggedInView").is(":visible") == true && getCookie(tokenCookieName) != null){
					userForceLogOut();
				}
                else{
                	refreshPage();
                }
            }
	    });
	    enableGATrackEvents();
	    loadDeliveryZoneInfo(); 
		checkLogin();
	});
})(jQuery);

function enableGATrackEvents(){
	$(document).on('click', '.gatrack', function(event){
		var separator = '||';
		var trackData = $(this).data('ga-track');
		if(trackData !== undefined){
			trackData = trackData.split(separator);
			if(trackData.length == 3){
				eCategory = trackData[0] || '';
				eAction = trackData[1] || '';
				eLabel = trackData[2] || '';
				try{
					_gaq.push(['_trackEvent', eCategory, eAction, eLabel]);
				}catch(e){SiteUtils.log(e.message);}
			}
		}
	}); 
}
function timerIncrement() {
    if(readCookie("idleTime")!=null){
    	var idleTime =readCookie("idleTime").split(",")[0];
    	var lastUpdated =parseInt(readCookie("idleTime").split(",")[1]);

	    if(parseInt(new Date().getTime())-lastUpdated>=55000){
	        idleTime=parseInt(idleTime)+1;
		    updateCookieTimer(idleTime+","+new Date().getTime());
	    }
        //idleTime + 1;
	    if (idleTime > 19) { // 20 minutes
	    	if($(".loggedInView").is(":visible") == true || getCookie(tokenCookieName) != null){
	    		userForceLogOut();
	    	}else{
	    		updateCookieTimer("0,"+new Date().getTime());
	    	}
	    }
    }else{
		refreshPage();
    }
}
function updateCookieTimer(c_time) {
	if(readCookie("idleTime")!=null){
		createIdleTimeCookie("idleTime",c_time);
	}
}

function refreshPage(){
	if($(".loggedInView").is(":visible") == true && getCookie(tokenCookieName) == null){
  		if($('#cug').length > 0){
			window.location.href = $("#cug").data("close-url");
         }else{
            location.reload(); 
         } 
    }
}
function createIdleTimeCookie(name, value, days){
    if(days){
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    }else{
        expires = "";
    }
    //document.cookie = name + "=" + encodeURIComponent(value) + expires + "; path=/"+locale;
	document.cookie = name+"="+value+expires+"; path=/"+"; domain="+$("#sub_domain").val();
}
function getCookie(c_name) {
    var i, x, y, ARRcookies = document.cookie.split(";");
    for (i = 0; i < ARRcookies.length; i++) {
        x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
        y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
        x = x.replace(/^\s+|\s+$/g, "");
        if (x == c_name) {
            return unescape(y);
        }
    }
}
/**
  * Generic AjaxCall function
  * Url to make request
  * @param dataUrl 
  * type of Request ( GET,POST,HEAD,PUT and etc..)	
  * @param methodType
  * response data type ( json / text / html)	
  * @param dataType
  * response MIME types ( application/json | text/html)	
  * @param contentMimeType
  * Is request is cached? default=false	
  * @param isCache
  * Is request is asynchrounous? default=false
  * @param isASync
  *	request parameters, default empty	  
  * @param params
  * callback function name after success
  * @param callbackfunctionname
  * 
  */
 function makeGenericAjaxCall(dataUrl,methodType,dataType,contentMimeType,isCache,isASync,params,callbackfunctionname){ 
		if(dataUrl!=null && dataType!=null && methodType!=null && contentMimeType!=null){
				if(isCache==undefined || isCache==null)	isCache=false;
				if(isASync==undefined || isASync==null)	isASync=true;
				if(params==undefined  || params==null)	params="";
				$.ajax({
						type : methodType,
						dataType : dataType,
						url : dataUrl,
						cache: isCache,
						data : params,
						async: isASync,
						contentType:contentMimeType,
						success:function(responseObject) {
							if(callbackfunctionname != undefined && callbackfunctionname!=null && callbackfunctionname!=""){
								callbackfunctionname(responseObject);
							}
						},
						error: function(jqXHR, exception){
							//console.log("Error while process request : caused by-:"+ exception);
						}
				});
	    }else{
	    	return null;
	    } 
  } 
 /**
 * Getting DeliveryZone Information,check whether user session already has information or not
 * If information available, process the information otherwise get the information by making ajax call to server
 * @returns {Boolean}
 */
 function loadDeliveryZoneInfo(){
	var msaCodes=readCookie("msacodes"); 
	var dataUrl="/pbstat/order/getmsacodeinfo.html";
	if(msaCodes==null){
		makeGenericAjaxCall(dataUrl,"GET","text","text/html",true,false,null,processDeliveryZoneInfo);
	}else{
		processDeliveryZoneInfo(msaCodes);
	}
  return false;
 }
 /**
 * Processing the delivery zone information, if information matched user delivery location 
 * Enable the Order Button otherwise Disable order button
 * @param msaCodes
 * @returns {Boolean}
 */
 function processDeliveryZoneInfo(msaCodes){
 	if(readCookie("msacodes")==null){
		createCookie("msacodes",msaCodes,0);
	}
 	var userLocation=readCookie("location")
 	var userMsaCode=null;
	if(userLocation!=null && msaCodes!=null){
		// GET MSA Code from User Location
		if(userLocation.indexOf(",")!=-1){
		    var msaCode=userLocation.split(",")[0];
		    if(msaCode!=null && msaCode.indexOf("=")!=-1){
		        userMsaCode=msaCode.split("=")[1];
		    }
		}
		var msaCodesList=msaCodes.split("^");
		var flag=false;
		for(var i=0;i<msaCodesList.length;i++){
			if(msaCodesList[i]==userMsaCode){
                $('#header-button-wrapper').removeClass('hide-order-button');
				break;
			}
		}
	}
     else{
		 $('#header-button-wrapper').removeClass('hide-order-button');
     }
	return false;
}
function checkLogin() {
	if (null != getCookie(tokenCookieName)) {
        //TODO: Handle the CUG page also in else condition if(non cug) {} else if(cug) {}
		var port=location.port;
		var url="/pbdyn/user/userProfile";
		var dataParam = 'pt=false';
		if(SiteUtils.App.isPtPage()){
			dataParam = 'pt=true';
		}
		var ptinfo = null;
		var userInfoCookieVal = getCookie('info');
		var userInfoAvailable = (userInfoCookieVal != null);

		if(SiteUtils.App.isPtPage()){
			//PT Page;
			if(userInfoAvailable){
				var userInfoData = SiteUtils.parseNVP(userInfoCookieVal);
				if(userInfoData['cardNumber'] !== undefined){
					try{
						ptReady.push(function(){
							PT.event.persistent('panera.user.cardinfo', userInfoData["cardNumber"]);
						});
					}catch(e){SiteUtils.log(e.message);}
				}
			}
		}else{
			//Non-PT pages.
			if(userInfoAvailable && (port=="" || port=="80")){
				url="/sso-spike/token?access_token="+getCookie(tokenCookieName);
			}
			if(!userInfoAvailable && (getCookie('just-signed-in') == 'true')){
				createIdleTimeCookie('just-signed-in', '', -1);
				$('html').on('event-check-login-success', function(){
					AccountModals.showSignInAcknowledgeMent();
				});
			}
		}

	    $.ajax({
	        url: url,
	        type: 'GET',
	        dataType: "json",
	        data: dataParam,
	        cache: false,

	        error: function () {
	        	createIdleTimeCookie(tokenCookieName,"",-1);
	        	createIdleTimeCookie("info","",-1);
                if($('#cug').length > 0){
					window.location.href = $("#cug").data("close-url");
                }
                else{
					$(".notLoggedInView").show();
                }

	        },
	        statusCode: {
	        	404: function(xhr) {
	        		createIdleTimeCookie(tokenCookieName,"",-1);
	        		createIdleTimeCookie("info","",-1);
                    if($('#cug').length > 0){
					window.location.href = $("#cug").data("close-url");
                }
                    else{
					$(".notLoggedInView").show();
                }
	            }
	        },
	        success: function (result) {
                if((typeof result.user !== 'undefined') && (result.user.statusCode === 206)){
					try{
						var dataUrl = $('#global-join-nav').data('secondary-reg');
						if((typeof dataUrl === 'string') && (dataUrl.length > 0)){
							AccountModals.loadSecondaryRegistrationModal(dataUrl, result.user);
							return;
						}
					}catch(e){SiteUtils.log(e.message);}
                }
	            if (result == false) {
	        		createIdleTimeCookie(tokenCookieName,"",-1);
	        		createIdleTimeCookie("info","",-1);
	                $(".notLoggedInView").show();
	            }else {
	                var user = result.user;
                    var ptinfo = result.ptinfo;
	                $(".loggedInView").show();
	                $(".welcome-banner").show().parent().addClass('show-user').removeClass('hide-user');
	                if(readCookie("info")!=null){
	                var result = SiteUtils.parseNVP($.cookie('info'));
	                }
	                else{
	                	createIdleTimeCookie("info",user['infocookie-value']);
	                	var result = SiteUtils.parseNVP($.cookie('info'));
	                }
	                if(result['firstName'] !== undefined){
	                	var firstNameOnly = result["firstName"].split(" ")[0];                                  
	                	//$(".welcomeUser").html(result["firstName"]);
	                	$(".welcomeUser").html(firstNameOnly);
	                }
	                if(result['cardNumber'] !== undefined){
	                	$(".cardNumber").html(result["cardNumber"]);
						if(SiteUtils.App.isPtPage()){
							try{
								ptReady.push(function(){
									PT.event.persistent('panera.user.cardinfo', result["cardNumber"]);
								});
							}catch(e){SiteUtils.log(e.message);}
						}
	                }
                    if(ptinfo != undefined) {
                        _pt_pre_config.auth_request = ptinfo.authRequest;
						_pt_pre_config.signature =  ptinfo.signature;
                        _pt_pre_config.timestamp = ptinfo.timestamp;
                        _pt_pre_config.client_id = ptinfo.clientId;
                        SiteUtils.App.loadPtPage();
                    }
	                try{
						$('html').trigger('event-check-login-success');
						$('html').off('event-check-login-success');
					}catch(e){SiteUtils.log(e.message);}
	            }
	        }
	    });
	}else{
	    $(".notLoggedInView").show();
		if($('#cug').length > 0){
             AccountModals.forceSignIn();
     	}
		SiteUtils.App.loadPtPage();
	}
}
//Read Cookie
function readCookie(name) {
    var nameEQ = name + "=";
    var cookieArray = document.cookie.split(';');
    for ( var i = 0; i < cookieArray.length; i++) {
        var cookieObj = cookieArray[i];
        while (cookieObj.charAt(0) == ' ') {
            cookieObj = cookieObj.substring(1, cookieObj.length);
        }
        if (cookieObj.indexOf(nameEQ) === 0) {
            return decodeURIComponent(cookieObj.substring(nameEQ.length, cookieObj.length));
        }
    }
    return null;
};

function userLogOut(url,method){
	$.ajax({
		type: method,
		url: url,
		cache: false,
		statusCode: {
	        	204: function(xhr) {
	        		createIdleTimeCookie(tokenCookieName,"",-1);
	        		createIdleTimeCookie("info","",-1);
	            },
				404: function(xhr) {
					createIdleTimeCookie(tokenCookieName,"",-1);
					createIdleTimeCookie("info","",-1);
				}
	        },
		success: function(data){
				if(SiteUtils.App.isPtPage()){
					AccountModals.reloadPage(true);
					return;
				}
			try{
				var logo = $('#page-header a.logo');
				if(logo.length >= 0 ){
					location.href = logo[0].href;
				}
			   }catch(e){
				   	location.reload();
			   }
			},
			error: function (data) {
				location.reload();
			}
	});
}
function userForceLogOut(){
	var method="POST";
	var logoutUrl="/pbdyn/user/logout";
	if(location.port=="" || location.port=="80" && getCookie(tokenCookieName)!=null){
		method="DELETE";
		logoutUrl=logoutUrl+"?access_token="+getCookie(tokenCookieName);
	}
	userLogOut(logoutUrl,method);
}
SiteHtml = {};
SiteHtml = {
	addAttrs: function(elem_obj, attr_obj){
		var elemObj = $(elem_obj);
		var attrVal = '';
		for(var attrName in attr_obj){
			attrVal = attr_obj[attrName];
			elemObj.attr(attrName, attrVal);
		}
	},
	show: function(elem_obj){
		var elemObj = $(elem_obj);
		elemObj.show();
		this.addAttrs(elemObj, {'aria-hidden' : 'false'});
	},
	hide: function(elem_obj){
		var elemObj = $(elem_obj);
		elemObj.hide();
		this.addAttrs(elemObj, {'aria-hidden' : 'true'});
	}
}

var KeyBoardAccess = {};
KeyBoardAccess.MainMenu = {};

KeyBoardAccess.MainMenu = {
	init: function(){
		if(!SiteUtils.PAGE.isPrimaryNavKeyboardAccessible()){
			return;
		}
		this.objNavWrapper = $('#nav-wrapper');
		this.objNav = $('#primary-nav');
		this.objMenuContainer = $('#pushdown-container');

		//classes
		this.clsIndicatorKeyPressedMenu = 'key-open';

		this.objPrimeNavs = {};
		this.objPrimeNavColumn = {};
		this.objPrimeNavAriaHidingElems = {};
		this.objPrimeNavFocusField = {};

		this.gatherObjects();
		this.addHandlers();
		this.doLoadEvents();
	},
	gatherObjects: function(){
		var that  = this;
		this.objPrimeNavs = this.objNav.find('li a');
		var totNavLength = this.objPrimeNavs.length;
		$.each(this.objPrimeNavs, function(index, element){
			var thisObj = $(element);
			thisObj.data('nav-index', index);

			if(index != 0 && index != (totNavLength -1)){
				thisObj.data('nav-next', index + 1);
				thisObj.data('nav-prev', index - 1);
			}else{
				if(index == 0){
					thisObj.data('nav-next', 1);
					thisObj.data('nav-prev', totNavLength - 1);
				}else{
					thisObj.data('nav-next', 0);
					thisObj.data('nav-prev', totNavLength - 2);
				}
			}
			SiteHtml.addAttrs(thisObj, {'role' : 'menuitem', 'aria-haspopup' : 'true' , 'aria-posinset' : (index + 1) });
		});

		this.objPrimeNavColumn = this.objMenuContainer.find('nav');
		
		$.each(this.objPrimeNavColumn, function(index, element){
			var thisObj = $(element);
			thisObj.data('nav-index', index);
			that.objPrimeNavFocusField['first_' + index] = null;
			that.objPrimeNavFocusField['last_' + index] = null;

			var fieldToFocus = thisObj.find('a, input[type=text]');
			fieldToFocus.addClass('focus-fields');
			if(fieldToFocus.length > 0){
				that.objPrimeNavFocusField['first_' + index] = fieldToFocus.first();
				that.objPrimeNavFocusField['last_' + index] = fieldToFocus.last();
				if(index < (totNavLength - 1)){
					fieldToFocus.last().data('next-nav-index', index + 1);
					$(fieldToFocus.last().on('blur mouseover'), function(){
						var indexNext = $(this).attr('data-next-nav-index', index);
						that.objPrimeNavs[indexNext].trigger('event-open-sub-menu');
					});
				}
			}

		});
		SiteHtml.addAttrs(this.objPrimeNavColumn, {'aria-hidden' : 'true', 'aria-expanded' : 'false'});


	},
	addHandlers: function(){
		var that = this;

		$(this.objPrimeNavs).on('keydown', function(event){
			var objPrimeNavElem = $(this);
			var isShiftKey = false;
			try{
				isShiftKey = event.shiftKey;
			}catch(e){SiteUtils.log(e.message);}

			var keyCode = event.which;
			var keysToOpen = [13, 40];//down-arrow, enterKey
			var keysToClose = [38, 27]; // up-arrow, esc
			var keysToRight = [39];
			var keysToLeft = [37];
			var keysToTab = [9]; //TAB key.
			if($.inArray(keyCode, keysToOpen) != -1){
				that.showMainMenu();
				that.objMenuContainer.addClass(that.clsIndicatorKeyPressedMenu);
				objPrimeNavElem.trigger('event-open-sub-menu', [true]);
				event.preventDefault();
				return;
			}

			if($.inArray(keyCode, keysToClose) != -1){
				that.hideMainMenu();
				that.objMenuContainer.removeClass(that.clsIndicatorKeyPressedMenu);
				objPrimeNavElem.trigger('event-hide-sub-menu');
				event.preventDefault();
				return;
			}

			if($.inArray(keyCode, keysToTab) != -1){
				if(isShiftKey){
					objPrimeNavElem.trigger('event-reverse-tab-at-menu', [event]);
				}else{
					objPrimeNavElem.trigger('event-tab-at-menu', [event]);
				}
				return;
			}

			if($.inArray(keyCode, keysToLeft) != -1){
				objPrimeNavElem.trigger('event-show-left-menu', [event]);
				return;
			}

			if($.inArray(keyCode, keysToRight) != -1){
				objPrimeNavElem.trigger('event-show-right-menu', [event]);
				return;
			}
			
		});
		$(this.objPrimeNavs).on('click key-open', function(event){
			$(this).trigger('event-open-sub-menu');
			event.preventDefault();
		});

		$(this.objPrimeNavs).on('event-open-sub-menu', function(event){
			var index = $(this).data('nav-index');
			KeyBoardAccess.MainMenu.showSubMenu.call(that, index);
		});

		$(this.objPrimeNavs).on('event-focus-first-element', function(event){
			var index = $(this).data('nav-index');
			var isKeyPress = that.isKeyPressedMenu();
			var fieldToFocus = that.objPrimeNavFocusField['first_' + index];
			if(fieldToFocus !== null){
				fieldToFocus.attr('tabindex', '0');
				fieldToFocus.focus();
				fieldToFocus.css('outline', 'none');
				if(isKeyPress){
					fieldToFocus.css('outline', '');
				}
			}
		});
		$(this.objPrimeNavs).on('event-blur-last-element', function(event){
			var index = $(this).data('nav-index');
			var fieldToFocus = that.objPrimeNavFocusField['last_' + index];
			if(fieldToFocus !== null){
				fieldToFocus.attr('tabindex', '0');
				fieldToFocus.focus();
			}
		});

		$(this.objPrimeNavs).on('event-hide-sub-menu', function(){
			var index = $(this).data('nav-index');
			KeyBoardAccess.MainMenu.showSubMenu.call(that, index);
		});

		$(this.objPrimeNavs).on('event-reverse-tab-at-menu', function(event, actEvent){
			if(that.isMainMenuOpened()){
				$(this).trigger('event-show-left-menu');
				try{
					actEvent.preventDefault();
				}catch(e){SiteUtils.log(e.message);}
			}
		});
		$(this.objPrimeNavs).on('event-tab-at-menu', function(event, actEvent){
			if(that.isMainMenuOpened()){
				$(this).trigger('event-focus-first-element');
				try{
					actEvent.preventDefault();
				}catch(e){SiteUtils.log(e.message);}
			}
		});

		$(this.objPrimeNavs).on('event-show-left-menu', function(event, actEvent){
			var index = $(this).data('nav-prev');
			KeyBoardAccess.MainMenu.selectNav(index);
			if(that.isMainMenuOpened()){
				KeyBoardAccess.MainMenu.showMainMenu();
				KeyBoardAccess.MainMenu.showSubMenu.call(that, index, true);	
				try{
					actEvent.preventDefault();
				}catch(e){SiteUtils.log(e.message);}
			}
			
		});

		$(this.objPrimeNavs).on('event-show-right-menu', function(event, actEvent){
			var index = $(this).data('nav-next');
			KeyBoardAccess.MainMenu.selectNav(index);
			if(that.isMainMenuOpened()){
				KeyBoardAccess.MainMenu.showMainMenu();
				KeyBoardAccess.MainMenu.showSubMenu.call(that, index, true);
				try{
					actEvent.preventDefault();
				}catch(e){SiteUtils.log(e.message);}
			}	
		});

		$(document).on('keydown', '.pushdown-close', function(event){
			var keyCode = event.which;
			if(keyCode !== 9){return;}
			var index = $(this).data('next-nav-index');
			that.hideMainMenu();
			if(index !== undefined){
				if(that.objPrimeNavs[index] !== undefined){
					KeyBoardAccess.MainMenu.showMainMenu();
					KeyBoardAccess.MainMenu.showSubMenu.call(that, index, true);
					event.preventDefault();
				}
			}
		})

		this.objPrimeNavs.hoverIntent(function(){
			that.objMenuContainer.removeClass(that.clsIndicatorKeyPressedMenu);
			that.showMainMenu();
			$(this).trigger('event-open-sub-menu');
			$(this).attr('style', 'outline:none');
			$(this).focus();
			that.objNav.addClass('mouse-hover');
			$('#pushdown-container,#content-outer-wrapper,.stuck,footer').addClass("nav-active");
		},function(){
			$(this).blur();
			that.objNav.removeClass('mouse-hover');
			$(this).attr('style', '');
		});

		this.objNavWrapper.hoverIntent(
            function(){},
            function(){
                that.hideMainMenu();
                $('#pushdown-container,footer,#content-outer-wrapper,.stuck').removeClass('nav-active');
            }
    	);
	},
	showMainMenu: function(){
		this.objMenuContainer.addClass('nav-active');
		SiteHtml.addAttrs(this.objMenuContainer, {'aria-hidden' : 'false', 'aria-expanded' : 'true'});
		SiteHtml.addAttrs(this.objPrimeNavColumn, {'aria-hidden' : 'true', 'aria-expanded' : 'false'});
	},
	showSubMenu: function(index, change_focus){
		this.objPrimeNavs.removeClass('nav-active');
		this.objPrimeNavColumn.removeClass('nav-active');
		SiteHtml.addAttrs(this.objPrimeNavColumn.find('.focus-fields'), {'tabindex' : '-1'});

		this.objPrimeNavs.eq(index).addClass('nav-active');
		this.objPrimeNavColumn.eq(index).addClass('nav-active');
		SiteHtml.addAttrs(this.objPrimeNavColumn.eq(index), {'aria-hidden' : 'false', 'aria-expanded' : 'true'});
		SiteHtml.addAttrs(this.objPrimeNavColumn.eq(index).find('.focus-fields'), {'tabindex' : '0'});

		if(change_focus){
			this.objPrimeNavs.eq(index).focus();
		}

	},
	hideSubMenu: function(index){
		this.objPrimeNavs.eq(index).removeClass('nav-active');
		this.objPrimeNavColumn.eq(index).removeClass('nav-active');
		SiteHtml.addAttrs(this.objPrimeNavColumn, {'aria-hidden' : 'true', 'aria-expanded' : 'false'});
	},
	hideMainMenu: function(){
		this.objPrimeNavs.removeClass('nav-active');
		this.objMenuContainer.removeClass('nav-active');
		this.objMenuContainer.removeClass(this.clsIndicatorKeyPressedMenu);
		SiteHtml.addAttrs(this.objMenuContainer, {'aria-hidden' : 'true', 'aria-expanded' : 'false'});
		SiteHtml.addAttrs(this.objPrimeNavColumn.find('.focus-fields'), {'tabindex' : '-1'});
	},
	isKeyPressedMenu: function(){
		return this.objMenuContainer.hasClass(this.clsIndicatorKeyPressedMenu);
	},
	isMainMenuOpened: function(){
		return this.objMenuContainer.hasClass('nav-active');
	},
	selectNav: function(index){
		if(this.objPrimeNavs[index] !== undefined){
			this.objPrimeNavs.removeClass('nav-active');
			this.objPrimeNavs[index].focus();
		}
	},
	doLoadEvents: function(){
		this.hideMainMenu();
	}
}

$(document).ready(function(){
	KeyBoardAccess.MainMenu.init();
});
/*!
 * account-logon-service.js
 * This file contains the Logon Scripts required for login, signin, forgot-password, reset-password 
 * @require   form-validation.js
 *
 * @project   Panera Web Unification
 * @date      2013-06-17
 * @author    Vijay Thangavel, SapientNitro <vthangavel@sapient.com>
 * @licensor  Panera Bread
 * @site      panerabread.com
 *
 */

//TODO: sourceDestination added at the eod, have to optimize it in a better way
var sourceDestination = {};
var cloneContainer = {};

function resetSourceAndDestination(){
	for(var i in sourceDestination){
		$('#' + sourceDestination[i]).appendTo($('#' + i));
		$('#' + sourceDestination[i]).html(cloneContainer[sourceDestination[i]]);
	}
	delete sourceDestination[i];
}

var AccountModals = {
	currentEntryPointer:null,
	init: function(form_handler){
		this.signUpLoadEvents();
		this.currentFormHandler = form_handler;
		SiteUtils.enableNumericInput();
	},
	setCurrentEntryPoint: function(anchor_obj){
		AccountModals.currentEntryPointer = anchor_obj;
	},
	trackCurrentEntryEndPoint: function(){
		SiteUtils.Track.doTrackEnd(AccountModals.currentEntryPointer);
	},
	getSecureProtocolId: function(){
		this.secureProtoColId = 'gosecure=1';
		return this.secureProtoColId;
	},
	isSecureLogin: function(){
		var isHttpsLoginFlow = false;//get this flag from a jsp property file.
		return isHttpsLoginFlow;
	},
	updatePartialDobIfAny: function(formHandler, dob_field, dob_year_default){
		if(formHandler.getFormObject().data('has-optional-birthyear') == 'true'){
			var params = formHandler.getFormObject().serializeArray();
			for(var i = 0, n = params.length; i < n; i++){
				if(params[i].name === dob_field){
					params[i].value = dob_year_default;
				}
			}
			var newSerializedData = jQuery.param(params);
			formHandler.setSerializedData(newSerializedData);
		}
	},
	doValidateJoinDob: function(){
		return SiteUtils.App.Forms.doValidateDob(this.currentFormHandler);//give proper naming in future.
	},
	signUpLoadEvents: function(){
		SiteUtils.autoTab($('#overlay-wrapper #phone_fields'));
		SiteUtils.UI.enableCustomDropDowns('#join_now_form select');
		var that = this;
		//Panera card option
		$('#join_card_not_available').on('click', function(){
			$('#panera-card-section, #panera-card-details').slideUp('slow').promise().done(function(){$('.join-panera-card').hide();});
			that.currentFormHandler.disableValidation('join_card_no', 'join_reg_no');
			$('#join_card_no, #join_reg_no').removeClass('input-error').val('');
	    });
	    $('#join_card_available').on('click', function(){
			$('.join-panera-card').show();
			$('#panera-card-section, #panera-card-details').slideDown('slow');
			that.currentFormHandler.enableValidation('join_card_no', 'join_reg_no');
	    });

	    /* Join now form init */
	    $("#join_now_form").on('invalid-form.validate', function(event, formHandler){
			that.doValidateJoinDob();
	    });
	    $("#join_now_form").on('submitHandler', function(event, formHandler){
			var isValidDob = that.doValidateJoinDob();
			if(!isValidDob){
				return false;
			}

			SiteUtils.App.Forms.updatePartialDobIfAny(formHandler, 'dob_year', SiteUtils.CONSTANTS.BIRTHYEAR_DEFAULT);

			//Do a AJAX Post, expecting JSON Response.
			formHandler.doQuickAjaxPostJson(function(response){
				if (response.hasOwnProperty('error')) {
				    //Registration failed
				    var formErrorMsg = $('#join_now_form_msg');
				    formErrorMsg.html('');
				    $.each(response.error, function (i, data) {
				        formErrorMsg.append($('<p class="error"/>').text(data));
				    });
				    formErrorMsg.removeClass('hide');
				} else {
					try{
						formHandler.getFormObject().off('submitHandlerEnd');
						formHandler.getFormObject().trigger('submitHandlerStart');
					}catch(e){SiteUtils.log(e.message);}

				    //Response has username, Registration success.
				    AccountModals.trackCurrentEntryEndPoint();

				    if(AccountModals.isLoseItFlow()){
				    	//redirection to lose-it. 
				    	//LoseIt model defined in lose-it.js
				    	LoseIt.fetchEncryptedCustomerId(
							{ 
								"success" : function(){
									window.location.href = LoseIt.getRegistrationRedirectUrl();
								},
								"error": function(){
									window.location.reload();
								}
							}
						);

				    }else{
					    var configUrl = formHandler.getFormObject().data('success-action-url');
					    if(typeof configUrl === 'undefined' || configUrl.length <= 0){
							//authorable one, better to have this logic since its a prioritized flow
							configUrl = '/content/panerabread/en-us/welcome.html';
					    }
					    if(typeof response === 'object' && typeof response.successUrl !== 'undefined'){
							//authorable one, better to have this logic since its a prioritized flow
							configUrl = response.successUrl;
					    }
					    if(SiteUtils.App.isPtPage()){
							window.location.reload();
					    }else{
							window.location.href = configUrl;
						}
					}
				}				
			});
		});
	},
	doSignIn: function(wrapper_class_identifier){
		var wrapperClass = wrapper_class_identifier || 'E';
		sourceDestination['hidden-modal-dialogs'] = 'sign-in-modal';
		$.fn.createModalWindow('sign-in-modal', false, 10);
		if(wrapperClass !== 'E'){
			$('#overlay-wrapper').addClass(wrapperClass);
		}
	},
	doRegistration: function(wrapper_class_identifier){
		var wrapperClass = wrapper_class_identifier || 'E';
		var globalJoinNow = $('#global-join-nav');
		if(globalJoinNow.length > 0){
			AccountModals.setCurrentEntryPoint(globalJoinNow);
			var dataUrl = globalJoinNow.data('modal');
			var jqxhr = $.get(dataUrl, function(response) {
				$.fn.createModalWindow(response, true);
				if(wrapperClass !== 'E'){
					$('#overlay-wrapper').addClass(wrapperClass);
				}
			});
		}
	},
	forceSignIn: function(){
		var homeUrl = '/en-us/home.html';
		var cug = $('#cug');
		if(cug.length > 0){
			var cugCloseUrl = cug.data('close-url');
			if((cugCloseUrl !== undefined) && (cugCloseUrl.length > 0)){
				homeUrl = cugCloseUrl;
			}
		}
		function goHome(){
			if(location.href.indexOf(homeUrl) < 0 ){
				location.href = homeUrl;
			}
		}
		sourceDestination['hidden-modal-dialogs'] = 'sign-in-modal';
		$.fn.createModalWindow('sign-in-modal', false, 10);
		$('#overlay-response').addClass('force-sign-in');

			$('#overlay-response').off('keydown');

			$('.overlay-close').off("click").on("click", function(event) {
				event.preventDefault();goHome();
			});

			$('.overlay-close').off("touchstart").on("touchstart", function(event) {
				event.preventDefault();goHome();
			});

			$('.close-logon-modal').off("click").on("click", function(event) {
				event.preventDefault();
			});
			$('#overlay-response').on('event-modal-closed')
	},
	doProtocolCheck: function(){
		var proceedNow = true;
		if(!this.isSecureLogin()){
			return proceedNow;
		}
		var protoCol = location.protocol;
		if(protoCol !== undefined){
			protoCol = protoCol.toString().toLowerCase();
			if(protoCol.indexOf('https') !== 0){
				proceedNow = false;
				var url = location.href;
				url = url.replace('http:', 'https:');

				var queryValue = location.search.toString();
				if(queryValue.indexOf('?') === 0){
					url = url.replace('?', '?' + AccountModals.getSecureProtocolId() + '&');
				}else{
					url = url.replace(location.pathname, location.pathname + '?' + AccountModals.getSecureProtocolId());
				}
				location.replace(url);
			}
		}
		return proceedNow;
	},
	closeModalWindow: function(){
		$('#overlay-wrapper').trigger('close-modal');
	},
	reloadPage: function(no_time_out){
		var noTimeout = no_time_out || false;
		var domHtml = $('html');
		if(domHtml.data('page-reloaded') !== true){
			domHtml.data('page-reloaded', 'true');
			if(!noTimeout){
				AccountModals.reloadTimer = null;
				try{clearTimeout(AccountModals.reloadTimer);}catch(e){SiteUtils.log(e.message);}
			}
			location.reload();
		}
	},
	showSignInAcknowledgeMent: function(){
		var globalSignInNav = $('#global-sign-in');
		try{
			var ackUrl = globalSignInNav.data('acknowledge');
			if((typeof ackUrl === 'string') && (ackUrl.length > 0)){
				var ackTimeOut = parseInt(globalSignInNav.data('acknowledge-timeout'));
				var jqxhr = $.get(ackUrl);
				jqxhr.done(function(response) {
					$.fn.createModalWindow(response, true, 'fast');
					$('#overlay-wrapper').addClass('signin-acknowledgment');
					if(ackTimeOut > 0){
						AccountModals.reloadTimer = setTimeout("AccountModals.closeModalWindow()", ackTimeOut * 1000)
					}
					var userInfo = SiteUtils.parseNVP($.cookie('info'));
					if(userInfo['firstName'] !== undefined){
						$('#overlay-response .welcomeUser').html(userInfo['firstName']);
					}
				}).fail(function(){
					AccountModals.reloadPage();
				});
			}
		}catch(e){SiteUtils.log(e.message);}
	},
	processSignInResponse: function(response, formHandler){
		if(response){
			//Sign-In Success:
			try{
				var token = response.access_token;
				if(token){
					// Create token cookie on successfull Login
					createIdleTimeCookie(tokenCookieName, token, 0);
				}
			}catch(e){SiteUtils.log(e.message);}

			//Google Analytics - SignIn flow- End tracking
			AccountModals.trackCurrentEntryEndPoint();

			//Logic for reset-password flow. Should not reload the page with token key.
			if(window.location.search.indexOf('token=') >= 0){
				window.location.href = window.location.pathname;
			}else{
				if(AccountModals.isLoseItFlow()){
					try{
						formHandler.getFormObject().off('submitHandlerEnd');
						formHandler.getFormObject().trigger('submitHandlerStart');
					}catch(e){SiteUtils.log(e.message);}
				    	//redirection to lose-it. 
				    	//LoseIt model defined in lose-it.js
				    	LoseIt.fetchEncryptedCustomerId(
							{ 
								"success" : function(){
									window.location.href = LoseIt.getLoginRedirectUrl();
								},
								"error": function(){
									window.location.reload();
								}
							}
						);
				}else{
					try{
						formHandler.getFormObject().off('submitHandlerEnd');
					}catch(e){SiteUtils.log(e.message);}
					if(!SiteUtils.App.isPtPage()){
						createIdleTimeCookie('just-signed-in', 'true');
					}
					AccountModals.reloadPage(true);
				}
			}
		}else{
			formHandler.clearError();
			formHandler.showErrorMessage(formHandler.getFormObject().data('msg-error'));
		}
	},
	loadSecondaryRegistrationModal: function(modal_url, user_profile_result){
		var userDetails = user_profile_result || {'first-name': 'User', 'phone-number': ''};
		var jqxhr = $.get(modal_url, function(response) {
			$.fn.createModalWindow(response, true);
			try{
				$('#sec-first-name').html(userDetails['first-name']);
				$('#overlay-wrapper-root').addClass('secondary-reg');
			}catch(e){SiteUtils.log(e.message);}
			try{
				var phoneNumber = userDetails['phone-number'];
				phoneNumber = phoneNumber.replace(/\D/g,'').match(/(\d{3})(\d{3})(\d{4})$/);
				if((phoneNumber !== null) && (phoneNumber.length > 3)){
					$('#phone_number_a').val(phoneNumber[1]);
					$('#phone_number_b').val(phoneNumber[2]);
					$('#phone_number_c').val(phoneNumber[3]);
				}
			}catch(e){SiteUtils.log(e.message);}
			$('#overlay-wrapper').on('event-modal-closed', function(){
				$('#overlay-wrapper-root').removeClass('secondary-reg');
				try{userForceLogOut();}catch(e){SiteUtils.log(e.message);}//Defined in panera-utils.js;
			});
		});
	},
	isLoseItFlow: function(){
		return $('#overlay-wrapper').hasClass(SiteUtils.CONSTANTS.CLASS_LOSE_IT_REGISTRATION);
	}
};

$.fn.extend({
	createModalWindow: function(container, isDynamicResponse, timer) {
		var fadeInTimer = timer || 'slow';
		if(isDynamicResponse){
			resetSourceAndDestination();
		}
		var formHandler = null;
		var resetModal = function(){
			if(formHandler !== null){
				formHandler.clearError();
				delete formHandler;
			}

		}
		function closeModal(){
			$('#overlay-wrapper').trigger('event-modal-closed');
			$('body').removeClass('modal-window-in-progress');
			//if(!isDynamicResponse){
				resetSourceAndDestination();
			//}
			$('.modal-mask,.overlay-wrap').remove();
			$("body").removeClass("overlay-on hide-overflow");
			resetModal();
		}

		function handleForms(){
			if(isDynamicResponse){
				var formElement = $("#overlay-response").find("form:first");
			}else{
				var formElement = $("#overlay-response").find("form.static-load:first");
			}
			if(formElement.length > 0){
				formHandler = new FormHandler(formElement);
				formHandler.clearError();
				formHandler.init();
				formHandler.submitHandler = function(){
					formElement.trigger('submitHandler', [formHandler]);
				}
				try{
					if(!SiteUtils.Is.ipad()){
						formElement.find('input:first').focus();
					}
					formElement.find('input:password').val('');
				}catch(e){SiteUtils.log(e.message);}

				formHandler.getFormObject().off('submitHandlerStart').on('submitHandlerStart', function(){
					$('body').addClass('modal-window-in-progress');
				});
				formHandler.getFormObject().off('submitHandlerEnd').on('submitHandlerEnd', function(){
					$('body').removeClass('modal-window-in-progress');
				});
				AccountModals.init(formHandler);
		    }
		}
		function adjustIt(already_opened){
		    $(".overlay-wrap .inner").css("padding", "0");
		    $(".overlay-wrap .inner").css("margin", "0 auto");
		    if(already_opened){
				$(".overlay-wrap").hide().promise().done($(".overlay-wrap").fadeIn(fadeInTimer).promise().done(handleForms()));
		    }else{
				$(".modal-mask,.overlay-wrap").fadeIn(fadeInTimer).promise().done(handleForms());
			}
			
			$(document).on('keydown', '#overlay-response' , function(event){
				if(event.keyCode == 27){
					closeModal();
					event.preventDefault();
				}
			});
			$('.overlay-close').off("click").on("click", function(event) {
				event.preventDefault();
				$(".modal-mask,.overlay-wrap").fadeOut("slow", function() {
            		closeModal();
				});
			});
			$('.overlay-close').off("touchstart").on("touchstart", function(event) {
				event.preventDefault();
				$(".modal-mask,.overlay-wrap").fadeOut("slow", function() {
					closeModal();
				});
			});
			$('.close-logon-modal').off("click").on("click", function(event) {
				event.preventDefault();
				$(".modal-mask,.overlay-wrap").fadeOut("slow", function() {
					closeModal();
				});
			});
			$('#overlay-wrapper').on('close-modal', function(){
				$(".modal-mask,.overlay-wrap").fadeOut("slow", function() {
					closeModal();
				});
			});

		}
	var alreadyOpened = true;
    if(!$('#overlay-response').length > 0){
    	alreadyOpened = false;
        $('body').addClass("overlay-on hide-overflow").prepend("<div class='modal-mask' style='display:none'></div><div class='overlay-wrap' style='display:none'  id='overlay-wrapper-root'><div class='inner'><div class='overlay' id='overlay-wrapper'><a href='#' class='overlay-close'>&nbsp;</a><div id='overlay-response'></div></div></div></div>");
    }
    if(isDynamicResponse){
		$('#overlay-response').html(container);
    }else{
    	$('#overlay-response').html($('#common-header-modal').html());
    	$('#'+ container).appendTo($('#overlay-response'));
    }
    adjustIt(alreadyOpened);
    staticModalListeners();
}
});

$(document).ready(function($){
	/* Punch tab tracking */
	if(SiteUtils.App.isPtPage()){
		$('#global-sign-in').on('click', function(){
			SiteUtils.Track.doPtHeaderLogin();
		});
		$('#global-join-nav').on('click', function(){
			SiteUtils.Track.doPtHeaderRegiser();
		});
	}

	if($('.modal-dynamic').length > 0){
		$(document).on('click', '.modal-dynamic', function(event){
			//if(!AccountModals.doProtocolCheck()){
			//	return;
			//}
			event.preventDefault();
			AccountModals.setCurrentEntryPoint(event.target);
			var dataUrl = $(this).data('modal');
			var jqxhr = $.get(dataUrl, function(response) {
				$.fn.createModalWindow(response, true);
			});
		});
	}

	if($('.modal-static').length > 0){
		$('.modal-static').each(function(){
			var dataContainer = $(this).data('modal');
			cloneContainer[dataContainer] = $('#' + dataContainer).html();
		});

		$(document).on('click', '.modal-static', function(event){
			if(!AccountModals.doProtocolCheck()){
				return;
			}			
			event.preventDefault();
			resetSourceAndDestination();
			var dataContainer = $(this).data('modal');
			AccountModals.setCurrentEntryPoint(event.target);
			sourceDestination['hidden-modal-dialogs'] = dataContainer;
			$.fn.createModalWindow(dataContainer, false);
		});

		if($('.landing-modal-open:first').length > 0){
			sourceDestination['hidden-modal-dialogs'] = $('.landing-modal-open:first').attr('id');
			$.fn.createModalWindow($('.landing-modal-open:first').attr('id'), false);
		}
	}
	if(AccountModals.isSecureLogin() && window.location.href.indexOf(AccountModals.getSecureProtocolId()) > 0 ){
		try{
			(function(){
				var referrer = document.referrer;
				if(referrer.length !== 0 && (window.location.href.indexOf(referrer) < 0)){
					AccountModals.doSignIn();
				}
			})();
		}catch(e){SiteUtils.log(e.message);}
	}

	/* Join Now ----- Direct Entry */
	if($('#join_now_form').length > 0){
		var formElement = $('#join_now_form');
		formHandler = new FormHandler('#join_now_form', {disableDefaultSubmision: true});
		formHandler.clearError();
		formHandler.init();
		formHandler.submitHandler = function(){
			formElement.trigger('submitHandler', [formHandler]);
		}
		AccountModals.init(formHandler, 'registration');
	}
	staticModalListeners();
});

function staticModalListeners(){
	$('#sign-in-modal').off('forceSignIn').on('forceSignIn', function(){
		AccountModals.forceSignIn();
	})
	//Sign In form
	$('#form_sign_in').off('submitHandler').on('submitHandler', function(event, formHandler){
		if(location.port == "" || location.port == "80"){
			var webUrl = formHandler.getAjaxActionUrl();
			var dataString = 'agrant_type=password&username=' + encodeURIComponent($("#user_email").val()) +'&password='+$("#user_password").val();
			formHandler.doAjaxSubmitPostJson(webUrl, dataString, function(response) {
				AccountModals.processSignInResponse(response, formHandler)
			}); 
		}else{
			formHandler.doQuickAjaxPostJson(function(response){
				AccountModals.processSignInResponse(response, formHandler)
			});
		}
	});
	//Forgot password form
	$('#form_forgot_password').off('submitHandler').on('submitHandler', function(event, formHandler){
		var formObject = formHandler.getFormObject();
		formHandler.doQuickAjaxPostJson(function(response){
			if(response){
				formObject.hide();
				$('#fp_email').html($('#forgot_password_email').val());
				$('#msg_forgot_password').show().next().hide();
				formHandler.hideFormMessage();
			}else{
				formHandler.clearError();
				formHandler.showErrorMessage(formObject.data('msg-error'));
			}
		});
	});

	//Reset password form
	$('#reset-password-form').off('submitHandler').on('submitHandler', function(event, formHandler){
        var formObject = formHandler.getFormObject();
		formHandler.doQuickAjaxPostJson(function(response){
			if(response){
				$('#reset-form').addClass('hide');
				$('#reset-completed').removeClass('hide');
			}
			else{
				formHandler.showErrorMessage(formHandler.getDataMsg(formObject, 'msg-reset-problem'));
			}
		});		
	});

	//Forgot Identity form || Disabled field validations based on input tags.
	$('#identify_account_form').off('invalid-form.validate').on('invalid-form.validate', function(){
		var formHandler = new FormHandler('#identify_account_form');
		var formObject = $(this);
		var successfulColumns = formObject.find('.form-success-column').length;
		//Either phone number or panera number is valid, do submit now.
		if(successfulColumns > 0){
			formObject.trigger('submitHandler', [formHandler]);
		}else{
			formHandler.showErrorMessage(formHandler.getDataMsg(formObject, 'msg-either-one'));
		}
	});

	$('#identify_account_form').off('submitHandler').on('submitHandler', function(event, form_handler){
		if(form_handler === undefined){
			var formHandler = new FormHandler('#identify_account_form');
		}else{
			var formHandler = form_handler;
		}

		var formObject = $(this);
		var dataString = formObject.serialize();
		$('#identify_account_form').addClass('hide-form-validation');
		
		formHandler.doQuickAjaxPostJson(function(response){
			if(typeof response !== 'boolean' && typeof response.accounts !== 'undefined'){
				var inputs = '';
				for(var i = 0, n = response.accounts.length; i < n; i++){
					id = 'user_name_selected_' + i;
					inputs += '<label for="' + id + '" class="table-listing"><span><input type="radio" value="' + response.accounts[i]['customer-id'] +'" name="user_name_selected" id="' + id + '"/></span><span>' + response.accounts[i]['first-name'] + '</span></label>';
				}
				$('#input_forgot_password_multiple_names').append(inputs);
				$('#user_name_selected_0').attr('checked', 'checked');
				$('#identify_account').addClass('hide');
				$('#duplicates_handler').removeClass('hide');
				$('#identify_account_form_duplicates').on('submit', function(event){
					event.preventDefault();
					var formHandler2 = new FormHandler('#identify_account_form_duplicates');
					var formObject = $(this);
					var dataString = formObject.serialize();
					formHandler2.clearError();
					formHandler2.doAjaxSubmitPostJson(formHandler2.getAjaxActionUrl(), dataString, function(response){
						if(response){
							$('#overlay-response .modal-content').addClass('hide');
							$('#overlay-response .modal-message').removeClass('hide').addClass('show');
							$('#overlay-response .modal-message').parent().removeClass('hide').addClass('show');
						}
					});
				});

			}else{
			if(response){
					$('#overlay-response .modal-content').addClass('hide');
					$('#overlay-response .modal-message').removeClass('hide').addClass('show');
					$('#overlay-response .modal-message').parent().removeClass('hide').addClass('show');
				}else{
					$('#identify_account_form').removeClass('hide-form-validation');
					formHandler.showErrorMessage(formHandler.getDataMsg(formObject, 'msg-error'));
					formHandler.clearError();
				}
			}
		});
	});

}
$(document).ready(function(){
	//Find-a-panera Results page from Navigation
    $("#nav-search-form").submit(function(event){
        event.preventDefault();
        var addBox = $("input#nav_find_panera"),addVal = $.trim('' + addBox.val());
        var limitBox = $("input#limit"),limitVal = $.trim('' + limitBox.val());
        if(addVal.length>0){
            window.location = $(this).attr("action") + '#' + encodeURIComponent(addVal) +'_' + limitVal;
            if(typeof drop === 'function'){
                drop();
            }
        }
    });
	//Find-a-panera Results page from Landing Page
    $("#find-panera-form").submit(function(event){
        event.preventDefault();
        var addBox = $("input#address"),addVal = $.trim(''+addBox.val());
        var limitBox = $("input#cafelimit"),limitVal = $.trim(''+limitBox.val());
         //return drop();
        if(addVal.length > 0){
            window.location = $(this).attr("action") + '#' + encodeURIComponent(addVal) +'_' + limitVal;
            if(typeof drop === 'function'){
                drop();
            }
        }
    });
});
