package lpoo.estudiodanca.modelo.dao;

import java.util.List;

public interface iGenericDao<T> {
	
	public void save(T object);
	public T listOne(String pkName, Long pkValue, Class clazz);
	public List listAll(Class object);
	public List listAll(final int first, final int max);
	public void update(T object);
	public void delete(T object);
}
