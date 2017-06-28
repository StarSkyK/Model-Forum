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

import bbs.Model.TopicModel;
import bbs.Model.loginModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class insertTopic extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public insertTopic() {
		super();
	}

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
		//加载数据库
		LoadMySQL();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//创建模型1
		TopicModel tpModel =new TopicModel();
	
		
		//模型2 获取session
		HttpSession session =request.getSession();
		loginModel mmmd =(loginModel) session.getAttribute("login");
		
		try {
			String uri = "jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
			String user="root";
			String password ="abc110";
	     	Connection con=(Connection) DriverManager.getConnection(uri,user,password);
	     	
	     	String title =request.getParameter("title");
	     	String content =request.getParameter("content");
	     	String userID =mmmd.getUserID();
	     	String userName =mmmd.getuserName();
	    	//获取帖子类型
			String select =request.getParameter("select");
			
	     	String sql="INSERT INTO announces(UserID,title,content,userName,type) VALUES(?,?,?,?,?)";
	     	PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
	     	st.setString(1,userID);
			st.setString(2,title );
			st.setString(3,content);
			st.setString(4,userName);
			st.setString(5,select);
			st.execute();
			
			request.setAttribute("TopicModel", tpModel);
			RequestDispatcher dispatcher = request.getRequestDispatcher("detailsSer");
			dispatcher.forward(request, response);
			
	     	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
