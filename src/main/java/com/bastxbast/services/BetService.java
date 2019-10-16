package com.bastxbast.services;

import java.util.Collection;

import com.bastxbast.model.Bet;

public interface BetService {

	int getNumeroBets(int skemaID);

	Collection<Bet> getBets(int i, int rows, String sord, String sidx, int skemaID);

}
