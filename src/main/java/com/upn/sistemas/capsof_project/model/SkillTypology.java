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
@Table(name = "SKILL_TYPOLOGY")
@NamedQueries({
    @NamedQuery(name = "SkillTypology.findAll", query = "SELECT s FROM SkillTypology s"),
    @NamedQuery(name = "SkillTypology.findBySkillTpId", query = "SELECT s FROM SkillTypology s WHERE s.skillTpId = :skillTpId"),
    @NamedQuery(name = "SkillTypology.findBySkillTpDesc", query = "SELECT s FROM SkillTypology s WHERE s.skillTpDesc = :skillTpDesc"),
    @NamedQuery(name = "SkillTypology.findByCreationDate", query = "SELECT s FROM SkillTypology s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "SkillTypology.findByUpdateDate", query = "SELECT s FROM SkillTypology s WHERE s.updateDate = :updateDate")})
public class SkillTypology implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SKILL_TP_ID")
    private Long skillTpId;
    @Column(name = "SKILL_TP_DESC")
    private String skillTpDesc;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skillTpId", fetch = FetchType.LAZY)
    private List<Skill> skillList;

    public SkillTypology() {
    }

    public SkillTypology(Long skillTpId) {
        this.skillTpId = skillTpId;
    }

    public SkillTypology(Long skillTpId, Date creationDate) {
        this.skillTpId = skillTpId;
        this.creationDate = creationDate;
    }

    public Long getSkillTpId() {
        return skillTpId;
    }

    public void setSkillTpId(Long skillTpId) {
        this.skillTpId = skillTpId;
    }

    public String getSkillTpDesc() {
        return skillTpDesc;
    }

    public void setSkillTpDesc(String skillTpDesc) {
        this.skillTpDesc = skillTpDesc;
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

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillTpId != null ? skillTpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillTypology)) {
            return false;
        }
        SkillTypology other = (SkillTypology) object;
        if ((this.skillTpId == null && other.skillTpId != null) || (this.skillTpId != null && !this.skillTpId.equals(other.skillTpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SkillTypology[ skillTpId=" + skillTpId + " ]";
    }

}
