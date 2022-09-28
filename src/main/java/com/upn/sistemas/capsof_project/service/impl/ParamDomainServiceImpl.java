package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upn.sistemas.capsof_project.model.ParamDomain;
import com.upn.sistemas.capsof_project.model.repository.ParamDomainRepository;
import com.upn.sistemas.capsof_project.service.IParamDomainService;
import com.upn.sistemas.capsof_project.service.dto.ParamDomainDTO;

@Service
@Transactional
public class ParamDomainServiceImpl implements IParamDomainService {

	private ModelMapper maper = new ModelMapper();

	@Autowired
	private ParamDomainRepository paramDomainRepository;

	@Override
	public List<ParamDomainDTO> getDomainsByCod(String codDomain) {

		if (!codDomain.isBlank()) {
			List<ParamDomainDTO> result = new ArrayList<>();
			List<ParamDomain> domains = paramDomainRepository.findByDomainCod(codDomain);

			if (!domains.isEmpty()) {
				ParamDomainDTO domainDTO;
				for (ParamDomain paramDomain : domains) {
					domainDTO = maper.map(paramDomain, ParamDomainDTO.class);
					result.add(domainDTO);
				}

				return result;
			}
		}

		return null;
	}

}
