package com.bastxbast.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.bastxbast.model.Bet;
import com.bastxbast.model.MatchResult;
import com.bastxbast.model.Bet.BetResult;
import com.bastxbast.model.Bet.BetState;
import com.bastxbast.model.Skema;
import com.bastxbast.model.Subbet;
import com.bastxbast.model.User;

public class AdminBetServiceEM implements AdminBetService {
	
	public static final float CHANGE = 1.26f;
	public static final int ADMIN_AGENCY_ID = 1;
	
	private EntityManagerFactory emf;

	@Override
	public boolean isWaitingForExecutionBet(int betID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet a = em.find(Bet.class, betID);
		
		boolean res = false;
		if(a != null && a.getState().equals(Bet.BetState.WAITING_EXECUTION))
			res = true;
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return res;
	}

	@Override
	public float getOtherEarn(int betIDev, float newShare) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet bet = em.find(Bet.class, betIDev);
		
		float res = bet.getInitialAmount()/CHANGE - bet.getInitialAmount()*bet.getSkema().getShare()/newShare/CHANGE;
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return res*CHANGE;
	}

	@Override
	public float sendBetToAdminAgency(int betIDev, float newShare, int adminID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet bet = em.find(Bet.class, betIDev);
		User admin = em.find(User.class, adminID);
		
		float earn = bet.getInitialAmount()/CHANGE - bet.getInitialAmount()*bet.getSkema().getShare()/newShare/CHANGE;
		float income = admin.getIncome() + earn;
		bet.setPlayedAmount(bet.getInitialAmount()*bet.getSkema().getShare()/newShare);
		bet.setRealQuote(newShare);
		bet.setState(Bet.BetState.WAITING_RESULT);
		admin.setIncome(income);
		
		em.persist(bet);
		em.persist(admin);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return income;
		
	}

	@Override
	public boolean isWaitingForResultBet(int betID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet a = em.find(Bet.class, betID);
		
		boolean res = false;
				
		if(a!=null && a.getState().equals(Bet.BetState.WAITING_RESULT))
			res = true;
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return res;
	}

	@Override
	public void setResult(int betIDch, BetResult result) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet bet = em.find(Bet.class, betIDch);
		
		bet.setResult(result);
		bet.updateResult(result);
		switch(result){
		case WIN:
			bet.setState(Bet.BetState.WON);
			break;
		case LOST:
			bet.setState(Bet.BetState.LOST);
			break;
		}
		
		Skema skema = em.find(Skema.class, bet.getSkema().getIDSkema());
		skema.setCurrentCash(bet.getSkemaCurrentCashAfterResult());
		skema.setPlayed(skema.getPlayed()+1);
		if(bet.getState().equals(BetState.WON))
			skema.setWon(skema.getWon()+1);
		skema.setState(Skema.SkemaState.ACTIVE);
		
		
		em.persist(bet);
		em.persist(skema);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		
	}

	@Override
	public void closeWonSkema(int skemaIDcl) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Skema skema = em.find(Skema.class, skemaIDcl);
		
		skema.setState(Skema.SkemaState.CLOSED_WON);
		
		em.persist(skema);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}

	@Override
	public float closeLeaveSkema(int skemaIDcl, int adminID) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Skema skema = em.find(Skema.class, skemaIDcl);	
		skema.setState(Skema.SkemaState.CLOSED_LOST);
		
		User admin = em.find(User.class, adminID);
		float income = admin.getIncome() + skema.getCurrentCash();
		admin.setIncome(income);
		
		em.persist(skema);
		em.persist(admin);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return income;
		
	}

	@Override
	public boolean isClosingBet(int skemaIDcl) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Skema a = em.find(Skema.class, skemaIDcl);
		
		boolean res = false;
		
		if(a!= null && a.getState().equals(Skema.SkemaState.WAITING_FOR_CLOSE))
			res = true;
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return res;
	}

	@Override
	public float executeBet(Bet bet, int adminID, List<Subbet> subbets, List<Integer> uGuess) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		User admin = em.find(User.class, adminID);
		
		float earn = bet.getInitialAmount()/CHANGE - bet.getPlayedAmount()/CHANGE;
		float income = admin.getIncome() + earn;
		
		admin.setIncome(income);
		em.persist(admin);
		
		bet.setResult(Bet.BetResult.LOST);
		
		for(int i=0; i<subbets.size(); i++){
			MatchResult res = em.find(MatchResult.class, uGuess.get(i));
			subbets.get(i).setUserGuess(res);
			subbets.get(i).setBet(bet);
		}
		em.persist(bet);
		
		Skema skema = em.find(Skema.class, bet.getSkema().getIDSkema());
		skema.setState(Skema.SkemaState.WAITING);
		em.persist(skema);

		for(Subbet s : subbets)
			em.persist(s);
		
		em.flush();
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return income;
	}

	@Override
	public BetState getBetState(int iDbet) {
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet bet = em.find(Bet.class, iDbet);
		
		Bet.BetState state = bet.getState();
		
		em.close();
		emf.close();
		
		return state;
		
	}

	@Override
	public float getShare(int betIDev) {
		
		emf = Persistence.createEntityManagerFactory("com.bastxbast.jpa");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Bet bet = em.find(Bet.class, betIDev);
		Query query = em.createQuery("Select u FROM Subbet u WHERE u.bet = :bet");
		query.setParameter("bet", bet);
		List<Subbet> subbets = query.getResultList();
		
		float share = 1;
		for(Subbet s : subbets)
			share *= s.getShare();
		
		em.close();
		emf.close();
		
		return share;
		
	}

}
