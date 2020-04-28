package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//练习一下事务的提交吧！
public class TestJDBC09 {
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/my_gamedb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "root";
		String delete = "delete from my_gamedb.goods where idgoods = ?";
		try( Connection c = DriverManager.getConnection(url,user,password);
				PreparedStatement ps = c.prepareStatement(delete);
				){
			Scanner in = new Scanner(System.in);
//			进行业务操作
			c.setAutoCommit(false);
			System.out.println("请输入你想要删除的行（输入0则停止）");
			String str = in.next();
			while(!str.equals("0")) {
				int i = Integer.parseInt(str);
				ps.setInt(1, i);
				System.out.println("试图删除id为："+i+"的行");
//				执行
				ps.execute();
				str = in.next();
			}
			System.out.println("是否执行上述操作？Y为确定，N为取消");
			str = in.next();
			while(true) {
				if(str.equals("Y")) {
					System.out.println("执行业务！");
					c.commit();
					break;
				}
				if(str.equals("N")) {
					System.out.println("不提交");
					break;
				}
				System.out.println("是否执行上述操作？Y为确定，N为取消");
				str = in.next();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
