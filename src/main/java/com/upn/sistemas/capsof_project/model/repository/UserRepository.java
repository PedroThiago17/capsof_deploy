package com.upn.sistemas.capsof_project.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUserEmail(String userEmail);

	public Optional<User> findByUserDni(String userDni);
	
	public Optional<User> findByUserEmailAndUserPass(String userEmail, String userPass);
	
}
