package com.upn.sistemas.capsof_project.service;

import java.util.List;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.service.dto.CompanyOfferDTO;
import com.upn.sistemas.capsof_project.service.dto.CompanyOfferSaveDTO;

public interface ICompanyOfferService {

	public CompanyOfferDTO addCompanyOffer(CompanyOfferSaveDTO companyOfferSaveDTO) throws CapsofException;

	public CompanyOfferDTO updateCompanyOffer(CompanyOfferSaveDTO companyOfferSaveDTO) throws CapsofException;

	public String deleteCompanyOffer(Long companyOfferId) throws CapsofException;

	public List<CompanyOfferDTO> findCompanyOffersByCompanyId(Long companyId) throws CapsofException;
	
	public List<CompanyOfferDTO> findCompanyOfferByProfileUserType(Long userId) throws CapsofException;

}
