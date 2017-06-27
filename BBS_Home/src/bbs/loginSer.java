package bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.*;

import com.mysql.jdbc.Connection;


public class loginSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public loginSer() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	
	public static void load() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		load();
		try {
		 
		loginModel loginM = new loginModel();
			
		System.out.println("asdasdasd------------------------");
		
		String str1 = request.getParameter("login");
		String str2 = request.getParameter("password");
		
		
		System.out.println(str1); // 账号
		System.out.println("输入的密码是："+str2); // 密码
	
		String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
		String userString ="root";
		String password ="abc110";
		Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
		
		Statement sql =(Statement) connection.createStatement();
		ResultSet rs=sql.executeQuery("select UserID from jsp_test.users where UserID ="+str1);
		
//		rs.next();
//		System.out.println(rs.getString("UserPwd"));
		
		if(!rs.next()) { 
			System.out.println("ID不存在"); // ID不存在
			
			// 弹出提示框
			response.setContentType("text/html;charset=gb2312"); // 乱码解决
			response.getWriter().print("<script language='javascript'>alert('ID不存在!');</script>");
			response.setHeader("refresh", "0.1;login.jsp"); // 延迟0.1秒
			
			return;
		} else {
			
			System.out.println("ID存在"); // ID存在
			
			ResultSet rs1 =sql.executeQuery("select UserPwd from jsp_test.users where UserID ="+str1);
			rs1.next();
			System.out.println("--正确的密码是："+rs1.getString("UserPwd"));
			
			if (rs1.getString("UserPwd").equals(str2)) { // 密码正确
				// 密码正确  可以登录
				System.out.println("密码正确---!!");

				ResultSet userNameRE =sql.executeQuery("select UserName from jsp_test.users where UserID ="+str1);
				userNameRE.next();
				
				// 把用户名传递到另一个servlet
				loginM.setuserName(userNameRE.getString("UserName"));
				loginM.setUserID(str1);
				HttpSession session = request.getSession(true);
				session.setAttribute("login", loginM);
				
//				loginModel loginM = session.getAttribute("login");
//				String loginName = loginM.getuserName();
				
				try {
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				// 保持登录状态，把用户名传给首页
				
				
//				 跳转到首页
				response.sendRedirect("sendTopic.jsp");
				
				
			} else {
				System.out.println("密码不正确");
				
				// 弹出提示框
				response.setContentType("text/html;charset=gb2312");
				response.getWriter().print("<script language='javascript'>alert('密码不正确!');</script>");
				response.setHeader("refresh", "0.1;login.jsp");
//				response.sendRedirect("login.jsp");
				return;
			}
		}
		
		
		
		
		
		
		System.out.println("------asdasdasd-----");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
