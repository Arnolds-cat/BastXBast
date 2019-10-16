package com.bastxbast.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bastxbast.exceptions.SkemaException;
import com.bastxbast.model.Bet;
import com.bastxbast.model.MatchResult;
import com.bastxbast.model.Skema;
import com.bastxbast.model.Subbet;
import com.bastxbast.model.User;
import com.bastxbast.model.factories.SkemaFactory;

public class SkemaServiceEM implements SkemaService {
	
	private EntityManagerFactory emf;

	@Override
	public Skema getSkemaID(int userID, String string) {
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a FROM Skema a WHERE a.user= :userID AND a.description = :desc AND (a.state= :state1 OR a.state = :state2)" );
		query.setParameter("userID", userID);
		query.setParameter("desc", string);
		query.setParameter("state1", Skema.SkemaState.ACTIVE);
		query.setParameter("state2", Skema.SkemaState.WAITING);
        List<Skema> skemas = query.getResultList();
        em.close();
        emf.close();
        if(skemas.isEmpty())
        	return null;
        return skemas.get(0);
        
		
		
	}

	@Override
	public void addSkema(int nEvents, int nExpected, int initialCash, float share, int userID, String description) throws SkemaException {
		Skema skema = SkemaFactory.getSkema(nEvents, nExpected, share, initialCash);
		skema.setDescription(description);
		skema.setPlayed(0);
		skema.setWon(0);
		skema.setState(Skema.SkemaState.ACTIVE);
		skema.setUser(userID);
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(skema);
		
		em.flush();
        
		em.getTransaction().commit();
		
		em.close();
        emf.close();
		
	}

	@Override
	public void sendBetToAdmin(Bet wbet, List<Subbet> subbets, List<Integer> uGuess) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		wbet.setResult(Bet.BetResult.LOST);
		for(int i=0; i<subbets.size(); i++){
			MatchResult res = em.find(MatchResult.class, uGuess.get(i));
			subbets.get(i).setUserGuess(res);
			subbets.get(i).setBet(wbet);
		}
		
		em.persist(wbet);
		Skema skema = em.find(Skema.class, wbet.getSkema().getIDSkema());
		skema.setState(Skema.SkemaState.WAITING);
		em.persist(skema);
		
		for(Subbet s : subbets)
			em.persist(s);
		
		em.flush();
        
		em.getTransaction().commit();
		
		em.close();
        emf.close();
		
	}

	@Override
	public void requireDeleteSkema(int skemaID) {

		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Skema skema = em.find(Skema.class, skemaID);
		
		skema.setState(Skema.SkemaState.WAITING_FOR_CLOSE);
		
		em.persist(skema);
		
		em.getTransaction().commit();		
		em.close();
        emf.close();
		
	}

	@Override
	public int getNumeroClosingSkema() {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		int numero = ((Number) em.createQuery("select count(a.id) from Skema a WHERE a.state = :state").setParameter("state", Skema.SkemaState.WAITING_FOR_CLOSE).getSingleResult()).intValue();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return numero;
	}

	@Override
	public Collection<Skema> getClosingSkema(int i, int rows, String sord, String sidx) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a "
                + "FROM Skema a WHERE a.state = :state ORDER BY a." + sidx + " " + sord);
		query.setParameter("state", Skema.SkemaState.WAITING_FOR_CLOSE);
		query.setFirstResult(i).setMaxResults(rows);
        List<Skema> skema = query.getResultList();
        
        em.getTransaction().commit();
		em.close();
		emf.close();
		
        return skema;
	}

	@Override
	public float updateManagerIncome(int initialCash, User manager) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		User managerToUpdate = em.find(User.class, manager.getUserID());
		float income = initialCash*managerToUpdate.getManagerPercent()/100.0f;
		managerToUpdate.setIncome(managerToUpdate.getIncome()+income);
		
		em.persist(managerToUpdate);
		
		em.getTransaction().commit();
		
		em.close();
        emf.close();
        
        return managerToUpdate.getIncome()+income;
		
	}
	
	@Override
	public int updateManagerIncome(int initialCash, int userID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("SELECT a.manager.userID FROM User a WHERE a.userID = :id").setParameter("id", userID);
		int id = ((Number)query.getSingleResult()).intValue();

		em.getTransaction().commit();
		
		em.close();
        emf.close();
        
        updateManagerIncome(initialCash, new User(id));
        
        return id;
		
	}

}
