package com.bastxbast.model;

import java.util.Set;

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
@Table(name="Skema")
public class Skema {
	
	public enum SkemaState {
		ACTIVE, WAITING, WAITING_FOR_CLOSE, CLOSED_WON, CLOSED_LOST
	}
	
	private int IDSkema;
	private int NEvents, NExpected, won, played;
	private float currentCash, initialCash, finalCash;
	private int roundedFinalCash;
	private float share;
	
	private String description;
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	
	private SkemaState state = SkemaState.ACTIVE;
	
	private int user;
	
	private Set<Bet> playedBets;

	public Skema() {
		super();
	}

	
	public Skema(int nEvents, int nExpected, float initialCash, float share) {
		super();
		this.NEvents = nEvents;
		this.NExpected = nExpected;
		this.initialCash = initialCash;
		this.share = share;
	}


	public int getWon() {
		return won;
	}


	public void setWon(int won) {
		this.won = won;
	}


	public int getPlayed() {
		return played;
	}


	public void setPlayed(int played) {
		this.played = played;
	}


	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getIDSkema() {
		return IDSkema;
	}

	public void setIDSkema(int iDSkema) {
		IDSkema = iDSkema;
	}

	public int getNEvents() {
		return NEvents;
	}

	public void setNEvents(int nEvents) {
		NEvents = nEvents;
	}

	public int getNExpected() {
		return NExpected;
	}

	public void setNExpected(int nExpected) {
		NExpected = nExpected;
	}

	public float getCurrentCash() {
		return currentCash;
	}

	public void setCurrentCash(float currentCash) {
		this.currentCash = currentCash;
	}

	public float getInitialCash() {
		return initialCash;
	}

	public void setInitialCash(float initialCash) {
		this.initialCash = initialCash;
	}

	public float getFinalCash() {
		return finalCash;
	}

	public void setFinalCash(float finalCash) {
		this.finalCash = finalCash;
	}

	public int getRoundedFinalCash() {
		return roundedFinalCash;
	}

	public void setRoundedFinalCash(int roundedFinalCash) {
		this.roundedFinalCash = roundedFinalCash;
	}

	public float getShare() {
		return share;
	}

	public void setShare(float share) {
		this.share = share;
	}

	public SkemaState getState() {
		return state;
	}

	public void setState(SkemaState state) {
		this.state = state;
	}

	@OneToMany(fetch=FetchType.EAGER, mappedBy="skema")
	public Set<Bet> getPlayedBets() {
		return playedBets;
	}

	public void setPlayedBets(Set<Bet> playedBets) {
		this.playedBets = playedBets;
	}


	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}


}
