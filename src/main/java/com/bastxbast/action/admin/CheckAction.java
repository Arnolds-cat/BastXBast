package com.bastxbast.action.admin;

import com.bastxbast.model.Bet;
import com.bastxbast.services.AdminBetService;
import com.opensymphony.xwork2.ActionSupport;

public class CheckAction extends ActionSupport {
	
	private int betIDch;
	private int result;
	private AdminBetService adminBetService;
	
	
	@Override
	public String execute() throws Exception {
		Bet.BetResult mres = Bet.BetResult.get(result);
		adminBetService.setResult(betIDch, mres);
		return "success";
	}
	@Override
	public void validate() {
		if(!adminBetService.isWaitingForResultBet(betIDch))
			addFieldError("betIDch", "Wrong bet ID");
	}
	public int getBetIDch() {
		return betIDch;
	}
	public void setBetIDch(int betIDch) {
		this.betIDch = betIDch;
	}
	public AdminBetService getAdminBetService() {
		return adminBetService;
	}
	public void setAdminBetService(AdminBetService adminBetService) {
		this.adminBetService = adminBetService;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	

}
