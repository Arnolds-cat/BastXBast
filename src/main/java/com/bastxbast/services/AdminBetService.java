package com.bastxbast.services;

import java.util.Collection;
import java.util.List;

import com.bastxbast.model.Bet;
import com.bastxbast.model.Subbet;
import com.bastxbast.model.Bet.BetResult;
import com.bastxbast.model.Bet.BetState;

public interface AdminBetService {

	boolean isWaitingForExecutionBet(int betID);

	float getOtherEarn(int betIDev, float newShare);

	float sendBetToAdminAgency(int betIDev, float newShare, int adminID);

	boolean isWaitingForResultBet(int betIDch);

	void closeWonSkema(int skemaIDcl);

	float closeLeaveSkema(int skemaIDcl, int adminID);

	boolean isClosingBet(int skemaIDcl);

	void setResult(int betIDch, BetResult result);

	float executeBet(Bet wbet, int adminID, List<Subbet> subsets, List<Integer> userGuess);

	BetState getBetState(int iDbet);

	float getShare(int betIDev);

}
