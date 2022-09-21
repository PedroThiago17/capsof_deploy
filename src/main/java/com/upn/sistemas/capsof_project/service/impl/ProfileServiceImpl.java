package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upn.sistemas.capsof_project.exceptions.BadRequestException;
import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.model.ParamDomain;
import com.upn.sistemas.capsof_project.model.Profile;
import com.upn.sistemas.capsof_project.model.Skill;
import com.upn.sistemas.capsof_project.model.SkillsProfile;
import com.upn.sistemas.capsof_project.model.SkillsProfilePK;
import com.upn.sistemas.capsof_project.model.repository.ParamDomainRepository;
import com.upn.sistemas.capsof_project.model.repository.ProfileRepository;
import com.upn.sistemas.capsof_project.model.repository.SkillRepository;
import com.upn.sistemas.capsof_project.model.repository.SkillsProfileRepository;
import com.upn.sistemas.capsof_project.service.IProfileService;
import com.upn.sistemas.capsof_project.service.dto.ParamDomainDTO;
import com.upn.sistemas.capsof_project.service.dto.ProfileDTO;
import com.upn.sistemas.capsof_project.service.dto.ProfileSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.SkillDTO;
import com.upn.sistemas.capsof_project.utils.ConstantsError;

@Service
@Transactional
public class ProfileServiceImpl implements IProfileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileServiceImpl.class);
	private Calendar calendar = Calendar.getInstance();
	private ModelMapper maper = new ModelMapper();

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private SkillsProfileRepository skillsProfileRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private ParamDomainRepository paramDomainRepository;

	@Override
	public ProfileDTO addProfile(ProfileSaveDTO profileSaveDTO) throws CapsofException {

		ProfileDTO profileDTO = new ProfileDTO();
		Profile profile = new Profile();
		profile.setProfileDesc(profileSaveDTO.getDescripcionProfile());

		Optional<ParamDomain> domTpProfile = paramDomainRepository.findById(profileSaveDTO.getDomTpPerfilId());
		Optional<ParamDomain> domExp = paramDomainRepository.findById(profileSaveDTO.getDomExpeId());

		if (domTpProfile.isPresent()) {
			profile.setDomTpProfileId(domTpProfile.get());
			profileDTO.setDomTpPerfil(this.maper.map(domTpProfile.get(), ParamDomainDTO.class));
		} else {
			LOGGER.error("The identifier {} of DOM_TP_PROFILE is not registered in the database.",
					profileSaveDTO.getDomTpPerfilId());
			throw new BadRequestException(ConstantsError.ERROR_CODE_NOT_FOUND_DOM_TP_PROFILE,
					ConstantsError.ERROR_MESSAGE_NOT_FOUND_DOM_TP_PROFILE);
		}

		if (domExp.isPresent()) {
			profile.setDomExpId(domExp.get());
			profileDTO.setDomExpe(this.maper.map(domExp.get(), ParamDomainDTO.class));
		} else {
			LOGGER.error("The identifier {} of DOM_EXP is not registered in the database.",
					profileSaveDTO.getDomExpeId());
			throw new BadRequestException(ConstantsError.ERROR_CODE_NOT_FOUND_DOM_EXP,
					ConstantsError.ERROR_MESSAGE_NOT_FOUND_DOM_EXP);
		}

		profile.setCreationDate(calendar.getTime());
		profile = profileRepository.save(profile);

		SkillDTO skillDTO;
		List<SkillDTO> skillDTOs = new ArrayList<>();

		for (SkillDTO skillsSave : profileSaveDTO.getSkillDTOs()) {
			SkillsProfilePK skillsProfilePK = new SkillsProfilePK(profile.getProfileId(), skillsSave.getSkillId());
			SkillsProfile skillsProfile = new SkillsProfile(skillsProfilePK);
			skillsProfile.setProfile(profile);

			Optional<Skill> skill = skillRepository.findById(skillsSave.getSkillId());

			if (skill.isPresent()) {
				skillsProfile.setSkill(skill.get());
				skillsProfile.setSkillNivel(skillsSave.getLevelSkill());
				skillsProfile.setCreationDate(calendar.getTime());
				skillsProfile = skillsProfileRepository.save(skillsProfile);
				skillDTO = new SkillDTO();
				skillDTO.setSkillId(skillsProfile.getSkill().getSkillId());
				skillDTO.setSkillDescription(skillsProfile.getSkill().getSkillDesc());
				skillDTO.setLevelSkill(skillsProfile.getSkillNivel());
				skillDTOs.add(skillDTO);
			} else {
				LOGGER.error("The identifier {} of SKILL is not registered in the database.", skillsSave.getSkillId());
			}

		}

		profileDTO.setProfileId(profile.getProfileId());
		profileDTO.setDescripcionProfile(profile.getProfileDesc());
		profileDTO.setSkillDTOs(skillDTOs);

		return profileDTO;
	}

}
