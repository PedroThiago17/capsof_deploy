package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upn.sistemas.capsof_project.model.UserType;
import com.upn.sistemas.capsof_project.model.repository.UserTypeRepository;
import com.upn.sistemas.capsof_project.service.IUserTypeService;
import com.upn.sistemas.capsof_project.service.dto.UserTypeDTO;
import com.upn.sistemas.capsof_project.service.dto.UserTypeSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.UserTypeUpdateDTO;
import com.upn.sistemas.capsof_project.utils.Constants;

@Service
@Transactional
public class UserTypeServiceImpl implements IUserTypeService {

	private ModelMapper mapper = new ModelMapper();
	private Calendar calendar = Calendar.getInstance();

	@Autowired
	UserTypeRepository userTypeRepository;

	private UserType validateUserType(int idUserType) {
		final Optional<UserType> userType = userTypeRepository.findById(Long.valueOf(idUserType));
		if (userType.isEmpty() || userType.get().getUserTpState().equals(Constants.FALSE_VALUE)) {
			return null;
		}
		return userType.get();
	}

	@Override
	public List<UserTypeDTO> findAll() {

		final List<UserTypeDTO> result = new ArrayList<>();
		List<UserType> userTypeList = userTypeRepository.findAll();

		for (UserType userType : userTypeList) {
			if (userType.getUserTpState().trim().toUpperCase().equals(Constants.TRUE_VALUE.trim().toUpperCase())) {
				result.add(this.mapper.map(userType, UserTypeDTO.class));
			}
		}
		return result;
	}

	@Override
	public UserTypeDTO saveUserType(UserTypeSaveDTO userType) {

		UserType userTypeModel = this.mapper.map(userType, UserType.class);
		userTypeModel.setUserTpState(Constants.TRUE_VALUE);
		userTypeModel.setCreationDate(this.calendar.getTime());

		final UserType userTypeSaved = userTypeRepository.save(userTypeModel);
		return this.mapper.map(userTypeSaved, UserTypeDTO.class);
	}

	@Override
	public UserTypeDTO updateUserType(UserTypeUpdateDTO userType) {
		final UserType userTypeSaved = validateUserType(userType.getUserTpId());

		if (!Objects.isNull(userTypeSaved)) {
			userTypeSaved.setUserTpDesc(userType.getUserTpDesc());
			userTypeSaved.setUpdateDate(this.calendar.getTime());
			return this.mapper.map(userTypeRepository.save(userTypeSaved), UserTypeDTO.class);
		}
		return null;
	}

	@Override
	public String deleteUserType(int idUserType) {
		UserType userTypeSaved = validateUserType(idUserType);
		if (!Objects.isNull(userTypeSaved)) {
			userTypeSaved.setUpdateDate(this.calendar.getTime());
			userTypeSaved.setUserTpState(Constants.FALSE_VALUE);
			userTypeSaved = userTypeRepository.save(userTypeSaved);
			return "UserType deleted";
		}
		return "The UserType is no registred in database or already deleted";
	}

}
