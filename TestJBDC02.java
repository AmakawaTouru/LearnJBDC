package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//尝试连接上数据库吧！
public class TestJBDC02 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("初始化驱动成功！");
//			连接数据库
			String url = "jdbc:mysql://localhost:3306/my_gamedb?characterEncore = utf-8&serverTimezone = UTC";
			String user = "root";
			String password = "root";
//			要catch这个报错。
			Connection c = DriverManager.getConnection(url,user,password);
			System.out.println("连接成功！");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
