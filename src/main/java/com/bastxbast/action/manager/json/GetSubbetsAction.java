package com.bastxbast.action.manager.json;

import java.util.Collection;
import java.util.Map;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.bastxbast.model.Bet;
import com.bastxbast.model.Subbet;
import com.bastxbast.model.User;
import com.bastxbast.services.BetService;
import com.bastxbast.services.SubbetService;
import com.bastxbast.services.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class GetSubbetsAction extends ActionSupport {
	
	Collection<Subbet> subbets;
    private int page, rows;
    private String sidx, sord;
    private int total;
    private int records;
    SubbetService subbetService;
    private int IDbet;
    
	@Override
	public String execute() throws Exception {
		records = subbetService.getNumeroSubbets(IDbet);
        subbets = subbetService.getSubbets(rows * (page - 1), rows, sord, sidx, IDbet);
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
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public Collection<Subbet> getSubbets() {
		return subbets;
	}
	public void setSubbets(Collection<Subbet> subbets) {
		this.subbets = subbets;
	}
	public SubbetService getSubbetService() {
		return subbetService;
	}
	public void setSubbetService(SubbetService subbetService) {
		this.subbetService = subbetService;
	}
	public int getIDbet() {
		return IDbet;
	}
	public void setIDbet(int iDbet) {
		IDbet = iDbet;
	}
	

}
