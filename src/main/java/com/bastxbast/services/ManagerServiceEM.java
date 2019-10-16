package com.bastxbast.services;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bastxbast.exceptions.AccountEsistenteException;
import com.bastxbast.model.User;

public class ManagerServiceEM implements ManagerService {
	
	private EntityManagerFactory emf;
	String man = "manager";

	@Override
	public int getNumeroManagers() {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		int numeroManagers = ((Number) em.createQuery("select count(a.id) from User a where a.type = :type").setParameter("type", man).getSingleResult()).intValue();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return numeroManagers;
	}

	@Override
	public Collection<User> getManagers(int i, int rows, String sord, String sidx) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a "
                + "FROM User a WHERE a.type = :type ORDER BY a." + sidx + " " + sord);
		query.setParameter("type", man);
        query.setFirstResult(i).setMaxResults(rows);
        List<User> managers = query.getResultList();
        
        em.getTransaction().commit();
		em.close();
		emf.close();
		
        return managers;
	}

	@Override
	public void modificaManager(int id, User manager) throws AccountEsistenteException {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		User found = em.find(User.class, id);
		
		Query query = em.createQuery("Select a "
                + "FROM User a WHERE a.username = :name");
		query.setParameter("name", manager.getUsername());
		
		List<User> us = query.getResultList();
        if(!us.isEmpty())
        	for(User a : us)
        		if(a.getUserID()!=id)
        			throw new AccountEsistenteException(manager.getUsername() + " already exists");
		found.setUsername(manager.getUsername());
        found.setPassword(manager.getPassword());
        found.setUserEmail(manager.getUserEmail());
        found.setUserPhone(manager.getUserPhone());	
        found.setUserFirstName(manager.getUserFirstName());
        found.setUserFamilyName(manager.getUserFamilyName());
        found.setIncome(manager.getIncome());
        found.setManagerPercent(manager.getManagerPercent());
        
        em.persist(found);
        
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}



	@Override
	public void inserisciNuovoManager(User manager) throws AccountEsistenteException {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a.username "
                + "FROM User a");
        List<String> names = query.getResultList();
        if(names.contains(manager.getUsername()))
        	throw new AccountEsistenteException();
		
		manager.setIncome(0);	
		manager.setType("manager");
		manager.setScore(0);
		manager.setManager(null);
		
		em.persist(manager);
        em.flush();
        
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}

	@Override
	public void eliminaManager(int id) {
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		User a = em.find(User.class, id);
        if(a != null)
        	em.remove(a);
        
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}

}
