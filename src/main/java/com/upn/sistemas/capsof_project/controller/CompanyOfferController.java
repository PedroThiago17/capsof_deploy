package com.upn.sistemas.capsof_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.service.ICompanyOfferService;
import com.upn.sistemas.capsof_project.service.dto.CompanyOfferDTO;
import com.upn.sistemas.capsof_project.service.dto.CompanyOfferSaveDTO;
import com.upn.sistemas.capsof_project.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping("companyOffer")
@Api(tags = "CompanyOfferApi", value = "/companyOffer")
public class CompanyOfferController {

	@Autowired
	private ICompanyOfferService companyOfferService;

	@PostMapping(value = "/addCompanyOffer")
	@ApiOperation(value = Constants.PROFILE_API_OP_POST)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> addCompanyOffer(@Validated @RequestBody CompanyOfferSaveDTO companyOfferSaveDTO)
			throws CapsofException {
		final CompanyOfferDTO response = this.companyOfferService.addCompanyOffer(companyOfferSaveDTO);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@PutMapping(value = "/updateCompanyOffer")
	@ApiOperation(value = Constants.PROFILE_API_OP_POST)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> updateCompanyOffer(@Validated @RequestBody CompanyOfferSaveDTO companyOfferSaveDTO)
			throws CapsofException {
		final CompanyOfferDTO response = this.companyOfferService.updateCompanyOffer(companyOfferSaveDTO);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@DeleteMapping(value = "/deleteCompanyOffer/{companyOfferId}")
	@ApiOperation(value = Constants.PROFILE_API_OP_POST)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> deleteCompanyOffer(@PathVariable Long companyOfferId) throws CapsofException {
		final String response = this.companyOfferService.deleteCompanyOffer(companyOfferId);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@GetMapping(value = "/findCompanyOffer/company/{companyId}")
	@ApiOperation(value = Constants.PROFILE_API_OP_POST)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> findCompanyOfferByCompanyId(@PathVariable Long companyId) throws CapsofException {
		final List<CompanyOfferDTO> response = this.companyOfferService.findCompanyOffersByCompanyId(companyId);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

}
