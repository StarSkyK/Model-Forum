<%@page import="bbs.Model.IndexModel"%>
<%@page import="java.sql.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import ="bbs.Model.loginModel" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=gbk" charset = "gbk"/>
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
	      	<a >
	      		<font color="#999999" face="微软雅黑">手机端</font>
	      	</a>
	      	<font face="微软雅黑"> 
		        <font color="#FFFFFF">
		        	<font color="#999999">|</font>
		        </font>
		        <font color="#FFFFFF"> 
			        <a  >
			        	<font color="#999999">帮助</font>
			        </a> 
			        <font color="#999999">|</font> 
			        <font color="#999999">
			        	<a  >
			        		<font color="#999999">用户申请</font>
			        	</a> | 
			        	<a >
			        		<font color="#999999">积分说明</font>
			        	</a> | 
			        	<a  >
			        		<font color="#999999">头条新闻</font>
			        	</a>
			        </font> 
		    	</font>
		    	
	    	</font></center>
	    	
	      </div>
	    </td>
	  </tr>
	</table>
	<link rel="stylesheet" type="text/css" href="cssFile/style_14_common.css" />
		<%
			HttpSession sss =request.getSession(true);
			loginModel mmmd =(loginModel) sss.getAttribute("login");
			String userName ="null";
			if(mmmd!=null){
				userName =mmmd.getuserName().toString();
			}
		 %>
	<script language = "javascript">
		function changeClass(){
			var a = document.getElementById("car");
			a.setAttribute("style","text-decoration:none;color:blue;background-color:red;")
		}
		function check(){
			var mmd =document.getElementById("checklogin").value;
			if (mmd=="null") {
				alert("请先登录");
				var form2 =document.form2;
				form2.action="login.jsp";
				form2.submit;
			}
			
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
 <body id="nv_portal" class="pg_index" onkeydown="if(event.keyCode==27) return false;">
	
		<div class="top">
			<div class="wrap1200 clearfix">
				<div class="fl1 toplinkl">
					<span class="user">
						<a class="mxmobile" href="index.jsp" id="5imxmobile">首&nbsp;页<b>&nbsp;</b></a>
					</span>
				</div>
				
				<div class="fl1 toplinkuser"></div>
	            <div class="fr1 toplinkr">
	            	<span class="log">
	            		<a href="login.jsp" onclick="" title="用户登录" id="userLoginID">登&nbsp;录&nbsp;</a>
	            	</span><!--
	            	<span class="log">
	            		<a href="sendTopic.jsp" onclick="check()" title="发帖" id="sendTopic">发帖</a>
	            	</span>--> 
	            	<span class="fav fl1">
	            		<form action="sendTopic.jsp" method="post" name="form2">
	            		
	            			<input type="submit" name="submit" value="发&nbsp;帖" onclick="check()">
	            		</form>
	            	</span> 
	            </div>
			</div>
			<div class="search">
				   
			</div>
		</div><br>
		<input type="hidden" id="checklogin" value=<%=userName%>>
		<table align=center width="1152px" bgcolor="white" height="1600px">

	
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
			<tr  align=center height="208px" >
				<!--  <td class="part" align="center">-->
				    
					<td width="285px">
				     	<a href="indexSer?typeID=1" ><img src="images/car.png" width="100px"><br><br><p>车模</p></a>
					</td>
					<td>
				     	<a href="indexSer?typeID=2"><img src="images/plane.png" width="100px"><br><br><p>航模</p></a>
					</td>
					<td>
				     	<a href="indexSer?typeID=3"><img src="images/ship.png" width="85px"><br><br><p>船模</p></a>
					</td>
					<td>
				     	<a href="indexSer?typeID=4"><img src="images/other.png" width="100px"><br><br><p>其他</p></a>
					</td>
		
					
				<!-- </td> -->
			</tr> 
			<tr >
					
					<% String typedddd=request.getParameter("typeID");
					if(typedddd == null){
						typedddd= "1";
					}
					if(typedddd.equals("1")){ %>
					<td width="100px" height="60px" bgcolor="#dbdadc" class="part"><p>车&nbsp;模</p></td>
					<%}else if(typedddd.equals("2")){ %>
					<td width="100px" height="60px" bgcolor="#dbdadc" class="part"><p>航&nbsp;模</p></td>
					<%}else if(typedddd.equals("3")){ %>
					<td width="100px" height="60px" bgcolor="#dbdadc"  class="part"><p>船&nbsp;模</p></td>
					<%}else if(typedddd.equals("4")){ %>		
					<td width="100px" height="60px" bgcolor="#dbdadc" class="part"><p>其&nbsp;他</p></td>
					<%}%>
					
			</tr> 
			<tr align="center">
				<td class="topic">
				 
					<table  spacing="2" width="1130" align="center"> 
						
						
					<%!  
					    public static final String DRIVER = "com.mysql.jdbc.Driver";  
					    public static final String USER = "root";  
					    public static final String PASS = "312312";  
					    public static final String URL = "jdbc:mysql://localhost:3306/jsp_test?useSSL=true";  
					    public static final int PAGESIZE = 10;  
					    int pageCount;  
					    int curPage = 1; 
					    String type="";
					%>  
					<%  
						
					    //一页放10个  
					    String user = null;  
					    String pass = null;  
					    try{  
					    	//System.out.println("ttt");
					        Class.forName(DRIVER);  
					        Connection con = DriverManager.getConnection(URL,USER,PASS); 
					        
					 
					       	type = request.getParameter("typeID");
					       	if(type==null){
					       		type="1";
					       	}
					        String sql="SELECT title,topicID From jsp_test.announces WHERE type="+type;
					        PreparedStatement stat = con.prepareStatement(sql);  
					        ResultSet rs = stat.executeQuery();  
					        rs.last();  
					        
					       
					        
					        int size = rs.getRow();  
					        pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1);  
					        String tmp = request.getParameter("curPage");  
					        if(tmp==null){  
					            tmp="1";  
					        }
					        if(Integer.parseInt(tmp)<1){
					        	tmp="1";
					        }
					        curPage = Integer.parseInt(tmp);  
					        if(curPage>=pageCount) curPage = pageCount;  
					        boolean flag = rs.absolute((curPage-1)*PAGESIZE+1);  
					        int count = 0;  
					      
					        do{  
					            if(count>=PAGESIZE)break;  
					            String title = rs.getString(1);
					            String topicID= rs.getString(2);
					            count++; 
					            if(count%2==0){
					            %>  
					            
						        <tr class="bg2">  
						            <td><a href="detailsSer?topicID=<%=topicID %>"><%=title%></a></td>  
						        </tr>
						        <%
					            }else{
						        %>
						        <tr class="bg1">  
					            	<td><a href="detailsSer?topicID=<%=topicID %>"><%=title%></a></td>  
					       		</tr>
					            <%  
					            }
					        }while(rs.next());  
					        con.close();  
					    }  
					    catch(Exception e){  
					          
					    }  
					%>  
					</table>  
					
				</td>
						
			</tr>
			<tr >
				<td align="center" colspan="4">
				<div class="foot">
							<a href = "index.jsp?curPage=1&typeID=<%=type%>" >首页</a>  &nbsp;&nbsp;&nbsp;
							<a href = "index.jsp?curPage=<%=curPage-1%>&typeID=<%=type%>" >上一页</a>&nbsp;  &nbsp;&nbsp;
							<a href = "index.jsp?curPage=<%=curPage+1%>&typeID=<%=type%>" >下一页</a>  &nbsp;&nbsp;&nbsp;
							<a href = "index.jsp?curPage=<%=pageCount%>&typeID=<%=type%>" >尾页</a>  
							&nbsp;&nbsp;&nbsp;&nbsp;	第<%=curPage%>页/共<%=pageCount%>页  
						</div>
					</td>
			</tr>
	</table>
	

</body>
</html>
