package com.bastxbast.services;

import java.util.Collection;

import com.bastxbast.model.Subbet;

public interface SubbetService {

	boolean check(int iDbet, int userID);

	int getNumeroSubbets(int iDbet);

	Collection<Subbet> getSubbets(int i, int rows, String sord, String sidx, int iDbet);

	void modificaSubbet(int parseInt, float share);

}
