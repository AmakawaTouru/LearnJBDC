package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//使用查询语句来进行查询吧！
public class TestJDBC05 {
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
		try(	Connection c = DriverManager.getConnection(url,user,password);
				Statement s = c.createStatement();
				){
				System.out.println("连接成功！");
				String sql ="select * from my_gamedb.chrater";
//				注意这里的执行语句
				ResultSet rs = s.executeQuery(sql);
//				如果仍然有结果
				while(rs.next()) {
//					从1开始，1列，2列······
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String role = rs.getString(3);
					int level = rs.getInt(4);
//					输出
					System.out.printf("%d\t%s\t%s\t%d%n",id,name,role,level);
				}
				System.out.println("查询完毕！");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
