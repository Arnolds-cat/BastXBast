/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bastxbast.action.admin.json;

import java.util.Collection;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.bastxbast.model.User;
import com.bastxbast.services.UserService;
import com.opensymphony.xwork2.ActionSupport;


/**
 *
 * @author corsojava
 */

public class GetUsersAction extends ActionSupport{

    Collection<User> users;
    private int page, rows;
    private String sidx, sord;
    private int total;
    private int records;
    UserService userService;

    public Collection<User> getUsers() {
		return users;
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
        records = userService.getNumeroUsers();
        users = userService.getUsers(rows * (page - 1), rows, sord, sidx);
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
