package com.jayam.jwtapp.dto;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRequest {
	
//	@NotNull(message="username is required and provide String value")
//	private String username;	
	
	@NotNull(message="firstname is required and provide String value")
    @Pattern(regexp = "^[a-zA-Z]*$")
	private String firstname;
	
    @Pattern(regexp = "^[a-zA-Z]*$")
	@NotNull(message="lastname is required and provide String value")
	private String lastname;	
	
	@NotNull(message="password is required and provide String value")
	 @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
     message = "Password must contain at least one digit, "
     		+ "one lowercase letter, one uppercase letter,"
     		+ " one special character, and be at least 8 characters long")	private String password;
	
	@NotNull(message="email is required and provide String value")
	@Email
	private String email;
	
	@NotNull(message="dateofbirth is required and provide String value")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	private String dateofbirth;
	
	public UserRequest() {
		
	}

	public UserRequest(@Valid String firstname, @Valid String lastname, @Valid String password, @Valid String email,
			@Valid String dateofbirth) {
		//this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.dateofbirth = dateofbirth;
	}

	/*
	 * public String getUsername() { return username; }
	 * 
	 * public void setUsername(String username) { this.username = username; }
	 */

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateofbirth, email, firstname, lastname, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRequest other = (UserRequest) obj;
		return Objects.equals(dateofbirth, other.dateofbirth) && Objects.equals(email, other.email)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "UserRegisterRequest  firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + ", email=" + email + ", dateofbirth=" + dateofbirth + "]";
	}	
	

	
	
}
