package LearnJBDC;

import java.util.List;

//建立一个Data Access Object接口
//将JDBC代码封装起来
public interface DAO<T> {
//	1.插入
	public void add(T row);
//	2.删除
	public void delete(int id);
//	3.修改
	public void update(T row);
//	4.获取
	public void get(int id);
//	5.查询
	public List<T> list();
//	6.分页查询
	public List<T> list(int start,int count);
}
