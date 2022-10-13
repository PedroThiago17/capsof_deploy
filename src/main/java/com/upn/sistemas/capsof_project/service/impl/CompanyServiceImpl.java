package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.model.Company;
import com.upn.sistemas.capsof_project.model.repository.CompanyRepository;
import com.upn.sistemas.capsof_project.service.ICompanyService;
import com.upn.sistemas.capsof_project.service.dto.CompanyDTO;
import com.upn.sistemas.capsof_project.service.dto.CompanySaveDTO;
import com.upn.sistemas.capsof_project.utils.Constants;

@Service
public class CompanyServiceImpl implements ICompanyService {

	private ModelMapper maper = new ModelMapper();
	private Calendar calendar = Calendar.getInstance();

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public CompanyDTO getCompanyById(Long companyId) {

		CompanyDTO companyDTO = new CompanyDTO();

		if (!Objects.isNull(companyId)) {
			Optional<Company> company = companyRepository.findById(companyId);
			companyDTO = this.maper.map(company.get(), CompanyDTO.class);
			return companyDTO;
		} else {
			return companyDTO;
		}

	}

	@Override
	public List<CompanyDTO> findAll() {

		List<Company> companies = companyRepository.findAll();
		final List<CompanyDTO> result = new ArrayList<>();
		CompanyDTO companyDTO;

		for (Company company : companies) {
			if (company.getCompanyState().trim().toUpperCase().equals(Constants.TRUE_VALUE.trim().toUpperCase())) {
				companyDTO = maper.map(company, CompanyDTO.class);
				result.add(companyDTO);
			}
		}
		return result;
	}

	@Override
	public CompanyDTO addCompany(CompanySaveDTO companySaveDTO) throws CapsofException {

		CompanyDTO companyDTO = new CompanyDTO();

		if (findByCompanyEmail(companySaveDTO.getCompanyEmail()).isPresent()) {
			companyDTO.setResponseStatus("EMAIL_DUPLICATED");
			return companyDTO;
		}

		if (findByCompanyRuc(companySaveDTO.getCompanyRuc()).isPresent()) {
			companyDTO.setResponseStatus("RUC_DUPLICATED");
			return companyDTO;
		}
		
		if (findByCompanyCode(companySaveDTO.getCompanyCode()).isPresent()) {
			companyDTO.setResponseStatus("COMPANY_CODE_DUPLICATED");
			return companyDTO;
		}

		Company company = new Company();
		company = maper.map(companySaveDTO, Company.class);
		company.setCreationDate(calendar.getTime());
		company.setCompanyState(Constants.TRUE_VALUE);
		company = companyRepository.save(company);

		companyDTO = maper.map(company, CompanyDTO.class);
		companyDTO.setResponseStatus("OK");

		return companyDTO;
	}

	@Override
	public Optional<Company> findByCompanyEmail(String companyEmail) {
		return companyRepository.findByCompanyEmail(companyEmail);
	}

	@Override
	public Optional<Company> findByCompanyRuc(String companyRuc) {
		return companyRepository.findByCompanyRuc(companyRuc);
	}

	@Override
	public Optional<Company> findByCompanyCode(String companyCode) {
		return companyRepository.findByCompanyCode(companyCode);
	}

	@Override
	public CompanyDTO updateCompany(CompanyDTO companyDTO) {

		Optional<Company> companySaved = companyRepository.findById(companyDTO.getCompanyId());

		if (companySaved.isPresent()) {

			Company company = companySaved.get();
			company.setCompanyName(companyDTO.getCompanyName());
			company.setCompanyState(companyDTO.getCompanyState());
			company = companyRepository.save(company);

			CompanyDTO companyDto = new CompanyDTO();
			companyDto = maper.map(company, CompanyDTO.class);
			return companyDto;
		}

		return null;
	}

	@Override
	public String deleteCompany(Long companyId) {

		Optional<Company> companySaved = companyRepository.findById(companyId);

		if (companySaved.isPresent()) {

			if (companySaved.get().getCompanyState().toUpperCase().equals(Constants.FALSE_VALUE)) {
				return "COMPANY_DELETED_ALREADY";
			} else {
				Company company = new Company();
				company = companySaved.get();
				company.setCompanyState(Constants.FALSE_VALUE);
				company = companyRepository.save(company);
				return "OK";
			}

		} else {
			return "COMPANY_NOT_FOUND";
		}

	}

}
