package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;

public class SkillDTO implements Serializable {

	private static final long serialVersionUID = 6468037896812145455L;

	private Long skillId;
	private String skillDescription;
	private Integer levelSkill;

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	public Integer getLevelSkill() {
		return levelSkill;
	}

	public void setLevelSkill(Integer levelSkill) {
		this.levelSkill = levelSkill;
	}

}
