package LearnJBDC;

public class TestJDBC11 {
	public static void main(String[] args) {
		HeroDAO dao = new HeroDAO();
//		dao.add(h);
//		dao.delete(5);
//		Hero h = new Hero(5,"哈尔希洛","刺客",10);
//		dao.update(h);
		dao.list();
	}
}
