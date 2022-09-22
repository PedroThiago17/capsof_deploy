package com.upn.sistemas.capsof_project.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserEmail(String userEmail);

	public User findByUserDni(String userDni);
}
