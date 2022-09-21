package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;
import java.util.List;

public class ProfileDTO implements Serializable {

	private static final long serialVersionUID = -7482298080237982097L;

	private Long profileId;
	private String descripcionProfile;
	private List<SkillDTO> skillDTOs;
	private ParamDomainDTO domTpPerfil;
	private ParamDomainDTO domExpe;

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getDescripcionProfile() {
		return descripcionProfile;
	}

	public void setDescripcionProfile(String descripcionProfile) {
		this.descripcionProfile = descripcionProfile;
	}

	public List<SkillDTO> getSkillDTOs() {
		return skillDTOs;
	}

	public void setSkillDTOs(List<SkillDTO> skillDTOs) {
		this.skillDTOs = skillDTOs;
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
