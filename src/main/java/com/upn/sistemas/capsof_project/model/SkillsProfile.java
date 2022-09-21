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
@Table(name = "SKILLS_PROFILE")
@NamedQueries({
    @NamedQuery(name = "SkillsProfile.findAll", query = "SELECT s FROM SkillsProfile s"),
    @NamedQuery(name = "SkillsProfile.findByProfileId", query = "SELECT s FROM SkillsProfile s WHERE s.skillsProfilePK.profileId = :profileId"),
    @NamedQuery(name = "SkillsProfile.findBySkillId", query = "SELECT s FROM SkillsProfile s WHERE s.skillsProfilePK.skillId = :skillId"),
    @NamedQuery(name = "SkillsProfile.findBySkillNivel", query = "SELECT s FROM SkillsProfile s WHERE s.skillNivel = :skillNivel"),
    @NamedQuery(name = "SkillsProfile.findByCreationDate", query = "SELECT s FROM SkillsProfile s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "SkillsProfile.findByUpdateDate", query = "SELECT s FROM SkillsProfile s WHERE s.updateDate = :updateDate")})
public class SkillsProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SkillsProfilePK skillsProfilePK;
    @Basic(optional = false)
    @Column(name = "SKILL_NIVEL")
    private int skillNivel;
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
    @JoinColumn(name = "SKILL_ID", referencedColumnName = "SKILL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Skill skill;

    public SkillsProfile() {
    }

    public SkillsProfile(SkillsProfilePK skillsProfilePK) {
        this.skillsProfilePK = skillsProfilePK;
    }

    public SkillsProfile(SkillsProfilePK skillsProfilePK, int skillNivel, Date creationDate) {
        this.skillsProfilePK = skillsProfilePK;
        this.skillNivel = skillNivel;
        this.creationDate = creationDate;
    }

    public SkillsProfile(long profileId, long skillId) {
        this.skillsProfilePK = new SkillsProfilePK(profileId, skillId);
    }

    public SkillsProfilePK getSkillsProfilePK() {
        return skillsProfilePK;
    }

    public void setSkillsProfilePK(SkillsProfilePK skillsProfilePK) {
        this.skillsProfilePK = skillsProfilePK;
    }

    public int getSkillNivel() {
        return skillNivel;
    }

    public void setSkillNivel(int skillNivel) {
        this.skillNivel = skillNivel;
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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillsProfilePK != null ? skillsProfilePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillsProfile)) {
            return false;
        }
        SkillsProfile other = (SkillsProfile) object;
        if ((this.skillsProfilePK == null && other.skillsProfilePK != null) || (this.skillsProfilePK != null && !this.skillsProfilePK.equals(other.skillsProfilePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SkillsProfile[ skillsProfilePK=" + skillsProfilePK + " ]";
    }

}
