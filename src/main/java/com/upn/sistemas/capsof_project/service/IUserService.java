package com.upn.sistemas.capsof_project.service;

import java.util.List;

import com.upn.sistemas.capsof_project.model.User;
import com.upn.sistemas.capsof_project.service.dto.UserDTO;
import com.upn.sistemas.capsof_project.service.dto.UserSaveDTO;

public interface IUserService {

	public List<UserDTO> findAll();

	public User findByUserEmail(String userEmail);

	public UserDTO addUser(UserSaveDTO userSave);

	public UserDTO findById(Long idUser);
}
