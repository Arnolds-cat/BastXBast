package com.bastxbast.action;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.exceptions.SkemaException;
import com.bastxbast.model.Skema;
import com.bastxbast.model.User;
import com.bastxbast.model.factories.SkemaFactory;
import com.bastxbast.services.AuthenticationService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.log4j2.Log4j2Logger;

@Results({
	  @Result(name="input", location="home.jsp"),  
	  @Result(name="success", location="home.jsp"),  
	})
@ResultPath("/")
public class CreateSkemaAction extends ActionSupport {
	
	private int nEvents, nExpected, initialCash;
	private String stringShare;
	private String result;
	private float share;
	
	@Override
    public String execute() {
		try {
			Skema skema = SkemaFactory.getSkema(nEvents, nExpected, share, initialCash);
			result = Integer.toString(skema.getRoundedFinalCash());
		} catch (SkemaException e) {
			result = "";
			addActionError("Something got wrong. Please contact the admin.");
		}
		
		
		return SUCCESS;
		
	}
	
	

    
    public String getResult() {
		return result;
	}




	public void setResult(String result) {
		this.result = result;
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




	

	public String getStringShare() {
		return stringShare;
	}




	public void setStringShare(String stringShare) {
		this.stringShare = stringShare;
	}




	@Override
    public void validate() {
        if(nEvents<=0 || nEvents>100)
        	addFieldError("nEvents", "Ju lutemi jepni një vlerë midis 1 dhe 100");
        if(nExpected <= 0 || nEvents < nExpected)
        	addFieldError("nExpected", "Ju lutemi jepni një vlerë më të ulët se numri i ngjarjeve");
        
        if(initialCash < 5)
        	addFieldError("initialCash", "Investimi minimum është 5");
       if(stringShare.isEmpty())
    	   addFieldError("stringShare", "pjesë e gabuar");
        if(stringShare.contains(",")){
        	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ITALY);
        	try {
				share = numberFormat.parse(stringShare).floatValue();
			} catch (ParseException e) {
				addFieldError("stringShare", "pjesë e gabuar");
			} catch (NumberFormatException e){
				addFieldError("stringShare", "pjesë e gabuar");
			}
        } else {
        	try {
        	share = Float.parseFloat(stringShare);
        	} catch (NumberFormatException e){
				addFieldError("stringShare", "pjesë e gabuar");
			}
        }
        	
        
        
    }

	

}
