var LazyLoad=function(a,b){
this._initialize(a,b);
if(this.isFinish())
{
return;
}
this._initMode();
this.resize(true);
};
LazyLoad.prototype={_initialize:function(a,c){
this._elems=a;
this._rect={};
this._range={};
this._loadData=null;
this._timer=null;
this._lock=false;
this._index=0;
this._direction=0;
this._lastScroll={"left":0,"top":0};
this._setElems=function(){
};
var b=this._setOptions(c);
this.delay=b.delay;
this.threshold=b.threshold;
this.beforeLoad=b.beforeLoad;
this._onLoadData=b.onLoadData;
this._container=this._initContainer(document.getElementById("mainFrame").contentWindow.document.getElementById(this.options.container));
},_setOptions:function(a){
this.options={container:window,mode:"static",threshold:0,delay:100,beforeLoad:function(){
},onLoadData:function(){
}};
return lazy_$$.extend(this.options,a||{});
},_initContainer:function(a){
var b=document,d=a==window||a==b||!a.tagName||(/^(?:body|html)$/i).test(a.tagName);
if(d)
{
a=b.compatMode=='CSS1Compat'?b.documentElement:b.body;
}
var e=this,f=0,c=0;
this.load=lazy_$$F.bind(this._load,this);
this.resize=lazy_$$F.bind(this._resize,this);
this.delayLoad=function(){
e._delay(e.load);
};
this.delayResize=function(){
var h=a.clientWidth,g=a.clientHeight;
if(h!=f||g!=c)
{
f=h;
c=g;
e._delay(e.resize);
}
};
this._binder=d?window:document.getElementById("mainFrame").contentWindow;
lazy_$$E.addEvent(this._binder,"scroll",this.delayLoad);
d&&lazy_$$E.addEvent(this._binder,"resize",this.delayResize);
this._getContainerRect=d&&("innerHeight" in window)?function(){
return {"left":0,"right":window.innerWidth,"top":0,"bottom":window.innerHeight};
}:function(){
return {"left":a.offsetLeft,"right":("innerWidth" in window)?window.innerWidth:document.documentElement.clientWidth,"top":a.offsetTop,"bottom":("innerHeight" in window)?window.innerHeight:document.documentElement.clientHeight};
};
this._getScroll=d?function(){
return {"left":lazy_$$D.getScrollLeft(),"top":lazy_$$D.getScrollTop()};
}:function(){
var g=document.getElementById("mainFrame").contentWindow.document;
return {"left":(g.documentElement&&g.documentElement.scrollLeft)?g.documentElement.scrollLeft:g.body.scrollLeft,"top":(g.documentElement&&g.documentElement.scrollTop)?g.documentElement.scrollTop:g.body.scrollTop};
};
return a;
},_initMode:function(){
switch(this.options.mode.toLowerCase())
{case "vertical":
this._initStatic("vertical","vertical");
break;
case "horizontal":
this._initStatic("horizontal","horizontal");
break;
case "cross":
case "cross-vertical":
this._initStatic("cross","vertical");
break;
case "cross-horizontal":
this._initStatic("cross","horizontal");
break;
case "dynamic":
default:
this._loadData=this._loadDynamic;
}
},_initStatic:function(d,a){
var c=a=="vertical";
if(d=="cross")
{
this._crossDirection=lazy_$$F.bind(this._getCrossDirection,this,c?"_verticalDirection":"_horizontalDirection",c?"_horizontalDirection":"_verticalDirection");
}
var e=c?"top":"left",f=function(g,h){
return g._rect[e]-h._rect[e];
},b=function(g){
g._rect=this._getRect(g);
return g;
};
this._setElems=function(){
this._elems=lazy_$$A.map(this._elems,b,this).sort(f);
};
this._loadData=lazy_$$F.bind(this._loadStatic,this,"_"+d+"Direction",lazy_$$F.bind(this._outofRange,this,d,"_"+a+"BeforeRange"),lazy_$$F.bind(this._outofRange,this,d,"_"+a+"AfterRange"));
},_delay:function(c){
clearTimeout(this._timer);
if(this.isFinish())
{
return;
}
var b=this,a=this.delay;
if(this._lock)
{
this._timer=setTimeout(function(){
b._delay(c);
},a);
}
else{
this._lock=true;
c();
setTimeout(function(){
b._lock=false;
},a);
}
},_resize:function(a){
if(this.isFinish())
{
return;
}
this._rect=this._getContainerRect();
if(a)
{
this._setElems();
}
this._load(true);
},_load:function(a){
if(this.isFinish())
{
return;
}
var c=this._rect,d=this._getScroll(),b=d.left,f=d.top,e=Math.max(0,this.threshold|0);
this._range={top:c.top+f-e,bottom:c.bottom+f+e,left:c.left+b-e,right:c.right+b+e};
this.beforeLoad();
this._loadData(a);
},_loadDynamic:function(){
this._elems=lazy_$$A.filter(this._elems,function(a){
return !this._insideRange(a);
},this);
},_loadStatic:function(d,b,a,g){
d=this[d](g);
if(!d)
{
return;
}
var e=this._elems,h=this._index,c=[],k=[],f=[];
if(d>0)
{
c=e.slice(0,h);
for(var j=e.length;h<j;h++)
{
if(a(k,e[h]))
{
f=e.slice(h+1);
break;
}
}
h=c.length+k.length-1;
}
else{
f=e.slice(h+1);
for(;h>=0;h--)
{
if(b(k,e[h]))
{
c=e.slice(0,h);
break;
}
}
k.reverse();
}
this._index=Math.max(0,h);
this._elems=c.concat(k,f);
},_verticalDirection:function(a){
return this._getDirection(a,"top");
},_horizontalDirection:function(a){
return this._getDirection(a,"left");
},_getDirection:function(b,e){
var c=this._getScroll()[e],a=this._lastScroll;
if(b)
{
a[e]=c;
this._index=0;
return 1;
}
var d=a[e];
a[e]=c;
return c-d;
},_getCrossDirection:function(c,d,b){
var a;
if(!b)
{
a=this[c]();
d=this[d]();
if(!a&&!d)
{
return 0;
}
else if(!a)
{
if(this._direction)
{
a=-this._direction;
}
else{
b=true;
}
}
else if(d&&a*this._direction>=0)
{
b=true;
}
}
if(b)
{
this._lastScroll=this._getScroll();
this._index=0;
a=1;
}
return (this._direction=a);
},_insideRange:function(a,e){
var f=this._range,g=this._getRect(a),h=a._rect,c=(g.right>=f.left&&g.left<=f.right)||(h.right>=f.left&&h.left<=f.right),d=(g.bottom>=f.top&&g.top<=f.bottom)||(h.bottom>=f.top&&h.top<=f.bottom),b={"horizontal":c,"vertical":d,"cross":c&&d}[e||"cross"];
if(b)
{
this._onLoadData(a);
}
return b;
},_outofRange:function(d,a,c,b){
if(!this._insideRange(b,d))
{
c.push(b);
return this[a](b._rect);
}
},_horizontalBeforeRange:function(a){
return a.right<this._range.left;
},_horizontalAfterRange:function(a){
return a.left>this._range.right;
},_verticalBeforeRange:function(a){
return a.bottom<this._range.top;
},_verticalAfterRange:function(a){
return a.top>this._range.bottom;
},_getRect:function(c){
var b=c,a=0,d=0;
while(b)
{
a+=b.offsetLeft;
d+=b.offsetTop;
b=b.offsetParent;
}
return {"left":a,"right":a+c.offsetWidth,"top":d,"bottom":d+c.offsetHeight};
},isFinish:function(){
if(!this._elems||!this._elems.length)
{
this.dispose();
return true;
}
else{
return false;
}
},dispose:function(a){
clearTimeout(this._timer);
if(this._elems||this._binder)
{
if(a&&this._elems)
{
lazy_$$A.forEach(this._elems,this._onLoadData,this);
}
lazy_$$E.removeEvent(this._binder,"scroll",this.delayLoad);
lazy_$$E.removeEvent(this._binder,"resize",this.delayResize);
this._elems=this._binder=null;
}
}};
var lazy_$$,lazy_$$B,lazy_$$A,lazy_$$F,lazy_$$D,lazy_$$E,lazy_$$S;
var container_name;
(function(){
var g,b,a,f,c,d,h;
g=function(e){
return "string"==typeof e?document.getElementById(e):e;
};
g.extend=function(e,k){
for(var j in k)
{
e[j]=k[j];
}
return e;
};
g.deepextend=function(j,l){
for(var k in l)
{
var e=l[k];
if(j===e)
continue;
if(typeof e==="object")
{
j[k]=arguments.callee(j[k]||{},e);
}
else{
j[k]=e;
}
}
return j;
};
g.wrapper=function(j,k){
var e=function(){
j.apply(this,arguments);
};
var l=function(){
};
l.prototype=k.prototype;
e.prototype=new l();
return e;
};
b=(function(k){
var e={msie:/msie/.test(k)&&!/opera/.test(k),opera:/opera/.test(k),safari:/webkit/.test(k)&&!/chrome/.test(k),firefox:/firefox/.test(k),chrome:/chrome/.test(k)};
var l="";
for(var j in e)
{
if(e[j])
{
l="safari"==j?"version":j;
break;
}
}
e.version=l&&RegExp("(?:"+l+")[\\/: ]([\\d.]+)").test(k)?RegExp.$1:"0";
e.ie=e.msie;
e.ie6=e.msie&&parseInt(e.version,10)==6;
e.ie7=e.msie&&parseInt(e.version,10)==7;
e.ie8=e.msie&&parseInt(e.version,10)==8;
return e;
})(window.navigator.userAgent.toLowerCase());
a=function(){
var j={isArray:function(k){
return Object.prototype.toString.call(k)==="[object Array]";
},indexOf:function(k,l,m){
if(k.indexOf)
{
return isNaN(m)?k.indexOf(l):k.indexOf(l,m);
}
else{
var n=k.length;
m=isNaN(m)?0:m<0?Math.ceil(m)+n:Math.floor(m);
for(;m<n;m++)
{
if(k[m]===l)
{
return m;
}
}
return -1;
}
},lastIndexOf:function(k,l,m){
if(k.lastIndexOf)
{
return isNaN(m)?k.lastIndexOf(l):k.lastIndexOf(l,m);
}
else{
var n=k.length;
m=isNaN(m)||m>=n-1?n-1:m<0?Math.ceil(m)+n:Math.floor(m);
for(;m>-1;m--)
{
if(k[m]===l)
{
return m;
}
}
return -1;
}
}};
function e(o,k)
{
if(undefined===o.length)
{
for(var n in o)
{
if(false===k(o[n],n,o))
break;
}
}
else{
for(var l=0,m=o.length;l<m;l++)
{
if(l in o)
{
if(false===k(o[l],l,o))
break;
}
}
}
}
e({forEach:function(l,k,m){
e.call(m,l,function(){
k.apply(m,arguments);
});
},map:function(l,k,n){
var m=[];
e.call(n,l,function(){
m.push(k.apply(n,arguments));
});
return m;
},filter:function(l,k,n){
var m=[];
e.call(n,l,function(o){
k.apply(n,arguments)&&m.push(o);
});
return m;
},every:function(l,k,n){
var m=true;
e.call(n,l,function(){
if(!k.apply(n,arguments))
{
m=false;
return false;
}
});
return m;
},some:function(l,k,n){
var m=false;
e.call(n,l,function(){
if(k.apply(n,arguments))
{
m=true;
return false;
}
});
return m;
}},function(k,l){
j[l]=function(n,m,o){
if(n[l])
{
return n[l](m,o);
}
else{
return k(n,m,o);
}
};
});
return j;
}();
f=(function(){
var e=Array.prototype.slice;
return {bind:function(k,l){
var j=e.call(arguments,2);
return function(){
return k.apply(l,j.concat(e.call(arguments)));
};
},bindAsEventListener:function(k,l){
var j=e.call(arguments,2);
return function(m){
return k.apply(l,[d.fixEvent(m)].concat(j));
};
}};
})();
c={getScrollTop:function(j){
var e=j?j.ownerDocument:document;
return e.documentElement.scrollTop||e.body.scrollTop;
},getScrollLeft:function(j){
var e=j?j.ownerDocument:document;
return e.documentElement.scrollLeft||e.body.scrollLeft;
},contains:document.defaultView?function(e,j){
return !!(e.compareDocumentPosition(j)&16);
}:function(e,j){
return e!=j&&e.contains(j);
},rect:function(l){
var j=0,p=0,o=0,e=0;
if(!l.getBoundingClientRect||b.ie8)
{
var k=l;
while(k)
{
j+=k.offsetLeft;
p+=k.offsetTop;
k=k.offsetParent;
}
o=j+l.offsetWidth;
e=p+l.offsetHeight;
}
else{
var m=l.getBoundingClientRect();
j=o=c.getScrollLeft(l);
p=e=c.getScrollTop(l);
j+=m.left;
o+=m.right;
p+=m.top;
e+=m.bottom;
}
return {"left":j,"top":p,"right":o,"bottom":e};
},clientRect:function(e){
var j=c.rect(e),k=c.getScrollLeft(e),l=c.getScrollTop(e);
j.left-=k;
j.right-=k;
j.top-=l;
j.bottom-=l;
return j;
},curStyle:document.defaultView?function(e){
return document.defaultView.getComputedStyle(e,null);
}:function(e){
return e.currentStyle;
},getStyle:document.defaultView?function(e,j){
var k=document.defaultView.getComputedStyle(e,null);
return j in k?k[j]:k.getPropertyValue(j);
}:function(e,j){
var m=e.currentStyle;
if(j=="opacity")
{
if(/alpha\(opacity=(.*)\)/i.test(m.filter))
{
var k=parseFloat(RegExp.$1);
return k?k/100:0;
}
return 1;
}
if(j=="float")
{
j="styleFloat";
}
var l=m[j]||m[h.camelize(j)];
if(!/^\-?\d+(px)?$/i.test(l)&&/^\-?\d/.test(l))
{
m=e.style,left=m.left,rsLeft=e.runtimeStyle.left;
e.runtimeStyle.left=e.currentStyle.left;
m.left=l||0;
l=m.pixelLeft+"px";
m.left=left;
e.runtimeStyle.left=rsLeft;
}
return l;
},setStyle:function(e,k,l){
if(!e.length)
{
e=[e];
}
if(typeof k=="string")
{
var j=k;
k={};
k[j]=l;
}
a.forEach(e,function(m){
for(var n in k)
{
var o=k[n];
if(n=="opacity"&&b.ie)
{
m.style.filter=(m.currentStyle.filter||"").replace(/alpha\([^)]*\)/,"")+"alpha(opacity="+o*100+")";
}
else if(n=="float")
{
m.style[b.ie?"styleFloat":"cssFloat"]=o;
}
else{
m.style[h.camelize(n)]=o;
}
}
});
}};
d=(function(){
var e,n,k=1;
if(window.addEventListener)
{
e=function(p,r,q){
p.addEventListener(r,q,false);
};
n=function(p,r,q){
p.removeEventListener(r,q,false);
};
}
else{
e=function(p,s,q){
if(!q.$$guid)
q.$$guid=k++;
if(!p.events)
p.events={};
var r=p.events[s];
if(!r)
{
r=p.events[s]={};
if(p["on"+s])
{
r[0]=p["on"+s];
}
}
r[q.$$guid]=q;
p["on"+s]=l;
};
n=function(p,r,q){
if(p.events&&p.events[r])
{
delete p.events[r][q.$$guid];
}
};
function l()
{
var s=true,p=j();
var q=this.events[p.type];
for(var r in q)
{
this.$$handleEvent=q[r];
if(this.$$handleEvent(p)===false)
{
s=false;
}
}
return s;
}
}
function j(p)
{
if(p)
{
return p;
}
p=document.getElementById("mainFrame").contentWindow.event;
p.pageX=p.clientX+c.getScrollLeft(p.srcElement);
p.pageY=p.clientY+c.getScrollTop(p.srcElement);
p.target=p.srcElement;
p.stopPropagation=o;
p.preventDefault=m;
switch(p.type)
{case "mouseout":
p.relatedTarget=p.toElement;
break;
case "mouseover":
p.relatedTarget=p.fromElement;
break;
}return p;
}
function o()
{
this.cancelBubble=true;
}
function m()
{
this.returnValue=false;
}
return {"addEvent":e,"removeEvent":n,"fixEvent":j};
})();
h={camelize:function(e){
return e.replace(/-([a-z])/ig,function(j,k){
return k.toUpperCase();
});
}};
if(b.ie6)
{
try{
document.execCommand("BackgroundImageCache",false,true);
}
catch(i)
{
}
}
lazy_$$=g;
lazy_$$B=b;
lazy_$$A=a;
lazy_$$F=f;
lazy_$$D=c;
lazy_$$E=d;
lazy_$$S=h;
})();
var ImagesLazyLoad=lazy_$$.wrapper(function(a){
this._initialize(a);
if(this.isFinish())
{
return;
}
this._initMode();
this.resize(true);
},LazyLoad);
lazy_$$.extend(ImagesLazyLoad.prototype,{_initialize:function(e){
LazyLoad.prototype._initialize.call(this,[],e);
var d=this.options;
this.onLoad=d.onLoad;
var a=this._attribute=d.attribute;
var c=d.getSrc,b=lazy_$$F.bind(this._filter,this,d["class"],c?function(f){
return c(f);
}:function(f){
return f.getAttribute(a)||f.src;
},d.holder);
this._elems=lazy_$$A.filter(d.images||this._container.getElementsByTagName("img"),b);
this._hasAttribute=lazy_$$B.ie6||lazy_$$B.ie7?function(f){
return "lazysrc" in f||"lazybackground" in f;
}:function(f){
return f.hasAttribute("lazysrc")||f.hasAttribute("lazybackground");
};
},_setOptions:function(a){
return LazyLoad.prototype._setOptions.call(this,lazy_$$.extend({images:undefined,attribute:"lazysrc",holder:"","class":"",getSrc:undefined,onLoad:function(){
}},lazy_$$.extend(a,{onLoadData:this._onLoadData})));
},_filter:function(a,b,c,d){
if(a&&(" "+d.className+" ").indexOf(" "+a+" ")==-1)
{
return false;
}
var e=b(d);
if(!e)
{
return false;
}
if(e==d.src)
{
if(d.complete||lazy_$$B.chrome||lazy_$$B.safari)
{
return false;
}
if(d.getAttribute("src")!=null)
{
d.removeAttribute("src");
}
else if(d.getAttribute("background")!=null)
{
d.removeAttribute("background");
}
}
if(c)
{
}
return true;
},_onLoadData:function(a){
if(this._hasAttribute(a))
{
if(a.getAttribute("lazysrc")!=null)
{
a.src=a.getAttribute("lazysrc");
a.removeAttribute("lazysrc");
this.onLoad(a);
}
else if(a.getAttribute("lazybackground")!=null)
{
a.setAttribute("background",a.getAttribute("lazybackground"));
a.removeAttribute("lazybackground");
this.onLoad(a);
}
}
}});
