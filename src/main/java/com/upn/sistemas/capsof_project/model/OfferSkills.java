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
@Table(name = "OFFER_SKILLS")
@NamedQueries({
    @NamedQuery(name = "OfferSkills.findAll", query = "SELECT o FROM OfferSkills o"),
    @NamedQuery(name = "OfferSkills.findByOfferId", query = "SELECT o FROM OfferSkills o WHERE o.offerSkillsPK.offerId = :offerId"),
    @NamedQuery(name = "OfferSkills.findBySkillId", query = "SELECT o FROM OfferSkills o WHERE o.offerSkillsPK.skillId = :skillId"),
    @NamedQuery(name = "OfferSkills.findBySkillNivel", query = "SELECT o FROM OfferSkills o WHERE o.skillNivel = :skillNivel"),
    @NamedQuery(name = "OfferSkills.findBySkillRequired", query = "SELECT o FROM OfferSkills o WHERE o.skillRequired = :skillRequired"),
    @NamedQuery(name = "OfferSkills.findByCreationDate", query = "SELECT o FROM OfferSkills o WHERE o.creationDate = :creationDate"),
    @NamedQuery(name = "OfferSkills.findByUpdateDate", query = "SELECT o FROM OfferSkills o WHERE o.updateDate = :updateDate")})
public class OfferSkills implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OfferSkillsPK offerSkillsPK;
    @Basic(optional = false)
    @Column(name = "SKILL_NIVEL")
    private int skillNivel;
    @Basic(optional = false)
    @Column(name = "SKILL_REQUIRED")
    private String skillRequired;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @JoinColumn(name = "OFFER_ID", referencedColumnName = "OFFER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CompanyOffer companyOffer;
    @JoinColumn(name = "SKILL_ID", referencedColumnName = "SKILL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Skill skill;

    public OfferSkills() {
    }

    public OfferSkills(OfferSkillsPK offerSkillsPK) {
        this.offerSkillsPK = offerSkillsPK;
    }

    public OfferSkills(OfferSkillsPK offerSkillsPK, int skillNivel, String skillRequired, Date creationDate) {
        this.offerSkillsPK = offerSkillsPK;
        this.skillNivel = skillNivel;
        this.skillRequired = skillRequired;
        this.creationDate = creationDate;
    }

    public OfferSkills(long offerId, long skillId) {
        this.offerSkillsPK = new OfferSkillsPK(offerId, skillId);
    }

    public OfferSkillsPK getOfferSkillsPK() {
        return offerSkillsPK;
    }

    public void setOfferSkillsPK(OfferSkillsPK offerSkillsPK) {
        this.offerSkillsPK = offerSkillsPK;
    }

    public int getSkillNivel() {
        return skillNivel;
    }

    public void setSkillNivel(int skillNivel) {
        this.skillNivel = skillNivel;
    }

    public String getSkillRequired() {
        return skillRequired;
    }

    public void setSkillRequired(String skillRequired) {
        this.skillRequired = skillRequired;
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

    public CompanyOffer getCompanyOffer() {
        return companyOffer;
    }

    public void setCompanyOffer(CompanyOffer companyOffer) {
        this.companyOffer = companyOffer;
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
        hash += (offerSkillsPK != null ? offerSkillsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfferSkills)) {
            return false;
        }
        OfferSkills other = (OfferSkills) object;
        if ((this.offerSkillsPK == null && other.offerSkillsPK != null) || (this.offerSkillsPK != null && !this.offerSkillsPK.equals(other.offerSkillsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OfferSkills[ offerSkillsPK=" + offerSkillsPK + " ]";
    }

}
