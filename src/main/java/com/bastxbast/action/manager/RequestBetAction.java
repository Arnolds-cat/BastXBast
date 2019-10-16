package com.bastxbast.action.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.model.Bet;
import com.bastxbast.model.Skema;
import com.bastxbast.model.Subbet;
import com.bastxbast.services.SkemaService;
import com.opensymphony.xwork2.ActionSupport;


public class RequestBetAction extends ActionSupport implements SessionAware {

	private String description;
	
	private float initialAmount;
	
	private int userGuess1, userGuess2, userGuess3, userGuess4, userGuess5, userGuess6, userGuess7, userGuess8, userGuess9, userGuess10;
	
	private SkemaService skemaService;
	
	private Map<String, Object> map;
	
	private String description1, description2, description3, description4, description5, description6, description7, description8, description9, description10;
	
	private int count;
	
	

	@Override
	public String execute() throws Exception {
		Skema skema = null;
		if(description.equals("Skema 1"))
			skema = (Skema) map.get("skema1");
		if(description.equals("Skema 2"))
			skema = (Skema) map.get("skema2");
		if(description.equals("Skema 3"))
			skema = (Skema) map.get("skema3");
		
		List<Subbet> submitted = new ArrayList<Subbet>();
		List<Integer> userGuessSub = new ArrayList<Integer>();
		
		switch(count){
		case 10: 
			submitted.add(new Subbet(description10));
			userGuessSub.add(userGuess10);
		case 9: 
			submitted.add(new Subbet(description9));
			userGuessSub.add(userGuess9);
		case 8: 
			submitted.add(new Subbet(description8));
			userGuessSub.add(userGuess8);
		case 7: 
			submitted.add(new Subbet(description7));
			userGuessSub.add(userGuess7);
		case 6: 
			submitted.add(new Subbet(description6));
			userGuessSub.add(userGuess6);
		case 5: 
			submitted.add(new Subbet(description5));
			userGuessSub.add(userGuess5);
		case 4: 
			submitted.add(new Subbet(description4));
			userGuessSub.add(userGuess4);
		case 3: 
			submitted.add(new Subbet(description3));
			userGuessSub.add(userGuess3);
		case 2: 
			submitted.add(new Subbet(description2));
			userGuessSub.add(userGuess2);
		case 1: 
			submitted.add(new Subbet(description1));
			userGuessSub.add(userGuess1);
		}
		
		Bet wbet = new Bet(skema, skema.getPlayed()+1, initialAmount, 0, 0);
		if(submitted.size()==1)
			wbet.setType(Bet.BetType.SINGLE);
		else if(submitted.size()==2)
			wbet.setType(Bet.BetType.DOUBLE);
		else
			wbet.setType(Bet.BetType.TRIPLEMULTIPLE);
		
		wbet.setState(Bet.BetState.WAITING_EXECUTION);
		
		skemaService.sendBetToAdmin(wbet, submitted, userGuessSub);
		return "success";
	
		
	}

	@Override
	public void validate() {
		switch(count){
		case 10:
			if(description10 == null || description10.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		case 9:
			if(description9 == null || description9.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		case 8:
			if(description8 == null || description8.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		case 7:
			if(description7 == null || description7.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		case 6:
			if(description6 == null || description6.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		case 5:
			if(description5 == null || description5.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		case 4:
			if(description4 == null || description4.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		case 3:
			if(description3 == null || description3.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		case 2:
			if(description2 == null || description2.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		default:
			if(description1 == null || description1.equals(""))
				addActionError("Please fill in all the bets descriptions.");
		}

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(float initialAmount) {
		this.initialAmount = initialAmount;
	}

	public SkemaService getSkemaService() {
		return skemaService;
	}

	public void setSkemaService(SkemaService skemaService) {
		this.skemaService = skemaService;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.map=arg0;
		
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public int getUserGuess1() {
		return userGuess1;
	}

	public void setUserGuess1(int userGuess1) {
		this.userGuess1 = userGuess1;
	}

	public int getUserGuess2() {
		return userGuess2;
	}

	public void setUserGuess2(int userGuess2) {
		this.userGuess2 = userGuess2;
	}

	public int getUserGuess3() {
		return userGuess3;
	}

	public void setUserGuess3(int userGuess3) {
		this.userGuess3 = userGuess3;
	}

	public int getUserGuess4() {
		return userGuess4;
	}

	public void setUserGuess4(int userGuess4) {
		this.userGuess4 = userGuess4;
	}

	public int getUserGuess5() {
		return userGuess5;
	}

	public void setUserGuess5(int userGuess5) {
		this.userGuess5 = userGuess5;
	}

	public int getUserGuess6() {
		return userGuess6;
	}

	public void setUserGuess6(int userGuess6) {
		this.userGuess6 = userGuess6;
	}

	public int getUserGuess7() {
		return userGuess7;
	}

	public void setUserGuess7(int userGuess7) {
		this.userGuess7 = userGuess7;
	}

	public int getUserGuess8() {
		return userGuess8;
	}

	public void setUserGuess8(int userGuess8) {
		this.userGuess8 = userGuess8;
	}

	public int getUserGuess9() {
		return userGuess9;
	}

	public void setUserGuess9(int userGuess9) {
		this.userGuess9 = userGuess9;
	}

	public int getUserGuess10() {
		return userGuess10;
	}

	public void setUserGuess10(int userGuess10) {
		this.userGuess10 = userGuess10;
	}

	public String getSubbets1() {
		return description1;
	}

	public void setSubbets1(String subbets1) {
		this.description1 = subbets1;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getDescription3() {
		return description3;
	}

	public void setDescription3(String description3) {
		this.description3 = description3;
	}

	public String getDescription4() {
		return description4;
	}

	public void setDescription4(String description4) {
		this.description4 = description4;
	}

	public String getDescription5() {
		return description5;
	}

	public void setDescription5(String description5) {
		this.description5 = description5;
	}

	public String getDescription6() {
		return description6;
	}

	public void setDescription6(String description6) {
		this.description6 = description6;
	}

	public String getDescription7() {
		return description7;
	}

	public void setDescription7(String description7) {
		this.description7 = description7;
	}

	public String getDescription8() {
		return description8;
	}

	public void setDescription8(String description8) {
		this.description8 = description8;
	}

	public String getDescription9() {
		return description9;
	}

	public void setDescription9(String description9) {
		this.description9 = description9;
	}

	public String getDescription10() {
		return description10;
	}

	public void setDescription10(String description10) {
		this.description10 = description10;
	}

	
	
	
}


