package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;
import java.util.Date;

public class ParamDomainDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long domainId;
	private String domainCod;
	private String domainDesc;
	private Integer intValue;
	private Double floatValue;
	private String stringValue;
	private Date creationDate;
	private Date updateDate;

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

}
