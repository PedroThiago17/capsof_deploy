package com.upn.sistemas.capsof_project.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.upn.sistemas.capsof_project.service.dto.ErrorDto;

public class CapsofException extends Exception {

	private static final long serialVersionUID = 8464020427565401319L;

	private final String code;

	private final int responseCode;

	private final List<ErrorDto> errorList = new ArrayList<>();

	public CapsofException(final String code, final int responseCode, final String message) {
		this (code, responseCode, message, null, null);
	}
		
	public CapsofException(final String code, final int responseCode, final String message, Throwable e) {
		this(code, responseCode, message, null, e);
	}

	public CapsofException(final String code, final int responseCode, final String message,
			final List<ErrorDto> errorList) {
		this(code, responseCode, message, errorList, null);
	}
	
	public CapsofException(final String code, final int responseCode, final String message,
			final List<ErrorDto> errorList, Throwable e) {
		super(message, e);
		this.code = code;
		this.responseCode = responseCode;
		if (Objects.nonNull(errorList)) {
			this.errorList.addAll(errorList);
		}
	}

	public String getCode() {
		return this.code;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public List<ErrorDto> getErrorList() {
		return errorList;
	}
	
}
