package com.bastxbast.services;

import java.util.Collection;

import com.bastxbast.exceptions.AccountEsistenteException;
import com.bastxbast.model.User;

public interface ManagerService {

	int getNumeroManagers();

	Collection<User> getManagers(int i, int rows, String sord, String sidx);

	void modificaManager(int parseInt, User user) throws AccountEsistenteException;

	void inserisciNuovoManager(User user) throws AccountEsistenteException;

	void eliminaManager(int parseInt);

}
