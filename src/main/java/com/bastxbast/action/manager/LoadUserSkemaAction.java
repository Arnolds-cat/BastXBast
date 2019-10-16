package com.bastxbast.action.manager;

import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.model.Skema;
import com.bastxbast.model.factories.SkemaFactory;
import com.bastxbast.services.SkemaService;
import com.bastxbast.services.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class LoadUserSkemaAction extends ActionSupport implements SessionAware {

	private Map<String, Object> map;	
	private SkemaService skemaService;
	private float nextBet1, nextBet2, nextBet3;
	private boolean wait1, wait2, wait3;
	
	@Override
	public String execute() throws Exception {
		int userID = (int) map.get("userID");	
		Skema sk1 = skemaService.getSkemaID(userID, "Skema 1");	
		if(sk1==null)
			map.put("skema1ID", 0);
		else {
			map.put("skema1ID", sk1.getIDSkema());
			nextBet1 = SkemaFactory.getNextBetAmount(sk1.getNEvents(), sk1.getNExpected(), sk1.getShare(), sk1.getCurrentCash(), sk1.getPlayed(), sk1.getWon());
			if(sk1.getState().equals(Skema.SkemaState.WAITING))
				wait1 = true;
			else
				wait1 = false;
		}
			
		map.put("skema1", sk1);
		
		Skema sk2 = skemaService.getSkemaID(userID, "Skema 2");	
		if(sk2==null)
			map.put("skema2ID", 0);
		else {
			map.put("skema2ID", sk2.getIDSkema());
			nextBet2 = SkemaFactory.getNextBetAmount(sk2.getNEvents(), sk2.getNExpected(), sk2.getShare(), sk2.getCurrentCash(), sk2.getPlayed(), sk2.getWon());
			if(sk2.getState().equals(Skema.SkemaState.WAITING))
				wait2 = true;
			else
				wait2 = false;
		}
			
		map.put("skema2", sk2);
		
		Skema sk3 = skemaService.getSkemaID(userID, "Skema 3");	
		if(sk3==null)
			map.put("skema3ID", 0);
		else {
			map.put("skema3ID", sk3.getIDSkema());
			nextBet3 = SkemaFactory.getNextBetAmount(sk3.getNEvents(), sk3.getNExpected(), sk3.getShare(), sk3.getCurrentCash(), sk3.getPlayed(), sk3.getWon());
			if(sk3.getState().equals(Skema.SkemaState.WAITING))
				wait3 = true;
			else
				wait3 = false;
		}
			
		map.put("skema3", sk3);
		
		if(sk1==null ||sk2==null || sk3==null)
			map.put("newskema", true);
		else
			map.put("newskema", false);
		String profile = (String)map.get("profiloUser");
		String r = "";
		if(profile.equals("ADMIN"))
			r = "success-admin";
		else
			r = "success-manager";
		if(sk1 != null)
			return r+"-1";
		else if(sk2 != null)
			return r+"-2";
		else if(sk3 != null)
			return r+"-3";
		else
			return r+"-new";
	}


	public Map<String, Object> getMap() {
		return map;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	public boolean isWait1() {
		return wait1;
	}


	public void setWait1(boolean wait1) {
		this.wait1 = wait1;
	}


	public boolean isWait2() {
		return wait2;
	}


	public void setWait2(boolean wait2) {
		this.wait2 = wait2;
	}


	public boolean isWait3() {
		return wait3;
	}


	public void setWait3(boolean wait3) {
		this.wait3 = wait3;
	}


	public SkemaService getSkemaService() {
		return skemaService;
	}


	public void setSkemaService(SkemaService skemaService) {
		this.skemaService = skemaService;
	}


	public float getNextBet1() {
		return nextBet1;
	}


	public void setNextBet1(float nextBet1) {
		this.nextBet1 = nextBet1;
	}


	public float getNextBet2() {
		return nextBet2;
	}


	public void setNextBet2(float nextBet2) {
		this.nextBet2 = nextBet2;
	}


	public float getNextBet3() {
		return nextBet3;
	}


	public void setNextBet3(float nextBet3) {
		this.nextBet3 = nextBet3;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		this.map = map;
		
	}


	
}
