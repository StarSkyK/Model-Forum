package bbs.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.Model.insertReplyModel;
import bbs.Model.loginModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class insertReplySer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public insertReplySer() {
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
			System.out.println("数据库已经连接");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		load();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		try {
			HttpSession session = request.getSession();
			
			// 创建模型
			insertReplyModel insertReplyM = new insertReplyModel();
			
//			loginModel loginModel = session.getattribute();
			
			System.out.println("++ insertReplySer ++");
			
			String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
			String userString ="root";
			String password ="abc110";
			Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
			
			String topicID = "2";
			String userID = "13482031511";
			String userName = "高淞源";
			String content = request.getParameter("content");
			String replyTime = "1997-03-29 10:31:57";
			
			System.out.println(content);
			
			String sql="INSERT INTO reply(topicID,userID,userName,content,replyTime) VALUES(?,?,?,?,?)";
			
	     	PreparedStatement st=(PreparedStatement) connection.prepareStatement(sql);
	     	st.setString(1,topicID);
			st.setString(2,userID);
			st.setString(3,userName);
			st.setString(4,content);
			st.setString(5,replyTime);
			st.execute();
			
//			response.setHeader("refresh", "0.1;details_test.jsp");
			
		
//			PrintWriter out = null;
//			out.write("<script>");
//			out.write("location.reload();");
//			out.write("</script>");
			
			request.setAttribute("insertReplyM", insertReplyM);
//			response.sendRedirect("details_test.jsp");
//			response.setHeader("refresh", "0.1;details_test.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher("detailsSer");
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
