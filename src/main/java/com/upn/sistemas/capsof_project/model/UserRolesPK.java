/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.upn.sistemas.capsof_project.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author pedro
 */
@Embeddable
public class UserRolesPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "USER_TP_ID")
	private long userTpId;
	@Basic(optional = false)
	@Column(name = "USER_ID")
	private long userId;

	public UserRolesPK() {
	}

	public UserRolesPK(long userTpId, long userId) {
		this.userTpId = userTpId;
		this.userId = userId;
	}

	public long getUserTpId() {
		return userTpId;
	}

	public void setUserTpId(long userTpId) {
		this.userTpId = userTpId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) userTpId;
		hash += (int) userId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserRolesPK)) {
			return false;
		}
		UserRolesPK other = (UserRolesPK) object;
		if (this.userTpId != other.userTpId) {
			return false;
		}
		if (this.userId != other.userId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.UserRolesPK[ userTpId=" + userTpId + ", userId=" + userId + " ]";
	}

}
