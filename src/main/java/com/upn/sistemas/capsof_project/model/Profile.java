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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PROFILE")
@NamedQueries({
    @NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p"),
    @NamedQuery(name = "Profile.findByProfileId", query = "SELECT p FROM Profile p WHERE p.profileId = :profileId"),
    @NamedQuery(name = "Profile.findByProfileDesc", query = "SELECT p FROM Profile p WHERE p.profileDesc = :profileDesc"),
    @NamedQuery(name = "Profile.findByCreationDate", query = "SELECT p FROM Profile p WHERE p.creationDate = :creationDate"),
    @NamedQuery(name = "Profile.findByUpdateDate", query = "SELECT p FROM Profile p WHERE p.updateDate = :updateDate")})
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROFILE_ID")
    private Long profileId;
    @Column(name = "PROFILE_DESC")
    private String profileDesc;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @JoinColumn(name = "DOM_TP_PROFILE_ID", referencedColumnName = "DOMAIN_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParamDomain domTpProfileId;
    @JoinColumn(name = "DOM_EXP_ID", referencedColumnName = "DOMAIN_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParamDomain domExpId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile", fetch = FetchType.LAZY)
    private List<SkillsProfile> skillsProfileList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile", fetch = FetchType.LAZY)
    private List<UserProfiles> userProfilesList;

    public Profile() {
    }

    public Profile(Long profileId) {
        this.profileId = profileId;
    }

    public Profile(Long profileId, Date creationDate) {
        this.profileId = profileId;
        this.creationDate = creationDate;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
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

    public ParamDomain getDomTpProfileId() {
        return domTpProfileId;
    }

    public void setDomTpProfileId(ParamDomain domTpProfileId) {
        this.domTpProfileId = domTpProfileId;
    }

    public ParamDomain getDomExpId() {
        return domExpId;
    }

    public void setDomExpId(ParamDomain domExpId) {
        this.domExpId = domExpId;
    }

    public List<SkillsProfile> getSkillsProfileList() {
        return skillsProfileList;
    }

    public void setSkillsProfileList(List<SkillsProfile> skillsProfileList) {
        this.skillsProfileList = skillsProfileList;
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
        hash += (profileId != null ? profileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.profileId == null && other.profileId != null) || (this.profileId != null && !this.profileId.equals(other.profileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Profile[ profileId=" + profileId + " ]";
    }

}
