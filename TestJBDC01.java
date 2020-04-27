package LearnJBDC;

//尝试初始化驱动吧！
public class TestJBDC01 {
	public static void main(String[] args) {
		try {
//			反射
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("数据库驱动加载成功！");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
}
