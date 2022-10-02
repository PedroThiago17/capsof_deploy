package com.upn.sistemas.capsof_project.service.dto;

public class CompanyDTO {

	private Long companyId;
	private String companyRuc;
	private String companyCode;
	private String companyName;
	private String companyState;
	private String companyEmail;

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

}
