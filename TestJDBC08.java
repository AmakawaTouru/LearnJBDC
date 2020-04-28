package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//查找插入数据后的自增长ID的上一个id，并删除
public class TestJDBC08 {
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
		String update = "insert into my_gamedb.goods values (null,?,null,100)";
		String retrieve = "select idgoods from my_gamedb.goods where idgoods = ?";
		String delete = "delete from my_gamedb.goods where idgoods = ?";
		try( Connection c = DriverManager.getConnection(url,user,password);
				PreparedStatement ps1 = c.prepareStatement(update,Statement.RETURN_GENERATED_KEYS);
				PreparedStatement ps2 = c.prepareStatement(retrieve);
				PreparedStatement ps3 = c.prepareStatement(delete);
				){
			ps1.setString(1, "无尽之刃");
			ps1.execute();
			System.out.println("插入成功！");
//			得到增加的那条元组
			ResultSet rs = ps1.getGeneratedKeys();
			int id = 0;
			while(rs.next()) {
//				得到自增的id
				id = rs.getInt(1);
			}
			ps2.setInt(1, id);
//			如果找不到该值
			while(!ps2.execute()) {
				id--;
				ps2.setInt(1, id);
			}
			System.out.println("找到存在的id为："+id);
			System.out.println("执行删除操作！");
			ps3.setInt(1, id);
			ps3.execute();
			System.out.println("删除成功！");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
