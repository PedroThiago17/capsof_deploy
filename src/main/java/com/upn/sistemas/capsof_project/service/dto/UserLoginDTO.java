package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;

public class UserLoginDTO implements Serializable {

	private static final long serialVersionUID = 8046113362064063766L;

	private String email;
	private String password;
	private String type;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
