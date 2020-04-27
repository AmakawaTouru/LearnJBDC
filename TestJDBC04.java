package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//利用学到的JDBC来插入10条数据吧！
public class TestJDBC04 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/my_gamedb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "root";
		//		采用autoClosable自动关闭
		try(	Connection c = DriverManager.getConnection(url,user,password);
				Statement s = c.createStatement();
				){
				System.out.println("成功连接！");
				for(int i = 5;i<15;i++) {
//					要非常注意字符串的单引号有没有加错位置或者漏加
					String sql = "insert into my_gamedb.goods values ("+i+",'物品"+i+"',null,10)";
					s.execute(sql);
					System.out.println("执行成功");
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
