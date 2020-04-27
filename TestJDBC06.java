package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//运用JDBC来进行分页查询吧！
public class TestJDBC06 {
	public static void main(String[] args) {
		list(0,4);
	}
//	分页查询
	public static void list(int start,int count) {
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
			String sql = "select * from my_gamedb.chrater limit "+start+","+count;
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String role = rs.getString(3);
				int level = rs.getInt(4);
//				输出
				System.out.printf("%d\t%s\t%s\t%d%n",id,name,role,level);
			}
			System.out.println("查询结束！");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
