package com.bastxbast.model;

import java.util.Set;

import javax.persistence.Column;
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
@Table( name = "User" )
public class User {
	
	private String username, password, userFirstName, userFamilyName;
	private String userEmail, userPhone;
	
	private float score;
	
	private String type;
	
	private int userID;
	
	private User manager;
	
	private Set<User> users;
	
	private float income;
	
	private float managerPercent;
	
	public User() {
	}
	
	public User(String username, String password, String userFirstName, String userFamilyName, String userEmail,
			String userPhone, float income, float managerPercent) {
		super();
		this.username = username;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userFamilyName = userFamilyName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.income = income;
		this.managerPercent = managerPercent;
	}
	
	public float getManagerPercent() {
		return managerPercent;
	}

	public void setManagerPercent(float managerPercent) {
		this.managerPercent = managerPercent;
	}

	public User(String username, String password, String userFirstName, String userFamilyName, String userEmail,
			String userPhone) {
		super();
		this.username = username;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userFamilyName = userFamilyName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
	}
	
	public User(String username, String password, String userFirstName, String userFamilyName, String userEmail,
			String userPhone, User manager) {
		super();
		this.username = username;
		this.password = password;
		this.userFirstName = userFirstName;
		this.userFamilyName = userFamilyName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.manager = manager;
	}
	
	

	public User(int userID) {
		super();
		this.userID = userID;
	}

	public User(String username, String password, String type) {

		this.username = username;
		this.password = password;
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name="manager", nullable=true)
	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	@OneToMany(fetch=FetchType.EAGER, mappedBy="manager")
	public Set<User> getUsers() {
		return users;
	}

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserFamilyName() {
		return userFamilyName;
	}

	public void setUserFamilyName(String userFamilyName) {
		this.userFamilyName = userFamilyName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	

}
