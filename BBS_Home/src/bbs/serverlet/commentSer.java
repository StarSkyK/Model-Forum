package bbs.serverlet;

import java.awt.print.Pageable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import bbs.Model.commentModel;
import bbs.Model.detailsModel;
import bbs.Model.insertReplyModel;
import bbs.Model.loginModel;

public class commentSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public commentSer() {
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
	public static void load() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
		
		load();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
//		System.out.println("++ commentSer get++");
		
		
		
		try {
			;
			boolean ispostback = false;
			
			String replyID = "";
			String topicID = "";
			String userID = "";
			String userName = "";
			String commentContent = "";
			String commentTime = "";
//			System.out.println(request.getParameter("commentContent").toString());
		
			commentModel commentM = new commentModel();
			HttpSession session = request.getSession();
			detailsModel model = (detailsModel)session.getAttribute("detailsS");
			loginModel model2 =(loginModel)session.getAttribute("login");
			
			String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
			String userString ="root";
			String password ="312312";
			Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
			
			
			replyID = request.getParameter("hidden_replyID");
			System.out.println("replyID == "+replyID);
			topicID = model.getTopicID().toString();
			userID = model2.getUserID().toString();
			userName = model2.getuserName().toString();
			//String commentContent = request.getParameter("commentContent").toString();
			commentContent = new String(request.getParameter("commentContent").getBytes("iso-8859-1"), "utf8");
			System.out.println("content == "+commentContent);
			commentTime = getTime().toString();
			System.out.println("time == "+commentTime);
if(!ispostback) {
				
			
			String sql="INSERT INTO comment(replyID,topicID,userID,userName,content,commentTime) VALUES(?,?,?,?,?,?)";
			
	     	PreparedStatement st=(PreparedStatement) connection.prepareStatement(sql);
	     	st.setString(1,replyID);
	     	st.setString(2,topicID);
			st.setString(3,userID);
			st.setString(4,userName);
			st.setString(5,commentContent);
			st.setString(6,commentTime);
			st.execute();
}else{ }
			
			request.setAttribute("commentM", commentM);
			request.setAttribute("model", model);
			RequestDispatcher dispatcher = request.getRequestDispatcher("detailsSer");
			dispatcher.forward(request, response);
			return;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
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
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	public static String getTime() {
		
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dFormat.format(new Date()).toString();
		
	}

}
