package com.bastxbast.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="Bet")
public class Bet {
	
	public enum BetState {
		WAITING_EXECUTION, WAITING_RESULT, WON, LOST;
	}
	

	public enum BetResult {
		LOST, WIN;

		public static BetResult get(int result) {
			if(result==0)
				return LOST;
			else
				return WIN;
		}
	}
	
	public enum BetType {
		SINGLE, DOUBLE, TRIPLEMULTIPLE, LIVE;

		public static BetType get(int type) {
			switch(type){
			case 0: return SINGLE;
			case 1: return DOUBLE;
			case 2: return TRIPLEMULTIPLE;
			case 3: return LIVE;
			}
			return null;
		}
	}
	
	private Skema skema;
	
	private int IDbet;
	
	private int positionInSkema;
	
	private BetState state;
	
	private float initialAmount, realQuote, playedAmount;
	
	private BetResult result;
	
	private BetType type;
	
	private float skemaCurrentCashAfterResult;

	private Set<Subbet> subbets;
	
	public Bet(Skema skema, int positionInSkema, float initialAmount, float realQuote,
			float playedAmount) {
		super();
		this.skema = skema;
		this.positionInSkema = positionInSkema;
		this.initialAmount = initialAmount;
		this.realQuote = realQuote;
		this.playedAmount = playedAmount;
		this.state = BetState.WAITING_EXECUTION;
	}

	public Bet() {
		super();
	}

	@ManyToOne
	@JoinColumn(name="skema", nullable=false)
	public Skema getSkema() {
		return skema;
	}

	public void setSkema(Skema skema) {
		this.skema = skema;
	}

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getIDbet() {
		return IDbet;
	}

	public void setIDbet(int iDbet) {
		IDbet = iDbet;
	}

	public BetType getType() {
		return type;
	}

	public void setType(BetType type) {
		this.type = type;
	}

	public float getSkemaCurrentCashAfterResult() {
		return skemaCurrentCashAfterResult;
	}

	public void setSkemaCurrentCashAfterResult(float skemaCurrentCashAfterResult) {
		this.skemaCurrentCashAfterResult = skemaCurrentCashAfterResult;
	}

	public int getPositionInSkema() {
		return positionInSkema;
	}

	public void setPositionInSkema(int positionInSkema) {
		this.positionInSkema = positionInSkema;
	}

	public BetState getState() {
		return state;
	}

	public void setState(BetState state) {
		this.state = state;
	}

	public float getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(float initialAmount) {
		this.initialAmount = initialAmount;
	}

	public float getRealQuote() {
		return realQuote;
	}

	public void setRealQuote(float realQuote) {
		this.realQuote = realQuote;
	}

	public float getPlayedAmount() {
		return playedAmount;
	}

	public void setPlayedAmount(float playedAmount) {
		this.playedAmount = playedAmount;
	}

	public BetResult getResult() {
		return result;
	}

	public void setResult(BetResult result) {
		this.result = result;		
	}
	
	public void updateResult(BetResult result){
		if(result.equals(Bet.BetResult.LOST)){
			this.skemaCurrentCashAfterResult = skema.getCurrentCash() - this.getInitialAmount();
		} else {
			this.skemaCurrentCashAfterResult = skema.getCurrentCash() + this.getInitialAmount()*(skema.getShare()-1);
		}
	}

	@OneToMany(fetch=FetchType.EAGER, mappedBy="bet", cascade=CascadeType.PERSIST)
	public Set<Subbet> getSubbets() {
		return subbets;
	}

	public void setSubbets(Set<Subbet> subbets) {
		this.subbets = subbets;
	}

	

}
