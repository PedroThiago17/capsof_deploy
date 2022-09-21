/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.upn.sistemas.capsof_project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "USER_TYPE")
@NamedQueries({
    @NamedQuery(name = "UserType.findAll", query = "SELECT u FROM UserType u"),
    @NamedQuery(name = "UserType.findByUserTpId", query = "SELECT u FROM UserType u WHERE u.userTpId = :userTpId"),
    @NamedQuery(name = "UserType.findByUserTpDesc", query = "SELECT u FROM UserType u WHERE u.userTpDesc = :userTpDesc"),
    @NamedQuery(name = "UserType.findByUserTpState", query = "SELECT u FROM UserType u WHERE u.userTpState = :userTpState"),
    @NamedQuery(name = "UserType.findByCreationDate", query = "SELECT u FROM UserType u WHERE u.creationDate = :creationDate"),
    @NamedQuery(name = "UserType.findByUpdateDate", query = "SELECT u FROM UserType u WHERE u.updateDate = :updateDate")})
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_TP_ID")
    private Long userTpId;
    @Basic(optional = false)
    @Column(name = "USER_TP_DESC")
    private String userTpDesc;
    @Basic(optional = false)
    @Column(name = "USER_TP_STATE")
    private String userTpState;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userType", fetch = FetchType.LAZY)
    private List<UserRoles> userRolesList;

    public UserType() {
    }

    public UserType(Long userTpId) {
        this.userTpId = userTpId;
    }

    public UserType(Long userTpId, String userTpDesc, String userTpState, Date creationDate) {
        this.userTpId = userTpId;
        this.userTpDesc = userTpDesc;
        this.userTpState = userTpState;
        this.creationDate = creationDate;
    }

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

    public String getUserTpState() {
        return userTpState;
    }

    public void setUserTpState(String userTpState) {
        this.userTpState = userTpState;
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

    public List<UserRoles> getUserRolesList() {
        return userRolesList;
    }

    public void setUserRolesList(List<UserRoles> userRolesList) {
        this.userRolesList = userRolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userTpId != null ? userTpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserType)) {
            return false;
        }
        UserType other = (UserType) object;
        if ((this.userTpId == null && other.userTpId != null) || (this.userTpId != null && !this.userTpId.equals(other.userTpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserType[ userTpId=" + userTpId + " ]";
    }

}
