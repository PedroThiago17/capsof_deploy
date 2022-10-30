package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.model.CompanyOffer;
import com.upn.sistemas.capsof_project.model.User;
import com.upn.sistemas.capsof_project.model.UserApplications;
import com.upn.sistemas.capsof_project.model.UserApplicationsPK;
import com.upn.sistemas.capsof_project.model.repository.CompanyOfferRepository;
import com.upn.sistemas.capsof_project.model.repository.UserApplicationsRepository;
import com.upn.sistemas.capsof_project.model.repository.UserRepository;
import com.upn.sistemas.capsof_project.service.IUserApplicationOfferService;
import com.upn.sistemas.capsof_project.service.dto.CompanyDTO;
import com.upn.sistemas.capsof_project.service.dto.CompanyOfferDTO;
import com.upn.sistemas.capsof_project.service.dto.UserApplicationOfferDTO;
import com.upn.sistemas.capsof_project.service.dto.UserApplicationOfferSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.UserDTO;

@Service
public class UserApplicationOfferServiceImpl implements IUserApplicationOfferService {

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
			userApplicationOfferDTO.setResponseStatus("COMPANY_OFFER_NOT_FOUND");
			return userApplicationOfferDTO;
		} else {

			if (companyOffer.get().getQuantVacants() == companyOffer.get().getOfferApps()) {
				userApplicationOfferDTO.setResponseStatus("OFFER_APPS_MAX");
				return userApplicationOfferDTO;
			}

			Optional<UserApplications> userApplications = getUserApplicationByCompanyOfferAndUser(
					userApplicationOfferSaveDTO.getOfferId(), userApplicationOfferSaveDTO.getUserId());

			if (userApplications.isPresent()) {
				userApplicationOfferDTO.setResponseStatus("USER_APP_OFFER_SAME_EXIST");
				return userApplicationOfferDTO;
			}

			int quantity = companyOffer.get().getOfferApps();
			companyOffer.get().setOfferApps(quantity + 1);
			CompanyOffer companyOfferSave = companyOfferRepository.save(companyOffer.get());

			mapDataCompanyOfferDTO(userApplicationOfferDTO, companyOfferSave);
		}

		Optional<User> user = getUser(userApplicationOfferSaveDTO.getUserId());

		if (!user.isPresent()) {
			userApplicationOfferDTO.setResponseStatus("USER_NOT_FOUND");
			return userApplicationOfferDTO;
		} else {
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(user.get().getUserId());
			userDTO.setUserDesc(
					Objects.nonNull(user.get().getUserDesc()) ? user.get().getUserDesc() : StringUtils.EMPTY);
			userDTO.setUserDni(user.get().getUserDni());
			userDTO.setUserEmail(user.get().getUserEmail());
			userDTO.setUserLastNames(user.get().getUserLastNames());
			userDTO.setUserNames(user.get().getUserNames());
			userDTO.setUserPhone(
					Objects.nonNull(user.get().getUserPhone()) ? user.get().getUserPhone() : StringUtils.EMPTY);
			userApplicationOfferDTO.setUserDTO(userDTO);
		}

		UserApplicationsPK userApplicationsPK = new UserApplicationsPK();
		userApplicationsPK.setOfferId(userApplicationOfferSaveDTO.getOfferId());
		userApplicationsPK.setUserId(userApplicationOfferSaveDTO.getUserId());

		UserApplications userApplications = new UserApplications();
		userApplications.setUserApplicationsPK(userApplicationsPK);
		userApplications.setApplicationDate(userApplicationOfferSaveDTO.getApplicationDate());
		userApplications.setApplicationState(userApplicationOfferSaveDTO.getApplicationState());

		userApplications = userApplicationsRepository.save(userApplications);
		userApplicationOfferDTO.setApplicationDate(userApplications.getApplicationDate());
		userApplicationOfferDTO.setApplicationState(userApplications.getApplicationState());

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
			Optional<CompanyOffer> companyOffer = getCompanyOffer(userApplicationOfferSaveDTO.getOfferId());
			int quantity = companyOffer.get().getOfferApps();
			CompanyOffer companyOfferSave = new CompanyOffer();

			if (quantity != 0) {
				companyOffer.get().setOfferApps(quantity - 1);
				companyOfferSave = companyOfferRepository.save(companyOffer.get());
			}

			mapDataCompanyOfferDTO(userApplicationOfferDTO, companyOfferSave);
			mapDataUserDTO(userApplicationOfferSaveDTO.getUserId(), userApplicationOfferDTO);

		}

		return userApplicationOfferDTO;
	}

	private void mapDataUserDTO(Long userId, UserApplicationOfferDTO userApplicationOfferDTO) {
		Optional<User> user = getUser(userId);

		if (user.isPresent()) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(user.get().getUserId());
			userDTO.setUserDesc(
					Objects.nonNull(user.get().getUserDesc()) ? user.get().getUserDesc() : StringUtils.EMPTY);
			userDTO.setUserDni(user.get().getUserDni().trim());
			userDTO.setUserEmail(user.get().getUserEmail().trim());
			userDTO.setUserLastNames(user.get().getUserLastNames().trim());
			userDTO.setUserNames(user.get().getUserNames().trim());
			userDTO.setUserPhone(
					Objects.nonNull(user.get().getUserPhone()) ? user.get().getUserPhone().trim() : StringUtils.EMPTY);
			userApplicationOfferDTO.setUserDTO(userDTO);
		}
	}

	private void mapDataCompanyOfferDTO(UserApplicationOfferDTO userApplicationOfferDTO,
			CompanyOffer companyOfferSave) {
		CompanyOfferDTO companyOfferDTO = new CompanyOfferDTO();
		CompanyDTO companyDTO = new CompanyDTO();
		companyOfferDTO.setCompanyOfferId(companyOfferSave.getOfferId());
		companyOfferDTO.setApplicationsOffers(companyOfferSave.getOfferApps());
		companyOfferDTO.setDateExpiry(companyOfferSave.getExpDate());
		companyOfferDTO.setOfferDescription(companyOfferSave.getOfferDesc().trim());
		companyOfferDTO.setOfferTitle(companyOfferSave.getOfferTitle().trim());
		companyOfferDTO.setQuantityVacants(companyOfferSave.getQuantVacants());
		companyOfferDTO.setStatusOffer(companyOfferSave.getOfferState().trim());

		companyDTO.setCompanyId(companyOfferSave.getCompanyId().getCompanyId());
		companyDTO.setCompanyEmail(companyOfferSave.getCompanyId().getCompanyEmail().trim());
		companyDTO.setCompanyCode(companyOfferSave.getCompanyId().getCompanyCode().trim());
		companyDTO.setCompanyName(companyOfferSave.getCompanyId().getCompanyName().trim());
		companyDTO.setCompanyRuc(companyOfferSave.getCompanyId().getCompanyRuc().trim());
		companyDTO.setCompanyState(companyOfferSave.getCompanyId().getCompanyState().trim());

		companyOfferDTO.setCompanyDTO(companyDTO);
		userApplicationOfferDTO.setCompanyOfferDTO(companyOfferDTO);
	}

	private Optional<UserApplications> getUserApplicationByCompanyOfferAndUser(Long companyOfferId, Long userId) {
		return userApplicationsRepository.findByUserApplicationsPK_OfferIdAndUserApplicationsPK_UserId(companyOfferId,
				userId);
	}

	@Override
	public List<UserApplicationOfferDTO> retrieveUserApplicationOfferByUser(Long userId) {

		List<UserApplicationOfferDTO> userApplicationOfferDTOs = new ArrayList<>();
		UserApplicationOfferDTO userApplicationOfferDTO = new UserApplicationOfferDTO();

		List<UserApplications> userApplications = userApplicationsRepository.findByUserApplicationsPK_UserId(userId);
		Optional<CompanyOffer> companyOffer = Optional.empty();

		for (UserApplications userApplicationsIterable : userApplications) {
			userApplicationOfferDTO = new UserApplicationOfferDTO();
			userApplicationOfferDTO = this.maper.map(userApplicationsIterable, UserApplicationOfferDTO.class);
			companyOffer = getCompanyOffer(userApplicationsIterable.getUserApplicationsPK().getOfferId());
			if (companyOffer.isPresent()) {
				mapDataCompanyOfferDTO(userApplicationOfferDTO, companyOffer.get());
			}
			mapDataUserDTO(userId, userApplicationOfferDTO);
			userApplicationOfferDTOs.add(userApplicationOfferDTO);
		}

		return userApplicationOfferDTOs;
	}

	@Override
	public List<UserApplicationOfferDTO> retrieveUserApplicationOfferByCompanyId(Long companyId)
			throws CapsofException {
		
		
		//Show profiles apply for each companyOffer
		
		return null;
	}

}
