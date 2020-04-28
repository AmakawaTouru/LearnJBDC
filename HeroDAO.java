package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//实现英雄类的DAO层吧！
public class HeroDAO implements DAO<Hero>{
//注册驱动只需要一次,因此只需要构造函数中进行
	public HeroDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	是代码易于维护
	public Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/my_gamedb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "root";
		return DriverManager.getConnection(url,user,password);
	}
	
	//	1.插入
	public void add(Hero h) {
		String sql = "insert into my_gamedb.chrater values (?,?,?,?,1)";
		try(Connection c = getConnection();
			  PreparedStatement ps = c.prepareStatement(sql);
				){
			ps.setInt(1, h.getId());
			ps.setString(2, h.getName());
			ps.setString(3, h.getRole());
			ps.setInt(4, h.getLevel());
			System.out.println("执行插入操作！");
			if(!ps.execute()) {
				System.out.println("插入成功！");
			}else {
				System.out.println("插入失败！");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
//	2.删除
	public void delete(int id) {
		String sql = "delete from my_gamedb.chrater where idchrater = ?";
		try(Connection c = getConnection();
			  PreparedStatement ps = c.prepareStatement(sql);
				){
			ps.setInt(1, id);
			System.out.println("执行删除操作！");
			if(!ps.execute()) {
				System.out.println("删除成功！");
			}else {
				System.out.println("删除失败！");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
//	3.修改
	public void update(Hero h) {
		String sql = "update my_gamedb.chrater set name = ?,roletyoe = ?,level = ? where idchrater = ?";
		try(Connection c = getConnection();
			  PreparedStatement ps = c.prepareStatement(sql);
				){
			ps.setInt(4, h.getId());
			ps.setString(1, h.getName());
			ps.setString(2, h.getRole());
			ps.setInt(3, h.getLevel());
			System.out.println("执行更新操作！");
			if(!ps.execute()) {
				System.out.println("更新成功！");
			}else {
				System.out.println("更新失败！");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
//	4.获取
	public void get(int id) {
		String sql = "select * from my_gamedb.chrater where idchrater = ?";
		try(Connection c = getConnection();
			  PreparedStatement ps = c.prepareStatement(sql);
				){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			String name = "";
			String roletype = "";
			int level = 0;
			while(rs.next()) {
				name = rs.getString(2);
				roletype = rs.getString(3);
				level = rs.getInt(4);
			}
			System.out.println("查询结果:");
			System.out.printf("%d\t%s\t%s\t%d\n",id,name,roletype,level);
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
//	5.查询全部
	public List<Hero> list(){
//		调用分页查询
		return list(0,Integer.MAX_VALUE);
	}
//	6.分页查询
	public List<Hero> list(int start,int count){
		String sql = "select *  from my_gamedb.chrater limit ?,?";
		ArrayList<Hero> al = new ArrayList<>();
		try(Connection c = getConnection();
			  PreparedStatement ps = c.prepareStatement(sql);
				){
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				al.add(new Hero(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
			}
			System.out.println("查询记录共"+al.size()+"条!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
}
