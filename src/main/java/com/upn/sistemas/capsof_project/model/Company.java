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
@Table(name = "COMPANY")
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByCompanyId", query = "SELECT c FROM Company c WHERE c.companyId = :companyId"),
    @NamedQuery(name = "Company.findByCompanyRuc", query = "SELECT c FROM Company c WHERE c.companyRuc = :companyRuc"),
    @NamedQuery(name = "Company.findByCompanyCode", query = "SELECT c FROM Company c WHERE c.companyCode = :companyCode"),
    @NamedQuery(name = "Company.findByCompanyPass", query = "SELECT c FROM Company c WHERE c.companyPass = :companyPass"),
    @NamedQuery(name = "Company.findByCompanyName", query = "SELECT c FROM Company c WHERE c.companyName = :companyName"),
    @NamedQuery(name = "Company.findByCompanyState", query = "SELECT c FROM Company c WHERE c.companyState = :companyState"),
    @NamedQuery(name = "Company.findByCompanyEmail", query = "SELECT c FROM Company c WHERE c.companyEmail = :companyEmail"),
    @NamedQuery(name = "Company.findByCreationDate", query = "SELECT c FROM Company c WHERE c.creationDate = :creationDate"),
    @NamedQuery(name = "Company.findByUpdateDate", query = "SELECT c FROM Company c WHERE c.updateDate = :updateDate")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COMPANY_ID")
    private Long companyId;
    @Basic(optional = false)
    @Column(name = "COMPANY_RUC")
    private String companyRuc;
    @Basic(optional = false)
    @Column(name = "COMPANY_CODE")
    private String companyCode;
    @Basic(optional = false)
    @Column(name = "COMPANY_PASS")
    private String companyPass;
    @Basic(optional = false)
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Basic(optional = false)
    @Column(name = "COMPANY_STATE")
    private String companyState;
    @Basic(optional = false)
    @Column(name = "COMPANY_EMAIL")
    private String companyEmail;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId", fetch = FetchType.LAZY)
    private List<CompanyOffer> companyOfferList;

    public Company() {
    }

    public Company(Long companyId) {
        this.companyId = companyId;
    }

    public Company(Long companyId, String companyRuc, String companyCode, String companyPass, String companyName, String companyState, String companyEmail, Date creationDate) {
        this.companyId = companyId;
        this.companyRuc = companyRuc;
        this.companyCode = companyCode;
        this.companyPass = companyPass;
        this.companyName = companyName;
        this.companyState = companyState;
        this.companyEmail = companyEmail;
        this.creationDate = creationDate;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyRuc() {
        return companyRuc;
    }

    public void setCompanyRuc(String companyRuc) {
        this.companyRuc = companyRuc;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyPass() {
        return companyPass;
    }

    public void setCompanyPass(String companyPass) {
        this.companyPass = companyPass;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
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

    public List<CompanyOffer> getCompanyOfferList() {
        return companyOfferList;
    }

    public void setCompanyOfferList(List<CompanyOffer> companyOfferList) {
        this.companyOfferList = companyOfferList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyId != null ? companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Company[ companyId=" + companyId + " ]";
    }

}
