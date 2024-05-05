package com.jayam.jwtapp.dto;

import java.util.Objects;

public class UserMiniProfile {

	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String dateOfBirth;
	public UserMiniProfile(){

	}	
	public UserMiniProfile(String username, String firstname, String lastname, String email, String dateOfBirth) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, email, firstname, lastname, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMiniProfile other = (UserMiniProfile) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email, other.email)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "UserMiniProfile [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", dateOfBirth=" + dateOfBirth + "]";
	}

}

