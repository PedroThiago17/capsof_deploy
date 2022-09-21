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
public class UserProfilesPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "USER_ID")
	private long userId;
	@Basic(optional = false)
	@Column(name = "PROFILE_ID")
	private long profileId;

	public UserProfilesPK() {
	}

	public UserProfilesPK(long userId, long profileId) {
		this.userId = userId;
		this.profileId = profileId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) userId;
		hash += (int) profileId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserProfilesPK)) {
			return false;
		}
		UserProfilesPK other = (UserProfilesPK) object;
		if (this.userId != other.userId) {
			return false;
		}
		if (this.profileId != other.profileId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.UserProfilesPK[ userId=" + userId + ", profileId=" + profileId + " ]";
	}

}
