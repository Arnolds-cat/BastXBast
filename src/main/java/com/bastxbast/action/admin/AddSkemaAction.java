package com.bastxbast.action.admin;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.exceptions.SkemaException;
import com.bastxbast.model.User;
import com.bastxbast.model.factories.SkemaFactory;
import com.bastxbast.services.SkemaService;
import com.opensymphony.xwork2.ActionSupport;


public class AddSkemaAction extends ActionSupport implements SessionAware {

	private int nEvents, nExpected, initialCash;
	private float share;
	private Map<String, Object> map;
	private SkemaService skemaService;
	
	
	@Override
	public String execute() {
		int userID = (int) map.get("userID");
		try {
			String description = null;
			if((int)map.get("skema1ID")==0)
				description = "Skema 1";
			else if((int)map.get("skema2ID")==0)
				description = "Skema 2";
			else if ((int)map.get("skema3ID")==0)
				description = "Skema 3";
			else
				throw new SkemaException("No space for new skema");
			skemaService.addSkema(nEvents, nExpected, initialCash, share, userID, description);
						
			skemaService.updateManagerIncome(initialCash, userID);
			
		} catch (SkemaException e) {
			addActionError("Impossible Skema, please contact the admin.");
		}
		return "success";
	}

	@Override
    public void validate() {
        if(nEvents<=0 || nEvents>100)
        	addFieldError("nEvents", "Ju lutemi jepni një vlerë midis 1 dhe 100");
        if(nExpected <= 0 || nEvents < nExpected)
        	addFieldError("nExpected", "Ju lutemi jepni një vlerë më të ulët se numri i ngjarjeve");
        
        if(initialCash < 5)
        	addFieldError("initialCash", "Investimi minimum është 5");
       if(share<=1)
    	   addFieldError("share", "Invalid share");
	}

	public int getnEvents() {
		return nEvents;
	}

	public void setnEvents(int nEvents) {
		this.nEvents = nEvents;
	}

	public int getnExpected() {
		return nExpected;
	}

	public void setnExpected(int nExpected) {
		this.nExpected = nExpected;
	}

	public int getInitialCash() {
		return initialCash;
	}

	public void setInitialCash(int initialCash) {
		this.initialCash = initialCash;
	}

	public float getShare() {
		return share;
	}

	public void setShare(float share) {
		this.share = share;
	}

	public SkemaService getSkemaService() {
		return skemaService;
	}

	public void setSkemaService(SkemaService skemaService) {
		this.skemaService = skemaService;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.map = arg0;
		
	}

	
}
