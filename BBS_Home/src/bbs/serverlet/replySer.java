package bbs.serverlet;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;





import bbs.Model.detailsModel;
import bbs.Model.loginModel;
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


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		load();
		try {
			
			detailsModel model =(detailsModel)request.getAttribute("detailsM");
			
			System.out.println("++ replySer get++");
			
//			System.out.println(request.getParameter(""));
			String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true&characterEncoding=utf8";
			String userString ="root";
			String password ="312312";
			Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
			
			String topicID= model.getTopicID().toString();
			System.out.println(topicID);
			
			Statement sql =(Statement) connection.createStatement();
			ResultSet rs = sql.executeQuery("select * from jsp_test.reply where topicID ="+topicID);
			
			ArrayList<Object> list = new ArrayList<Object>();
			
			while (rs.next()) {
				System.out.println("===== : "+rs.getString("userID"));
				System.out.println("===== : "+rs.getString("content"));
				System.out.println("===== : "+rs.getString("userName"));
				
				replyModel replyM = new replyModel();
				
				replyM.setReplyID(rs.getString("replyID"));
				System.out.println("1");
				replyM.setUserName(rs.getString("userName"));
				System.out.println("2");
				replyM.setUserID(rs.getString("userID"));
				System.out.println("3");
				replyM.setContent(rs.getString("content"));
				System.out.println("4");
				replyM.setReplyTime(rs.getString("replyTime"));
				System.out.println("5");
				replyM.setTopicID(rs.getString("topicID"));
				System.out.println("6");
				list.add(replyM);
				
				System.out.println("while ->");
			}
			
			System.out.println("while end ===");	
			request.setAttribute("replyList", list);
			request.setAttribute("topicID", topicID);
			RequestDispatcher dispatcher = request.getRequestDispatcher("details_test.jsp");
			System.out.print("cross　fire");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
		}
		
		
	}

	public static void load() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("++++++++1");
		} catch (Exception e) {
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			load();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			try {
				
				detailsModel model =(detailsModel)request.getAttribute("detailsM");
				
				System.out.println("++ replySer post++");
				
				String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
				String userString ="root";
				String password ="312312";
				Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
				
				String topicID= model.getTopicID().toString();
				System.out.println(topicID);
				
				Statement sql =(Statement) connection.createStatement();
				ResultSet rs = sql.executeQuery("select * from jsp_test.reply where topicID ="+topicID);
				
				ArrayList<Object> list = new ArrayList<Object>();
				
				while (rs.next()) {
					System.out.println("===== : "+rs.getString("userID"));
					System.out.println("===== : "+rs.getString("content"));
					System.out.println("===== : "+rs.getString("userName"));
					
					replyModel replyM = new replyModel();
					
					replyM.setUserName(rs.getString("userName"));
					replyM.setUserID(rs.getString("userID"));
					replyM.setContent(rs.getString("content"));
					replyM.setReplyTime(rs.getString("replyTime"));
					replyM.setTopicID(rs.getString("topicID"));
					list.add(replyM);
					
					System.out.println("while ->");
				}
				
				System.out.println("while end ===");	
				request.setAttribute("replyList", list);
				request.setAttribute("topicID", topicID);
				RequestDispatcher dispatcher = request.getRequestDispatcher("details_test.jsp");
				dispatcher.forward(request, response);
				return;
			} catch (Exception e) {
			}
		
	}

	public void init() throws ServletException {
	}
	
}
