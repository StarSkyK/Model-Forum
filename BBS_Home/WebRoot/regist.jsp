<%@page import="bbs.Model.linkModel"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <meta http-equiv="Content-Type" content="text/html;  charset = utf-8"/>
    <title>航模论坛-注册界面</title>
    <link rel="stylesheet" type="text/css" href="cssFile/zhuce.css">
    <link rel="stylesheet" type="text/css" href="cssFile/style.css">
      <style type="text/css">
    	.tijiao {
    		 width:200px; height:50px; margin:0 auto;  margin-top:700px; 
    	}
    </style>
    <script type="text/javascript">
	var flag = true;
	var pwdvalue;

	var nameflag = true;
	var pwdflag = true;
	var repwdflag = true;
	var teleflag = true;
	var phoneflag = true;
	var mailflag = true;
	var addressflag = true;
	
	var showflag = true; // 个性签名
	var birthdayflag = true;  // 出生日期
	
	
	function username() { // 昵称
		// flag = true;
		var p = document.getElementById('tishi1');
		var un1 = document.getElementById("text1");
		var un = document.getElementById('text1').value;
		// alert(un);
		if(!isNaN(un[0]) || un.length<2 || un.length>18){
			un1.className = "red";
			p.className = 'show';
			//alert("昵称输入错误");
			//nameflag = false;
		}
	}

	function pwd() { // 密码

		// alert("p");

		// flag = true;
		var flag1 = false; // 6 -- 18 
		var flag2 = false; // 有数字
		var flag3 = false; // 含特殊字符
		var flag4 = false; // 含大写英文
		var flag5 = false; // 含小写字母
		var pw = document.getElementById('text2').value;
		var pw1 = document.getElementById('text2');
		var p = document.getElementById('tishi2');
		// alert("p");

		if (pw.length<6 || pw.length>18 ) {
			//alert("密码少于六位或多于18位");
			flag1 = false;
			pw1.className = "red";
			p.className = 'show';


		} else {
			flag1 = true;
			for (var i = 0; i < pw.length; i++) {

				// alert("for循环");

				var asc = pw[i].charCodeAt(); // asc 为阿斯克码

				if (!isNaN(pw[i])) { // 是数字
					
					flag2 = true; 
					// alert("2"+flag2);
				}else if( (asc>=33 && asc<=47) || (asc>=58 && asc<=64) || (asc>=91 && asc<=96) || (asc>=123 && asc<=126) ){ // 含特殊字符
					
					flag3 = true;
					// alert("3"+flag3);
				}else if(asc>=65 && asc<=90) { // 含 大写英文字母
					
					flag4 = true;
					// alert("4"+flag4);
				}else if(asc>=97 && asc<=122) { // 含小写英文字母
					
					flag5 = true;
					// alert("5"+flag5);
				} 
			}
		}

		if (flag1==true && flag2==true && flag3==true && flag4==true && flag5==true) {
			pwdflag = true;
			pwdvalue = pw;
			// alert(p);
			// alert(pwdvalue);
		}else {
			pwdflag = false;
			//alert("密码输入有误");
			pw1.className = "red";
			pw.className = 'show';
		}

	}

	function repwd() { // 确认密码
		var repwdvalue = document.getElementById('text3').value;
		var repwdvalue1 = document.getElementById('text3');
		var p = document.getElementById('tishi3');
		// alert(repwdvalue);
		// alert(pwdvalue);
		if (repwdvalue != pwdvalue) { // 不相等  返回false   !repwdvalue.equals(pwdvalue)   repwdvalue != pwdvalue
			
			repwdflag = false;
			//alert("密码不一致");
			repwdvalue1.className = "red";
			p.className = 'show';
		} else {
			repwdflag = true;

		}
	}

	function telephone() {       // 手机号
		// flag = true;
		var tele = document.getElementById('text4').value;
		var tele1 = document.getElementById('text4');
		var p = document.getElementById('tishi4');
		if (tele.length != 11) {
			//alert("手机号不等于11位");
			teleflag = false;
			tele1.className = "red";
			p.className = 'show';
		}else { // 等于11位
			var fnum = tele.substring(0,2);
			if ( fnum!="13" && fnum!="15" && fnum!="17" ) {
				//alert("手机号不正确");
				tele1.className = "red";
				p.className = 'show';
				teleflag = false;
			}else {
				teleflag = true;
			}
		}
	}

	function address() {       // 家庭住址
		// flag = true;
		var ads = document.getElementById('text7').value;
		var ads1 = document.getElementById('text7');
		var p = document.getElementById('tishi7');

		if (ads.length>10) { // 大于10
			addressflag = true;
		}else {
			//alert("家庭住址小于10位");
			ads1.className = "red";
			p.className = 'show';
			addressflag = false;
		}
	}
	function textShow() {       // 个性签名
		// flag = true;
		var ads = document.getElementById('text8').value;
		var ads1 = document.getElementById('text8');
		var p = document.getElementById('tishi8');
		if (ads.length>10) { // 大于10
			showflag = true;
		}else {
			//alert("个性签名小于10位");
			
			ads1.className = "red";
			p.className = 'show';
			showflag = false;
		}
	}
	function birthday() {       // 出生日期
		var ads = document.getElementById('text9').value;
		var ads1 = document.getElementById('text9');
		var p = document.getElementById('tishi9');
		
		//var judge = /^\d{4}\-\d{2}\-\d{2}+$/; // 正则表达式
		var judge1 = /^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$/;
		
		if (judge1.test(ads)) { 
			birthdayflag = true;
			// alert("日期正确！！！！！！！！！！");
		}else {
			//alert("出生日期格式不正确");
			birthdayflag = false;
			ads1.className = "red";
			p.className = 'show';
		}
	}

	function allvalue() {
		//alert("123123");

		telephone();
		username();
		pwd();
		repwd();
		birthday(); // 出生日期
		address();
		
		// show();  // 个性签名需要限制么?

		if (nameflag==true && pwdflag==true && repwdflag==true && teleflag==true && phoneflag==true && mailflag==true && addressflag==true && birthdayflag==true && showflag == true) {
			alert("注册成功");
			return true;
		}else {
			alert("注册失败！");
			//alert(nameflag);
			//alert(pwdflag);
			//alert(repwdflag);
			//alert(teleflag);
			//alert(phoneflag);
			//alert(mailflag);
			//alert(addressflag);
			//alert(birthdayflag);
			//alert(showflag);
			return false;
		}


		// if (flag == true) {
		// 	// alert("chenggong");
		// 	return true;
		// } else{
		// 	alert("失败");
		// 	return false;
		// }
	}

	function notNull() {
	 alert("用户ID已被占用");
	}
	
	</script>
    
  </head>
  
 <body>
<form action="insertDate" method="post" name="form1">
<div class="d2-part1logo" ></div>
       <div class="d1-tog" >
           <!-- <div style="float: left;margin-left:60px;margin-top:40px;background:yellow">手机号:</div>
           <div style="float: left;margin-left:60px;margin-top:40px;"><input type="text" name="phone" placeholder="在这输入你的手机号" style="height:30px;" id="text4"></div>
           <div style="float: left;margin-left:60px;margin-top:40px;">昵称:</div>
           <div style="float: left;margin-left:60px;margin-top:40px;"><input type="text" name="name" placeholder="在这输入你的昵称" style="height:30px;"></div>
           <div style="float: left;margin-left:60px;margin-top:40px;">密码:</div>
           <div style="float: left;margin-left:60px;margin-top:40px;"><input type="text" name="pwd" placeholder="在这输入你的密码" style="height:30px;" id="text2"></div>  -->
           
           <div style=" margin:0 auto; margin-top:50px;">
           		<h1>航模论坛</h1>
           </div>
           
           <div style="float: left;margin-left:60px;margin-top:20px;height: 55px">
           		<div style="float:left;margin-top:1px;">手机号:</div>
           		<div style="float: left;margin-left:73px;">
           			<input type="text" name="phoneNum" placeholder="请输入你的手机号" style="height:35px;width:400px"  id="text4">
           			<p class="tishi" id="tishi4">手机号输入错误</p>
           		</div>
           </div>
           <div style="float: left;margin-left:60px;margin-top:20px;height: 55px">
           		<div style="float:left;margin-top:1px;">昵称:</div>
           		<div style="float: left;margin-left:85px;">
           			<input type="text" name="userName" placeholder="请输入你的昵称(2-18位，如果是字母的话不能用数字开头)" style="height:35px;width:400px" id="text1">
           			<p class="tishi" id="tishi1">昵称输入错误</p>
           		</div>
           </div>
           <div style="float: left;margin-left:60px;margin-top:20px;height: 55px">
           		<div style="float:left;margin-top:1px;">密码:</div>
           		<div style="float: left;margin-left:85px;">
           			<input type="password" name="password" placeholder="请输入你的密码(6-18位且含有数字、字符以及大小写字母)" style="height:35px;width:400px" id="text2">
           			<p class="tishi" id="tishi2">密码格式错误</p>
           		</div>
           </div>
           
           
           <div style="float: left;margin-left:60px;margin-top:20px;height: 55px">
           		<div style="float:left;margin-top:1px;">确认密码:</div>
           		<div style="float: left;margin-left:60px;">
           			<input type="password" name="pwdagain" placeholder="再次输入你的密码" style="height:35px;width:400px" id="text3">
           			<p class="tishi" id="tishi3">密码不一致</p>
           		</div>
           </div>
          
           
          <!--  <div style="float: left;margin-left:60px;margin-top:40px;background:blue">性别: -->
           		<div style="float: left;margin-left:60px;margin-top:20px;height: 55px">性别：
                    <select name="select" id="select_k1" class="xla_k" style="width:80px;height:35px;margin-left:72px;margin-top:0px;">
                    <option value="男">男</option>
                    <option value="女">女</option>
                    </select> 
           		</div>
           
           
           <div style="float: left;margin-left:60px;margin-top:20px;height: 55px">
           		<div style="float:left;margin-top:1px;">出生日期:</div>
           		<div style="float: left;margin-left:60px;">
           			<input type="text" name="birthday" placeholder="日期格式：AAAA-BB-CC" style="height:35px;width:400px" id="text9">
           			<p class="tishi" id="tishi9">出生日期格式错误</p>
           		</div>
           </div>
           <div style="float: left;margin-left:60px;margin-top:20px;height: 55px">
           		<div style="float:left;margin-top:1px;">家庭住址:</div>
           		<div style="float: left;margin-left:60px;">
           			<input type="text" name="address" placeholder="请输入你的家庭住址（多于10位）" style="height:35px;width:400px" id="text7">
           			<p class="tishi" id="tishi7">住址格式错误</p>
           		</div>
           </div>
           <div style="float: left;margin-left:60px;margin-top:20px;height: 55px">
           		<div style="float:left;margin-top:1px;">个性签名:</div>
           		<div style="float: left;margin-left:60px;">
           			<input type="text" name="show" placeholder="请输入你的个性签名" style="height:35px;width:400px" id="text8">
           			<p class="tishi" id="tishi8">不符合要求</p>
           		</div>
           </div>
           
         
 			<br>
           <div class="tijiao"> 		
          		<input type="submit" style="width:100px;height:50px;" value="注册" onclick="return allvalue()">	
           </div>
  
</form>     
           <!-- 
            <a href="login.jsp">
            <button style="background-color:rgb(2,163,136);color:white;width:80px;height:40px;margin-left:0 auto;margin-top:25px;font-size:12px;">
            	点我去登陆
            </button>
            </a>  -->
            
            
       
       </div>
       <div class="login-help">
      			<p>已有账号？ <a href="login.jsp">点我去登陆</a></p>
       </div>
    
  </body>
</html>
