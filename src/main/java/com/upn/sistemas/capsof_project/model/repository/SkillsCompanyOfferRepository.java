package com.upn.sistemas.capsof_project.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.OfferSkills;

@Repository
public interface SkillsCompanyOfferRepository extends JpaRepository<OfferSkills, Long> {
	
	void deleteByCompanyOffer_OfferId(Long offerId);

}
