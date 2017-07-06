package bbs.serverlet;

import java.io.IOException;
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

import bbs.Model.linkModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class insertDate extends HttpServlet {

	public insertDate() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
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
			try {
				String uri = "jdbc:mysql://localhost:3306/jsp_test?useSSL=true";
				String user="root";
				String pwdd ="312312";
		     	Connection con=(Connection) DriverManager.getConnection(uri,user,pwdd);
		     	//模型
		     	linkModel linkModel =new linkModel();
		     	String timeString =getTime();//获取系统时间		     	
		     	String phoneNum =request.getParameter("phoneNum") ;
		     	String userName =request.getParameter("userName").toString();
		     	String pwd =request.getParameter("password").toString();
		     	String sex =request.getParameter("select").toString();
		     	String birthday =request.getParameter("birthday").toString();
		     	String address =request.getParameter("address").toString();
		     	String show =request.getParameter("show").toString();
		     	
		     	Statement statement =(Statement)con.createStatement();
				ResultSet rs = statement.executeQuery("select * from jsp_test.users where userID ="+phoneNum);
				if (rs.next()) {
					if (rs.getString("userID").toString()!=null) {
						
						response.setContentType("text/html;charset=gb2312");
						response.getWriter().print("<script language='javascript'>alert('用户ID已被占用!');</script>");
						response.setHeader("refresh", "0.1;regist.jsp");
					}
				}else {
					String sql="INSERT INTO users(UserID,UserName,UserPwd,UserSex,UserBirth,UserPro,User_show,User_time) VALUES(?,?,?,?,?,?,?,?)";
			     	PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
			     	
			     	st.setString(1,phoneNum);
					st.setString(2,userName );
					st.setString(3,pwd);
					st.setString(4,sex);
					st.setString(5,birthday);
					st.setString(6,address);
					st.setString(7,show);
					st.setString(8,timeString);
					st.execute();
					
					System.out.println("data 不存在");
					request.setAttribute("linkModel", linkModel);
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
					return;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
	}
	//鑾峰彇绯荤粺鏃堕棿
	public static String getTime() {
		
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dFormat.format(new Date()).toString();
		
	}

}
