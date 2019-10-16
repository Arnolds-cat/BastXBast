package com.bastxbast.action.manager;

import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.model.Skema;
import com.bastxbast.services.SkemaService;
import com.bastxbast.services.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class GetUserSkemaAction extends ActionSupport implements SessionAware {

	private Map<String, Object> map;
	private int userID;
	private UserService userService;
	
	@Override
	public String execute() throws Exception {
		map.put("userID", userID);
		
		return "success";
	}


	@Override
	public void validate() {
		if(!userService.validate(userID))
			addActionError("Wrong User ID");
	}


	public Map<String, Object> getMap() {
		return map;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		this.map = map;
		
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
