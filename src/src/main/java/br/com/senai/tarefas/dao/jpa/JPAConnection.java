package br.com.senai.tarefas.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnection {

	private static EntityManagerFactory emf;
	
	private EntityManager em;
	
	private EntityManagerFactory getEntityManagerFactory(){
		if(emf == null || !emf.isOpen()){
			emf = Persistence.createEntityManagerFactory("TarefasPU");
		}
		return emf;
	}
	
	protected EntityManager getEntityManager(){
		if(em == null || !em.isOpen()){
			em = getEntityManagerFactory().createEntityManager();
		}
		return em;
	}
	
	protected void closeEntityManager(){
		getEntityManager().close();
		em = null;
	}
	
	protected void beginTransaction(){
		getEntityManager().getTransaction().begin();
	}
	
	protected void commit(){
		getEntityManager().getTransaction().commit();
	}
	
	protected void rollback(){
		getEntityManager().getTransaction().rollback();
	}
}
