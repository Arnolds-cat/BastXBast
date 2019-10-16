/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bastxbast.action.admin.json;

import java.util.Collection;

import com.bastxbast.model.User;
import com.bastxbast.services.ManagerService;
import com.opensymphony.xwork2.ActionSupport;


/**
 *
 * @author corsojava
 */

public class GetManagersAction extends ActionSupport{

    Collection<User> managers;
    private int page, rows;
    private String sidx, sord;
    private int total;
    private int records;
    ManagerService managerService;

    public String execute() throws Exception {
        records = managerService.getNumeroManagers();
        managers = managerService.getManagers(rows * (page - 1), rows, sord, sidx);
        total = (int) (records / rows) + 1;
        if(records%rows==0)
            total -= 1;
        return SUCCESS;
    }

    

    


	public Collection<User> getManagers() {
		return managers;
	}






	public void setManagers(Collection<User> managers) {
		this.managers = managers;
	}






	public ManagerService getManagerService() {
		return managerService;
	}






	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
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
