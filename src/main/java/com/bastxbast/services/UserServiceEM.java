package com.bastxbast.services;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bastxbast.exceptions.AccountEsistenteException;
import com.bastxbast.exceptions.AccountInesistenteException;
import com.bastxbast.model.User;

public class UserServiceEM implements UserService {
	
	private EntityManagerFactory emf;
	private String sim = "simple";

	@Override
	public int getNumeroUsers() {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		int numeroUsers = ((Number) em.createQuery("select count(a.id) from User a where a.type = :type").setParameter("type", sim).getSingleResult()).intValue();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return numeroUsers;
	}

	@Override
	public Collection<User> getUsers(int i, int rows, String sord, String sidx) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a "
                + "FROM User a WHERE a.type = :type ORDER BY a." + sidx + " " + sord);
		query.setParameter("type", sim);
        query.setFirstResult(i).setMaxResults(rows);
        List<User> users = query.getResultList();
        
        em.getTransaction().commit();
		em.close();
		emf.close();
		
        return users;
	}

	@Override
	public int getNumeroUsers(int managerID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		int numeroUsers = ((Number) em.createQuery("select count(a.id) from User a where a.type = :type AND a.manager.userID = :id").setParameter("type", sim).setParameter("id", managerID).getSingleResult()).intValue();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return numeroUsers;
	}

	@Override
	public Collection<User> getUsers(int i, int rows, String sord, String sidx, int managerID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a "
                + "FROM User a WHERE a.type = :type AND a.manager.userID = :id ORDER BY a." + sidx + " " + sord);
		query.setParameter("type", sim);
		query.setParameter("id", managerID);
        query.setFirstResult(i).setMaxResults(rows);
        List<User> users = query.getResultList();
        
        em.getTransaction().commit();
		em.close();
		emf.close();
		
        return users;
	}
	
	@Override
	public void modificaUser(int id, User user) throws AccountEsistenteException, AccountInesistenteException {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a "
                + "FROM User a WHERE a.username = :name");
		query.setParameter("name", user.getUsername());
		
		List<User> us = query.getResultList();
        if(!us.isEmpty())
        	for(User a : us)
        		if(a.getUserID()!=id)
        			throw new AccountEsistenteException(user.getUsername() + " already exists");
		
		User found = em.find(User.class, id);
		found.setUsername(user.getUsername());
        found.setPassword(user.getPassword());
        found.setUserEmail(user.getUserEmail());
        found.setUserPhone(user.getUserPhone());	
        found.setUserFirstName(user.getUserFirstName());
        found.setUserFamilyName(user.getUserFamilyName());
        
        query = em.createQuery("SELECT a FROM User a WHERE a.username = :name AND a.type = :type").setParameter("name", user.getManager().getUsername()).setParameter("type", "manager");
		List<User> f = query.getResultList();
		if(!f.isEmpty()){
			found.setManager(f.get(0));
		} else
			throw new AccountInesistenteException();
		
        em.persist(found);
        
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}
	
	@Override
	public void modificaUserFromAgency(int id, User user) throws AccountEsistenteException {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a "
                + "FROM User a WHERE a.username = :name");
		query.setParameter("name", user.getUsername());
		
		List<User> us = query.getResultList();
        if(!us.isEmpty())
        	for(User a : us)
        		if(a.getUserID()!=id)
        			throw new AccountEsistenteException(user.getUsername() + " already exists");
		
		User found = em.find(User.class, id);
		found.setUsername(user.getUsername());
        found.setPassword(user.getPassword());
        found.setUserEmail(user.getUserEmail());
        found.setUserPhone(user.getUserPhone());	
        found.setUserFirstName(user.getUserFirstName());
        found.setUserFamilyName(user.getUserFamilyName());
		
        em.persist(found);
        
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}



	@Override
	public void inserisciNuovoUser(User user) throws AccountEsistenteException, AccountInesistenteException {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a.username "
                + "FROM User a");
        List<String> names = query.getResultList();
        if(names.contains(user.getUsername()))
        	throw new AccountEsistenteException();
		
        query = em.createQuery("SELECT a FROM User a WHERE a.username = :name AND a.type = :type").setParameter("name", user.getManager().getUsername()).setParameter("type", "manager");
		List<User> f = query.getResultList();
		if(!f.isEmpty()){
			user.setManager(f.get(0));
		} else
			throw new AccountInesistenteException();
		
		user.setType("simple");
		user.setScore(0);
		user.setIncome(0);
		user.setManagerPercent(0);
		
		em.persist(user);
        em.flush();
        
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}
	
	@Override
	public void inserisciNuovoUserFromAgency(User user, int managerID) throws AccountEsistenteException {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a.username "
                + "FROM User a");
        List<String> names = query.getResultList();
        if(names.contains(user.getUsername()))
        	throw new AccountEsistenteException();
		
        User manager = em.find(User.class, managerID);
		user.setManager(manager);
		
		user.setType("simple");
		user.setScore(0);
		user.setIncome(0);
		user.setManagerPercent(0);
		
		em.persist(user);
        em.flush();
        
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}

	@Override
	public void eliminaUser(int id) {
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

	@Override
	public Collection<User> getUsersFromAgency(int i, int rows, String sord, String sidx, Integer agencyID) {
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a "
                + "FROM User a WHERE a.type = :type ORDER BY a." + sidx + " " + sord);
		
//		Query query = em.createQuery("Select a "
//                + "FROM User a WHERE a.type = :type AND a.registrationAgency.agencyID = :agency ORDER BY a." + sidx + " " + sord);
//		query.setParameter("type", "simple").setParameter("agency", agencyID);
		query.setParameter("type", "simple");
        query.setFirstResult(i).setMaxResults(rows);
        List<User> users = query.getResultList();
        
        em.getTransaction().commit();
		em.close();
		emf.close();
		
        return users;
	}

	@Override
	public boolean validate(int userID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		User user = em.find(User.class, userID);
		if(user==null || !user.getType().equals("simple"))
			return false;
		return true;
	}

}
