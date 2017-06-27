<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>航模论坛-登录界面</title>
    <link rel="stylesheet" href="cssFile/style.css">
    <style type="text/css">
    </style>
    <script type="text/javascript">
    var teleflag = true;
    function test() {
    	alert("test click");
    }
    
    function telephone() {       // 手机号
		// flag = true;
		var tele = document.getElementById('login').value;
		if (tele.length != 11) {
			alert("手机号不等于11位");
			teleflag = false;
		}else { // 等于11位
			var fnum = tele.substring(0,2);
			if ( fnum!="13" && fnum!="15" && fnum!="17" ) {
				alert("手机号不正确");
				teleflag = false;
			}else {
				teleflag = true;
			}
		}
	}
	
	function allvalue() {
		telephone();
		if (teleflag==true) {
			alert("账号格式输入正确");
			return true;
		}else {
			alert("账号格式输入有误！+++++++");  
			return false;
		}
	}
    </script>
  </head>
  
  <body>
  <form action="loginSer" method="post">
    <div class="container">
    	
    		<div class="login">
     			 <h1>登录到航模论坛</h1>    
       			 <input type="text" name="login" id="login" placeholder="请输入你的账号/手机号" style="height:30px;width:300px">
       			 <input type="password" name="password"  placeholder="请输入你的密码" style="height:30px;width:300px;margin-top:20px;" >
       			 <div>      	
        		 	<center>
         	    		<input type="submit" style="width:70px;height:30px;  margin-top:20px;" value="登录" onclick="return allvalue()">
         			</center>
       			 </div>
    		</div>
		
		
    	<div class="login-help">
      		<p>没有账号？ <a href="test.jsp">点击注册</a></p>
    	</div>
   </div>
  </form>
  </body>
</html>
