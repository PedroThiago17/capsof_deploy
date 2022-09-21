package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;

public class UserTypeSaveDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userTpDesc;

	public String getUserTpDesc() {
		return userTpDesc;
	}

	public void setUserTpDesc(String userTpDesc) {
		this.userTpDesc = userTpDesc;
	}
}
