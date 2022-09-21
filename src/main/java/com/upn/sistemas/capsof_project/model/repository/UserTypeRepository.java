/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.upn.sistemas.capsof_project.model.repository;

import com.upn.sistemas.capsof_project.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    
}
