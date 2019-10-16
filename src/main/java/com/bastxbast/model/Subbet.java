package com.bastxbast.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Subbet {
	
	private int subbetID;
	private MatchResult userGuess;
	private String description;
	private float share;
	private Bet bet;
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getSubbetID() {
		return subbetID;
	}
	public void setSubbetID(int subbetID) {
		this.subbetID = subbetID;
	}
	@ManyToOne
	@JoinColumn(name="userGuess", nullable=true)
	public MatchResult getUserGuess() {
		return userGuess;
	}
	public void setUserGuess(MatchResult userGuess) {
		this.userGuess = userGuess;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getShare() {
		return share;
	}
	public void setShare(float share) {
		this.share = share;
	}
	@ManyToOne
	@JoinColumn(name="bet", nullable=true)
	public Bet getBet() {
		return bet;
	}
	public void setBet(Bet bet) {
		this.bet = bet;
	}
	public Subbet(String description) {
		super();
		this.description = description;
	}
	public Subbet() {
		super();
	}
	public Subbet(String description, float share) {
		super();
		this.description = description;
		this.share = share;
	}
	
	

}
