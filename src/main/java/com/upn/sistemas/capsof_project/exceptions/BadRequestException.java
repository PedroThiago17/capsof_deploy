package com.upn.sistemas.capsof_project.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.upn.sistemas.capsof_project.service.dto.ErrorDto;

public class BadRequestException extends CapsofException {

	private static final long serialVersionUID = 6009136896133518232L;

	public BadRequestException(final String code, final String message) {
		super(code, HttpStatus.BAD_REQUEST.value(), message);
	}

	public BadRequestException(final String code, final String message, final ErrorDto data) {
		super(code, HttpStatus.BAD_REQUEST.value(), message, Arrays.asList(data));
	}

}
