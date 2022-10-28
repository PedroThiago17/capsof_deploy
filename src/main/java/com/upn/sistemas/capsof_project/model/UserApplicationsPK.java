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
public class UserApplicationsPK implements Serializable {

	private static final long serialVersionUID = -608301977033130270L;
	
	@Basic(optional = false)
    @Column(name = "OFFER_ID")
    private long offerId;
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private long userId;

    public UserApplicationsPK() {
    }

    public UserApplicationsPK(long offerId, long userId) {
        this.offerId = offerId;
        this.userId = userId;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
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
        hash += (int) offerId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserApplicationsPK)) {
            return false;
        }
        UserApplicationsPK other = (UserApplicationsPK) object;
        if (this.offerId != other.offerId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upn.mavenproject1.UserApplicationsPK[ offerId=" + offerId + ", userId=" + userId + " ]";
    }

}
