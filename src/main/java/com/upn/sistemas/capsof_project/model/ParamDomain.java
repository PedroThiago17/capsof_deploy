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
@Table(name = "PARAM_DOMAIN")
@NamedQueries({
    @NamedQuery(name = "ParamDomain.findAll", query = "SELECT p FROM ParamDomain p"),
    @NamedQuery(name = "ParamDomain.findByDomainId", query = "SELECT p FROM ParamDomain p WHERE p.domainId = :domainId"),
    @NamedQuery(name = "ParamDomain.findByDomainCod", query = "SELECT p FROM ParamDomain p WHERE p.domainCod = :domainCod"),
    @NamedQuery(name = "ParamDomain.findByDomainDesc", query = "SELECT p FROM ParamDomain p WHERE p.domainDesc = :domainDesc"),
    @NamedQuery(name = "ParamDomain.findByIntValue", query = "SELECT p FROM ParamDomain p WHERE p.intValue = :intValue"),
    @NamedQuery(name = "ParamDomain.findByFloatValue", query = "SELECT p FROM ParamDomain p WHERE p.floatValue = :floatValue"),
    @NamedQuery(name = "ParamDomain.findByStringValue", query = "SELECT p FROM ParamDomain p WHERE p.stringValue = :stringValue"),
    @NamedQuery(name = "ParamDomain.findByCreationDate", query = "SELECT p FROM ParamDomain p WHERE p.creationDate = :creationDate"),
    @NamedQuery(name = "ParamDomain.findByUpdateDate", query = "SELECT p FROM ParamDomain p WHERE p.updateDate = :updateDate")})
public class ParamDomain implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DOMAIN_ID")
    private Long domainId;
    @Basic(optional = false)
    @Column(name = "DOMAIN_COD")
    private String domainCod;
    @Basic(optional = false)
    @Column(name = "DOMAIN_DESC")
    private String domainDesc;
    @Column(name = "INT_VALUE")
    private Integer intValue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FLOAT_VALUE")
    private Double floatValue;
    @Column(name = "STRING_VALUE")
    private String stringValue;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "domTpProfileId", fetch = FetchType.LAZY)
    private List<Profile> profileList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "domExpId", fetch = FetchType.LAZY)
    private List<Profile> profileList1;
    @OneToMany(mappedBy = "domainParentId", fetch = FetchType.LAZY)
    private List<ParamDomain> paramDomainList;
    @JoinColumn(name = "DOMAIN_PARENT_ID", referencedColumnName = "DOMAIN_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ParamDomain domainParentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "domTpProfileId", fetch = FetchType.LAZY)
    private List<CompanyOffer> companyOfferList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "domExpId", fetch = FetchType.LAZY)
    private List<CompanyOffer> companyOfferList1;

    public ParamDomain() {
    }

    public ParamDomain(Long domainId) {
        this.domainId = domainId;
    }

    public ParamDomain(Long domainId, String domainCod, String domainDesc, Date creationDate) {
        this.domainId = domainId;
        this.domainCod = domainCod;
        this.domainDesc = domainDesc;
        this.creationDate = creationDate;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getDomainCod() {
        return domainCod;
    }

    public void setDomainCod(String domainCod) {
        this.domainCod = domainCod;
    }

    public String getDomainDesc() {
        return domainDesc;
    }

    public void setDomainDesc(String domainDesc) {
        this.domainDesc = domainDesc;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Double getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(Double floatValue) {
        this.floatValue = floatValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
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

    public List<Profile> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<Profile> profileList) {
        this.profileList = profileList;
    }

    public List<Profile> getProfileList1() {
        return profileList1;
    }

    public void setProfileList1(List<Profile> profileList1) {
        this.profileList1 = profileList1;
    }

    public List<ParamDomain> getParamDomainList() {
        return paramDomainList;
    }

    public void setParamDomainList(List<ParamDomain> paramDomainList) {
        this.paramDomainList = paramDomainList;
    }

    public ParamDomain getDomainParentId() {
        return domainParentId;
    }

    public void setDomainParentId(ParamDomain domainParentId) {
        this.domainParentId = domainParentId;
    }

    public List<CompanyOffer> getCompanyOfferList() {
        return companyOfferList;
    }

    public void setCompanyOfferList(List<CompanyOffer> companyOfferList) {
        this.companyOfferList = companyOfferList;
    }

    public List<CompanyOffer> getCompanyOfferList1() {
        return companyOfferList1;
    }

    public void setCompanyOfferList1(List<CompanyOffer> companyOfferList1) {
        this.companyOfferList1 = companyOfferList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (domainId != null ? domainId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParamDomain)) {
            return false;
        }
        ParamDomain other = (ParamDomain) object;
        if ((this.domainId == null && other.domainId != null) || (this.domainId != null && !this.domainId.equals(other.domainId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ParamDomain[ domainId=" + domainId + " ]";
    }

}
