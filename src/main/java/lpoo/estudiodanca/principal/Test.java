package lpoo.estudiodanca.principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lpoo.estudiodanca.modelo.vo.Estudante;

public class Test {

	public static void main(String[] args) {
		
		Estudante es4 = new Estudante(0, "jonas", null);
		Estudante es5 = new Estudante(0, "joana", null);
		Estudante es6 = new Estudante(0, "joao", null);
		Estudante es = new Estudante(0, "deysi", null);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
			em.persist(es4);
			em.persist(es5);
			em.persist(es6);
			em.persist(es);
			em.getTransaction().commit();
		System.out.println("Prontinho");

	}
//	public List<Estudante> findAll(){
//		List<Estudante> list = new ArrayList<>();
//		list.add(new Estudante(1, "Deysi"));
//		list.add(new Estudante(2, "Debora"));
//		list.add(new Estudante(3, "Luana"));
//		return list;
//}
}
