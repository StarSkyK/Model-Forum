package bbs.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;



import bbs.Model.detailsModel;

public class detailsSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public detailsSer() {
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
		// Â¥Ö÷µÄÄÚÈÝ
		load();
		try {
			
			HttpSession session = request.getSession();
			
			System.out.println("++++++++2");
			detailsModel detailsM = new detailsModel();
			
			String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
			String userString ="root";
			String password ="abc110";
			Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
			
			String str1 = "15543563223";
			
			Statement sql =(Statement) connection.createStatement();
			ResultSet rs = sql.executeQuery("select * from jsp_test.announces where userID ="+str1);
			rs.next();
			
//			ResultSet title = sql.executeQuery("select carTopic from jsp_test.post_car where userID = 13482031500");
//			ResultSet userName = sql.executeQuery("select userName from jsp_test.post_car where userID ="+str1);
//			ResultSet userID = sql.executeQuery("select userID from jsp_test.post_car where userID ="+str1);
//			ResultSet topicID = sql.executeQuery("select carTopicID from jsp_test.post_car where userID ="+str1);
//			ResultSet content = sql.executeQuery("select content from jsp_test.post_car where userID ="+str1);
			
//			title.next();
//			userName.next();
//			userID.next();
//			topicID.next();
//			content.next();
			
			System.out.println("++++++++3");
			
//			System.out.println(userID);
//			System.out.println("===== : "+title);
			
			detailsM.setTitle(rs.getString("title"));
			detailsM.setUserName(rs.getString("userName"));
			detailsM.setUserID(rs.getString("userID"));
			detailsM.setTopicID(rs.getString("topicID"));
			detailsM.setContent(rs.getString("content"));
			detailsM.setType(rs.getString("type"));
			
			System.out.println("===== : "+rs.getString("title"));
			System.out.println("===== : "+rs.getString("content"));
			
			request.setAttribute("detailsM", detailsM);
			RequestDispatcher dispatcher = request.getRequestDispatcher("replySer");
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
