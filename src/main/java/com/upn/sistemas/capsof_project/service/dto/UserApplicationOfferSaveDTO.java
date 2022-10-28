package com.upn.sistemas.capsof_project.service.dto;

import java.util.Date;

public class UserApplicationOfferSaveDTO {
	
	private long userApplicationId;

    private long offerId;

    private long userId;

    private Date applicationDate;

    private Date desapplicationDate;

    private String applicationState;

	public long getUserApplicationId() {
		return userApplicationId;
	}

	public void setUserApplicationId(long userApplicationId) {
		this.userApplicationId = userApplicationId;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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

}
