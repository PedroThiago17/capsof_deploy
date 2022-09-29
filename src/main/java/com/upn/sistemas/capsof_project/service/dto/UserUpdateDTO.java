package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;

public class UserUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	private String userNames;
	private String userLastNames;
	private String userDesc;
	private String userEmail;
	private String userPhone;
	private String dniUser;

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getUserLastNames() {
		return userLastNames;
	}

	public void setUserLastNames(String userLastNames) {
		this.userLastNames = userLastNames;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDniUser() {
		return dniUser;
	}

	public void setDniUser(String dniUser) {
		this.dniUser = dniUser;
	}
}
