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
@Table(name = "USER_APP")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUserDni", query = "SELECT u FROM User u WHERE u.userDni = :userDni"),
    @NamedQuery(name = "User.findByUserNames", query = "SELECT u FROM User u WHERE u.userNames = :userNames"),
    @NamedQuery(name = "User.findByUserLastNames", query = "SELECT u FROM User u WHERE u.userLastNames = :userLastNames"),
    @NamedQuery(name = "User.findByUserDesc", query = "SELECT u FROM User u WHERE u.userDesc = :userDesc"),
    @NamedQuery(name = "User.findByUserEmail", query = "SELECT u FROM User u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "User.findByUserPass", query = "SELECT u FROM User u WHERE u.userPass = :userPass"),
    @NamedQuery(name = "User.findByUserPhone", query = "SELECT u FROM User u WHERE u.userPhone = :userPhone"),
    @NamedQuery(name = "User.findByUserState", query = "SELECT u FROM User u WHERE u.userState = :userState"),
    @NamedQuery(name = "User.findByCreationDate", query = "SELECT u FROM User u WHERE u.creationDate = :creationDate"),
    @NamedQuery(name = "User.findByUpdateDate", query = "SELECT u FROM User u WHERE u.updateDate = :updateDate")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Long userId;
    @Basic(optional = false)
    @Column(name = "USER_DNI")
    private String userDni;
    @Basic(optional = false)
    @Column(name = "USER_NAMES")
    private String userNames;
    @Basic(optional = false)
    @Column(name = "USER_LAST_NAMES")
    private String userLastNames;
    @Column(name = "USER_DESC")
    private String userDesc;
    @Basic(optional = false)
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Basic(optional = false)
    @Column(name = "USER_PASS")
    private String userPass;
    @Column(name = "USER_PHONE")
    private String userPhone;
    @Basic(optional = false)
    @Column(name = "USER_STATE")
    private String userState;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userApp", fetch = FetchType.LAZY)
    private List<UserRoles> userRolesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userApp", fetch = FetchType.LAZY)
    private List<UserProfiles> userProfilesList;

    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
    }

    public User(Long userId, String userDni, String userNames, String userLastNames, String userEmail, String userPass, String userState, Date creationDate) {
        this.userId = userId;
        this.userDni = userDni;
        this.userNames = userNames;
        this.userLastNames = userLastNames;
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.userState = userState;
        this.creationDate = creationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public String getUserLastNames() {
        return userLastNames;
    }

    public void setUserLastNames(String userLastNames) {
        this.userLastNames = userLastNames;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
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

    public List<UserProfiles> getUserProfilesList() {
        return userProfilesList;
    }

    public void setUserProfilesList(List<UserProfiles> userProfilesList) {
        this.userProfilesList = userProfilesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserApp[ userId=" + userId + " ]";
    }

}
