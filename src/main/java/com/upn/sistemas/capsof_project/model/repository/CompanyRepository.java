package com.upn.sistemas.capsof_project.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	public Optional<Company> findByCompanyEmail(String companyEmail);

	public Optional<Company> findByCompanyEmailAndCompanyPass(String userEmail, String userPass);
	
	public Optional<Company> findByCompanyId(String companyId);
	
	public Optional<Company> findByCompanyRuc(String companyRuc);
	
	public Optional<Company> findByCompanyCode(String companyCode);

}
