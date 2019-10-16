/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bastxbast.action.admin.json;

import com.bastxbast.exceptions.AccountEsistenteException;
import com.bastxbast.model.User;
import com.bastxbast.services.ManagerService;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author corsojava
 */
public class EditManagersAction extends ActionSupport {
	ManagerService managerService;
	private String id, oper, username, password, userFirstName, userFamilyName, userEmail, userPhone;
	private float income, managerPercent;
	private String responseText;
	

	public String execute() {

		if (oper.equals("edit")) {
			try {
				managerService.modificaManager(Integer.parseInt(id), new User(username, password, userFirstName,
						userFamilyName, userEmail, userPhone, income, managerPercent));
				this.responseText = "success";
			} catch (NumberFormatException e) {
				this.responseText = "error";
			} catch (AccountEsistenteException e) {
				this.responseText = "error";
			} 
		}
		if (oper.equals("add"))
			try {
				managerService.inserisciNuovoManager(new User(username, password, userFirstName, userFamilyName, userEmail,
						userPhone, 0, managerPercent));
				this.responseText = "success";
			} catch (AccountEsistenteException e) {
				this.responseText = "error";
			} 

		if (oper.equals("del")){
			managerService.eliminaManager(Integer.parseInt(id));
			this.responseText = "success";
		}
		

		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserFamilyName() {
		return userFamilyName;
	}

	public void setUserFamilyName(String userFamilyName) {
		this.userFamilyName = userFamilyName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public float getManagerPercent() {
		return managerPercent;
	}

	public void setManagerPercent(float managerPercent) {
		this.managerPercent = managerPercent;
	}
	
	
	
}
