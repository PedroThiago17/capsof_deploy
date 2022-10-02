package com.upn.sistemas.capsof_project.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.CompanyOffer;

@Repository
public interface CompanyOfferRepository extends JpaRepository<CompanyOffer, Long> {

}
