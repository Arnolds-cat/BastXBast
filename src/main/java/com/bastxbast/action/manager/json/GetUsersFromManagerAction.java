/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bastxbast.action.manager.json;

import java.util.Collection;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.bastxbast.model.User;
import com.bastxbast.services.UserService;
import com.opensymphony.xwork2.ActionSupport;


/**
 *
 * @author corsojava
 */
@ParentPackage("json1")
@Results({
   @Result(type = "json", location="/WEB-INF/manager/managerHome.jsp"),
   @Result(name="login" ,location="home.jsp")
})

public class GetUsersFromManagerAction extends ActionSupport implements SessionAware{

    Collection<User> users;
    private int page, rows;
    private String sidx, sord;
    private int total;
    private int records;
    UserService userService;
    private Map<String, Object> sessionMap;

    public Collection<User> getUsers() {
		return users;
	}

    @Override
	public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }

	public void setUsers(Collection<User> users) {
		this.users = users;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String execute() throws Exception {
		int managerID = ((User)sessionMap.get("user")).getUserID();
        records = userService.getNumeroUsers(managerID);
        users = userService.getUsers(rows * (page - 1), rows, sord, sidx, managerID);
        total = (int) (records / rows) + 1;
        if(records%rows==0)
            total -= 1;
        return SUCCESS;
    }

  
	public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
