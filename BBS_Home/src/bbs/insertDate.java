package bbs;

import java.io.IOException;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				String password ="abc110";
		     	Connection con=(Connection) DriverManager.getConnection(uri,user,password);
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
		     	
		     	linkModel.setPhoneNum(phoneNum);
		     	linkModel.setUserName(userName);
		     	linkModel.setPassword(password);
		     	linkModel.setSex(sex);
		     	linkModel.setBirthday(birthday);
		     	linkModel.setAddress(address);
		     	linkModel.setShow(show);
		     	
		     	String sql="INSERT INTO users(UserID,UserName,UserPwd,UserSex,UserBirth,UserPro,User_show,User_time) VALUES(?,?,?,?,?,?,?,?)";
		     	PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
		     	
		     	st.setString(1,linkModel.getPhoneNum());
				st.setString(2,linkModel.getUserName() );
				st.setString(3,linkModel.getPassword());
				st.setString(4,linkModel.getSex());
				st.setString(5,linkModel.getBirthday());
				st.setString(6,linkModel.getAddress());
				st.setString(7,linkModel.getShow());
				st.setString(8,timeString);
				st.execute();
				request.setAttribute("linkModel", linkModel);
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
				
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
