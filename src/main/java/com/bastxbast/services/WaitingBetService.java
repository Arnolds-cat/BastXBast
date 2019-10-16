package com.bastxbast.services;

import java.util.Collection;

import com.bastxbast.model.Bet;

public interface WaitingBetService {

	int getNumeroBets();

	Collection<Bet> getBets(int i, int rows, String sord, String sidx);

}
