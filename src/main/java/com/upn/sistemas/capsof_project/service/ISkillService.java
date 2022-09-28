package com.upn.sistemas.capsof_project.service;

import java.util.List;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.service.dto.SkillDTO;

public interface ISkillService {

	public List<SkillDTO> findByAllSkill() throws CapsofException;

	public List<SkillDTO> findByTypeSkill(String typeSkill) throws CapsofException;

}
