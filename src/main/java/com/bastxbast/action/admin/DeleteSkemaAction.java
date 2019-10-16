package com.bastxbast.action.admin;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.model.User;
import com.bastxbast.services.AdminBetService;
import com.bastxbast.services.SkemaService;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteSkemaAction extends ActionSupport implements SessionAware{
	
	private int skemaIDcl, reason;
	private AdminBetService adminBetService;
	private Map<String, Object> map;
	
	@Override
	public String execute() throws Exception {
		User admin = (User)(map.get("user"));
		switch(reason){
		case 0:
			adminBetService.closeWonSkema(skemaIDcl);
			break;
		case 1:
			float updateIncome = adminBetService.closeLeaveSkema(skemaIDcl, admin.getUserID());
			map.put("income", updateIncome);
		}
		return "success";
	}
	
	public int getSkemaIDcl() {
		return skemaIDcl;
	}

	public void setSkemaIDcl(int skemaIDcl) {
		this.skemaIDcl = skemaIDcl;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public AdminBetService getAdminBetService() {
		return adminBetService;
	}

	public void setAdminBetService(AdminBetService adminBetService) {
		this.adminBetService = adminBetService;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.map = arg0;
		
	}
	
	

}
