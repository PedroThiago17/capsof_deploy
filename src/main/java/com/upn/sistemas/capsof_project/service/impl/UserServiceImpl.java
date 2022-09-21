package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upn.sistemas.capsof_project.model.User;
import com.upn.sistemas.capsof_project.model.UserRoles;
import com.upn.sistemas.capsof_project.model.UserRolesPK;
import com.upn.sistemas.capsof_project.model.UserType;
import com.upn.sistemas.capsof_project.model.repository.UserRepository;
import com.upn.sistemas.capsof_project.model.repository.UserRolesRepository;
import com.upn.sistemas.capsof_project.model.repository.UserTypeRepository;
import com.upn.sistemas.capsof_project.service.IUserService;
import com.upn.sistemas.capsof_project.service.dto.UserDTO;
import com.upn.sistemas.capsof_project.service.dto.UserSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.UserUpdateDTO;
import com.upn.sistemas.capsof_project.utils.Constants;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService, UserDetailsService {

	private ModelMapper maper = new ModelMapper();
	private Calendar calendar = Calendar.getInstance();
	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

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
	public User findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserEmail(username);

		if (Objects.isNull(user)) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		}

		List<GrantedAuthority> authorities = user.getUserRolesList().stream()
				.map(role -> new SimpleGrantedAuthority(role.getUserType().getUserTpDesc()))
				.peek(auth -> log.info("Role: " + auth.getAuthority())).collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPass(), true,
				true, true, true, authorities);
	}

	@Override
	public UserDTO addUser(UserSaveDTO userSave) {
		final Optional<UserType> defaultRole = userTypeRepository.findById(Constants.ROLE_USER_VALUE);

		if (Objects.isNull(findByUserEmail(userSave.getUserEmail()))) {
			User userModel = this.maper.map(userSave, User.class);
			userModel.setUserPass(passwordEncoder.encode(userSave.getUserPass()));
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
}
