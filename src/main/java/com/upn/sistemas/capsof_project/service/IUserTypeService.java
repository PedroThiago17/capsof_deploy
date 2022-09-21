/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.upn.sistemas.capsof_project.service;

import java.util.List;

import com.upn.sistemas.capsof_project.service.dto.UserTypeDTO;
import com.upn.sistemas.capsof_project.service.dto.UserTypeSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.UserTypeUpdateDTO;

/**
 *
 * @author pedro
 */
public interface IUserTypeService {

	public List<UserTypeDTO> findAll();

	public UserTypeDTO saveUserType(UserTypeSaveDTO userType);

	public UserTypeDTO updateUserType(UserTypeUpdateDTO userType);

	public String deleteUserType(int idUserType);
}
