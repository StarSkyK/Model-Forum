package bbs.serverlet;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


import bbs.Model.replyModel;

public class replySer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public replySer() {
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
			System.out.println("++++++++1");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			load();
			
			try {
				HttpSession session = request.getSession();
				
				System.out.println("++ replySer ++");
//				replyModel replyM = new replyModel();
				
				String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
				String userString ="root";
				String password ="abc110";
				Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
				
				// topicID
				String s = "2";
				
				Statement sql =(Statement) connection.createStatement();
				
				// 查看相同的topicID有多少条：
//				ResultSet rsCount = sql.executeQuery("select count(*) from jsp_test.reply where topicID ="+s);
//				rsCount.next();
//				String i = rsCount.getString("count(*)");
//				System.out.println("查看相同的topicID有多少条:"+i);
				
				ResultSet rs = sql.executeQuery("select * from jsp_test.reply where topicID ="+s);
				
				ArrayList<Object> list = new ArrayList<Object>();
				
				while (rs.next()) {
					System.out.println("===== : "+rs.getString("userID"));
					System.out.println("===== : "+rs.getString("content"));
					System.out.println("===== : "+rs.getString("userName"));
					
					replyModel replyM = new replyModel();
					
					replyM.setUserName(rs.getString("userName"));
					replyM.setUserID(rs.getString("userID"));
					replyM.setContent(rs.getNString("content"));
//					replyM.setReplyTime(rs.getNString("replyTime"));
					list.add(replyM);
					
					System.out.println("while ->");
				}
				
				System.out.println("while end ===");
				
//				detailsM.setTitle(rs.getString("title"));
//				detailsM.setUserName(rs.getString("userName"));
//				detailsM.setUserID(rs.getString("userID"));
//				detailsM.setTopicID(rs.getString("topicID"));
//				detailsM.setContent(rs.getString("content"));
//				detailsM.setType(rs.getString("type"));
				
//				System.out.println("===== : "+rs.getString("userID"));
//				System.out.println("===== : "+rs.getString("content"));
//				System.out.println("===== : "+rs.getString("userName"));
				
				request.setAttribute("replyList", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("details_test.jsp");
				dispatcher.forward(request, response);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				
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
