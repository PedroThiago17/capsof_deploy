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
public class OfferSkillsPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "OFFER_ID")
	private long offerId;
	@Basic(optional = false)
	@Column(name = "SKILL_ID")
	private long skillId;

	public OfferSkillsPK() {
	}

	public OfferSkillsPK(long offerId, long skillId) {
		this.offerId = offerId;
		this.skillId = skillId;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
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
		hash += (int) offerId;
		hash += (int) skillId;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof OfferSkillsPK)) {
			return false;
		}
		OfferSkillsPK other = (OfferSkillsPK) object;
		if (this.offerId != other.offerId) {
			return false;
		}
		if (this.skillId != other.skillId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.OfferSkillsPK[ offerId=" + offerId + ", skillId=" + skillId + " ]";
	}

}
