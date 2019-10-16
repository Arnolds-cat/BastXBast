package com.bastxbast.action.manager;

import com.bastxbast.services.SkemaService;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteSkemaAction extends ActionSupport {
	
	private int SkemaID;
	private SkemaService skemaService;
	
	
	
	@Override
	public String execute() throws Exception {
		skemaService.requireDeleteSkema(SkemaID);
		return "success";
	}
	public int getSkemaID() {
		return SkemaID;
	}
	public void setSkemaID(int skemaID) {
		SkemaID = skemaID;
	}
	public SkemaService getSkemaService() {
		return skemaService;
	}
	public void setSkemaService(SkemaService skemaService) {
		this.skemaService = skemaService;
	}
	
	

}
