<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sendTopic.jsp' starting page</title>
    
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
    <form action="insertTopic" method="post" name="form2">
		<div>
			&nbsp;主题&nbsp;&nbsp;<input type="text" name="title" style="width:500px"><br>
			&nbsp;分类&nbsp;&nbsp;<select name="select">
				    <option value="1">车模</option>
				    <option value="2">航模</option>
				    <option value="3">船模</option>
				    <option value="4">其他</option>
	 	 		</select><br>
	 	 	<table>
	 	 		<tr>
	 	 			<td valign="top">内容</td>
	 	 			<td>&nbsp;<textarea rows="20" cols="70" name="content" style="resize: none;"></textarea></td>
	 	 		</tr>
	 	 		<tr>
	 	 			<td></td>
	 	 			<td align="right"><input type="submit" name="submit" value="发帖"></td>
	 	 		</tr>
	 	 	</table>
	 	 	
		</div>
	</form>
  </body>
</html>
