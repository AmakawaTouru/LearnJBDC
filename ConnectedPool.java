package LearnJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

//连接池
public class ConnectedPool {
	private ArrayList<Connection> cl = new ArrayList<>();
//	创建多大的连接池
	private int size;
	public ConnectedPool(int size) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.size = size;
		String url = "jdbc:mysql://127.0.0.1:3306/my_gamedb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "root";
		for(int i = 0;i<size;i++) {
			try {
				Connection c = DriverManager.getConnection(url,user,password);
				cl.add(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	得到连接
	public synchronized Connection getConnection() {
//		如果为空
		while(cl.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Connection c = cl.remove(0);
		return c;
	}
	
//	返回连接
	public synchronized void returnConnection(Connection c) {
		cl.add(c);
		this.notifyAll();
	}
}
