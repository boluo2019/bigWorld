(function(e,f){function u(e){return 25<(e=e||"").length&&(e=[e.substr(0,25)+"<wbr/>"+u(e.substr(25))].join("")),e}function b(e){return e==f.g_admuin}function v(e){return e==f.g_admuin+"@qq.com"||0==e.toLowerCase().indexOf("postmaster@")}var t,h;t=(h=function(e){this._moWin=e,this._mnStatus,this._moTriggerDom,this._mnTimeId,this._moInfoCard,this._msUin,this._msDomId,this._msEmail,this._moTmpTR,this._moAjax}).prototype,(h._TEMPLATE={})._INSTANCE="_QmProfileTipsInst_",h._TEMPLATE._OPERATE_COMMON_ITEM=TE(["$@$if($addrid$)$@$",'<a href="javascript:;" ck="profile_add" email="$email$" addrid="$addrid$" mailid="$mailid$">\u7f16\u8f91</a>',"$@$else$@$",'<a href="javascript:;" ck="profile_add" email="$email$" name="$jsname$" mailid="$mailid$">\u6dfb\u52a0</a>',"$@$endif$@$",'<a href="javascript:;" ck="profile_history" email="$email$" name="$jsname$" class="personOperate3_last">\u5f80\u6765\u90ae\u4ef6</a>','<a href="javascript:;" ck="profile_compose" email="$email$" name="$jsname$">\u53d1\u90ae\u4ef6</a>',""]),h._TEMPLATE._OPERATE=TE(["$@$if($bindqq$>10000)$@$",'<div class="personOperate personOperate4">',h._TEMPLATE._OPERATE_COMMON_ITEM,'<a href="javascript:;" ck="profile_qq" bindqq="$bindqq$" class="personOperate4_last">\u53d1QQ\u6d88\u606f</a>',"</div>","$@$else$@$",'<div class="personOperate personOperate3">',h._TEMPLATE._OPERATE_COMMON_ITEM,"</div>","$@$endif$@$"]),h._TEMPLATE._INFOCARD_DETAIL=TE(['<div class="tipInner">','<div class="left" style="width:43px;  padding:15px 5px 0 10px;">','<div class="icon_user" style="background:#fff url($icon$); float:none; width:40px; height:40px; overflow:hidden; display:block; margin-left:0px;">','<a><img src="$images_path$spacer.gif" title="$title.DATA$" class="iconMask_gray" ></a>','<img src="$images_path$spacer.gif" class="icon_getblogW" uin="$uin$" name="qqicon" id="icon_getblogW" style="position: absolute;$@$if($ownfeedcount$>0)$@$display:inline;$@$endif$@$" title="\u5df2\u8ba4\u9886\u535a\u5ba2" />',"</div>","</div>",'<div class="left gray" style="padding:17px 0 5px 0; width:230px; *margin-left:-2px;" >','<div class="b_size"><a class="green bold b_size">$dispname$</a>&nbsp;',"</div>",'<div style="margin:5px 0;" class="graytext">$dispemail$</div>',"</div>",'<div class="clr"></div>',"$@$if($bNotMyCard$)$@$",h._TEMPLATE._OPERATE,"$@$endif$@$","</div>"]),h._TEMPLATE._INFOCARD_GMEMBERS=TE(['<div class="tipInner">','<div style="overflow-y: auto; width: 320px;$@$if($count$>9)$@$height:258px;$@$endif$@$" class="txtflow">',' <div class="menu_item_nofun" style="height:35px;line-height:35px;">','<div class="bold black" style="height:15px;line-height:15px;padding-top:10px;padding-bottom:10px"><img title="\u90ae\u4ef6\u7fa4\u7ec4" src="$images_path$spacer.gif" class="lm_groupAutoIcon" style="margin-left:0;"/>$groupName$&nbsp;\u7fa4\u7ec4\u6210\u5458</div>'," </div>",' <div class="menu_item_nofun" style="height:8px;line-height:8px;">','  <div style="background:#CCC; height:1px; overflow:hidden; margin:0 15px 0 0;"></div>'," </div>"," $@$if($count$==0)$@$",' <div class="menu_item_nofun" style="height:24px;line-height:24px;">','  <span style="color:#666">\u8be5\u7fa4\u7ec4\u6ca1\u6709\u6210\u5458</span>'," </div>"," $@$else$@$"," $@$for($members$)$@$",' <div class="menu_item_nofun" style="height:24px;line-height:24px;">',"  $@$if($bStatus$==1)$@$",'   <span style="color:#333;margin-left:17px;">$sNickName$</span>&nbsp;<span class="tcolor">&lt;$sEmail$&gt;</span>',"  $@$else if($bStatus$==2)$@$",'   <img title="\u90ae\u4ef6\u7fa4\u7ec4" src="$images_path$spacer.gif" class="lm_groupAutoIcon" style="margin-left:0;"/>','   <span style="color:#333">$sNickName$</span>&nbsp;<span class="tcolor">&lt;$sEmail$&gt;</span>',"  $@$else if($bStatus$==3)$@$",'   <span style="color:#333;margin-left:17px;">$sNickName$</span>&nbsp;<span class="tcolor">(\u90e8\u95e8)</span>',"  $@$endif$@$"," </div>"," $@$endfor$@$"," $@$endif$@$",' <div class="menu_item_nofun">','   <div style="height:5px;overflow:hidden;"></div>'," </div>","</div>","</div>"]),h._TEMPLATE._CORP_PROFILETIPS_DETAIL=TE(['<div class="tipInner tipVerified">',"$@$if($sCorpHomePage$)$@$",'<a href="$sCorpHomePage$" target="_blank" class="ico_verified pointer"></a>','<a class="tipVerified_logo pointer" href="$sCorpHomePage$" target="_blank" title="$@$eval htmlEncode($sCorpName$)$@$" style="background-image:url($sLogoUrl$);"></a>',"$@$else$@$",'<span class="ico_verified"></span>','<span class="tipVerified_logo" title="$@$eval htmlEncode($sCorpName$)$@$" style="background-image:url($sLogoUrl$);"></span>',"$@$endif$@$",'<div class="tipVerified_name green" title="$@$eval htmlEncode($sCorpName$)$@$" test="$sLogoUrl$" style="padding:20px 0 3px;">$@$eval htmlEncode($sCorpName$)$@$</div>','<div class="tipVerified_addr" style="word-break:break-all;"><span class="">\u5730\u5740\uff1a</span><span>$@$eval htmlEncode($sCorpAddr$)$@$</span></div>','<div class="tipVerified_desc graytext" style="word-break:break-all;">$@$eval htmlEncode($sCorpRemark$)$@$</div>','<div class="tipVerified_from"><a class="pointer" href="http://service.exmail.qq.com/cgi-bin/help?subtype=1&&id=23&&no=1001001" target="_blank">\u817e\u8baf\u4f01\u4e1a\u90ae\u7bb1\u8ba4\u8bc1</a></div>',"</div>"]),h._TEMPLATE._INFOCARD=TE(["$@$if($bIsQywx$)$@$",'<div class="profileTip" id="infocard_$id$" style="border: 1px solid #D8D8D8;box-shadow: 0px 1px 10px 0px rgba(0,0,0,0.24);padding: 0;border-radius: 4px display:none;">','\t<div class="tipInner" style="width: 370px;">','\t\t<div class="qywx_user_card">','\t\t\t<label class="biz_icon biz_icon_card_right_corner right"></label>','\t\t\t<div class="user_info">','\t\t\t\t<div class="user_detail">','\t\t\t\t<img src="$headImg$" class="avatar left">','\t\t\t\t\t<div class="line">','\t\t\t\t\t\t$qywx_name$<label class="biz_icon biz_icon_icon_$@$if($gender$==2)$@$female$@$else$@$male$@$endif$@$"></label>','           <span class="tip">\u6b63\u5728\u4f7f\u7528<a style="color: #275770; cursor: pointer;" href="$@$if($vcode$=="")$@$https://work.weixin.qq.com/$@$else$@$https://work.weixin.qq.com/wework_admin/mail_promote_activedcnt?vccode=$vcode$&from=exmail_web_profile_stat$@$endif$@$" target="_blank">\u4f01\u4e1a\u5fae\u4fe1</a></span>',"\t\t\t\t\t</div>",'\t\t\t\t\t<div class="line">',"\t\t\t\t\t\t$english_name$","\t\t\t\t\t</div>",'\t\t\t\t\t<div class="line">','\t\t\t\t\t\t<span class="pos">$positon$</span>',"\t\t\t\t\t</div>","\t\t\t\t</div>",'\t\t\t\t<div class="info">','\t\t\t\t\t<div class="line">','\t\t\t\t\t\t<label class="left">\u624b\u673a</label><label class="">$mobile$</label>',"\t\t\t\t\t</div>",'\t\t\t\t$@$if($landline$!="")$@$','\t\t\t\t\t<div class="line">','\t\t\t\t\t\t<label class="left">\u7535\u8bdd</label><label class="">$landline$</label>',"\t\t\t\t\t</div>","\t\t\t\t$@$endif$@$",'\t\t\t\t\t<div class="line">','\t\t\t\t\t\t<label class="left">\u90ae\u7bb1</label><label class="">$email$</label>',"\t\t\t\t\t</div>\t\t\t","\t\t\t\t</div>",'\t\t\t\t$@$if($party_path$!="")$@$','\t\t\t\t<div class="info">','\t\t\t\t\t<div class="line">','\t\t\t\t\t\t<label class="left">\u90e8\u95e8</label><label class="" style="display: block; margin-left: 36px;">$party_path$</label>',"\t\t\t\t\t</div>\t","\t\t\t\t</div>","\t\t\t\t$@$endif$@$","\t\t\t</div>",'\t\t\t<div class="operate_cnt">','\t\t\t\t<a href="javascript:;" ck="profile_compose" email="$email$" name="$jsname$" >\u5199\u90ae\u4ef6</a>','\t\t\t\t<a href="$@$if($active$==1)$@$wxwork://message/?email=$email$$@$else$@$javascript:;$@$endif$@$" target="_blank" ck="profile_qywx_send_msg" class="btn_operate" ><label class="biz_icon biz_icon_mail_list_qywx_logo"></label>\u53d1\u6d88\u606f</a>','\t\t\t\t<a href="javascript:;" ck="profile_qywx_call" class="btn_operate last" email="$email$">\u6253\u7535\u8bdd</a>',"\t\t\t</div>","\t\t</div>   ","\t</div>   ","</div>","$@$else$@$",'<div class="profileTip" id="infocard_$id$" style="display:none;user-select:none">','<div class="infoArrowUp" id="infoarrowup_$id$" style="display:none;"></div>',"$@$if($bIsCorp$)$@$",h._TEMPLATE._CORP_PROFILETIPS_DETAIL,"$@$else if($bGroupMem$)$@$",h._TEMPLATE._INFOCARD_GMEMBERS,"$@$else$@$",h._TEMPLATE._INFOCARD_DETAIL,"$@$endif$@$",'<div class="infoArrowDown" id="infoarrowdown_$id$" style="display:none;"></div>',"</div>","$@$endif$@$"]),t._setStatus=function(e){var t=this;switch(t._mnStatus=e){case 0:t._showInfoCard(!1);break;case 1:t._mnTimeId=setTimeout(function(){1==t._mnStatus&&(t._moInfoCard?t._setStatus(2):t._buildInfoCard())},t._mnWaitTime);break;case 2:try{if("imglogo"==t._moTriggerDom.id)break}catch(i){}t._showInfoCard(!0),t._computeCardPos();break;case 3:t._mnTimeId=setTimeout(function(){3==t._mnStatus&&(t._setStatus(0),t._moTmpTR&&t._doMouseOver(t._moTmpTR))},100)}},t._showInfoCard=function(e){var t=this,i=t._moInfoCard;i&&(e&&!f.isShow(i)||!e&&f.isShow(i))&&f.qmAnimation.play(i,{from:e?.5:1,to:e?1:.5,speed:"fast",onaction:function(e){show(i,!0),setOpacity(i,e)},oncomplete:function(){f.setOpacity(i,e?1:.5),f.show(i,e&&2==t._mnStatus),f.gbIsIE&&(i.style.filter="")}})},t._computeCardPos=function(){var e=this,t=e._moTriggerDom,i=e._moInfoCard,a=e._msDomId,s=e._moWin,n=s.document,o=t.getAttribute("beside"),l=o?f.S(o,e._moWin):t,r=f.calcPos(l),d=f.calcPos(i),m=f.S("infoarrowup_"+a,s),c=f.S("infoarrowdown_"+a,s),_=n.body.clientWidth;if(r[1]+d[4]<_)if(("IMG"==l.tagName||r[3]<50)&&"imglogo"!=l.id)f.show(m,!1),f.show(c,!1),i.style.top=r[0]+"px",i.style.left=r[1]+5+"px";else{var p=r[0]-d[5]>f.bodyScroll(s,"scrollTop");f.show(m,!p),f.show(c,p),i.style.top=p?r[0]-d[5]-5+"px":r[0]+r[5]+8+"px",i.style.left=r[1]-r[4]/2-65+"px"}else f.show(m,!0),f.show(c,!1),i.style.top=r[2]+"px",i.style.left=r[3]+"px"},t._buildInfoCard=function(){var l=this,r=l._msUin,d=l._msDomId,m=l._msEmail,c=l._msBindQQ,e=l._mbIsGroup,_=l._mbIsCorp,p=l._msAddrId,$=(l._msIcon,l._sMailid),t=(l._msQQFnd,l._mbIsPgroup),g=l._mbIsQywx;if(_fBuildHelp=function(e){var t=l._moTriggerDom,i=l._msNickName,a=(b(r),v(m)),s=m&&!a;e=e||{};i&&(e.name=f.htmlEncode(i));var n=f.htmlEncode(f.encodeNick(e.name)),o=u(f.htmlEncode(e.name));extend(e,{id:d,uin:r,email:m,bindqq:c,dispname:o,jsname:n,dispemail:u(m),sid:f.getSid(),images_path:f.getPath("image"),arrow:"IMG"==t.tagName?0:1,bmail:s,addrid:p,bIsCorp:_,bIsQywx:g,bNotMyCard:f.g_encryptuin!==r,mailid:$}),g&&extend(e,{qywx_name:e.qywx_name,gender:e.gender,positon:e.positon,english_name:e.english_name,headImg:e.headImg,mobile:e.mobile,landline:e.landline,email:e.email,vcode:e.vcode,active:e.active,party_path:e.party_path.replace(";","<br>")}),f.insertHTML(l._moWin.document.body,"afterBegin",h._TEMPLATE._INFOCARD.replace(e)),l._moInfoCard=_oInfoCard=f.S("infocard_"+d,l._moWin),f.addEvents(_oInfoCard,{mouseover:function(e){l._setStatus(4)},mouseout:function(e){l._setStatus(3)}}),l._setStatus(2),l._initEvents()},g)(i=l._moAjax=new f.QMAjax).method="post",i.url="/cgi-bin/readtemplate?action=get_wework_user_info&t=rss_mgr&sid="+f.getSid()+"&sender_email="+l._msEmail,i.send(),i.onComplete=function(e){var t=null;e&&1==l._mnStatus&&(t=f.evalValue(e.responseText))&&_fBuildHelp(t.feed)},(new Image).src="/cgi-bin/sellonlinestatic?sid="+f.getSid()+"&type=session_statistics&businame=qywework&item=biz_wwlogo_profile_show&r="+Math.random();else if(_){(i=l._moAjax=new f.QMAjax).method="post",i.url="/cgi-bin/getauthbizinfo?t=infocard&s=corp&bizdomain="+l._msEmail+"&sid="+f.getSid(),i.send(),i.onComplete=function(e){var t=null;e&&1==l._mnStatus&&(t=f.evalValue(e.responseText))&&_fBuildHelp(t)}}else if(e){(i=l._moAjax=new f.QMAjax).method="get",i.url=["/cgi-bin/addr_listall?sid=",f.getSid(),"&t=mail_group&category=mailgroup&groupaddr=",m].join(""),i.onComplete=function(e){var t=null;if(e&&1==l._mnStatus&&e.responseText&&(t=f.evalValue(e.responseText))&&t.members){for(var i=f,a={id:d,groupName:f.htmlEncode(l._msNickName),bGroupMem:!0,count:t.members.length,members:[],images_path:f.getPath("image")},s=0,n=t.members,o=n.length;s<o;s++)a.members.push({sNickName:i.limitString(n[s].sNickName,14),bStatus:n[s].bStatus,sEmail:i.limitString(n[s].sEmail,28)});insertHTML(l._moWin.document.body,"afterBegin",h._TEMPLATE._INFOCARD.replace(a)),l._moInfoCard=_oInfoCard=S("infocard_"+d,l._moWin),f.addEvents(_oInfoCard,{mouseover:function(e){l._setStatus(4)},mouseout:function(e){l._setStatus(3)}}),l._setStatus(2)}},i.send()}else if(t){(i=l._moAjax=new f.QMAjax).method="get",i.url=["/cgi-bin/addr_listall?sid=",f.getSid(),"&t=mail_group&category=mailgroup&groupaddr=",m].join(""),i.onComplete=function(e){var t=null;if(e&&1==l._mnStatus&&e.responseText&&(t=f.evalValue(e.responseText))&&t.members){for(var i=f,a={id:d,groupName:f.htmlEncode(l._msNickName),bGroupMem:!0,count:t.members.length,members:[],images_path:f.getPath("image")},s=0,n=t.members,o=n.length;s<o;s++)a.members.push({sNickName:i.limitString(n[s].sNickName,14),bStatus:n[s].bStatus,sEmail:i.limitString(n[s].sEmail,28)});insertHTML(l._moWin.document.body,"afterBegin",h._TEMPLATE._INFOCARD.replace(a)),l._moInfoCard=_oInfoCard=S("infocard_"+d,l._moWin),f.addEvents(_oInfoCard,{mouseover:function(e){l._setStatus(4)},mouseout:function(e){l._setStatus(3)}}),l._setStatus(2)}},i.send()}else if(r&&!b(r)){var i;(i=l._moAjax=new f.QMAjax).method="post",i.url="/cgi-bin/readtemplate",i.onComplete=function(e){var t=null;e&&1==l._mnStatus&&(t=f.evalValue(e.responseText))&&(t.feed.icon="/cgi-bin/getqqicon?uin="+r+"&time="+now(),_fBuildHelp(t.feed))},i.send(T("func=infocard&uin=$uin$&sid=$sid$&t=rss_mgr&s=infocard").replace({sid:f.getSid(),uin:r}))}else _fBuildHelp({icon:[getPath("image"),"rss/",v(m)?"face_admin.gif":"male.gif"].join("")})},t._initEvents=function(){var e={click:{profile_add:{bPropagable:!1},profile_reject:{bPropagable:!1},profile_compose:{bPropagable:!1},profile_history:{bPropagable:!1},profile_qq:{bPropagable:!1},profile_qywx_send_msg:{bPropagable:!1},profile_qywx_call:{bPropagable:!1}}},t={profile_add:function(e,t){var i=t.getAttribute("addrid"),a=i?"editAddr":"newAddr",s=i?"\u7f16\u8f91\u8054\u7cfb\u4eba":"\u65b0\u5efa\u8054\u7cfb\u4eba",n=f.T(i?"/cgi-bin/laddr_detail?sid=$sid$&view=normal&t=contact_detail&edit=2&dlgname=editAddr&AddrID=$addrid$":"/cgi-bin/readtemplate?sid=$sid$&view=normal&t=contact_detail&edit=1&dlgname=newAddr&user=$user$&email=$email$&resp_charset=UTF8").replace({email:t.getAttribute("email"),user:encodeURIComponent(t.getAttribute("name")),addrid:i,sid:f.getSid()});new f.QMDialog({sId:a,sTitle:s,sUrl:n,nHeight:500,nWidth:600})},profile_reject:function(e,t){f.fireMouseEvent(f.S("qmAntiSpam_reject",f.getMainWin()),"click")},profile_compose:function(e,t){var i=f.T('"$name$"<$email$>').replace({name:f.htmlEncode(t.getAttribute("name")&&t.getAttribute("name").replace(/&nbsp;|\u00a0/g," ")),email:t.getAttribute("email")});f.openComposeDlg("normal",{sDefAddrs:i,bUinEncrypt:!0,bAddrEdit:!1})},profile_history:function(e,t){f.getMainWin().location.replace(f.T(["/cgi-bin/mail_list?sid=$sid$&sender=$sender$&receiver=$receiver$&name=$name$","&s=searchcontact&matchtype=include&folderid=all&pagesize=50&category=all&from=profile"]).replace({sid:f.getSid(),sender:t.getAttribute("email"),receiver:t.getAttribute("email"),name:t.getAttribute("name")}))},profile_qq:function(e,t){f.QMAjax.send(f.T("/cgi-bin/getinvestigate?sid=$sid$&stat=qqbindchart&qqbindno=$qqbindno$").replace({sid:f.getSid(),qqbindno:t.getAttribute("bindqq")})),window.open(f.T("http://wpa.qq.com/msgrd?v=3&uin=$qqbindno$&site=qq&menu=yes").replace({qqbindno:t.getAttribute("bindqq")}))},profile_qywx_send_msg:function(e,t){var i=new f.QMAjax;i.method="post",i.url="/cgi-bin/readtemplate?action=get_self_wework_info&t=rss_mgr&sid="+f.getSid(),i.send(),i.onComplete=function(e){var t=null;e&&"1"!=(t=f.evalValue(e.responseText)).feed.active&&(getTop().confirmBox({title:"\u4f01\u4e1a\u5fae\u4fe1\u5de5\u4f5c\u4fe1\u606f\u5c55\u793a",msg:'<p style="font-size: 12px; margin: 0; line-height: 12px;">\u4f60\u7684\u4f01\u4e1a\u5df2\u5f00\u901a\u201c\u4f01\u4e1a\u5fae\u4fe1\u201d\uff0c\u5b89\u88c5\u540e\u5373\u53ef\u53d1\u9001\u6d88\u606f</p><p style="margin: 0; font-size: 12px; color: #9F9F9F; margin-top: 3px;">\u5df2\u7ecf\u6709'+t.feed.ww_user_cnt+'\u4eba\u540c\u4e8b\u4f7f\u7528\u4f01\u4e1a\u5fae\u4fe1 <a href="http://work.weixin.qq.com/wework_admin/mail_promote_activedcnt?vccode='+t.feed.vcode+'&from=exmail_web_profile_message"  target="_blank">\u4e86\u89e3\u66f4\u591a</a></p> ',confirmBtnTxt:"\u7acb\u5373\u5b89\u88c5",cancelBtnTxt:"\u53d6\u6d88",onreturn:function(e){e&&(window.open("https://work.weixin.qq.com/wework_admin/commdownload?vccode="+t.feed.ww_user_cnt+"&from=exmail_web_profile_message"),(new Image).src="/cgi-bin/sellonlinestatic?sid="+f.getSid()+"&type=session_statistics&businame=qywework&item=biz_wwlogo_profile_chat_download&r="+Math.random())}}),(new Image).src="/cgi-bin/sellonlinestatic?sid="+f.getSid()+"&type=session_statistics&businame=qywework&item=biz_wwlogo_profile_chat_dialog&r="+Math.random())},(new Image).src="/cgi-bin/sellonlinestatic?sid="+f.getSid()+"&type=session_statistics&businame=qywework&item=biz_wwlogo_profile_chat&r="+Math.random()},profile_qywx_call:function(e,a){var t=new f.QMAjax;(new Image).src="/cgi-bin/sellonlinestatic?sid="+f.getSid()+"&type=session_statistics&businame=qywework&item=biz_wwlogo_profile_call&r="+Math.random(),t.method="post",t.url="/cgi-bin/readtemplate?action=get_self_wework_info&t=rss_mgr&sid="+f.getSid(),t.send(),t.onComplete=function(e){var t=null;if(e)if("1"===(t=f.evalValue(e.responseText)).feed.active){var i=new f.QMAjax;i.method="post",i.url="/cgi-bin/readtemplate?sid="+f.getSid()+"&action=call_by_wework&t=rss_mgr&callee_email="+a.getAttribute("email"),i.send(),i.onComplete=function(e){var t=null;e&&("0"===(t=f.evalValue(e.responseText)).feed.errcode?new(getTop().QMDialog)({sTitle:"\u547c\u53eb\u5df2\u7ecf\u53d1\u51fa",sBodyHtml:'<div class="cnfx_content"><span class="dialog_icon icon_finish_b"></span><div class="dialog_f_c"><p style="font-size: 12px; margin: 0; line-height: 12px;">\u547c\u53eb\u5df2\u7ecf\u53d1\u51fa\u3002\u8bf7\u5148\u5728\u4f60\u7684\u624b\u673a\u4e0a\u63a5\u542c\u6765\u7535\uff0c\u968f\u540e\u5c06\u81ea\u52a8\u547c\u53eb\u5bf9\u65b9\u3002</p> </div></div>',sFootHtml:'<div class=" txt_right cnfx_btn"><input class="btn_gray btn_input" type="button" id="cancel" value="\u5173\u95ed" />',onload:function(){var t=this,e=t.S("cancel");addEvents([e],{click:function(e){t.close()}})}}):new(getTop().QMDialog)({sTitle:"\u547c\u53eb\u5931\u8d25",sBodyHtml:'<div class="cnfx_content"><span class="dialog_icon icon_info_b"></span><div class="dialog_f_c"><p style="margin: 0;">'+t.feed.errmsg+"</p></div></div>",sFootHtml:'<div class=" txt_right cnfx_btn"><input class="btn_gray btn_input" type="button" id="cancel" value="\u5173\u95ed" />',onload:function(){var t=this,e=t.S("cancel");addEvents([e],{click:function(e){t.close()}})}}))}}else(new Image).src="/cgi-bin/sellonlinestatic?sid="+f.getSid()+"&type=session_statistics&businame=qywework&item=biz_wwlogo_profile_call_dialog&r="+Math.random(),getTop().confirmBox({title:"\u4f01\u4e1a\u5fae\u4fe1\u5de5\u4f5c\u4fe1\u606f\u5c55\u793a",msg:'<p style="font-size: 12px; margin: 0; line-height: 18px; margin-top: -10px;">\u4f60\u7684\u4f01\u4e1a\u5df2\u5f00\u901a\u201c\u4f01\u4e1a\u5fae\u4fe1\u201d\uff0c\u5b89\u88c5\u540e\u5373\u53ef\u4f7f\u7528\u516c\u8d39\u7535\u8bdd\u529f\u80fd\u7ed9<br>\u540c\u4e8b\u6253\u7535\u8bdd\u3002</p><p style="margin: 0; font-size: 12px; color: #9F9F9F; margin-top: 3px; margin-bottom: -10px;">\u5df2\u7ecf\u6709'+t.feed.ww_user_cnt+'\u4f4d\u540c\u4e8b\u4f7f\u7528\u4f01\u4e1a\u5fae\u4fe1 <a href="http://work.weixin.qq.com/wework_admin/mail_promote_activedcnt?vccode='+t.feed.vcode+'&from=exmail_web_profile_telephone"  target="_blank">\u4e86\u89e3\u66f4\u591a</a></p> ',confirmBtnTxt:"\u7acb\u5373\u5b89\u88c5",cancelBtnTxt:"\u53d6\u6d88",onreturn:function(e){e&&((new Image).src="/cgi-bin/sellonlinestatic?sid="+f.getSid()+"&type=session_statistics&businame=qywework&item=biz_wwlogo_profile_call_download&r="+Math.random(),new(getTop().QMDialog)({nWidth:300,sTitle:"\u626b\u7801\u4e0b\u8f7d\u4f01\u4e1a\u5fae\u4fe1",sBodyHtml:'<div><img src="https://work.weixin.qq.com/wework_admin/genqrcode?action=commdownload&vccode='+t.feed.vcode+'&from=exmail_profile_tips&qr_size=32" style="width:250px; height:250px; margin-top: 10px;"><p style="margin: 0px;padding-bottom: 30px;">\u626b\u7801\u4e0b\u8f7d\u4f01\u4e1a\u5fae\u4fe1\u5ba2\u6237\u7aef</p></div>',onload:function(){var t=this,e=t.S("cancel");addEvents([e],{click:function(e){t.close()}})}}))}})}}};f.liveEvent(this._moInfoCard,{rule:function(){return e},events:function(){return t}})},t._forceStop=function(){var e=this._moInfoCard;e&&(f.qmAnimation.stop(e),f.show(e,!1)),clearTimeout(this._mnTimeId),this._moAjax&&this._moAjax.abort()},t._doMouseOver=function(e){var t=this;if(t._moTriggerDom!=e){if(3==t._mnStatus)return void(t._moTmpTR=e);var i=e.getAttribute("u"),a=e.getAttribute("n"),s=f.htmlEncode(e.getAttribute("e")||""),n=e.getAttribute("b"),o=1==e.getAttribute("g"),l=e.getAttribute("i"),r=e.getAttribute("addrid"),d="corpprofile"==e.getAttribute("r"),m=e.getAttribute("mailid"),c="1"==e.getAttribute("hasOpenQywx"),_=e.getAttribute("w")||500,p=!1;"g_"==s.substring(0,2)&&-1==s.indexOf("@")&&(o=d=!(p=!0)),"0"!=i&&i&&i!=f.g_encryptzero||(i=""),/\D/g.test(r)&&(r=""),t._forceStop(),t._moTmpTR=null,t._moTriggerDom=e,t._msUin=i,t._msDomId=[i,s.replace(/\W/gi,"")].join(""),t._mnStatus=0,t._moInfoCard=S("infocard_"+t._msDomId,t._moWin),t._msNickName=a,t._msEmail=s||(i?i+"@qq.com":""),t._msBindQQ=n,t._mbIsGroup=o,t._mbIsPgroup=p,t._msIcon=l,t._msAddrId=r,t._mbIsCorp=d,t._sMailid=m,t._mbIsQywx=c,t._mnWaitTime=parseInt(_,10)}t._setStatus(0==t._mnStatus?1:2)},t._doMouseOut=function(e){var t=this._mnStatus;this._moTmpTR=null,this._setStatus(2==t||3==t?3:0)},h._getInstance=function(i){if(i){var e=h._TEMPLATE._INSTANCE,t=i[e];return t||(t=i[e]=new h(i),addEvent(i,"unload",function(e){var t=h._getInstance(i);t&&t._forceStop()})),t}},h.doMouseEvent=function(e,t,i){"over"==e?h._getInstance(t)._doMouseOver(i):"out"==e&&h._getInstance(t)._doMouseOut(i)},e.QMProfileTips=h})(window,getTop());