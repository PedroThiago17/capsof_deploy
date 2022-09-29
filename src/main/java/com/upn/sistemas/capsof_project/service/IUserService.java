package com.upn.sistemas.capsof_project.service;

import java.util.List;
import java.util.Optional;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.model.User;
import com.upn.sistemas.capsof_project.service.dto.UserDTO;
import com.upn.sistemas.capsof_project.service.dto.UserLoginDTO;
import com.upn.sistemas.capsof_project.service.dto.UserSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.UserUpdateDTO;

public interface IUserService {

	public List<UserDTO> findAll();

	public Optional<User> findByUserEmail(String userEmail);

	public UserDTO addUser(UserSaveDTO userSave);

	public UserDTO findById(Long idUser);

	public UserDTO updateUser(UserUpdateDTO userUpdate);

	public String deleteUser(Long idUser);
	
	public UserDTO login(UserLoginDTO userLoginDTO) throws CapsofException;

}
