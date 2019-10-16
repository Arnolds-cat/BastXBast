package com.bastxbast.action.admin.json;

import java.util.Collection;

import com.bastxbast.model.Bet;
import com.bastxbast.services.ResultBetService;
import com.opensymphony.xwork2.ActionSupport;


public class GetResbetAction extends ActionSupport {
	
	Collection<Bet> bets;
    private int page, rows;
    private String sidx, sord;
    private int total;
    private int records;
    ResultBetService resultBetService;
    
    public String execute() throws Exception {
        records = resultBetService.getNumeroBets();
        bets = resultBetService.getBets(rows * (page - 1), rows, sord, sidx);
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

	public ResultBetService getResultBetService() {
		return resultBetService;
	}

	public void setResultBetService(ResultBetService resultBetService) {
		this.resultBetService = resultBetService;
	}

	public Collection<Bet> getBets() {
		return bets;
	}

	public void setBets(Collection<Bet> bets) {
		this.bets = bets;
	}
    
    

}
