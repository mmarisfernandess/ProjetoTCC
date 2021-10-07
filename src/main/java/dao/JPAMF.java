package main.java.dao;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAMF {

	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Projeto.TCCPU");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
