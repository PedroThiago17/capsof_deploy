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
public class SkillsProfilePK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "PROFILE_ID")
	private long profileId;
	@Basic(optional = false)
	@Column(name = "SKILL_ID")
	private long skillId;

	public SkillsProfilePK() {
	}

	public SkillsProfilePK(long profileId, long skillId) {
		this.profileId = profileId;
		this.skillId = skillId;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) profileId;
		hash += (int) skillId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SkillsProfilePK)) {
			return false;
		}
		SkillsProfilePK other = (SkillsProfilePK) object;
		if (this.profileId != other.profileId) {
			return false;
		}
		if (this.skillId != other.skillId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.SkillsProfilePK[ profileId=" + profileId + ", skillId=" + skillId + " ]";
	}

}
