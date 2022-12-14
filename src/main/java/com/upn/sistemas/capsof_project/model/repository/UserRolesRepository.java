package com.upn.sistemas.capsof_project.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.UserRoles;
import com.upn.sistemas.capsof_project.model.UserRolesPK;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UserRolesPK> {

}
