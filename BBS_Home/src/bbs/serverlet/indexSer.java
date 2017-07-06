package bbs.serverlet;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import bbs.Model.IndexModel;
import bbs.Model.loginModel;
public class indexSer extends HttpServlet {

	public indexSer() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public static void LoadMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("数据库连接成功");
		} catch (Exception e) {}
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LoadMySQL();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=gb2312");
		System.out.println("--进doget方法");
		
//		 try {
//				String uri = "jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
//				String user="root";
//				String password ="312312";
//		     	Connection con=(Connection) DriverManager.getConnection(uri,user,password);
//		     
//		     	Statement st = (Statement) con.createStatement(); 
//		     	String type  =request.getParameter("typeID").toString();
//		     	String sql ="";
//		     	if (type.equals("1")||type.equals("")) {
//					sql ="select * from jsp_test.announces where type ="+1;
//				}else if (type.equals("2")) {
//					sql ="select * from jsp_test.announces where type ="+2;
//				}else if (type.equals("3")) {
//					sql ="select * from jsp_test.announces where type ="+3;
//				}else if (type.equals("4")) {
//					sql ="select * from jsp_test.announces where type ="+4;
//				}
//		     	ResultSet rs = st.executeQuery(sql);
//		     	ArrayList<Object> list = new ArrayList<Object>();
//		     	while (rs.next()) {
//		     		IndexModel model =new IndexModel();
//		     		model.setTopicID(rs.getString(1));;
//		     		model.setTitle(rs.getString("title"));
//		     		list.add(model);
//				}
//		     	
//		     	request.setAttribute("indexList", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
				return;
//	     	} 
//	    	catch (Exception e) {
//				System.out.println(e);
//			}
//		
//		
		
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoadMySQL();
		request.setCharacterEncoding("UTF-8");
		System.out.print("进入post方法");
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		
		doGet(request, response);
		
//		HttpSession session = request.getSession(true);
//	    loginModel loginModel = (loginModel)session.getAttribute("login");
//		
//	    try {
//				String uri = "jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
//				String user="root";
//				String password ="312312";
//		     	Connection con=(Connection) DriverManager.getConnection(uri,user,password);
//		     	Statement st = (Statement) con.createStatement(); 
//		     	ResultSet rs = st.executeQuery("select * from jsp_test.announces where type =1");
//		     	System.out.println("---------rs is ok ");
//		     	ArrayList<Object> list = new ArrayList<Object>();
//		     	while (rs.next()) {
//		     		IndexModel model =new IndexModel();
//		     		model.setTitle(rs.getString("title"));
//		     		model.setTopicID(rs.getString(1));
//		     		list.add(model);
//				}
//		     	
//		     	request.setAttribute("indexList", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
				return;
//
//	     	} 
//	    	catch (Exception e) {
//				System.out.println(e);
//			}
		
		
		
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
