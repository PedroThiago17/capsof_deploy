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
@Table(name = "SKILL")
@NamedQueries({
    @NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s"),
    @NamedQuery(name = "Skill.findBySkillId", query = "SELECT s FROM Skill s WHERE s.skillId = :skillId"),
    @NamedQuery(name = "Skill.findBySkillDesc", query = "SELECT s FROM Skill s WHERE s.skillDesc = :skillDesc"),
    @NamedQuery(name = "Skill.findByCreationDate", query = "SELECT s FROM Skill s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "Skill.findByUpdateDate", query = "SELECT s FROM Skill s WHERE s.updateDate = :updateDate")})
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SKILL_ID")
    private Long skillId;
    @Basic(optional = false)
    @Column(name = "SKILL_DESC")
    private String skillDesc;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skill", fetch = FetchType.LAZY)
    private List<OfferSkills> offerSkillsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skill", fetch = FetchType.LAZY)
    private List<SkillsProfile> skillsProfileList;
    @JoinColumn(name = "SKILL_TP_ID", referencedColumnName = "SKILL_TP_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SkillTypology skillTpId;

    public Skill() {
    }

    public Skill(Long skillId) {
        this.skillId = skillId;
    }

    public Skill(Long skillId, String skillDesc, Date creationDate) {
        this.skillId = skillId;
        this.skillDesc = skillDesc;
        this.creationDate = creationDate;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillDesc() {
        return skillDesc;
    }

    public void setSkillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
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

    public List<OfferSkills> getOfferSkillsList() {
        return offerSkillsList;
    }

    public void setOfferSkillsList(List<OfferSkills> offerSkillsList) {
        this.offerSkillsList = offerSkillsList;
    }

    public List<SkillsProfile> getSkillsProfileList() {
        return skillsProfileList;
    }

    public void setSkillsProfileList(List<SkillsProfile> skillsProfileList) {
        this.skillsProfileList = skillsProfileList;
    }

    public SkillTypology getSkillTpId() {
        return skillTpId;
    }

    public void setSkillTpId(SkillTypology skillTpId) {
        this.skillTpId = skillTpId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillId != null ? skillId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skill)) {
            return false;
        }
        Skill other = (Skill) object;
        if ((this.skillId == null && other.skillId != null) || (this.skillId != null && !this.skillId.equals(other.skillId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Skill[ skillId=" + skillId + " ]";
    }

}
