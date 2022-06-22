package lpoo.estudiodanca.principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lpoo.estudiodanca.modelo.vo.Estudante;

public class Test {

	public static void main(String[] args) {
		
		Estudante es4 = new Estudante(null, "jonas");
		Estudante es5 = new Estudante(null, "joana");
		Estudante es6 = new Estudante(null, "joao");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
			em.persist(es4);
			em.persist(es5);
			em.persist(es6);
		em.getTransaction().commit();
		System.out.println("Prontinho");

	}

}
