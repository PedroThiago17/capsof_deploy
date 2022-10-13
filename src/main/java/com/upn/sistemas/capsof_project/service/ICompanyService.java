package com.upn.sistemas.capsof_project.service;

import java.util.List;
import java.util.Optional;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.model.Company;
import com.upn.sistemas.capsof_project.service.dto.CompanyDTO;
import com.upn.sistemas.capsof_project.service.dto.CompanySaveDTO;

public interface ICompanyService {
	
	public CompanyDTO getCompanyById(Long companyId);
	
	public List<CompanyDTO> findAll();
	
	public CompanyDTO addCompany(CompanySaveDTO companySaveDTO) throws CapsofException;
	
	public Optional<Company> findByCompanyEmail(String companyEmail);

	public Optional<Company> findByCompanyRuc(String companyRuc);
	
	public Optional<Company> findByCompanyCode(String companyCode);
	
	public CompanyDTO updateCompany(CompanyDTO companyDTO);
	
	public String deleteCompany(Long companyId);

}
