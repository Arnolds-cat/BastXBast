package com.bastxbast.action.admin;

import com.bastxbast.services.AdminBetService;
import com.opensymphony.xwork2.ActionSupport;

public class EvaluateBetAction extends ActionSupport {
	
	private int betIDev;
	private float newShare;
	private float otherEarn;
	private AdminBetService adminBetService;
	private int IDbet;
	
	@Override
	public String execute() throws Exception {
		newShare = adminBetService.getShare(betIDev);
		otherEarn = adminBetService.getOtherEarn(betIDev, newShare);
		IDbet = betIDev;
		return "success";
		
	}

	@Override
	public void validate() {
		if(!adminBetService.isWaitingForExecutionBet(betIDev))
			addFieldError("betIDev", "Wrong bet id");
	}

	public int getIDbet() {
		return IDbet;
	}

	public void setIDbet(int iDbet) {
		IDbet = iDbet;
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

	public float getOtherEarn() {
		return otherEarn;
	}

	public void setOtherEarn(float otherEarn) {
		this.otherEarn = otherEarn;
	}

	public AdminBetService getAdminBetService() {
		return adminBetService;
	}

	public void setAdminBetService(AdminBetService adminBetService) {
		this.adminBetService = adminBetService;
	}
	
	

}
