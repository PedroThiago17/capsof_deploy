package com.upn.sistemas.capsof_project.service;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.service.dto.ProfileDTO;
import com.upn.sistemas.capsof_project.service.dto.ProfileSaveDTO;

public interface IProfileService {

	public ProfileDTO addProfile(ProfileSaveDTO profileSaveDTO) throws CapsofException;

}
