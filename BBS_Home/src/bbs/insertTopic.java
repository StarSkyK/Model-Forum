package bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class insertTopic extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public insertTopic() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public static void LoadMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("连接数据库成功");
		} catch (Exception e) {}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoadMySQL();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		TopicModel tpModel =new TopicModel();
		
		HttpSession session =request.getSession();
		loginModel mmmd =(loginModel) session.getAttribute("login");
		
		try {
			String uri = "jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
			String user="root";
			String password ="abc110";
	     	Connection con=(Connection) DriverManager.getConnection(uri,user,password);
	     	
	     	String title =request.getParameter("title");
	     	String select =request.getParameter("select");
	     	String content =request.getParameter("content");
	     	String userID =mmmd.getUserID();
	     	String userName =mmmd.getuserName();
	     	String sql="INSERT INTO post_car(UserID,carTopic,content,userName) VALUES(?,?,?,?)";
	     	PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
	     	
	     	st.setString(1,userID);
			st.setString(2,title );
			st.setString(3,content);
			st.setString(4,userName);
			st.execute();
			
			request.setAttribute("TopicModel", tpModel);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
	     	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
