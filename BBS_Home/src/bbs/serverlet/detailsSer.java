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





import bbs.Model.TopicModel;
import bbs.Model.detailsModel;
import bbs.Model.loginModel;

public class detailsSer extends HttpServlet {

	public detailsSer() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		load();
		try {
			
			System.out.println("++++details get++++2");
			
			
			String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
			String userString ="root";
			String password ="312312";
			Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
			HttpSession session =request.getSession();
			loginModel mmmd =(loginModel) session.getAttribute("login");
			
			detailsModel model = (detailsModel)request.getAttribute("model");
			//第一遍从首页进来需要getparameter中topicid，第二遍从commentser中model获取
			String topicID =request.getParameter("topicID");
			if (topicID==null) {
				topicID =model.getTopicID().toString();
			}

			System.out.println("get--topicID==="+topicID);
			Statement sql =(Statement) connection.createStatement();
			ResultSet rs2 = sql.executeQuery("select * from jsp_test.announces where topicID ="+topicID);
			detailsModel detailsM = new detailsModel();
			if (rs2.next()) {
				session.setAttribute("detailsS", detailsM);
				detailsM.setTitle(rs2.getString("title"));
				detailsM.setUserName(rs2.getString("userName"));
				detailsM.setUserID(rs2.getString("userID"));
				detailsM.setTopicID(topicID);
				detailsM.setContent(rs2.getString("content"));
				detailsM.setType(rs2.getString("type"));
				detailsM.setAnnouncesTime(rs2.getString("announcesTime").toString());
				request.setAttribute("detailsM", detailsM);
				RequestDispatcher dispatcher = request.getRequestDispatcher("replySer");
				dispatcher.forward(request, response);
				return;
			}else {
				System.err.println("data 不存在");
				System.out.println("get===== : "+rs2.getString("title"));
				System.out.println("get===== : "+rs2.getString("content"));
				System.out.println("get===== : "+rs2.getString("announcesTime"));
				request.setAttribute("detailsM", detailsM);
				RequestDispatcher dispatcher = request.getRequestDispatcher("replySer");
				dispatcher.forward(request, response);
				return;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
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
	
		load();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			
			System.out.println("++++details post++++2");
			detailsModel detailsM = new detailsModel();
			
			String uriString ="jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
			String userString ="root";
			String password ="312312";
			Connection connection = (Connection) DriverManager.getConnection(uriString, userString, password);
			
			HttpSession session =request.getSession();
			loginModel mmmd =(loginModel) session.getAttribute("login");
			
			
			
			System.out.print(mmmd.getUserID().toString());
			
			Statement sql =(Statement) connection.createStatement();
			ResultSet rs1 = sql.executeQuery("select topicID from jsp_test.announces where userID ="+mmmd.getUserID().toString());
//			rs1.next();
	
			System.out.println("++++++++3"); 
			
			int intTopicID = 0;
			while (rs1.next()) {
				 
				int i = Integer.parseInt(rs1.getString("topicID"));
				System.out.print("======"+i+"=======");
				if (i>intTopicID) {
					
					intTopicID = i;
					System.out.print("======"+intTopicID+"=======");
				}
			}
			
			String topicID = Integer.toString(intTopicID);
			System.out.println("最大topicID"+topicID);
			ResultSet rs2 = sql.executeQuery("select * from jsp_test.announces where topicID ="+topicID);
			if (rs2.next()) {
			detailsM.setTitle(rs2.getString("title"));
			detailsM.setUserName(rs2.getString("userName"));
			detailsM.setUserID(rs2.getString("userID"));
			detailsM.setTopicID(topicID);
			detailsM.setContent(rs2.getString("content"));
			detailsM.setType(rs2.getString("type"));
			detailsM.setAnnouncesTime(rs2.getString("announcesTime").toString());
			}else{
				System.out.print("data 不存在");
			}
			
			System.out.println("===== : "+rs2.getString("title"));
			System.out.println("===== : "+rs2.getString("content"));
			System.out.println("===== : "+rs2.getString("announcesTime"));
			
			request.setAttribute("detailsM", detailsM);
			RequestDispatcher dispatcher = request.getRequestDispatcher("replySer");
			dispatcher.forward(request, response);
			return;
			
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
