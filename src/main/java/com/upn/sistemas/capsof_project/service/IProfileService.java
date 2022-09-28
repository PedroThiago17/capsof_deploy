package com.upn.sistemas.capsof_project.service;

import java.util.List;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.service.dto.ProfileDTO;
import com.upn.sistemas.capsof_project.service.dto.ProfileSaveDTO;

public interface IProfileService {

	public ProfileDTO addProfile(ProfileSaveDTO profileSaveDTO) throws CapsofException;

	public ProfileDTO updateProfile(ProfileSaveDTO profileSaveDTO) throws CapsofException;

	public String deleteProfile(Long profileId) throws CapsofException;

	public List<ProfileDTO> findProfilesByUserId(Long userId) throws CapsofException;

}
