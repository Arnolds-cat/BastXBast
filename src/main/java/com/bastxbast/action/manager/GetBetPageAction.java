package com.bastxbast.action.manager;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.services.SubbetService;
import com.opensymphony.xwork2.ActionSupport;

public class GetBetPageAction extends ActionSupport implements SessionAware {
	
	private int IDbet;
	private Map<String, Object> map;
	private SubbetService subbetService;
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.map = arg0;		
	}

	public int getIDbet() {
		return IDbet;
	}

	public void setIDbet(int iDbet) {
		IDbet = iDbet;
	}

	public SubbetService getSubbetService() {
		return subbetService;
	}

	public void setSubbetService(SubbetService subbetService) {
		this.subbetService = subbetService;
	}

	@Override
	public String execute() throws Exception {
		return "success";
	}

	@Override
	public void validate() {
		int userID = (int) map.get("userID");
		if(!subbetService.check(IDbet, userID))
			addActionError("Invalid ID");
	}
	
	

}
