package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//来尝试各种ORM操作吧！
public class TestJDBC10 {
	
//	增加一个英雄对象进入数据库中
	public static void add(Hero h) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/my_gamedb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "root";
		String sql = "insert into my_gamedb.chrater values (?,?,?,?,1)";
		try(	Connection c = DriverManager.getConnection(url,user,password);
				PreparedStatement ps = c.prepareStatement(sql);
				){
//			获取英雄类数据
			int id = h.getId();
			String name = h.getName();
			String roletype = h.getRole();
			int level = h.getLevel();
//			进行数据库插入操作
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, roletype);
			ps.setInt(4,level);
			System.out.println("执行数据库插入操作！");
			if(!ps.execute()) {
				System.out.println("插入成功！");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
//	删除一条数据库里的英雄数据
	public static void delete(Hero h) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/my_gamedb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "root";
		String sql = "delete from my_gamedb.chrater where idchrater = ?";
		try( Connection c = DriverManager.getConnection(url,user,password);
				PreparedStatement ps = c.prepareStatement(sql);
				){
			int id = h.getId();
			ps.setInt(1, id);
			System.out.println("执行删除操作");
			boolean flag = ps.execute();
			if(flag) {
				System.out.println("删除失败！");
			}else {
				System.out.println("删除成功！");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
//	将所有英雄数据全部查询一遍
	public static List<Hero> list(){
		ArrayList<Hero> heroList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/my_gamedb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "root";
//		查询语句
		String sql = "select * from my_gamedb.chrater";
		try(	Connection c = DriverManager.getConnection(url,user,password);
				PreparedStatement ps = c.prepareStatement(sql);
				){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String roletype = rs.getString(3);
				int level = rs.getInt(4);
				Hero tmpHero = new Hero(id,name,roletype,level);
				heroList.add(tmpHero);
			}
			System.out.printf("查询成功！共有%d条英雄记录！\n",heroList.size());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return heroList;
	}
	
	public static void main(String[] args) {
		Hero h1 = new Hero(5,"哈尔希洛","刺客",1);
//		add(h1);
//		delete(h1);
		list();
	}
}
