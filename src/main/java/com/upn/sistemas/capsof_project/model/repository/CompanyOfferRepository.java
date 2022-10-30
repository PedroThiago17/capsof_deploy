package com.upn.sistemas.capsof_project.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.CompanyOffer;

@Repository
public interface CompanyOfferRepository extends JpaRepository<CompanyOffer, Long> {
	
	Optional<CompanyOffer> findByOfferId(Long offerId);
	
	void deleteByOfferId(Long offerServiceId);
	
	List<CompanyOffer> findByCompanyId_CompanyId(Long companyId);
	
	List<CompanyOffer> findByDomTpProfileId_DomainIdIn(List<Long> domainIds);
	
	void deleteByOfferSkillsList_OfferSkillsPK_OfferId(Long offer);

}
