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
@Table(name = "COMPANY_OFFER")
@NamedQueries({
    @NamedQuery(name = "CompanyOffer.findAll", query = "SELECT c FROM CompanyOffer c"),
    @NamedQuery(name = "CompanyOffer.findByOfferId", query = "SELECT c FROM CompanyOffer c WHERE c.offerId = :offerId"),
    @NamedQuery(name = "CompanyOffer.findByOfferTitle", query = "SELECT c FROM CompanyOffer c WHERE c.offerTitle = :offerTitle"),
    @NamedQuery(name = "CompanyOffer.findByOfferDesc", query = "SELECT c FROM CompanyOffer c WHERE c.offerDesc = :offerDesc"),
    @NamedQuery(name = "CompanyOffer.findByQuantVacants", query = "SELECT c FROM CompanyOffer c WHERE c.quantVacants = :quantVacants"),
    @NamedQuery(name = "CompanyOffer.findByOfferApps", query = "SELECT c FROM CompanyOffer c WHERE c.offerApps = :offerApps"),
    @NamedQuery(name = "CompanyOffer.findByExpDate", query = "SELECT c FROM CompanyOffer c WHERE c.expDate = :expDate"),
    @NamedQuery(name = "CompanyOffer.findByOfferState", query = "SELECT c FROM CompanyOffer c WHERE c.offerState = :offerState"),
    @NamedQuery(name = "CompanyOffer.findByCreationDate", query = "SELECT c FROM CompanyOffer c WHERE c.creationDate = :creationDate"),
    @NamedQuery(name = "CompanyOffer.findByUpdateDate", query = "SELECT c FROM CompanyOffer c WHERE c.updateDate = :updateDate")})
public class CompanyOffer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OFFER_ID")
    private Long offerId;
    @Basic(optional = false)
    @Column(name = "OFFER_TITLE")
    private String offerTitle;
    @Basic(optional = false)
    @Column(name = "OFFER_DESC")
    private String offerDesc;
    @Basic(optional = false)
    @Column(name = "QUANT_VACANTS")
    private int quantVacants;
    @Basic(optional = false)
    @Column(name = "OFFER_APPS")
    private int offerApps;
    @Basic(optional = false)
    @Column(name = "EXP_DATE")
    @Temporal(TemporalType.DATE)
    private Date expDate;
    @Basic(optional = false)
    @Column(name = "OFFER_STATE")
    private String offerState;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyOffer", fetch = FetchType.LAZY)
    private List<OfferSkills> offerSkillsList;
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Company companyId;
    @JoinColumn(name = "DOM_TP_PROFILE_ID", referencedColumnName = "DOMAIN_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParamDomain domTpProfileId;
    @JoinColumn(name = "DOM_EXP_ID", referencedColumnName = "DOMAIN_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParamDomain domExpId;

    public CompanyOffer() {
    }

    public CompanyOffer(Long offerId) {
        this.offerId = offerId;
    }

    public CompanyOffer(Long offerId, String offerTitle, String offerDesc, int quantVacants, int offerApps, Date expDate, String offerState, Date creationDate) {
        this.offerId = offerId;
        this.offerTitle = offerTitle;
        this.offerDesc = offerDesc;
        this.quantVacants = quantVacants;
        this.offerApps = offerApps;
        this.expDate = expDate;
        this.offerState = offerState;
        this.creationDate = creationDate;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }

    public int getQuantVacants() {
        return quantVacants;
    }

    public void setQuantVacants(int quantVacants) {
        this.quantVacants = quantVacants;
    }

    public int getOfferApps() {
        return offerApps;
    }

    public void setOfferApps(int offerApps) {
        this.offerApps = offerApps;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getOfferState() {
        return offerState;
    }

    public void setOfferState(String offerState) {
        this.offerState = offerState;
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

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (offerId != null ? offerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyOffer)) {
            return false;
        }
        CompanyOffer other = (CompanyOffer) object;
        if ((this.offerId == null && other.offerId != null) || (this.offerId != null && !this.offerId.equals(other.offerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CompanyOffer[ offerId=" + offerId + " ]";
    }

}
