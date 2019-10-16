package com.bastxbast.services;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bastxbast.model.Bet;

public class WaitingBetServiceEM implements WaitingBetService {
	
	private EntityManagerFactory emf;

	@Override
	public int getNumeroBets() {
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		int numero = ((Number) em.createQuery("select count(a.id) from Bet a WHERE a.state = :state").setParameter("state", Bet.BetState.WAITING_EXECUTION).getSingleResult()).intValue();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return numero;
	}

	@Override
	public Collection<Bet> getBets(int i, int rows, String sord, String sidx) {
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("Select a "
                + "FROM Bet a WHERE a.state = :state ORDER BY a." + sidx + " " + sord);
		query.setFirstResult(i).setMaxResults(rows);
		query.setParameter("state", Bet.BetState.WAITING_EXECUTION);
        List<Bet> bets = query.getResultList();
        
        em.getTransaction().commit();
		em.close();
		emf.close();
		
        return bets;
	}

}
