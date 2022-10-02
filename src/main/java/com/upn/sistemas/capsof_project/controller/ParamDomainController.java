package com.upn.sistemas.capsof_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.service.IParamDomainService;
import com.upn.sistemas.capsof_project.service.dto.ParamDomainDTO;
import com.upn.sistemas.capsof_project.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping("param")
@Api(tags = "ParamDomainApi", value = "/param")
public class ParamDomainController {

	@Autowired
	private IParamDomainService paramDomainService;

	@GetMapping(value = "/findByCode")
	@ApiOperation(value = Constants.PROFILE_API_OP_POST)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> getDomainsByCod(@RequestParam String codDomain) throws CapsofException {
		final List<ParamDomainDTO> response = this.paramDomainService.getDomainsByCod(codDomain);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
	}

}
