package com.bastxbast.action.admin;

import com.bastxbast.model.User;
import com.bastxbast.services.AdminBetService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

@Results({
@Result(location="admin/adminHome.jsp"),
@Result(name="input", location="admin/adminHome.jsp"),
@Result(name="login" ,location="home.jsp")
})
public class OtherExecuteBetAction extends ActionSupport implements SessionAware{

	private int betIDev;
	private float newShare;
	private AdminBetService adminBetService;
	private Map<String, Object> map;
	
	@Override
	public String execute() throws Exception {
		User admin = (User)map.get("user");
		float income = adminBetService.sendBetToAdminAgency(betIDev, newShare, admin.getUserID());
		map.put("income", income);
		return "success";
	}
	@Override
	public void validate() {
		if(!adminBetService.isWaitingForExecutionBet(betIDev))
			addFieldError("betIDev", "Wrong bet id");
		
	}
	
	public AdminBetService getAdminBetService() {
		return adminBetService;
	}
	public void setAdminBetService(AdminBetService adminBetService) {
		this.adminBetService = adminBetService;
	}
	public int getBetIDev() {
		return betIDev;
	}
	public void setBetIDev(int betIDev) {
		this.betIDev = betIDev;
	}
	public float getNewShare() {
		return newShare;
	}
	public void setNewShare(float newShare) {
		this.newShare = newShare;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.map = arg0;
		
	}
	
}
