package com.bastxbast.services;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bastxbast.model.Bet;
import com.bastxbast.model.Skema;
import com.bastxbast.model.Subbet;
import com.bastxbast.model.User;

public class SubbetServiceEM implements SubbetService {
	
	private EntityManagerFactory emf;

	@Override
	public boolean check(int iDbet, int userID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet bet = em.find(Bet.class, iDbet);
		
		if(bet==null)
			return false;
		
		Skema skema = bet.getSkema();
		if(skema==null)
			return false;
		if(skema.getUser()!=userID)
			return false;
		return true;
		
	}

	@Override
	public int getNumeroSubbets(int iDbet) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet bet = em.find(Bet.class, iDbet);
		
		Query query = em.createQuery("Select count(a.subbetID) "
                + "FROM Subbet a WHERE a.bet = :bet");
		query.setParameter("bet", bet);
		int numero = ((Number) query.getSingleResult()).intValue();
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return numero;
	}

	@Override
	public Collection<Subbet> getSubbets(int i, int rows, String sord, String sidx, int iDbet) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet bet = em.find(Bet.class, iDbet);
		
		Query query = em.createQuery("Select a "
                + "FROM Subbet a WHERE a.bet = :bet ORDER BY a." + sidx + " " + sord);
		query.setParameter("bet", bet);
        query.setFirstResult(i).setMaxResults(rows);
        List<Subbet> subbets = query.getResultList();
        
        em.getTransaction().commit();
		em.close();
		emf.close();
		
        return subbets;
	}

	@Override
	public void modificaSubbet(int id, float share) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Subbet subbet = em.find(Subbet.class, id);
		subbet.setShare(share);
		
		em.persist(subbet);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
