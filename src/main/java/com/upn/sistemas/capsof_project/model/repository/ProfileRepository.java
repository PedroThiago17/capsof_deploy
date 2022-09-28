package com.upn.sistemas.capsof_project.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Optional<Profile> findByProfileId(Long profileId);

	List<Profile> findByUserProfilesList_UserApp_UserId(Long userId);
	
}
