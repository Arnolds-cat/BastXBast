package com.bastxbast.services;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bastxbast.model.Bet;
import com.bastxbast.model.Skema;
import com.bastxbast.model.User;

public class BetServiceEM implements BetService {
	
	private EntityManagerFactory emf;

	@Override
	public int getNumeroBets(int skemaID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Skema skema = em.find(Skema.class, skemaID);
		
		int numero = skema.getPlayed();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return numero;
	}

	@Override
	public Collection<Bet> getBets(int i, int rows, String sord, String sidx, int skemaID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Skema skema = em.find(Skema.class, skemaID);
		
		Query query = em.createQuery("Select a "
                + "FROM Bet a WHERE a.skema = :skema AND (a.state = :state1 OR a.state = :state2) ORDER BY a." + sidx + " " + sord);
		query.setParameter("skema", skema);
		query.setParameter("state1", Bet.BetState.LOST);
		query.setParameter("state2", Bet.BetState.WON);
        query.setFirstResult(i).setMaxResults(rows);
        List<Bet> bets = query.getResultList();
        
        em.getTransaction().commit();
		em.close();
		emf.close();
		
		return bets;
	}

}
