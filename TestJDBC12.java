package LearnJBDC;

import java.sql.Connection;

//用数据池的方式来进行来建立连接
//用传统的方式建立连接
public class TestJDBC12 {
	public static void main(String[] args) {
//		建立10个连接
		ConnectedPool cp = new ConnectedPool(10);
		long start = System.currentTimeMillis();
//		弄100个线程
		for(int i = 0; i< 100;i++) {
			Thread t = new Thread() {
				public void run() {
					Connection c = cp.getConnection();
					System.out.println(this.currentThread().getName()+"获得连接!执行操作！");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cp.returnConnection(c);
				}
			};
			t.start();
		}
		long end = System.currentTimeMillis();
		System.out.println("进行了时间："+(end-start));
	}
}
