package com.jayam.jwtapp.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class UserData implements Serializable {

	private String username;
	private List<String> roles;
	private String token;
	
	public UserData() {
		
	}
	public UserData(String username, List<String> roles, String token) {
		this.username = username;
		this.roles = roles;
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public int hashCode() {
		return Objects.hash(roles, token, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserData other = (UserData) obj;
		return Objects.equals(roles, other.roles) && Objects.equals(token, other.token)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "UserData [username=" + username + ", roles=" + roles + ", token=" + token + "]";
	}

	
	
}
