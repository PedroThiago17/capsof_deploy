package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upn.sistemas.capsof_project.model.Company;
import com.upn.sistemas.capsof_project.model.repository.CompanyRepository;
import com.upn.sistemas.capsof_project.service.ICompanyService;
import com.upn.sistemas.capsof_project.service.dto.CompanyDTO;
import com.upn.sistemas.capsof_project.utils.Constants;

@Service
public class CompanyServiceImpl implements ICompanyService {

	private ModelMapper maper = new ModelMapper();

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

}
