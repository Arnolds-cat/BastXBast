package com.bastxbast.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MatchResult {
	
	private int matchResultID;
	private String description;
	private Set<Subbet> bets;
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getMatchResultID() {
		return matchResultID;
	}
	public void setMatchResultID(int matchResultID) {
		this.matchResultID = matchResultID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToMany(fetch=FetchType.EAGER, mappedBy="userGuess")
	public Set<Subbet> getBets() {
		return bets;
	}
	public void setBets(Set<Subbet> bets) {
		this.bets = bets;
	}
	
	
}
