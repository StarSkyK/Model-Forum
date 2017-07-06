package bbs.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.Model.detailsModel;
import bbs.Model.insertReplyModel;
import bbs.Model.loginModel;
import bbs.Model.replyModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class insertReplySer extends HttpServlet {

	
	public insertReplySer() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		load();
		//request.setCharacterEncoding("utf8");
		String topicID = request.getParameter("topicID");
		System.out.print("进了get方法"+topicID);
		
		System.out.println("insertreplyser");
		String scontent = new String(request.getParameter("content").getBytes("iso-8859-1"), "utf8");
		
		try {
			HttpSession session = request.getSession();
			loginModel mmmd =(loginModel) session.getAttribute("login");
			if(mmmd == null) {
				response.setContentType("text/html;charset=gb2312");
				response.getWriter().print("<script language='javascript'>alert('请登录!');</script>");
				response.setHeader("refresh", "0.1;login.jsp");
			}else {
				System.out.println("mmmd ========="+mmmd.getUserID().toString() + mmmd.getuserName().toString());
				System.out.println("topicID ========="+topicID);
				
				insertReplyModel insertReplyM = new insertReplyModel();
				System.out.println("++ insertReplySer ++");
				
				String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true&characterEncoding=utf8";
				String userString ="root";
				String password ="312312";
				Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
		
				String userID = mmmd.getUserID().toString();
				String userName = mmmd.getuserName().toString();
				String content = scontent;	
				String replyTime = getTime().toString();
				
				System.out.println(content);
				
				String sql="INSERT INTO reply(topicID,userID,userName,content,replyTime) VALUES(?,?,?,?,?)";
				
		     	PreparedStatement st=(PreparedStatement) connection.prepareStatement(sql);
		     	st.setString(1,topicID);
				st.setString(2,userID);
				st.setString(3,userName);
				st.setString(4,content);
				st.setString(5,replyTime);
				st.execute();
	
				
				request.setAttribute("insertReplyM", insertReplyM);
				RequestDispatcher dispatcher = request.getRequestDispatcher("detailsSer");
				dispatcher.forward(request, response);
				//response.sne
				return;
			}
			
		} catch (Exception e) {
		}
	}

	public static void load() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
//		load();
//		
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html");
//		
//		String topicID = request.getParameter("topicID");
//		System.out.print("进了post方法"+topicID);
//		
//		try {
//			HttpSession session = request.getSession();
//			loginModel mmmd =(loginModel) session.getAttribute("login");
//			System.out.println("mmmd ========="+mmmd.getUserID().toString() + mmmd.getuserName().toString());
//			System.out.println("topicID ========="+topicID);
//			
//			insertReplyModel insertReplyM = new insertReplyModel();
//			System.out.println("++ insertReplySer ++");
//			
//			String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
//			String userString ="root";
//			String password ="312312";
//			Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
//	
//			String userID = mmmd.getUserID().toString();
//			String userName = mmmd.getuserName().toString();
//			String content = request.getParameter("content");	
//			String replyTime = getTime().toString();
//			
//			System.out.println(content);
//			
//			String sql="INSERT INTO reply(topicID,userID,userName,content,replyTime) VALUES(?,?,?,?,?)";
//			
//	     	PreparedStatement st=(PreparedStatement) connection.prepareStatement(sql);
//	     	st.setString(1,topicID);
//			st.setString(2,userID);
//			st.setString(3,userName);
//			st.setString(4,content);
//			st.setString(5,replyTime);
//			st.execute();
//
//			
//			request.setAttribute("insertReplyM", insertReplyM);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("detailsSer");
//			dispatcher.forward(request, response);
//		} catch (Exception e) {
//		}
		return;
		
	}


	public void init() throws ServletException {
	}

	public static String getTime() {
		
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dFormat.format(new Date()).toString();
		
	}
}
