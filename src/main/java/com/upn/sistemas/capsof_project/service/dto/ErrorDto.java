package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;

public class ErrorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String value;

	public ErrorDto(final String name, final String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
