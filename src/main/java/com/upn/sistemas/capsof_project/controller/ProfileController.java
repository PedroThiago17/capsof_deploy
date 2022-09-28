package com.upn.sistemas.capsof_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.service.IProfileService;
import com.upn.sistemas.capsof_project.service.dto.ProfileDTO;
import com.upn.sistemas.capsof_project.service.dto.ProfileSaveDTO;
import com.upn.sistemas.capsof_project.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("profile")
@Api(tags = "ProfileApi", value = "/profile")
public class ProfileController {

	@Autowired
	private IProfileService profileService;

	@PostMapping(value = "/addProfile")
	@ApiOperation(value = Constants.PROFILE_API_OP_POST)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> addProfile(@Validated @RequestBody ProfileSaveDTO profileSaveDTO)
			throws CapsofException {
		final ProfileDTO response = this.profileService.addProfile(profileSaveDTO);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@PutMapping(value = "/updateProfile")
	@ApiOperation(value = Constants.PROFILE_API_OP_PUT)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> updateProfile(@Validated @RequestBody ProfileSaveDTO profileSaveDTO)
			throws CapsofException {
		final ProfileDTO response = this.profileService.updateProfile(profileSaveDTO);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@DeleteMapping(value = "/deleteProfile/{profileId}")
	@ApiOperation(value = Constants.PROFILE_API_OP_DELETE)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> deleteProfile(@PathVariable Long profileId) throws CapsofException {
		final String response = this.profileService.deleteProfile(profileId);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

	@GetMapping(value = "/findProfiles/user/{userId}")
	@ApiOperation(value = Constants.PROFILE_API_OP_DELETE)
	@ApiResponses({ @ApiResponse(code = 201, message = Constants.HTTP_TEXT_201),
			@ApiResponse(code = 400, message = Constants.HTTP_TEXT_400),
			@ApiResponse(code = 401, message = Constants.HTTP_TEXT_401),
			@ApiResponse(code = 403, message = Constants.HTTP_TEXT_403),
			@ApiResponse(code = 500, message = Constants.HTTP_TEXT_500) })
	public ResponseEntity<Object> findProfilesByUserId(@PathVariable Long userId) throws CapsofException {
		final List<ProfileDTO> response = this.profileService.findProfilesByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

}
