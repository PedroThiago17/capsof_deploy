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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "USER_ROLES")
@NamedQueries({
    @NamedQuery(name = "UserRoles.findAll", query = "SELECT u FROM UserRoles u"),
    @NamedQuery(name = "UserRoles.findByUserTpId", query = "SELECT u FROM UserRoles u WHERE u.userRolesPK.userTpId = :userTpId"),
    @NamedQuery(name = "UserRoles.findByUserId", query = "SELECT u FROM UserRoles u WHERE u.userRolesPK.userId = :userId"),
    @NamedQuery(name = "UserRoles.findByRoleState", query = "SELECT u FROM UserRoles u WHERE u.roleState = :roleState"),
    @NamedQuery(name = "UserRoles.findByCreationDate", query = "SELECT u FROM UserRoles u WHERE u.creationDate = :creationDate"),
    @NamedQuery(name = "UserRoles.findByUpdateDate", query = "SELECT u FROM UserRoles u WHERE u.updateDate = :updateDate")})
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserRolesPK userRolesPK;
    @Basic(optional = false)
    @Column(name = "ROLE_STATE")
    private String roleState;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userApp;
    @JoinColumn(name = "USER_TP_ID", referencedColumnName = "USER_TP_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserType userType;

    public UserRoles() {
    }

    public UserRoles(UserRolesPK userRolesPK) {
        this.userRolesPK = userRolesPK;
    }

    public UserRoles(UserRolesPK userRolesPK, String roleState, Date creationDate) {
        this.userRolesPK = userRolesPK;
        this.roleState = roleState;
        this.creationDate = creationDate;
    }

    public UserRoles(long userTpId, long userId) {
        this.userRolesPK = new UserRolesPK(userTpId, userId);
    }

    public UserRolesPK getUserRolesPK() {
        return userRolesPK;
    }

    public void setUserRolesPK(UserRolesPK userRolesPK) {
        this.userRolesPK = userRolesPK;
    }

    public String getRoleState() {
        return roleState;
    }

    public void setRoleState(String roleState) {
        this.roleState = roleState;
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

    public User getUserApp() {
        return userApp;
    }

    public void setUserApp(User userApp) {
        this.userApp = userApp;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRolesPK != null ? userRolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoles)) {
            return false;
        }
        UserRoles other = (UserRoles) object;
        if ((this.userRolesPK == null && other.userRolesPK != null) || (this.userRolesPK != null && !this.userRolesPK.equals(other.userRolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserRoles[ userRolesPK=" + userRolesPK + " ]";
    }

}
