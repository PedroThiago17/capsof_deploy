package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;
import java.util.Date;

public class UserTypeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userTpId;
	private String userTpDesc;
	private Date creationDate;
	private Date updateDate;

	public Long getUserTpId() {
		return userTpId;
	}

	public void setUserTpId(Long userTpId) {
		this.userTpId = userTpId;
	}

	public String getUserTpDesc() {
		return userTpDesc;
	}

	public void setUserTpDesc(String userTpDesc) {
		this.userTpDesc = userTpDesc;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
