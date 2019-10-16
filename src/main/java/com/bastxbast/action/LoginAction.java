package com.bastxbast.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.model.Bet;
import com.bastxbast.model.User;
import com.bastxbast.services.AuthenticationService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	
	private String username, password;
	private Map<String, Object> sessionMap;
	private AuthenticationService servizioAutenticazione;

	public AuthenticationService getServizioAutenticazione() {
        return servizioAutenticazione;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public void setServizioAutenticazione(AuthenticationService servizioAutenticazione) {
        this.servizioAutenticazione = servizioAutenticazione;
    }

    @Override
    public String execute() throws Exception {
        
    	User user;
    		try {
				user = servizioAutenticazione.autentica(username, password);
			} catch (Exception e) {
				addActionError(e.getMessage());
				throw e;
				//return INPUT;
			}
            
            sessionMap.put("user", user);
            sessionMap.put("res", servizioAutenticazione.getMatchResults());
         
            if(user.getType().equals("admin")){
                sessionMap.put("profiloUser", "ADMIN");
                sessionMap.put("income", user.getIncome());
                return "admin";
            }
            else if(user.getType().equals("simple")){
                sessionMap.put("profiloUser", "SIMPLE");
                return "simple";
            } else if(user.getType().equals("manager")){
            	sessionMap.put("profiloUser", "MANAGER"); 
            	sessionMap.put("income", user.getIncome());
                return "manager";
            } 
            return "input";
            
        
        
        
    }

    private Object getMatchResults() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }

    
    @Override
    public void validate() {
        if(username==null || username.equals(""))
            addFieldError("user.username", "Username missing");
        if(password==null || password.equals(""))
            addFieldError("user.password", "Password missing");
    }

}
