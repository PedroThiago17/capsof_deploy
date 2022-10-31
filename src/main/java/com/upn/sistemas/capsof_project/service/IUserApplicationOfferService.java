package com.upn.sistemas.capsof_project.service;

import java.util.List;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.service.dto.UserApplicationOfferDTO;
import com.upn.sistemas.capsof_project.service.dto.UserApplicationOfferSaveDTO;

public interface IUserApplicationOfferService {

	UserApplicationOfferDTO applyCompanyOfferByUser(UserApplicationOfferSaveDTO userApplicationOfferSaveDTO);

	UserApplicationOfferDTO deApplyCompanyOfferByUser(UserApplicationOfferSaveDTO userApplicationOfferSaveDTO);

	List<UserApplicationOfferDTO> retrieveUserApplicationOfferByUser(Long userId);

	List<UserApplicationOfferDTO> retrieveUserApplicationByOfferId(Long offerId) throws CapsofException;

}
