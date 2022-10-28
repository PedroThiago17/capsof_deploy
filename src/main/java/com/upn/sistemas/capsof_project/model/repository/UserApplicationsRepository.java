package com.upn.sistemas.capsof_project.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.UserApplications;
import com.upn.sistemas.capsof_project.model.UserApplicationsPK;

@Repository
public interface UserApplicationsRepository extends JpaRepository<UserApplications, UserApplicationsPK> {

	Optional<UserApplications> findByUserApplicationsPK_OfferIdAndUserApplicationsPK_UserId(long companyOfferId,
			long userId);

	List<UserApplications> findByUserApplicationsPK_UserId(long userId);

}
