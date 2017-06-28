<%@page import="bbs.Model.IndexModel"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=gbk" charset = "utf-8"/>
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <table width="100%" border="1" align="center" cellpadding="2" cellspacing="3">
	  <tr bgcolor="#333333"> 
	    <td nowrap height="49" width="25%"> 
	      <div align="left">
	      	<font color="#FFFFFF" face="微软雅黑"><b>模型国度宗旨：</b>分享模型兴趣爱好</font>
	      </div>
	    </td>
	    <td nowrap height="49" width="50%" bgcolor="#333333" align = "center"> 
	      <p>
	      	<font color="#FF0000" face="微软雅黑" >想你所想，爱你所爱！</font>
	       
	      </p>
	    </td>
	    <td nowrap height="49" width="25%"> 
	      <div align="right"> 
	      	<a href="" target="_blank">
	      		<font color="#999999" face="微软雅黑">手机端</font>
	      	</a>
	      	<font face="微软雅黑"> 
		        <font color="#FFFFFF">
		        	<font color="#999999">|</font>
		        </font>
		        <font color="#FFFFFF"> 
			        <a href="misc.php?mod=faq" target="_blank">
			        	<font color="#999999">帮助</font>
			        </a> 
			        <font color="#999999">|</font> 
			        <font color="#999999">
			        	<a href="" target="_blank">
			        		<font color="#999999">用户申请</font>
			        	</a> | 
			        	<a href="" target="_blank">
			        		<font color="#999999">积分说明</font>
			        	</a> | 
			        	<a href="" target="_blank">
			        		<font color="#999999">头条新闻</font>
			        	</a>
			        </font> 
		    	</font>
		    	
	    	</font>
	    	
	      </div>
	    </td>
	  </tr>
	</table>
	<link rel="stylesheet" type="text/css" href="cssFile/style_14_common.css" />
  </head>
 <body id="nv_portal" class="pg_index" onkeydown="if(event.keyCode==27) return false;">
	
		<div class="top">
			<div class="wrap1200 clearfix">
				<div class="fl1 toplinkl">
					<span class="user">
						<a class="mxmobile" href="" id="5imxmobile" onMouseOver="">手机用户<b>&nbsp;</b></a>
					</span>
				</div>
				<div class="search">
				   <form id="" method="post" autocomplete="off" onsubmit="searchFocus($('scbar_txt'))" action="search.php?searchsubmit=yes" target="_blank">
						<input type="hidden" name="mod" id="scbar_mod" value="search" />
						<input type="hidden" name="formhash" value="79ed1f4e" />
						<input type="hidden" name="srchtype" value="title" />
						<input type="hidden" name="srhfid" value="0" />
						<input type="hidden" name="srhlocality" value="portal::index" />
						<input  class="txt fl1" type="text" name="srchtxt" id="scbar_txt" value="" autocomplete="off" x-webkit-speech speech />
						<input type="submit" name="searchsubmit" id="scbar_btn" class="btn fl1" value="搜索" sc="1"/>

					</form>
				</div>
				<div class="fl1 toplinkuser"></div>
	            <div class="fr1 toplinkr">
	            	<span class="log fl1">
	            		<a href="" onClick="" title="用户登录">登录</a>
	            	</span>
	            	<span class="shop fl1">
	            		<a href="">商城</a>
	            	</span>
	            	<span class="bbs fl1">
	            		<a href="">论坛</a>
	            	</span>
	            	<span class="fav fl1">
	            		<a href=""  onclick="">收藏</a>
	            	</span>
	            </div>
			</div>
		</div>
<br>
<table align=center width="1152px" height="300%" bgcolor="white" >

	
	<tr align=center height="580px">
		<td class = "frame">
			
		    <div id="photos" class="play">
			    <img src="images/scroll1.jpg" >	        
			    <img src="images/scroll2.jpg" >
				<img src="images/scroll3.jpg" >
			    <img src="images/scroll4.jpg" >
				
		   	</div>
		</td>
	</tr>	
	<tr  align=center height="208px">
		<!--  <td class="part" align="center">-->
			<td>
		     	<a href="indexSer?type=1"><img src="images/car.png"><br><br><p>车模</p></a>
			</td>
			<td>
		     	<a href="indexSer?type=2"><img src="images/car.png"><br><br><p>航模</p></a>
			</td>
			<td>
		     	<a href="indexSer?type=3"><img src="images/car.png"><br><br><p>船模</p></a>
			</td>
			<td>
		     	<a href="indexSer?type=4"><img src="images/car.png"><br><br><p>其他</p></a>
			</td>

			
		<!-- </td> -->
	</tr> 
	
	<tr align="center" >
		<td class="topic">
		 
				<var class="number">
					<%
						ArrayList<Object> list =(ArrayList<Object>)request.getAttribute("indexList");
						for(int i=0;i<list.size();i++){
							IndexModel model =(IndexModel)list.get(i);
							out.print("<div>"+model.getTitle()+"</div>"+"<br>"+"<hr>");
							
						}
					%>
				</var>
		</td>
			
	</tr>
</table>
<form action="sendTopic.jsp" method="post" name="form2">
	<td align="right"><input type="submit" name="submit" value="发帖"></td>
</form>  
</body>
  </html>
