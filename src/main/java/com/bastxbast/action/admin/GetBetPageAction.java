package com.bastxbast.action.admin;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.model.Bet;
import com.bastxbast.services.AdminBetService;
import com.bastxbast.services.SubbetService;
import com.opensymphony.xwork2.ActionSupport;

public class GetBetPageAction extends ActionSupport implements SessionAware {
	
	private int IDbet;
	private float newShare;
	private Map<String, Object> map;
	private AdminBetService adminBetService;
	private boolean waitForResult, waitForExecution;
	
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

	
	public AdminBetService getAdminBetService() {
		return adminBetService;
	}

	public void setAdminBetService(AdminBetService adminBetService) {
		this.adminBetService = adminBetService;
	}

	@Override
	public String execute() throws Exception {
		Bet.BetState state = adminBetService.getBetState(IDbet);
		if(state.equals(Bet.BetState.WAITING_EXECUTION)){
			this.waitForExecution=true;
			this.waitForResult=false;
		} else if(state.equals(Bet.BetState.WAITING_RESULT)){
			this.waitForExecution=false;
			this.waitForResult=true;
		} else {
			this.waitForExecution=false;
			this.waitForResult=false;
		}
		newShare = adminBetService.getShare(IDbet);
		return "success";
	}

	public boolean isWaitForResult() {
		return waitForResult;
	}

	public void setWaitForResult(boolean waitForResult) {
		this.waitForResult = waitForResult;
	}

	public boolean isWaitForExecution() {
		return waitForExecution;
	}

	public void setWaitForExecution(boolean waitForExecution) {
		this.waitForExecution = waitForExecution;
	}

	public float getNewShare() {
		return newShare;
	}

	public void setNewShare(float newShare) {
		this.newShare = newShare;
	}

	
}
