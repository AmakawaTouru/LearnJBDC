package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//用statement来进行sql语句吧！
public class TestJDBC03 {
	public static void main(String[] args) {
		try {
//			驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
//			3306是mysql的端口
			String url = "jdbc:mysql://127.0.0.1:3306/my_gamedb?characterEncoding=UTF-8&serverTimezone=UTC";
			String user = "root";
			String password = "root";
			Connection c = DriverManager.getConnection(url,user,password);
//			创建statement，并运行sql语句！
			Statement s = c.createStatement();
			String sql = "insert into my_gamedb.goods values(4,'蓝瓶',null,0)";
			s.execute(sql);
			System.out.println("执行语句成功！");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
