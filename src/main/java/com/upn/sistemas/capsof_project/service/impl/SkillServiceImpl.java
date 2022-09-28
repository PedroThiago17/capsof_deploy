package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.model.Skill;
import com.upn.sistemas.capsof_project.model.repository.SkillRepository;
import com.upn.sistemas.capsof_project.service.ISkillService;
import com.upn.sistemas.capsof_project.service.dto.SkillDTO;

@Service
@Transactional
public class SkillServiceImpl implements ISkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public List<SkillDTO> findByAllSkill() throws CapsofException {

		List<Skill> skills = skillRepository.findAll();
		List<SkillDTO> skillDTOs = new ArrayList<>();
		SkillDTO skillDTO = new SkillDTO();

		for (Skill skill : skills) {
			skillDTO = new SkillDTO();
			skillDTO.setSkillId(skill.getSkillId());
			skillDTO.setTypeSkill(skill.getSkillTpId().getSkillTpDesc());
			skillDTO.setSkillDescription(skill.getSkillDesc());
			skillDTOs.add(skillDTO);
		}

		return skillDTOs;
	}

	@Override
	public List<SkillDTO> findByTypeSkill(String typeSkill) throws CapsofException {

		List<Skill> skills = skillRepository.findBySkillTpId_SkillTpDesc(typeSkill);

		List<SkillDTO> skillDTOs = new ArrayList<>();
		SkillDTO skillDTO = new SkillDTO();

		for (Skill skill : skills) {
			skillDTO = new SkillDTO();
			skillDTO.setSkillId(skill.getSkillId());
			skillDTO.setTypeSkill(skill.getSkillTpId().getSkillTpDesc());
			skillDTO.setSkillDescription(skill.getSkillDesc());
			skillDTOs.add(skillDTO);
		}

		return skillDTOs;
	}

}
