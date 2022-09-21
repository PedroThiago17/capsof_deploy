package com.upn.sistemas.capsof_project.service.dto;

import java.io.Serializable;
import java.util.List;

public class ProfileSaveDTO implements Serializable {

	private static final long serialVersionUID = -8163157384310061180L;

	private String descripcionProfile;
	private List<SkillDTO> skillDTOs;
	private Long domTpPerfilId;
	private Long domExpeId;

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

	public Long getDomTpPerfilId() {
		return domTpPerfilId;
	}

	public void setDomTpPerfilId(Long domTpPerfilId) {
		this.domTpPerfilId = domTpPerfilId;
	}

	public Long getDomExpeId() {
		return domExpeId;
	}

	public void setDomExpeId(Long domExpeId) {
		this.domExpeId = domExpeId;
	}

}
