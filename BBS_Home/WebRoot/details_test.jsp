<%@page import="bbs.Model.replyModel"%>
<%@page import="java.awt.List"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import = "bbs.Model.detailsModel" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>帖子详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
    <%
 detailsModel model = new detailsModel();
  model =(detailsModel)request.getAttribute("detailsM");    
     %>
    (楼主创建的帖子：) <br>
标题： <%out.println(model.getTitle()); %> <br>
    昵称：<%out.println(model.getUserName()); %> <br>
    账号：<%out.println(model.getUserID()); %> <br>
 tpoicID：<%out.println(model.getTopicID()); %> <br>
    内容：<%out.println(model.getContent()); %> <br>
    type?<br>
    时间？
     <br> <br> <br> <br>
     

  
(已有的，回复的帖子：) 
    <%   
 ArrayList<Object> list = (ArrayList<Object>)request.getAttribute("replyList");
     %>
<br>
<%
int a = list.size();
for(int i = 0; i<a ; i++) {
	replyModel modelR = (replyModel)list.get(i); %>
	昵称: <%out.println(modelR.getUserName());%><br>
	账号：<%out.println(modelR.getUserID());%><br>
	内容：<%out.println(modelR.getContent());%><br>
	回复时间：?<br> <br>
	============<br><br>
<% 
} %>

     <br> <br> <br> <br>
     
(回帖：) <br>
		<form action="insertReplySer" method="post">
		<table>
			<tr>
				<td valign = "top">回帖：</td>
				<td><textarea rows = "20" cols = "70" name = "content" style = "resize:none;"></textarea></td>
			</tr>
		</table>


      帖子详情中的回帖提交按钮：
         
         	<input type="submit" name="submit" style="width:70px;height:30px;  margin-top:20px;" value="提交回帖">
         </form>
         
      <br> <br> <br> <br>
 
  </body>
</html>
