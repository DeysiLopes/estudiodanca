package lpoo.estudiodanca.modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericDao<T> implements iGenericDao<T> {

	EntityManager manager = ConexaoHibernate.getInstance();
	
	public EntityManager getManager() {
		return manager;
	}
	
	public void commit() {
		manager.getTransaction().commit();
	}
	
	
	public void save(T object) {
		manager.getTransaction().begin();
		manager.persist(object);
		manager.flush();
		manager.getTransaction().commit();
		System.out.println(object.getClass().getSimpleName() + "Salvo com sucesso");
	}

	@SuppressWarnings("unchecked")
	public T listOne(String pkName, Long pkValue, Class clazz) {
		String jpql = " SELECT t FROM " + clazz.getTypeName() + " t WHERE t. " + pkName + " = " + pkValue;
		Query query = manager.createQuery(jpql);
		Object obj = query.getSingleResult();
		return (T) obj;
	}

	public List<T> listAll(Class clazz) {
		String jpql = " SELECT t FROM " + clazz.getTypeName() + " t";
        Query query = manager.createQuery(jpql);
        List<T> objects = query.getResultList();
        return objects;
	}

	public List listAll(int first, int max) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void update(T object) {
		 manager.getTransaction().begin();
	        manager.persist(object);
	        manager.flush();
	        manager.getTransaction().commit();
	        System.out.println(object.getClass().getSimpleName() + " Atualizado com sucesso!");

		
	}

	public void delete(T object) {
		manager.getTransaction().begin();
        manager.remove(object);
        manager.flush();
        manager.getTransaction().commit();
        System.out.println(object.getClass().getSimpleName() + " Excluído com sucesso!");
		
	}
	public T search(Class<T> persistedClass, Long id) {
        return manager.find(persistedClass, id);
    }
	public List<T> search(String pkName, String pkValue, Class clazz) {
        String jpql = " SELECT t FROM " + clazz.getTypeName() + " t where t." + pkName + " LIKE '" + pkValue + "%'";
        Query query = manager.createQuery(jpql);
        List<T> objs = query.getResultList();
        return objs;
    }
	
}
