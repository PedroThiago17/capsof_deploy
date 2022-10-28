package com.upn.sistemas.capsof_project.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upn.sistemas.capsof_project.model.CompanyOffer;
import com.upn.sistemas.capsof_project.model.User;
import com.upn.sistemas.capsof_project.model.UserApplications;
import com.upn.sistemas.capsof_project.model.UserApplicationsPK;
import com.upn.sistemas.capsof_project.model.repository.CompanyOfferRepository;
import com.upn.sistemas.capsof_project.model.repository.UserApplicationsRepository;
import com.upn.sistemas.capsof_project.model.repository.UserRepository;
import com.upn.sistemas.capsof_project.service.UserApplicationOfferService;
import com.upn.sistemas.capsof_project.service.dto.CompanyOfferDTO;
import com.upn.sistemas.capsof_project.service.dto.UserApplicationOfferDTO;
import com.upn.sistemas.capsof_project.service.dto.UserApplicationOfferSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.UserDTO;

@Service
public class UserApplicationOfferServiceImpl implements UserApplicationOfferService {

	@Autowired
	UserApplicationsRepository userApplicationsRepository;

	@Autowired
	CompanyOfferRepository companyOfferRepository;

	@Autowired
	UserRepository userRepository;

	private ModelMapper maper = new ModelMapper();

	@Override
	public UserApplicationOfferDTO applyCompanyOfferByUser(UserApplicationOfferSaveDTO userApplicationOfferSaveDTO) {

		UserApplicationOfferDTO userApplicationOfferDTO = new UserApplicationOfferDTO();

		Optional<CompanyOffer> companyOffer = getCompanyOffer(userApplicationOfferSaveDTO.getOfferId());

		if (!companyOffer.isPresent()) {
			userApplicationOfferDTO.setResponseStatus("COMPANY_NOT_FOUND");
			return userApplicationOfferDTO;
		} else {
			userApplicationOfferDTO.setCompanyOfferDTO(this.maper.map(companyOffer.get(), CompanyOfferDTO.class));
		}

		Optional<User> user = getUser(userApplicationOfferSaveDTO.getUserId());

		if (!user.isPresent()) {
			userApplicationOfferDTO.setResponseStatus("USER_NOT_FOUND");
			return userApplicationOfferDTO;
		} else {
			userApplicationOfferDTO.setUserDTO(this.maper.map(user.get(), UserDTO.class));
		}
		
		//TODO set quantity vacant

		UserApplicationsPK userApplicationsPK = new UserApplicationsPK();
		userApplicationsPK.setOfferId(userApplicationOfferSaveDTO.getOfferId());
		userApplicationsPK.setUserId(userApplicationOfferSaveDTO.getUserId());

		UserApplications userApplications = new UserApplications();
		userApplications.setUserApplicationsPK(userApplicationsPK);
		userApplications.setApplicationDate(userApplicationOfferSaveDTO.getApplicationDate());
		userApplications.setApplicationState(userApplicationOfferSaveDTO.getApplicationState());

		userApplications = userApplicationsRepository.save(userApplications);
		userApplicationOfferDTO = this.maper.map(userApplications, UserApplicationOfferDTO.class);

		return userApplicationOfferDTO;
	}

	private Optional<CompanyOffer> getCompanyOffer(Long companyOfferId) {
		return companyOfferRepository.findById(companyOfferId);
	}

	private Optional<User> getUser(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public UserApplicationOfferDTO deApplyCompanyOfferByUser(UserApplicationOfferSaveDTO userApplicationOfferSaveDTO) {

		UserApplicationOfferDTO userApplicationOfferDTO = new UserApplicationOfferDTO();

		Optional<UserApplications> userApplications = getUserApplicationByCompanyOfferAndUser(
				userApplicationOfferSaveDTO.getOfferId(), userApplicationOfferSaveDTO.getUserId());

		if (!userApplications.isPresent()) {
			userApplicationOfferDTO.setResponseStatus("COMPANY_OFFER_APPLY_NOT_FOUND");
			return userApplicationOfferDTO;
		} else {
			UserApplications userApplication = userApplications.get();
			userApplication.setDesapplicationDate(userApplicationOfferSaveDTO.getDesapplicationDate());
			userApplication.setApplicationState(userApplicationOfferSaveDTO.getApplicationState());
			userApplication = userApplicationsRepository.save(userApplication);
			userApplicationOfferDTO = this.maper.map(userApplication, UserApplicationOfferDTO.class);
			//TODO set quantity vacant
		}

		return userApplicationOfferDTO;
	}

	private Optional<UserApplications> getUserApplicationByCompanyOfferAndUser(Long companyOfferId, Long userId) {
		return userApplicationsRepository.findByUserApplicationsPK_OfferIdAndUserApplicationsPK_UserId(companyOfferId,
				userId);
	}

}
