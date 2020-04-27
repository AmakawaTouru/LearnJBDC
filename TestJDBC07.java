package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//来比较一下，preparedStatement和Statement的区别吧！
//Statement执行用时：17166ms
//preparedStatement执行用时：14891ms
public class TestJDBC07 {
	public static void main(String[] args) {
//		useStatement();
		usePrepared();
	}
	
	public static void useStatement() {
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
//			String sql = "insert into my_gamedb.goods values (null,'可删除',null,null)";
			String sql = "delete from my_gamedb.goods where name = '可删除'";
			long startTime = System.currentTimeMillis();
//			插入一万条数据
			for(int i = 0;i<10000;i++) {
				s.execute(sql);
			}
			long endTime = System.currentTimeMillis();
			long result = endTime - startTime;
			System.out.println("程序执行了"+result+"ms");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void usePrepared() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/my_gamedb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "root";
		String sql = "insert into my_gamedb.goods values (null,?,null,null)";
//		String sql = "delete from my_gamedb.goods where name = ?";
		try(	Connection c = DriverManager.getConnection(url,user,password);
				PreparedStatement ps = c.prepareStatement(sql);
				){
//			注意这里是以1为基
//			且不需要加单引号
			ps.setString(1, "可删除");
			long startTime = System.currentTimeMillis();
//			插入一万条数据
			for(int i = 0;i<10000;i++) {
				ps.execute();
			}
			long endTime = System.currentTimeMillis();
			long result = endTime - startTime;
			System.out.println("程序执行了"+result+"ms");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
