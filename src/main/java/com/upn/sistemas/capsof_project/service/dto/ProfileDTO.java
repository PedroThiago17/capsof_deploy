package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;
import java.util.List;

public class ProfileDTO implements Serializable {

	private static final long serialVersionUID = -7482298080237982097L;

	private Long profileId;
	private Long userId;
	private String descriptionProfile;
	private List<SkillDTO> skillsDTO;
	private List<SkillDTO> skillsDeleteDTO;
	private ParamDomainDTO domTpPerfil;
	private ParamDomainDTO domExpe;

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

	public ParamDomainDTO getDomTpPerfil() {
		return domTpPerfil;
	}

	public void setDomTpPerfil(ParamDomainDTO domTpPerfil) {
		this.domTpPerfil = domTpPerfil;
	}

	public ParamDomainDTO getDomExpe() {
		return domExpe;
	}

	public void setDomExpe(ParamDomainDTO domExpe) {
		this.domExpe = domExpe;
	}

}
