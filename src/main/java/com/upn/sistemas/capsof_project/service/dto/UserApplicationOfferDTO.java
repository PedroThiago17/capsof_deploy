package com.upn.sistemas.capsof_project.service.dto;

import java.util.Date;
import java.util.List;

public class UserApplicationOfferDTO {

	private Date applicationDate;

	private Date desapplicationDate;

	private String applicationState;
	
	private Integer percentageSimilarity;

	private CompanyOfferDTO companyOfferDTO;

	private UserDTO userDTO;
	
	private List<ProfileDTO> profilesDto;

	private String responseStatus;

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getDesapplicationDate() {
		return desapplicationDate;
	}

	public void setDesapplicationDate(Date desapplicationDate) {
		this.desapplicationDate = desapplicationDate;
	}

	public String getApplicationState() {
		return applicationState;
	}

	public void setApplicationState(String applicationState) {
		this.applicationState = applicationState;
	}

	public Integer getPercentageSimilarity() {
		return percentageSimilarity;
	}

	public void setPercentageSimilarity(Integer percentageSimilarity) {
		this.percentageSimilarity = percentageSimilarity;
	}

	public CompanyOfferDTO getCompanyOfferDTO() {
		return companyOfferDTO;
	}

	public void setCompanyOfferDTO(CompanyOfferDTO companyOfferDTO) {
		this.companyOfferDTO = companyOfferDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public List<ProfileDTO> getProfilesDto() {
		return profilesDto;
	}

	public void setProfilesDto(List<ProfileDTO> profilesDto) {
		this.profilesDto = profilesDto;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

}
