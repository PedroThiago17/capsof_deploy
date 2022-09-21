package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;

public class UserSaveDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userDni;
	private String userNames;
	private String userLastNames;
	private String userDesc;
	private String userEmail;
	private String userPass;
	private String userPhone;

	public String getUserDni() {
		return userDni;
	}

	public void setUserDni(String userDni) {
		this.userDni = userDni;
	}

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

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
}
