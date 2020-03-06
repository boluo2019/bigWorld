(function(d,c){
var a={open:function(e){
var f=this;
f._mnOpenServices|=e;
f._mbInit&&QMWebpush.getInst().open(f._mnOpenServices);
return f;
},close:function(g,f,e){
var h=this;
h._mnOpenServices&=~g;
if(h._mbInit)
{
e&&QMWebpush.getInst().close(g);
f&&h.read(g,"");
}
return h;
},hideAll:function(e){
var h=this,g=now(),f;
if(h._mbInit)
{
d.clearTimeout(h._mnHideTimeout);
h._mnHideTimeout=setTimeout(function(){
for(var j=0;j<h._nTOTALSERVICES;j++)
{
f=1<<j;
if(h._moDataCache[f]&&g-h._moDataTime[f]>e)
{
h._delData(f,"");
}
}
h._cleanupTip(true)._setDocTitle(true);
},e);
}
return h;
},read:function(e,f){
var h=this,j,g;
if(h._mbInit)
{
for(j=0;j<h._nTOTALSERVICES;j++)
{
if((g=1<<j)&e)
{
h._delData(g,f);
}
}
h._updateTip();
}
return h;
},_sMAINFRAMEWEBPUSHTIPTITLE:"mfwebpushtiptitle",_nTOTALSERVICES:31,_mnOpenServices:0,_moDataCache:{},_moDataTime:{},_moTipCache:{},_mbInit:false,_addData:function(e,f){
var g=this;
if(g._mnOpenServices&e)
{
g._moDataTime[e]=now();
g._moDataCache[e]=(g._moDataCache[e]||[]).concat(f);
}
return g;
},_delData:function(e,f){
var g=this;
if(f==="")
{
g._moDataCache[e]=[];
}
else{
for(var h=g._moDataCache[e],j=(h?h.length:0)-1;j>=0&&h[j].mailid!=f;j--)
;
j>=0&&h.splice(j,1);
}
return g;
},_cleanupTip:function(e){
var h=this,g=h._moDataCache,j=h._moTipCache,f,k;
for(k=0;k<h._nTOTALSERVICES;k++)
{
f=1<<k;
if(!(h._mnOpenServices&f)||!(g[f]&&g[f].length)||(j[f]&&(h._moDataTime[f]!=j[f].r||j[f].isClose())))
{
j[f]&&j[f].close(e);
j[f]=null;
}
}
return h;
},_updateTip:function(){
var k=this,h=k._moDataCache,l=k._moTipCache,j=[],e,f,g;
k._cleanupTip(false);
for(var m=0;m<k._nTOTALSERVICES;m++)
{
e=1<<m;
f=h[e]&&h[e].length;
if((k._mnOpenServices&e)&&f)
{
if(!l[e])
{
j.push(e);
}
else{
g=h[e][f-1];
l[e].update({count:f,content:b[e].replace(g)});
}
}
}
m=-1;
(function(){
if(++m<j.length)
{
var i=arguments.callee;
e=j[m],f=h[e].length,g=h[e][f-1];
(l[e]=new k._popuptip({type:e,count:f,eviltype:e==32?g.eviltype:"",content:b[e].replace(g),onclick:k._action(e,h[e]),onload:i})).r=k._moDataTime[e];
}
})();
return k;
},_reloadLeftWinNoCache:function(){
var e=S("leftFrame",getTop());
{
e.src=T('/cgi-bin/folderlist?sid=$sid$&r=$rand$&cache=false').replace({sid:getSid(),rand:Math.random()});
}
},_moReloadWinServices:0,_moReloadWinTimeout:null,_mnReloadTime:0,_reloadWin:function(e){
var g=this;
var f=500;
if(((new Date()).getTime()-g._mnReloadTime)<5000)
{
f=5000;
if(g._moReloadWinTimeout)
{
return;
}
}
else{
if(g._moReloadWinTimeout)
{
clearTimeout(g._moReloadWinTimeout);
}
}
g._moReloadWinServices|=e;
g._moReloadWinTimeout=setTimeout(function(){
var h=false;
_sUrl=getMainWin().location.href,_oReloadWinServices=g._moReloadWinServices,_sTODAY_URL="cgi-bin/today",_sMAIL_LIST_URL="cgi-bin/mail_list";
g._moReloadWinServices=0;
g._moReloadWinTimeout=null;
g._mnReloadTime=(new Date()).getTime();
switch(false)
{case !(_oReloadWinServices&1):
h=(_sUrl.indexOf(_sTODAY_URL)>-1&&_sUrl.indexOf("t=compose_meeting")==-1)||(_sUrl.indexOf(_sMAIL_LIST_URL)>-1&&_sUrl.indexOf("page=0")>-1&&QMMailList.getCBInfo(getMainWin()).oMail.length==0);
g._reloadLeftWinNoCache();
if(h)
{
setTimeout(function(){
reloadFrm(getMainWin());
},500);
}
break;
case !(_oReloadWinServices&2):
h=_sUrl.indexOf(_sTODAY_URL)>-1;
g._reloadLeftWinNoCache();
if(h)
{
setTimeout(function(){
reloadFrm(getMainWin());
},500);
}
break;
}if(h)
{
try{
getMainWin().frameElement.setAttribute(g._sMAINFRAMEWEBPUSHTIPTITLE,"1");
}
catch(i)
{
}
}
},f);
return g;
},_setDocTitle:function(f){
try{
var m=this,n=getTop(),o=n.document,k=getMainWin(),l=k.document,j=k.frameElement;
if(f&&j.getAttribute(m._sMAINFRAMEWEBPUSHTIPTITLE)!="1")
{
o.title!=l.title&&(o.title=l.title);
}
else{
var h=(m._moDataCache[1]||{}).length,i=(m._moDataCache[2]||{}).length,g=(m._moDataCache[4]||{}).length;
if(h||i||g)
{
o.title=b._TITLE.replace({email:h,sms:i,plp:g});
}
k.frameElement.setAttribute(m._sMAINFRAMEWEBPUSHTIPTITLE,"0");
}
}
catch(p)
{
}
return m;
},_closePreviewer:function(){
var g=this,h=getTop(),e=h.document.body.childNodes;
for(var k=0,o=e.length;k<o;k++)
{
if(e[k].tagName&&h.hasClass(e[k],"previewer"))
{
for(var n=0,f=e[k].childNodes,p=f.length;n<p;n++)
{
if(f[n].tagName&&h.hasClass(f[n],"mask"))
{
h.fireMouseEvent(f[n],"click");
break;
}
}
break;
}
}
return g;
},_action:function(e,f){
var g=this;
return function(h){
preventDefault(h);
stopPropagation(h);
g.read(e,"");
var j=getEventTarget(h);
if(j.className=="notify_close")
{
return;
}
;g._closePreviewer();
switch(e)
{case 1:
if(f.length>1||!f[0].mailid)
{
goUrlMainFrm("/cgi-bin/mail_list?page=0&flag=new&s=unread&folderid=1&folderid=3&folderid=8&folderid=9&folderid=11&folderid=personal&folderid=pop&folderid=subscribe&fun=slock&loc=webpushtip,,,1&sid="+QMWebpush.sid(),true,true);
}
else{
var i=f[0];
rdVer(i.mailid,1);
RD(null,i.mailid,1,1,i.folderid,0,0,0,'');
}
break;
case 2:
goUrlMainFrm([location.protocol,"//msgopt.mail.qq.com/cgi-bin/readtemplate?folderid=9&page=0&t=sms_list_v2&loc=webpushtip,,,1&sid=",QMWebpush.sid()].join(''),true,true);
break;
case 4:
goUrlMainFrm("/cgi-bin/bottle_panel?t=bottle&plpfrom=webmail&loc=webpushtip,,,1&sid="+QMWebpush.sid(),true,true);
break;
}
};
}};
var b={_TITLE:TE(['\u6536\u5230','$@$if($email$)$@$ $email$\u5C01\u65B0\u90AE\u4EF6$@$endif$@$','$@$if($sms$)$@$ $sms$\u6761\u65B0\u77ED\u6D88\u606F$@$endif$@$','$@$if($plp$)$@$ $plp$\u4E2A\u65B0\u6F02\u6D41\u74F6$@$endif$@$']),_CONTAINER:T(['<div id="webpushtipcontainer" class="webpushtipoutter"></div>']),_TIP:TE(['<div class="webpushtipinner">','<div id="webpushtip$type$" style="visibility:hidden;" class="newmailNotifyItem $@$if($type$==2)$@$notify_sms$@$else if($type$==4)$@$notify_bottle$@$else$@$notify_mail$@$endif$@$">','$@$sec value$@$','<div class="newmailNotify">','<a nocheck="true" id="webpushtip$type$close" class="notify_close" href="javascript:void(0);" title="\u5173\u95ED"></a>','$@$if($type$==32)$@$','$@$if($eviltype$==1||$eviltype$==5)$@$','<div class="notify_type">','<span></span>','<label><em>$count$</em></label>','</div>','$@$else if($eviltype$==2)$@$','<div class="notify_type">','<span></span>','<label><em>$count$</em></label>','</div>','$@$endif$@$','$@$else$@$','<div class="notify_type">','<span></span>','<label><em>$count$</em></label>','</div>','$@$endif$@$','<div class="notify_content">$content$</div>','</div>','$@$endsec$@$','</div>','</div>']),1:TE(['<p class="notify_account">$senderNick$ $@$if($senderEmail$)$@$<span class="addrtitle notify_accountmail">&lt;$senderEmail$&gt;</span>$@$endif$@$</p>','<p class="notify_title">$@$if($subject$)$@$$subject$$@$else$@$(\u65E0\u4E3B\u9898)$@$endif$@$</p>','<p class="notify_digest">$summary$</p>']),2:TE(['<p class="notify_account">$senderNick$ $@$if($senderEmail$)$@$<span class="addrtitle notify_accountmail">&lt;$senderEmail$&gt;</span>$@$endif$@$</p>','<p class="notify_digest">$subject$</p>']),4:T(['<p class="notify_title">\u60A8\u6536\u5230\u4E00\u4E2A\u65B0\u7684\u6F02\u6D41\u74F6</p>']),32:T(['<p class="notify_title">\u8BE5\u5730\u5740\u5B58\u5728\u4E0D\u826F\u5185\u5BB9\uFF0C\u5EFA\u8BAE\u505C\u6B62\u8BBF\u95EE</p>'])};
a._popuptip=function(e){
this._init(e);
};
a._popuptip.prototype={update:function(e){
var f=this;
if(!f._mbClosing)
{
for(var g in e)
{
f._moCfg[g]=e[g];
}
f._moDom.innerHTML=b._TIP.replace(f._moCfg,"value");
}
return f;
},isClose:function(){
return this._mbClosing;
},close:function(e){
var g=this,f=function(){
removeSelf(g._moDom.parentNode);
g._moDom=null;
};
if(!g._mbClosing)
{
g._mbClosing=true;
e?g._animationFadeOut(f):f();
}
return g;
},_getDomId:function(e){
return "webpushtip"+this._moCfg.type+(e||"");
},_animationExpand:function(e){
var j=this,g=j._moDom,f=new qmAnimation({from:1,to:100}),i=g.style,h=-g.offsetHeight;
i.bottom=h+"px";
i.visibility="visible";
f.play({speed:"slow",onaction:function(l,k){
if(!j._mbClosing)
{
var m=Math.floor(h*(1-k));
i.bottom=m+"px";
}
},oncomplete:function(l,k){
!j._mbClosing&&(i.bottom="0px");
e&&e();
}});
return j;
},_animationFadeOut:function(e){
var h=this,g=h._moDom,f=new qmAnimation({from:1,to:100});
f.play({speed:"slow",onaction:function(j,i){
setOpacity(h._moDom,Math.abs(i-1));
},oncomplete:function(j,i){
e&&e();
}});
return h;
},_initEvent:function(){
var e=this;
addEvent(e._moDom,"click",function(f){
if(!e._mbClosing)
{
e.close(true);
QMWebpush.callBack(e,e._moCfg.onclick,[f]);
}
});
return e;
},_createDom:function(){
var f=this,g="webpushtipcontainer",e=S(g,f._moWin);
if(!e)
{
insertHTML(f._moWin.document.body,"beforeEnd",b._CONTAINER);
e=S(g,f._moWin);
}
debug(f._moCfg,2);
insertHTML(e,"afterBegin",b._TIP.replace(f._moCfg));
f._moDom=S(f._getDomId(),f._moWin);
f._moContainer=e;
return f;
},_init:function(e){
var f=this;
f._moCfg=e;
f._moWin=getTop();
f._mbClosing=false;
return f._createDom()._animationExpand(function(){
f._initEvent();
QMWebpush.callBack(f,e.onload);
});
}};
(function(){
var e=a,f=getTop();
f.loadJsFile(f.getPath("js")+f.getFullResSuffix("qmwebpush.js"),true,f.document,function(){
QMWebpush.getInst().addEvent((1<<e._nTOTALSERVICES-1)*2-1,function(g,h){
e._addData(g,h)._reloadWin(g)._updateTip()._setDocTitle();
}).open(e._mnOpenServices);
e._mbInit=true;
});
})();
d.QMWebpushTip=a;
})(window);
