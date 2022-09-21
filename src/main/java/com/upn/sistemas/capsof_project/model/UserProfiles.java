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
@Table(name = "USER_PROFILES")
@NamedQueries({
    @NamedQuery(name = "UserProfiles.findAll", query = "SELECT u FROM UserProfiles u"),
    @NamedQuery(name = "UserProfiles.findByUserId", query = "SELECT u FROM UserProfiles u WHERE u.userProfilesPK.userId = :userId"),
    @NamedQuery(name = "UserProfiles.findByProfileId", query = "SELECT u FROM UserProfiles u WHERE u.userProfilesPK.profileId = :profileId"),
    @NamedQuery(name = "UserProfiles.findByProfileState", query = "SELECT u FROM UserProfiles u WHERE u.profileState = :profileState"),
    @NamedQuery(name = "UserProfiles.findByCreationDate", query = "SELECT u FROM UserProfiles u WHERE u.creationDate = :creationDate"),
    @NamedQuery(name = "UserProfiles.findByUpdateDate", query = "SELECT u FROM UserProfiles u WHERE u.updateDate = :updateDate")})
public class UserProfiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserProfilesPK userProfilesPK;
    @Basic(optional = false)
    @Column(name = "PROFILE_STATE")
    private String profileState;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profile profile;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userApp;

    public UserProfiles() {
    }

    public UserProfiles(UserProfilesPK userProfilesPK) {
        this.userProfilesPK = userProfilesPK;
    }

    public UserProfiles(UserProfilesPK userProfilesPK, String profileState, Date creationDate) {
        this.userProfilesPK = userProfilesPK;
        this.profileState = profileState;
        this.creationDate = creationDate;
    }

    public UserProfiles(long userId, long profileId) {
        this.userProfilesPK = new UserProfilesPK(userId, profileId);
    }

    public UserProfilesPK getUserProfilesPK() {
        return userProfilesPK;
    }

    public void setUserProfilesPK(UserProfilesPK userProfilesPK) {
        this.userProfilesPK = userProfilesPK;
    }

    public String getProfileState() {
        return profileState;
    }

    public void setProfileState(String profileState) {
        this.profileState = profileState;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public User getUserApp() {
        return userApp;
    }

    public void setUserApp(User userApp) {
        this.userApp = userApp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userProfilesPK != null ? userProfilesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserProfiles)) {
            return false;
        }
        UserProfiles other = (UserProfiles) object;
        if ((this.userProfilesPK == null && other.userProfilesPK != null) || (this.userProfilesPK != null && !this.userProfilesPK.equals(other.userProfilesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserProfiles[ userProfilesPK=" + userProfilesPK + " ]";
    }

}
