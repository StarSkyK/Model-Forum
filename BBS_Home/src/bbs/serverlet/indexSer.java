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

	/**
	 * Constructor of the object.
	 */
	public indexSer() {
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
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LoadMySQL();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		
		 try {
				String uri = "jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
				String user="root";
				String password ="abc110";
		     	Connection con=(Connection) DriverManager.getConnection(uri,user,password);
		     	//模型
		     	Statement st = (Statement) con.createStatement(); 
		     	String type  =request.getParameter("type").toString();
		     	String sql ="";
		     	if (type.equals("1")) {
					sql ="select * from jsp_test.announces where type ="+1;
				}else if (type.equals("2")) {
					sql ="select * from jsp_test.announces where type ="+2;
				}else if (type.equals("3")) {
					sql ="select * from jsp_test.announces where type ="+3;
				}else if (type.equals("4")) {
					sql ="select * from jsp_test.announces where type ="+4;
				}
		     	ResultSet rs = st.executeQuery(sql);
		     	ArrayList<Object> list = new ArrayList<Object>();
		     	while (rs.next()) {
		     		IndexModel model =new IndexModel();
		     		model.setTitle(rs.getString("title"));
		     		list.add(model);
				}
		     	
		     	request.setAttribute("indexList", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);


	     	} 
	    	catch (Exception e) {
				System.out.println(e);
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
		LoadMySQL();
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(true);
	    loginModel loginModel = (loginModel)session.getAttribute("login");
		
	    try {
				String uri = "jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
				String user="root";
				String password ="abc110";
		     	Connection con=(Connection) DriverManager.getConnection(uri,user,password);
		     	//模型
		     	Statement st = (Statement) con.createStatement(); 
		     	ResultSet rs = st.executeQuery("select * from jsp_test.announces where type = 1");
		     	ArrayList<Object> list = new ArrayList<Object>();
		     	while (rs.next()) {
		     		IndexModel model =new IndexModel();
		     		model.setTitle(rs.getString("title"));
		     		list.add(model);
				}
		     	
		     	request.setAttribute("indexList", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);


	     	} 
	    	catch (Exception e) {
				System.out.println(e);
			}
		
		
		
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
