package com.upn.sistemas.capsof_project.service;

import java.util.List;

import com.upn.sistemas.capsof_project.service.dto.ParamDomainDTO;

public interface IParamDomainService {

	List<ParamDomainDTO> getDomainsByCod(String codDomain);
}
