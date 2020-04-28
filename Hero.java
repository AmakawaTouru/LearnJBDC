package LearnJBDC;

//用于执行ORM的类。
public class Hero {
	private int id;
	private String name;
	private String roletype;
	private int level;
	
//	构造函数
	public Hero(int id,String name,String roletype,int level) {
		this.id = id;
		this.name = name;
		this.roletype = roletype;
		this.level = level;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRole() {
		return roletype;
	}
	
	public int getLevel() {
		return level;
	}
}
