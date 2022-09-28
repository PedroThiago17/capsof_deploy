package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;
import java.util.List;

public class ProfileSaveDTO implements Serializable {

	private static final long serialVersionUID = -8163157384310061180L;

	private Long profileId;
	private Long userId;
	private String descriptionProfile;
	private List<SkillDTO> skillsDTO;
	private List<SkillDTO> skillsDeleteDTO;
	private Long domTpProfileId;
	private Long domExpeId;

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDescriptionProfile() {
		return descriptionProfile;
	}

	public void setDescriptionProfile(String descriptionProfile) {
		this.descriptionProfile = descriptionProfile;
	}

	public List<SkillDTO> getSkillsDTO() {
		return skillsDTO;
	}

	public void setSkillsDTO(List<SkillDTO> skillsDTO) {
		this.skillsDTO = skillsDTO;
	}

	public List<SkillDTO> getSkillsDeleteDTO() {
		return skillsDeleteDTO;
	}

	public void setSkillsDeleteDTO(List<SkillDTO> skillsDeleteDTO) {
		this.skillsDeleteDTO = skillsDeleteDTO;
	}

	public Long getDomTpProfileId() {
		return domTpProfileId;
	}

	public void setDomTpProfileId(Long domTpProfileId) {
		this.domTpProfileId = domTpProfileId;
	}

	public Long getDomExpeId() {
		return domExpeId;
	}

	public void setDomExpeId(Long domExpeId) {
		this.domExpeId = domExpeId;
	}

}
