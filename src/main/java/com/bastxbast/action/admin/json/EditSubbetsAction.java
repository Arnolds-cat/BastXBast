/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bastxbast.action.admin.json;

import com.bastxbast.exceptions.AccountEsistenteException;
import com.bastxbast.exceptions.AccountInesistenteException;
import com.bastxbast.model.User;
import com.bastxbast.services.SubbetService;
import com.bastxbast.services.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author corsojava
 */

public class EditSubbetsAction extends ActionSupport {
	SubbetService subbetService;
	private String id, oper;
	private float share;

	private String responseText;

	public String execute() {

		if (oper.equals("edit")) {
			try {
				subbetService.modificaSubbet(Integer.parseInt(id), share);
				responseText = "success";
			} catch (NumberFormatException e) {
				responseText = "error";
			} 
		}
		
		
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public SubbetService getSubbetService() {
		return subbetService;
	}

	public void setSubbetService(SubbetService subbetService) {
		this.subbetService = subbetService;
	}

	public float getShare() {
		return share;
	}

	public void setShare(float share) {
		this.share = share;
	}

	

	
	
}
