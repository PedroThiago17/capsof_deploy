package com.upn.sistemas.capsof_project.service.dto;

import java.util.Date;
import java.util.List;

public class CompanyOfferDTO {

	private Long companyOfferId;
	private Long companyId;
	private String offerTitle;
	private String offerDescription;
	private Integer quantityVacants;
	private Integer applicationsOffers;
	private Date dateExpiry;
	private String statusOffer;
	private ParamDomainDTO domTpPerfil;
	private ParamDomainDTO domExpe;
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

	public ParamDomainDTO getDomTpPerfil() {
		return domTpPerfil;
	}

	public void setDomTpPerfil(ParamDomainDTO domTpPerfil) {
		this.domTpPerfil = domTpPerfil;
	}

	public ParamDomainDTO getDomExpe() {
		return domExpe;
	}

	public void setDomExpe(ParamDomainDTO domExpe) {
		this.domExpe = domExpe;
	}

	public List<SkillDTO> getSkillsDTO() {
		return skillsDTO;
	}

	public void setSkillsDTO(List<SkillDTO> skillsDTO) {
		this.skillsDTO = skillsDTO;
	}

}
