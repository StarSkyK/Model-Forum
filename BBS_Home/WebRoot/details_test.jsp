<%@page import="bbs.Model.replyModel"%>
<%@page import="java.awt.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import = "bbs.Model.detailsModel" %>
<%@ page import="bbs.Model.commentModel"%>
<%@ page import = "com.mysql.jdbc.Connection" %>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import ="bbs.Model.loginModel" %>
<%@ page import="javax.servlet.http.HttpSession" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>帖子详情</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="cssFile/details.css" />
	<table width="100%" border="1" align="center" cellpadding="2" cellspacing="3">
	  <tr bgcolor="#333333"> 
	    <td nowrap height="49" width="25%"> 
	      <div align="left">
	      	<font color="#FFFFFF" face="微软雅黑"><center><b>模型国度宗旨：</b>分享模型兴趣爱好</center></font>
	      </div>
	    </td>
	    <td nowrap height="49" width="50%" bgcolor="#333333" align = "center"> 
	      <p>
	      	<font color="#FF0000" face="微软雅黑" >想你所想，爱你所爱！</font>
	       
	      </p>
	    </td>
	    <td nowrap height="49" width="25%"> 
	      <div align="right"> <center>
	      	<a>
	      		<font color="#999999" face="微软雅黑">手机端</font>
	      	</a>
	      	<font face="微软雅黑"> 
		        <font color="#FFFFFF">
		        	<font color="#999999">|</font>
		        </font>
		        <font color="#FFFFFF"> 
			        <a>
			        	<font color="#999999">帮助</font>
			        </a> 
			        <font color="#999999">|</font> 
			        <font color="#999999">
			        	<a>
			        		<font color="#999999">用户申请</font>
			        	</a> | 
			        	<a>
			        		<font color="#999999">积分说明</font>
			        	</a> | 
			        	<a>
			        		<font color="#999999">头条新闻</font>
			        	</a>
			        </font> 
		    	</font>
		    	
	    	</font>
	    	</center>
	      </div>
	    </td>
	  </tr>
	</table>
<!-- 动态加文本框和按钮 -->

	<%
		HttpSession sss =request.getSession(true);
		loginModel mmmd =(loginModel) sss.getAttribute("login");
					
		String userName ="null";
		if(mmmd!=null){
			userName =mmmd.getuserName().toString();
		}
		 %>
<script type="text/javascript">
    var flag = 0;
	// 动态添加文本框
	function addText(node){
		var idd = node.name;
	  	var vv = document.getElementById(idd);
	  	//alert("addText1");
	    if(flag == 0) {
	    	vv.style.visibility = "visible";
	    	flag = 1;
	    	if(vv.hasChildNodes() == true) {
	    		
	    	}else {
	    		//alert("addText2");
	    		// 创建文本框
		    	var ipt = document.createElement("textarea");
			    //ipt.type="text";
			    ipt.style="width:700px;height:50px;valign='top';resize: none;";
			    ipt.placeholder="评论字数不能超过100个字";
			    ipt.name="commentContent";
			    ipt.id="commentCount";
			    vv.appendChild(ipt);
			   // alert("addText3");
			    // 创建发表按钮
			   
			    var ipt2 = document.createElement("input");
			    ipt2.type="submit";
			    ipt2.value="发表";
			    ipt2.style="float:right;margin-top:0px;margin-right:50px;";
			    ipt2.onclick=function() {
			    	var commentC = document.getElementById("commentCount").value;
			    	//alert("点击了新创建的按钮");
			    	if(commentC.length > 100 || commentC.length < 1) {
						alert("回帖字数大于100或为空");
						return false;
					}else {
						//alert("字数正确+++++");
						return true;		
					}
			    }
			    //ipt2.onclick="check()";
			    vv.appendChild(ipt2);
	    	}
	    }else  {
	    	//alert("addText4");
	    	vv.style.visibility = "hidden";
	    	flag = 0;
	    }
	}
	
	function check(){
		//alert("check1");
		var mmd =document.getElementById("idcheckk").value;
		if (mmd==null || mmd=="null") {
			alert("请先登录");
			top.location='login.jsp';
			alert("check2");
		}
		
		//alert("check3");
	}
	
	function replyCheck() {
		check();
		var replyC =  document.getElementById("replyContentCount").value;
		if(replyC.length > 300 || replyC.length < 1) {
			alert("回帖字数大于300或为空");
			return false;
		}else {
			//alert("字数正确+++++");
			return true;		
		}
	}
	
	function allFun(node) {
		//alert("allFun");
		check();
		addText(node);
	}
	
	function check2(){
			var mmd =document.getElementById("checklogin").value;
			if (mmd!="null") {
				document.getElementById("userLoginID").innerHTML =mmd+"(切换当前账号)";
			}
		}
		
	window.onload = check2;
	</script>
  </head>
  
  <body>
  	<div class="top">
			<div class="wrap1200 clearfix">
			
				<div class="fl1 toplinkl">
					<span class="user">
						<a class="mxmobile" href="index.jsp" id="5imxmobile" onMouseOver="">首页<b>&nbsp;</b></a>
					</span>
					<span class="user1">
						<a class="mxmobile"  id="5imxmobile" onMouseOver=""><center>帖子详情</center></a>
					</span>
				</div>
				
				
	            <div class="fr1 toplinkr">
	            	<span class="log">
	            		<a href="login.jsp" onclick="" title="用户登录" id="userLoginID">登录</a>
	            	</span>
	            	<!--  <span class="shop fl1">
	            		<a href="" onclick="">商城</a>
	            	</span>
	            	<span class="bbs fl1">
	            		<a href="" onclick="">论坛</a>
	            	</span>-->
	            	<!--<span class="fav fl1">
	            		  <a href=""  onclick="">发帖</a> 
	            	</span>-->
	            </div>
	            
			</div>
			<input type="hidden" id="checklogin" value=<%=userName%>>
			<div class="search">
				   
			</div>
	</div>
    <%
	 detailsModel model = new detailsModel();
 	 model =(detailsModel)request.getAttribute("detailsM");    
     %>
     <table class="detail" align="center">
		<tr height="60px" id="title" class="tr1">
			<td align="center" colspan="2">
				标题： <%out.println(model.getTitle()); %>
			</td>
		</tr>
		<tr class="tr1">
			<td width="35%" bgcolor="#f3f3f4">
				<table  align="center" class="holder">
					<tr>
						<td>
							<!--  (楼主创建的帖子：) <br>-->
							<br>
							<img src="images/userIcon2.jpg"><br>
			   				昵称：<%out.println(model.getUserName()); %> <br>
			   				账号：<%out.println(model.getUserID()); %> <br>
			 				<!--  tpoicID：<%out.println(model.getTopicID()); %> <br>-->
		 				</td>
	 				</tr>
 				</table>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;<%out.println(model.getContent()); %> 
    			<!--  type:<%out.println(model.getType()); %><br>-->
    			<div class="time">
    				<%out.println(model.getAnnouncesTime()); %>
    			</div>
   				
    
    		</td>
		</tr>
		
	</table>	
   
  	<table class="detail" align="center">
		<!-- (已有的，回复的帖子：)  -->
   	 <%   
		 ArrayList<Object> list = (ArrayList<Object>)request.getAttribute("replyList");
     %>


	<%
	int a = list.size(); %>
	<%
	for(int i = 0; i<a ; i++) { %>
	
		<tr  border="none" id="details_trr">	
			<%replyModel modelR = (replyModel)list.get(i); %>
			<% int countI = Integer.parseInt(modelR.getReplyID().toString()); %>
			<td width="35%" bgcolor="#f3f3f4"><br>
				<table  align="center" class="holder">
					<tr height="60px"></tr>
					<tr>
						<td>
							<img src="images/userIcon2.jpg"><br>
							
							
						</td>
					</tr>
					<tr>
						<td>
							
							昵称: <%out.println(modelR.getUserName());%><br>
						</td>
					</tr><tr>
						<td>
							
							
							账号：<%out.println(modelR.getUserID());%><br>
						</td>
					</tr>
					<tr height="60px"></tr>
				</table>
			</td>
			<td>
				<table class="topic">
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;<%out.println(modelR.getContent());%>
							<div class="time">
								
								<%out.println(modelR.getReplyTime());%>&nbsp;
							</div>
							<input type="hidden" id="idcheckk" value="<%=mmmd%>">
							<form class="pl">
									<a href="javascript:void(0);"  onclick="allFun(this)"  name="<%=countI %>">评论</a>
							</form>
						
						
					
						<form action = "commentSer" method = "get">
							<input type="hidden" value = "<%=countI %>" name="hidden_replyID">
							<div id="<%=countI %>" class="reply"></div>
						</form>
						</td>
					</tr>
				<!-- replyID：<%out.println(countI); %><br> <br>&nbsp;
						<form action="">
							<a href="javascript:void(0);"  onclick="allFun(this)"  name="<%=countI %>">评论</a>
						</form>-->
					</table>
					
					
					
				
				
			</td>
			</tr>
			
			<tr class="tr1">
				<td bgcolor="#f3f3f4"></td>
				<td>
					<table class="floor" width=94%>
	
						<% // 连接数据库，根据 replyID == countI 查对应的content comment
						try {
							Class.forName("com.mysql.jdbc.Driver");
							String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
							String userString ="root";
							String password ="312312";
							Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
							Statement sql =(Statement) connection.createStatement();
							ResultSet rs = sql.executeQuery("select * from jsp_test.comment where replyID ="+countI);
							while(rs.next()) {
							%>
								<tr>
									<td>
										<font class="text"><br>
											&nbsp;&nbsp;&nbsp;&nbsp;<a style="font-size:18px;color:#227fe7;"><%out.println(rs.getString("userName").toString()+":");%></a> &nbsp;  <%out.print(rs.getString("content").toString());%>
											<p style="font-size:15px;color:#c5c3c3;float:right;position:relative;padding-right:60px;"><% out.print(rs.getString("commentTime")); %></p><br>
											
										</font>
										<center><hr></center>
									</td>
								</tr>
							<%
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
						%>
					</table>
				<br>
			</td>
		</tr>
<% 
} %>

     

		<tr>
			<!--  (回帖：) <br>-->
			<form action="insertReplySer" method="get">
				<table class="detail" align="center" >
					<tr colspan="2" align="center">
						
						<td>
							<br>
							<textarea rows = "15" cols = "120" name = "content" style = "resize:none;" id="replyContentCount" placeholder="回帖字数请在300字以内"></textarea>
							
         					<br>
         					<!--  <input type="submit" name="submit" style="width:70px;height:30px;  margin-top:20px;" value="提交回帖">-->
         					<input type="hidden" value ="<%=request.getAttribute("topicID")%>" name="topicID">
         	<input type="submit" name="submit" style="width:70px;height:30px;  margin-top:20px;" value="提交回帖" onclick="return replyCheck()"><br><br>
						</td>
					</tr>
				
					
                            
         			
         		</table>
        	 </form>
          
      		
      		
		 </tr>
		</table>
 		<!--  (回帖：)
<tr>
		<form action="insertReplySer" method="get">
			<table>
				<tr>
					<td valign = "top">回帖：</td>
					<td><textarea rows = "20" cols = "70" name = "content" style = "resize:none;"></textarea></td>
				</tr>
			</table>


      帖子详情中的回帖提交按钮：
         	<input type="hidden" value ="<%=request.getAttribute("topicID")%>" name="topicID">
         	<input type="submit" name="submit" style="width:70px;height:30px;  margin-top:20px;" value="提交回帖" onclick="check()">
         </form>
         --> 
     
 
  </body>
</html>
