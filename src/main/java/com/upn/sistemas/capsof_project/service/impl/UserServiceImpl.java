package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.model.User;
import com.upn.sistemas.capsof_project.model.UserRoles;
import com.upn.sistemas.capsof_project.model.UserRolesPK;
import com.upn.sistemas.capsof_project.model.UserType;
import com.upn.sistemas.capsof_project.model.repository.UserRepository;
import com.upn.sistemas.capsof_project.model.repository.UserRolesRepository;
import com.upn.sistemas.capsof_project.model.repository.UserTypeRepository;
import com.upn.sistemas.capsof_project.service.IUserService;
import com.upn.sistemas.capsof_project.service.dto.UserDTO;
import com.upn.sistemas.capsof_project.service.dto.UserLoginDTO;
import com.upn.sistemas.capsof_project.service.dto.UserSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.UserUpdateDTO;
import com.upn.sistemas.capsof_project.utils.Constants;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	private ModelMapper maper = new ModelMapper();
	private Calendar calendar = Calendar.getInstance();

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Override
	public List<UserDTO> findAll() {

		List<User> userList = userRepository.findAll();
		final List<UserDTO> result = new ArrayList<>();
		UserDTO userDTO;

		for (User user : userList) {
			if (user.getUserState().trim().toUpperCase().equals(Constants.TRUE_VALUE.trim().toUpperCase())) {
				userDTO = maper.map(user, UserDTO.class);
				userDTO.setRoles(user.getUserRolesList().stream().map(r -> r.getUserType().getUserTpDesc())
						.collect(Collectors.toList()));
				result.add(userDTO);
			}
		}
		return result;
	}

	@Override
	public Optional<User> findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public UserDTO addUser(UserSaveDTO userSave) {
		final Optional<UserType> defaultRole = userTypeRepository.findById(Constants.ROLE_USER_VALUE);

		try {
			if (!(findByUserEmail(userSave.getUserEmail()).isPresent())) {
				User userModel = this.maper.map(userSave, User.class);
				userModel.setUserPass(Base64.getEncoder().encodeToString(userSave.getUserPass().getBytes()));
				userModel.setCreationDate(calendar.getTime());
				userModel.setUserState(Constants.TRUE_VALUE.trim());
				userModel = userRepository.save(userModel);

				UserRolesPK userRolesPK = new UserRolesPK(defaultRole.get().getUserTpId(), userModel.getUserId());
				UserRoles roleUserModel = new UserRoles(userRolesPK);
				roleUserModel.setCreationDate(calendar.getTime());
				roleUserModel.setRoleState(Constants.TRUE_VALUE.trim());
				roleUserModel.setUserApp(userModel);
				roleUserModel.setUserType(defaultRole.get());
				roleUserModel = userRolesRepository.save(roleUserModel);

				UserDTO userSaved = this.maper.map(userModel, UserDTO.class);
				userSaved.setRoles(Arrays.asList(defaultRole.get().getUserTpDesc()));
				return userSaved;
			} else {
				return new UserDTO();
			}
		} catch (DataIntegrityViolationException e) {
			return null;
		}
	}

	@Override
	public UserDTO findById(Long idUser) {
		if (!Objects.isNull(idUser)) {
			Optional<User> user = userRepository.findById(idUser);
			UserDTO userDTO = this.maper.map(user.get(), UserDTO.class);
			userDTO.setRoles(user.get().getUserRolesList().stream().map(r -> r.getUserType().getUserTpDesc())
					.collect(Collectors.toList()));
			return userDTO;
		} else {
			return null;
		}
	}

	@Override
	public UserDTO updateUser(UserUpdateDTO userUpdate) {

		Optional<User> userSaved = userRepository.findById(userUpdate.getUserId());

		if (userSaved.isPresent()) {
			User userModel = userSaved.get();
			userModel.setUserNames(userUpdate.getUserNames());
			userModel.setUserLastNames(userUpdate.getUserLastNames());
			userModel.setUserDesc(userUpdate.getUserDesc());
			userModel.setUserEmail(userUpdate.getUserEmail());
			userModel.setUserPhone(userUpdate.getUserPhone());
			userModel.setUpdateDate(calendar.getTime());
			userModel = userRepository.save(userModel);

			UserDTO userDTO = this.maper.map(userModel, UserDTO.class);
			userDTO.setRoles(userModel.getUserRolesList().stream().map(r -> r.getUserType().getUserTpDesc())
					.collect(Collectors.toList()));

			return userDTO;
		}
		return null;
	}

	@Override
	public String deleteUser(Long idUser) {
		Optional<User> userSaved = userRepository.findById(idUser);
		if (userSaved.isPresent()) {
			if (userSaved.get().getUserState().trim().toUpperCase().equals(Constants.TRUE_VALUE.trim().toUpperCase())) {
				User userModel = userSaved.get();
				userModel.setUpdateDate(this.calendar.getTime());
				userModel.setUserState(Constants.FALSE_VALUE);
				userModel = userRepository.save(userModel);
				return "User deleted";
			} else {
				return "User already deleted";
			}
		} else {
			return "The UserType is no registred in database";
		}
	}

	@Override
	public UserDTO login(UserLoginDTO userLoginDTO) throws CapsofException {

		UserDTO userDTO = new UserDTO();

		Optional<User> user = userRepository.findByUserEmail(userLoginDTO.getEmail());

		if (user.isPresent()) {

			Optional<User> userAndPassword = userRepository.findByUserEmailAndUserPass(userLoginDTO.getEmail(),
					userLoginDTO.getPassword());

			if (userAndPassword.isPresent()) {
				userDTO = this.maper.map(userAndPassword.get(), UserDTO.class);
			} else {
				throw new CapsofException("BAD_CREDENTIALS", 400, "Credentials incorrect");
			}

		} else {
			throw new CapsofException("EMAIL_NOT_FOUND", 404, "Email not found");
		}

		return userDTO;
	}

}
