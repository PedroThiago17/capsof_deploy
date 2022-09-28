package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upn.sistemas.capsof_project.exceptions.BadRequestException;
import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.model.ParamDomain;
import com.upn.sistemas.capsof_project.model.Profile;
import com.upn.sistemas.capsof_project.model.Skill;
import com.upn.sistemas.capsof_project.model.SkillsProfile;
import com.upn.sistemas.capsof_project.model.SkillsProfilePK;
import com.upn.sistemas.capsof_project.model.UserProfiles;
import com.upn.sistemas.capsof_project.model.UserProfilesPK;
import com.upn.sistemas.capsof_project.model.repository.ParamDomainRepository;
import com.upn.sistemas.capsof_project.model.repository.ProfileRepository;
import com.upn.sistemas.capsof_project.model.repository.SkillRepository;
import com.upn.sistemas.capsof_project.model.repository.SkillsProfileRepository;
import com.upn.sistemas.capsof_project.model.repository.UserProfileRepository;
import com.upn.sistemas.capsof_project.service.IProfileService;
import com.upn.sistemas.capsof_project.service.dto.ParamDomainDTO;
import com.upn.sistemas.capsof_project.service.dto.ProfileDTO;
import com.upn.sistemas.capsof_project.service.dto.ProfileSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.SkillDTO;
import com.upn.sistemas.capsof_project.utils.Constants;
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

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Override
	public ProfileDTO addProfile(ProfileSaveDTO profileSaveDTO) throws CapsofException {

		ProfileDTO profileDTO = new ProfileDTO();
		Profile profile = new Profile();
		profile.setProfileDesc(profileSaveDTO.getDescriptionProfile());

		setDataExperienceAndDomProfile(profileSaveDTO, profileDTO, profile);

		profile.setCreationDate(calendar.getTime());
		profile = profileRepository.save(profile);

		UserProfilesPK userProfilesPK = new UserProfilesPK(profileSaveDTO.getUserId(), profile.getProfileId());
		UserProfiles userProfiles = new UserProfiles();
		userProfiles.setUserProfilesPK(userProfilesPK);
		userProfiles.setProfileState(Constants.OK);
		userProfiles.setCreationDate(calendar.getTime());
		userProfileRepository.save(userProfiles);

		List<SkillDTO> skillDTOs = new ArrayList<>();
		setSkillsForProfessional(profileSaveDTO, profile, skillDTOs);

		profileDTO.setProfileId(profile.getProfileId());
		profileDTO.setDescriptionProfile(profile.getProfileDesc());
		profileDTO.setSkillsDTO(skillDTOs);

		return profileDTO;
	}

	private void setSkillsForProfessional(ProfileSaveDTO profileSaveDTO, Profile profile, List<SkillDTO> skillDTOs) {

		SkillDTO skillDTO;

		if (Objects.nonNull(profileSaveDTO.getSkillsDTO()) && !profileSaveDTO.getSkillsDTO().isEmpty()) {
			for (SkillDTO skillsSave : profileSaveDTO.getSkillsDTO()) {
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
					LOGGER.error("The identifier {} of SKILL is not registered in the database.",
							skillsSave.getSkillId());
				}

			}
		}

	}

	private void setDataExperienceAndDomProfile(ProfileSaveDTO profileSaveDTO, ProfileDTO profileDTO, Profile profile)
			throws BadRequestException {
		Optional<ParamDomain> domTpProfile = paramDomainRepository.findById(profileSaveDTO.getDomTpProfileId());
		Optional<ParamDomain> domExp = paramDomainRepository.findById(profileSaveDTO.getDomExpeId());

		if (domTpProfile.isPresent()) {
			profile.setDomTpProfileId(domTpProfile.get());
			profileDTO.setDomTpPerfil(this.maper.map(domTpProfile.get(), ParamDomainDTO.class));
		} else {
			LOGGER.error("The identifier {} of DOM_TP_PROFILE is not registered in the database.",
					profileSaveDTO.getDomTpProfileId());
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
	}

	@Override
	public ProfileDTO updateProfile(ProfileSaveDTO profileSaveDTO) throws CapsofException {

		ProfileDTO profileDTO = new ProfileDTO();
		Optional<Profile> profile = profileRepository.findByProfileId(profileSaveDTO.getProfileId());

		if (profile.isPresent()) {
			profile.get().setProfileDesc(profileSaveDTO.getDescriptionProfile());
			setDataExperienceAndDomProfile(profileSaveDTO, profileDTO, profile.get());
			profile.get().setUpdateDate(calendar.getTime());
			Profile profileSave = profileRepository.save(profile.get());
			List<SkillDTO> skillsAddDTO = new ArrayList<>();
			setSkillsForProfessional(profileSaveDTO, profileSave, skillsAddDTO);
			List<SkillDTO> skillsDeleteDTO = new ArrayList<>();
			deleteSkillForProfessional(profileSaveDTO, profileSave, skillsDeleteDTO);
			profileDTO.setProfileId(profileSave.getProfileId());
			profileDTO.setDescriptionProfile(profileSave.getProfileDesc());
			profileDTO.setSkillsDTO(skillsAddDTO);
			profileDTO.setSkillsDeleteDTO(skillsDeleteDTO);
		} else {
			throw new CapsofException(String.valueOf(HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND.value(), StringUtils
					.join("User", StringUtils.SPACE, profileSaveDTO.getProfileId(), StringUtils.SPACE, "not found"));
		}

		return profileDTO;
	}

	private void deleteSkillForProfessional(ProfileSaveDTO profileSaveDTO, Profile profile, List<SkillDTO> skillDTOs) {

		SkillDTO skillDTO;

		if (Objects.nonNull(profileSaveDTO.getSkillsDeleteDTO()) && !profileSaveDTO.getSkillsDeleteDTO().isEmpty()) {
			for (SkillDTO skillsSave : profileSaveDTO.getSkillsDeleteDTO()) {

				Optional<SkillsProfile> skillsProfile = skillsProfileRepository.findByProfile_ProfileIdAndSkill_SkillId(
						profileSaveDTO.getProfileId(), skillsSave.getSkillId());

				if (skillsProfile.isPresent()) {
					skillsProfileRepository.deleteByProfile_ProfileIdAndSkill_SkillId(profileSaveDTO.getProfileId(),
							skillsSave.getSkillId());
					skillDTO = new SkillDTO();
					skillDTO.setSkillId(skillsProfile.get().getSkill().getSkillId());
					skillDTO.setSkillDescription(skillsProfile.get().getSkill().getSkillDesc());
					skillDTO.setLevelSkill(skillsProfile.get().getSkillNivel());
					skillDTOs.add(skillDTO);
				} else {
					LOGGER.error(StringUtils.join("Professional Skill not found, with data: ProfileId: ",
							profileSaveDTO.getProfileId(), "and SkillId: ", skillsSave.getSkillId()));
				}

			}
		}

	}

	@Override
	public String deleteProfile(Long profileId) throws CapsofException {

		Optional<Profile> profile = profileRepository.findByProfileId(profileId);

		if (profile.isPresent()) {
			profileRepository.delete(profile.get());
		} else {
			throw new CapsofException(String.valueOf(HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND.value(),
					StringUtils.join("User", StringUtils.SPACE, profileId, StringUtils.SPACE, "not found"));
		}

		return Constants.OK;
	}

	@Override
	public List<ProfileDTO> findProfilesByUserId(Long userId) throws CapsofException {

		List<ProfileDTO> profilesDTO = new ArrayList<>();
		List<Profile> profiles = profileRepository.findByUserProfilesList_UserApp_UserId(userId);
		SkillDTO skillDTO = new SkillDTO();
		List<SkillDTO> skillDTOs = new ArrayList<>();

		if (!profiles.isEmpty()) {
			ProfileDTO profileDTO = new ProfileDTO();
			for (Profile profile : profiles) {
				profileDTO = new ProfileDTO();
				profileDTO = this.maper.map(profile, ProfileDTO.class);
				profileDTO.setDescriptionProfile(profile.getProfileDesc());
				profileDTO.setDomExpe(this.maper.map(profile.getDomExpId(), ParamDomainDTO.class));
				profileDTO.setDomTpPerfil(this.maper.map(profile.getDomTpProfileId(), ParamDomainDTO.class));
				if (Objects.nonNull(profile.getSkillsProfileList()) && !profile.getSkillsProfileList().isEmpty()) {
					skillDTOs = new ArrayList<>();
					for (SkillsProfile skillsProfile : profile.getSkillsProfileList()) {
						skillDTO = new SkillDTO();
						skillDTO.setLevelSkill(skillsProfile.getSkillNivel());
						skillDTO.setSkillDescription(skillsProfile.getSkill().getSkillDesc());
						skillDTO.setSkillId(skillsProfile.getSkill().getSkillId());
						skillDTOs.add(skillDTO);
					}
					profileDTO.setSkillsDTO(skillDTOs);
				}
				profileDTO.setUserId(userId);
				profilesDTO.add(profileDTO);
			}
		}

		return profilesDTO;
	}

}
