/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.upn.sistemas.capsof_project.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "USER_APPLICATIONS")
@NamedQueries({
    @NamedQuery(name = "UserApplications.findAll", query = "SELECT u FROM UserApplications u")})
public class UserApplications implements Serializable {

	private static final long serialVersionUID = 7887841834122315777L;

	@EmbeddedId
    protected UserApplicationsPK userApplicationsPK;
    @Basic(optional = false)
    @Column(name = "APPLICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date applicationDate;
    @Column(name = "DESAPPLICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date desapplicationDate;
    @Basic(optional = false)
    @Column(name = "APPLICATION_STATE")
    private String applicationState;

    public UserApplications() {
    }

    public UserApplications(UserApplicationsPK userApplicationsPK) {
        this.userApplicationsPK = userApplicationsPK;
    }

    public UserApplications(UserApplicationsPK userApplicationsPK, Date applicationDate, String applicationState) {
        this.userApplicationsPK = userApplicationsPK;
        this.applicationDate = applicationDate;
        this.applicationState = applicationState;
    }

    public UserApplications(long offerId, long userId) {
        this.userApplicationsPK = new UserApplicationsPK(offerId, userId);
    }

    public UserApplicationsPK getUserApplicationsPK() {
        return userApplicationsPK;
    }

    public void setUserApplicationsPK(UserApplicationsPK userApplicationsPK) {
        this.userApplicationsPK = userApplicationsPK;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getDesapplicationDate() {
        return desapplicationDate;
    }

    public void setDesapplicationDate(Date desapplicationDate) {
        this.desapplicationDate = desapplicationDate;
    }

    public String getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(String applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userApplicationsPK != null ? userApplicationsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserApplications)) {
            return false;
        }
        UserApplications other = (UserApplications) object;
        if ((this.userApplicationsPK == null && other.userApplicationsPK != null) || (this.userApplicationsPK != null && !this.userApplicationsPK.equals(other.userApplicationsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upn.mavenproject1.UserApplications[ userApplicationsPK=" + userApplicationsPK + " ]";
    }

}
