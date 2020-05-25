package com.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "users")
@DynamicUpdate
public class User {
	
	@Id
	private String user_id;
	private String firstname;
	private String lastname;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Override
	public String toString() {
		
		return "User ID : "+ this.getUser_id() + System.lineSeparator() +
				"FirstName : "+ this.getFirstname() + System.lineSeparator() +
				"LastName : "+ this.getLastname() + System.lineSeparator();
		
	}
}
