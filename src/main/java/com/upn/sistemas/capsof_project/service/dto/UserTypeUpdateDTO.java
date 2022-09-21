package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;

public class UserTypeUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userTpId;
	private String userTpDesc;

	public int getUserTpId() {
		return userTpId;
	}

	public void setUserTpId(int userTpId) {
		this.userTpId = userTpId;
	}

	public String getUserTpDesc() {
		return userTpDesc;
	}

	public void setUserTpDesc(String userTpDesc) {
		this.userTpDesc = userTpDesc;
	}

}
