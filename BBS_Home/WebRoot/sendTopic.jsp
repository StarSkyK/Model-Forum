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
	
	<link rel="stylesheet" type="text/css" href="cssFile/sendTopic.css">
	
	<script type="text/javascript">
	
	var flag = true;
	function countCheck() {
		var title = document.getElementById("titleCount").value;
		if(title.length > 50 || title.length < 1) {
			alert("标题字数大于50或为空");
			flag = false;
		}else {
			var contentC = document.getElementById("contentCount").value;
			if(contentC.length > 300 || contentC.length <1) {
				alert("内容字数大于300或为空");
				flag = false;
			}else {
				flag = true;
			}
		}
	}
	
	
	function allvalue() {
		countCheck();
		if (flag==true) {
			//alert("zishu 全部输入正确");
			return true;
		}else {
			//alert("字数有错误");  
			return false;
		}
	}
	
	</script>

  </head>
  
  <body>
    <form action="insertTopic" method="post" name="form2">
		<div class="d1-tog">
			<div class="tog"><br>
				&nbsp;主题&nbsp;&nbsp;<input type="text" name="title" style="width:500px;height:30px;" id="titleCount" placeholder="标题字数请在50字以内"><br><br>
				&nbsp;分类&nbsp;&nbsp;
					<select name="select" height="30px">
					    <option value="1">车模</option>
					    <option value="2">航模</option>
					    <option value="3">船模</option>
					    <option value="4">其他</option>
		 	 		</select><br><br><br>
		 	 	<table>
		 	 		<tr>
		 	 			<td valign="top">内容</td>
		 	 			<td>&nbsp;<textarea rows="20" cols="70" name="content" style="resize: none;" id="contentCount"  placeholder="帖子字数请在300字以内"></textarea></td>
		 	 		</tr>
		 	 		<tr>
		 	 			<td></td>
		 	 			<td align="right">
		 	 				<input type="submit" name="submit" value="发帖" onclick="return allvalue()">
		 	 			</td>
		 	 		</tr>
		 	 	</table>
	 	 	</div>
		</div>
	</form>  
	
  </body>
</html>
