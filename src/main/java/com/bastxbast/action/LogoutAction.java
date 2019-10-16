/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bastxbast.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author corsojava
 */
@ParentPackage("default")
@Result(name = "success", location ="/home.jsp", type="redirect")
public class LogoutAction  extends ActionSupport implements ServletRequestAware, SessionAware{
    
    
    private HttpServletRequest hsr;
    private Map<String, Object> sessionMap;
    
  
    
    @Override
    public String execute (){    
        sessionMap.clear();
        hsr.getSession().invalidate();
        return SUCCESS;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        this.hsr = hsr;
    }

    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }
}
