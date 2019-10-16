package com.bastxbast.services;

import java.util.Collection;

import com.bastxbast.exceptions.AccountEsistenteException;
import com.bastxbast.exceptions.AccountInesistenteException;
import com.bastxbast.model.User;

public interface UserService {

	int getNumeroUsers();

	Collection<User> getUsers(int i, int rows, String sord, String sidx);
	
	int getNumeroUsers(int managerID);

	Collection<User> getUsers(int i, int rows, String sord, String sidx, int managerID);

	void modificaUser(int parseInt, User user) throws AccountEsistenteException, AccountInesistenteException;

	void inserisciNuovoUser(User user) throws AccountEsistenteException, AccountInesistenteException;

	void eliminaUser(int parseInt);

	Collection<User> getUsersFromAgency(int i, int rows, String sord, String sidx, Integer agencyID);

	void inserisciNuovoUserFromAgency(User user, int managerID) throws AccountEsistenteException;

	void modificaUserFromAgency(int id, User user) throws AccountEsistenteException;

	boolean validate(int userID);

}
