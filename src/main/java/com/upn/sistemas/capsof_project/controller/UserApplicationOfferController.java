package com.upn.sistemas.capsof_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.service.IUserApplicationOfferService;
import com.upn.sistemas.capsof_project.service.dto.UserApplicationOfferDTO;
import com.upn.sistemas.capsof_project.service.dto.UserApplicationOfferSaveDTO;
import com.upn.sistemas.capsof_project.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping("userApplication")
@Api(tags = "UserApplicationApi", value = "/userApplication")
public class UserApplicationOfferController {

	@Autowired
	IUserApplicationOfferService userApplicationOfferService;

	@PostMapping(value = "/apply")
	@ApiOperation(value = Constants.USER_APPLICATION_OFFER_SAVE)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> applyCompanyOfferByUser(
			@RequestBody UserApplicationOfferSaveDTO userApplicationOfferSaveDTO) throws CapsofException {
		try {
			final UserApplicationOfferDTO response = this.userApplicationOfferService
					.applyCompanyOfferByUser(userApplicationOfferSaveDTO);
			if ("COMPANY_OFFER_NOT_FOUND".equals(response.getResponseStatus())) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
						.body(new String("COMPANY_OFFER_NOT_FOUND"));
			}
			if ("USER_NOT_FOUND".equals(response.getResponseStatus())) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
						.body(new String("USER_NOT_FOUND"));
			}
			if ("OFFER_APPS_MAX".equals(response.getResponseStatus())) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
						.body(new String("OFFER_APPS_MAX"));
			}
			if ("USER_APP_OFFER_SAME_EXIST".equals(response.getResponseStatus())) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
						.body(new String("USER_APP_OFFER_SAME_EXIST"));
			}
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

	@PutMapping(value = "/deApply")
	@ApiOperation(value = Constants.USER_APPLICATION_OFFER_UPDATE)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> deApplyCompanyOfferByUser(
			@RequestBody UserApplicationOfferSaveDTO userApplicationOfferSaveDTO) throws CapsofException {
		try {
			final UserApplicationOfferDTO response = this.userApplicationOfferService
					.deApplyCompanyOfferByUser(userApplicationOfferSaveDTO);
			if ("COMPANY_OFFER_APPLY_NOT_FOUND".equals(response.getResponseStatus())) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
						.body(new String("COMPANY_OFFER_APPLY_NOT_FOUND"));
			}
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

	@GetMapping(value = "/{" + "userId" + "}")
	@ApiOperation(value = Constants.USER_APPLICATION_OFFER_GET)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> retrieveUserApplicationOfferByUser(@PathVariable Long userId) throws CapsofException {
		try {
			final List<UserApplicationOfferDTO> response = this.userApplicationOfferService
					.retrieveUserApplicationOfferByUser(userId);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}
	
	@PutMapping(value = "/user/{userId}/companyOffer/{companyOfferId}")
	@ApiOperation(value = Constants.USER_APPLICATION_CHANGE_STATUS_UPDATE)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> changeStatusUserApplication(@PathVariable Long userId,
			@PathVariable Long companyOfferId, @RequestParam String newStatus) throws CapsofException {
		try {
			final UserApplicationOfferDTO response = this.userApplicationOfferService
					.changeStatusUserApplication(companyOfferId, userId, newStatus);
			if ("COMPANY_OFFER_APPLY_NOT_FOUND".equals(response.getResponseStatus())) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
						.body(new String("COMPANY_OFFER_APPLY_NOT_FOUND"));
			}
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
					.body(e.getLocalizedMessage());
		}
	}

}
