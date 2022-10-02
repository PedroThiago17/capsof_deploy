package com.upn.sistemas.capsof_project.service;

import java.util.List;

import com.upn.sistemas.capsof_project.service.dto.CompanyDTO;

public interface ICompanyService {
	
	public CompanyDTO getCompanyById(Long companyId);
	
	public List<CompanyDTO> findAll();

}
