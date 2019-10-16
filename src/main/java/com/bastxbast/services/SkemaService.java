package com.bastxbast.services;

import java.util.List;
import java.util.Collection;

import com.bastxbast.exceptions.SkemaException;
import com.bastxbast.model.Bet;
import com.bastxbast.model.Skema;
import com.bastxbast.model.User;
import com.bastxbast.model.Subbet;

public interface SkemaService {

	Skema getSkemaID(int userID, String string);

	void addSkema(int nEvents, int nExpected, int initialCash, float share, int userID, String description) throws SkemaException;

	void sendBetToAdmin(Bet wbet, List<Subbet> subbets, List<Integer> uGuess);

	void requireDeleteSkema(int skemaID);

	int getNumeroClosingSkema();

	Collection<Skema> getClosingSkema(int i, int rows, String sord, String sidx);

	int updateManagerIncome(int initialCash, int userID);
	
	float updateManagerIncome(int initialCash, User manager);

}
