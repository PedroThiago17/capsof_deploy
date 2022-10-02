package com.upn.sistemas.capsof_project.service.dto;

import java.util.Date;
import java.util.List;

public class CompanyOfferSaveDTO {

	private Long companyOfferId;
	private Long companyId;
	private String offerTitle;
	private String offerDescription;
	private Integer quantityVacants;
	private Integer applicationsOffers;
	private Date dateExpiry;
	private String statusOffer;
	private Long domTpProfileId;
	private Long domExpeId;
	private List<SkillDTO> skillsDTO;

	public Long getCompanyOfferId() {
		return companyOfferId;
	}

	public void setCompanyOfferId(Long companyOfferId) {
		this.companyOfferId = companyOfferId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getOfferTitle() {
		return offerTitle;
	}

	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public Integer getQuantityVacants() {
		return quantityVacants;
	}

	public void setQuantityVacants(Integer quantityVacants) {
		this.quantityVacants = quantityVacants;
	}

	public Integer getApplicationsOffers() {
		return applicationsOffers;
	}

	public void setApplicationsOffers(Integer applicationsOffers) {
		this.applicationsOffers = applicationsOffers;
	}

	public Date getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public String getStatusOffer() {
		return statusOffer;
	}

	public void setStatusOffer(String statusOffer) {
		this.statusOffer = statusOffer;
	}

	public Long getDomTpProfileId() {
		return domTpProfileId;
	}

	public void setDomTpProfileId(Long domTpProfileId) {
		this.domTpProfileId = domTpProfileId;
	}

	public Long getDomExpeId() {
		return domExpeId;
	}

	public void setDomExpeId(Long domExpeId) {
		this.domExpeId = domExpeId;
	}

	public List<SkillDTO> getSkillsDTO() {
		return skillsDTO;
	}

	public void setSkillsDTO(List<SkillDTO> skillsDTO) {
		this.skillsDTO = skillsDTO;
	}

}
