package bbs.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.*;
import bbs.Model.loginModel;

import com.mysql.jdbc.Connection;


public class loginSer extends HttpServlet {

	public loginSer() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
		 
		loginModel loginM = new loginModel();
			
		System.out.println("-----------------------loginSer------------------------");
		
		String str1 = request.getParameter("login");
		String str2 = request.getParameter("password");
		
		String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
		String userString ="root";
		String password ="312312";
		Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
		
		Statement sql =(Statement) connection.createStatement();
		ResultSet rs=sql.executeQuery("select UserID from jsp_test.users where UserID ="+str1);
		if (rs.next()) {
			ResultSet rs1 =sql.executeQuery("select * from jsp_test.users where UserID ="+str1);
			if (rs1.next()) {
				String userPwd =rs1.getString("UserPwd").toString();
				if (userPwd.equals(str2)) {
					loginM.setuserName(rs1.getString("UserName"));
					loginM.setUserID(str1);
					HttpSession session = request.getSession(true);
					session.setAttribute("login", loginM);
					
					try {
					request.setAttribute("loginM", loginM);
					RequestDispatcher dispatcher = request.getRequestDispatcher("indexSer");
					dispatcher.forward(request, response);
					}catch(Exception e) {}
					
				}else {
					response.setContentType("text/html;charset=gb2312");
					response.getWriter().print("<script language='javascript'>alert('密码不正确!');</script>");
					response.setHeader("refresh", "0.1;login.jsp");
					//return;
				}
			}else {
				response.setContentType("text/html;charset=gb2312");
				response.getWriter().print("<script language='javascript'>alert('ID不存在!');</script>");
				response.setHeader("refresh", "0.1;login.jsp");
				//return;
			}
		}else {
			response.setContentType("text/html;charset=gb2312");
			response.getWriter().print("<script language='javascript'>alert('ID不存在!');</script>");
			response.setHeader("refresh", "0.1;login.jsp");
			//return;
		}
//		if(!rs.next()) { 
//			System.out.println("ID");
//			response.setContentType("text/html;charset=gb2312"); 
//			response.getWriter().print("<script language='javascript'>alert('ID不存在!');</script>");
//			response.setHeader("refresh", "0.1;login.jsp"); 
//			
//			return;
//		} else {
//			
//			ResultSet rs1 =sql.executeQuery("select UserPwd from jsp_test.users where UserID ="+str1);
//			if (rs1.next()) {
//				if (rs1.getString("UserPwd").equals(str2)) { 
//						ResultSet userNameRE =sql.executeQuery("select UserName from jsp_test.users where UserID ="+str1);
//						if (userNameRE.next()) {
//							loginM.setuserName(userNameRE.getString("UserName"));
//							loginM.setUserID(str1);
//							HttpSession session = request.getSession(true);
//							session.setAttribute("login", loginM);
//							
//							try {
//								
//							} catch (Exception e) {
//								// TODO: handle exception
//							}
//						}else {
//							//设置属性
//							request.setAttribute("loginM", loginM);
//							RequestDispatcher dispatcher = request.getRequestDispatcher("indexSer");
//							dispatcher.forward(request, response);
//							return;
//						}
//				}else {
//					response.setContentType("text/html;charset=gb2312");
//					response.getWriter().print("<script language='javascript'>alert('密码不正确!');</script>");
//					response.setHeader("refresh", "0.1;login.jsp");
//					return;
//				}
//				
//			}else {
//				response.setContentType("text/html;charset=gb2312");
//				response.getWriter().print("<script language='javascript'>alert('密码不正确!');</script>");
//				response.setHeader("refresh", "0.1;login.jsp");
//
//				return;
//			}
//		}
//		
//		
//		
//		
////		response.setContentType("text/html");
////		PrintWriter out = response.getWriter();
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
