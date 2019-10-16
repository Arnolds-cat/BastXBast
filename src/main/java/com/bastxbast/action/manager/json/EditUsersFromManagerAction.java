/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bastxbast.action.manager.json;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.exceptions.AccountEsistenteException;
import com.bastxbast.model.User;
import com.bastxbast.services.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author corsojava
 */
public class EditUsersFromManagerAction extends ActionSupport implements SessionAware{
	UserService userService;
	private String id, oper, username, password, userFirstName, userFamilyName, userEmail, userPhone;
	private int score;
	
	private Map<String, Object> sessionMap;

	private String responseText;

	public String execute() {
		
		int managerID = ((User)sessionMap.get("user")).getUserID();

		if (oper.equals("edit")) {
			try {
				userService.modificaUserFromAgency(Integer.parseInt(id), new User(username, password, userFirstName,
						userFamilyName, userEmail, userPhone));
				responseText = "success";
			} catch (NumberFormatException e) {
				responseText = "error";
			} catch (AccountEsistenteException e) {
				responseText = "error";
			} 
		}
		if (oper.equals("add"))
			try {
				userService.inserisciNuovoUserFromAgency(new User(username, password, userFirstName, userFamilyName, userEmail,
						userPhone), managerID);
				responseText = "success";
			} catch (AccountEsistenteException e) {
				responseText = "error";
			} 

		if (oper.equals("del")){
			userService.eliminaUser(Integer.parseInt(id));
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

	

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

	@Override
	public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }
}
