package com.jayam.jwtapp.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class UserResponse  implements Serializable {
	private String username;
	private List<String> roles;
	private String token;

	private UserResponse(Builder userBuilder) {
		this.username = userBuilder.username;
		this.roles = userBuilder.roles;
		this.token = userBuilder.token;

	}
	public UserResponse(){
		
	}

	public static class Builder {
		private String username;
		private List<String> roles;
		private String token;

		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}
		public Builder setToken(String token) {
			this.token = token;
			return this;
		}

		public Builder setRoles(List<String> roles) {
			this.roles = roles;
			return this;
		}
		public String getToken() {
			return token;
		}
		public String getUsername() {
			return username;
		}

		public List<String> getRoles() {
			return roles;
		}


		public UserResponse build() {
			return new UserResponse(this);
		}

	}

}
