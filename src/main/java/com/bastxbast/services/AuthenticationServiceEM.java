package com.bastxbast.services;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bastxbast.exceptions.AccountEsistenteException;
import com.bastxbast.exceptions.AccountInesistenteException;
import com.bastxbast.model.MatchResult;
import com.bastxbast.model.User;

public class AuthenticationServiceEM implements AuthenticationService {

	private EntityManagerFactory emf;

	@Override
	public User autentica(String username, String password) throws Exception {

		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("Select u FROM User u");
		List<User> users = query.getResultList();
		User res = null;
		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				res = u;
				break;
			}
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		if (res == null)
			throw new Exception("Wrong email and/or password");
		else 
			return res;
	}

	@Override
	public int getNumeroAccount() {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		int numeroAccount = ((Number) em.createQuery("select count(u.id) from User u").getSingleResult()).intValue();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return numeroAccount;
	}

	@Override
	public Collection<User> getAccounts(int i, int rows, String sord, String sidx) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select u "
                + "FROM User u ORDER BY u." + sidx + " " + sord);
        query.setFirstResult(i).setMaxResults(rows);
        List<User> users = query.getResultList();
        
        em.getTransaction().commit();
		em.close();
		emf.close();
		
        return users;
	}

	@Override
	public List<MatchResult> getMatchResults() {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("Select u FROM MatchResult u");
		List<MatchResult> res = query.getResultList();
		
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return res;
	}

	

}
