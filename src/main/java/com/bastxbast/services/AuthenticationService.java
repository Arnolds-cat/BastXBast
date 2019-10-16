package com.bastxbast.services;

import java.util.Collection;
import java.util.List;

import com.bastxbast.exceptions.AccountEsistenteException;
import com.bastxbast.exceptions.AccountInesistenteException;
import com.bastxbast.model.MatchResult;
import com.bastxbast.model.User;

public interface AuthenticationService {

	User autentica(String username, String password) throws Exception;

	int getNumeroAccount();

	Collection<User> getAccounts(int i, int rows, String sord, String sidx);

	List<MatchResult> getMatchResults();




}
