package com.upn.sistemas.capsof_project.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upn.sistemas.capsof_project.exceptions.BadRequestException;
import com.upn.sistemas.capsof_project.exceptions.CapsofException;
import com.upn.sistemas.capsof_project.model.Company;
import com.upn.sistemas.capsof_project.model.CompanyOffer;
import com.upn.sistemas.capsof_project.model.OfferSkills;
import com.upn.sistemas.capsof_project.model.OfferSkillsPK;
import com.upn.sistemas.capsof_project.model.ParamDomain;
import com.upn.sistemas.capsof_project.model.Skill;
import com.upn.sistemas.capsof_project.model.repository.CompanyOfferRepository;
import com.upn.sistemas.capsof_project.model.repository.CompanyRepository;
import com.upn.sistemas.capsof_project.model.repository.ParamDomainRepository;
import com.upn.sistemas.capsof_project.model.repository.SkillRepository;
import com.upn.sistemas.capsof_project.model.repository.SkillsCompanyOfferRepository;
import com.upn.sistemas.capsof_project.service.ICompanyOfferService;
import com.upn.sistemas.capsof_project.service.dto.CompanyOfferDTO;
import com.upn.sistemas.capsof_project.service.dto.CompanyOfferSaveDTO;
import com.upn.sistemas.capsof_project.service.dto.ParamDomainDTO;
import com.upn.sistemas.capsof_project.service.dto.SkillDTO;
import com.upn.sistemas.capsof_project.utils.ConstantsError;

@Service
public class CompanyOfferServiceImpl implements ICompanyOfferService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyOfferServiceImpl.class);
	private Calendar calendar = Calendar.getInstance();
	private ModelMapper maper = new ModelMapper();

	@Autowired
	private ParamDomainRepository paramDomainRepository;

	@Autowired
	private CompanyOfferRepository companyOfferRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private SkillsCompanyOfferRepository skillsCompanyOfferRepository;

	@Override
	public CompanyOfferDTO addCompanyOffer(CompanyOfferSaveDTO companyOfferSaveDTO) throws CapsofException {

		CompanyOfferDTO companyOfferDTO = new CompanyOfferDTO();
		companyOfferDTO = this.maper.map(companyOfferSaveDTO, CompanyOfferDTO.class);
		CompanyOffer companyOffer = new CompanyOffer();
		companyOffer.setOfferTitle(companyOfferSaveDTO.getOfferTitle());
		companyOffer.setOfferDesc(companyOfferSaveDTO.getOfferDescription());
		companyOffer.setQuantVacants(companyOfferSaveDTO.getQuantityVacants());
		companyOffer.setOfferApps(0);
		companyOffer.setExpDate(companyOfferSaveDTO.getDateExpiry());
		companyOffer.setOfferState(companyOfferSaveDTO.getStatusOffer());
		companyOffer.setCreationDate(calendar.getTime());
		Optional<Company> company = companyRepository.findById(companyOfferSaveDTO.getCompanyId());
		if (company.isPresent()) {
			companyOffer.setCompanyId(company.get());
		}
		setDataExperienceAndDomCompanyOffer(companyOfferSaveDTO, companyOfferDTO, companyOffer);

		companyOffer = companyOfferRepository.save(companyOffer);

		List<SkillDTO> skillDTOs = new ArrayList<>();
		setSkillsForCompanyOffer(companyOfferSaveDTO, companyOffer, skillDTOs);

		companyOfferDTO.setSkillsDTO(skillDTOs);

		return companyOfferDTO;
	}

	private void setDataExperienceAndDomCompanyOffer(CompanyOfferSaveDTO companyOfferSaveDTO,
			CompanyOfferDTO companyOfferDTO, CompanyOffer companyOffer) throws BadRequestException {
		Optional<ParamDomain> domTpProfile = paramDomainRepository.findById(companyOfferSaveDTO.getDomTpProfileId());
		Optional<ParamDomain> domExp = paramDomainRepository.findById(companyOfferSaveDTO.getDomExpeId());

		if (domTpProfile.isPresent()) {
			companyOffer.setDomTpProfileId(domTpProfile.get());
			companyOfferDTO.setDomTpPerfil(this.maper.map(domTpProfile.get(), ParamDomainDTO.class));
		} else {
			LOGGER.error("The identifier {} of DOM_TP_PROFILE is not registered in the database.",
					companyOfferSaveDTO.getDomTpProfileId());
			throw new BadRequestException(ConstantsError.ERROR_CODE_NOT_FOUND_DOM_TP_PROFILE,
					ConstantsError.ERROR_MESSAGE_NOT_FOUND_DOM_TP_PROFILE);
		}

		if (domExp.isPresent()) {
			companyOffer.setDomExpId(domExp.get());
			companyOfferDTO.setDomExpe(this.maper.map(domExp.get(), ParamDomainDTO.class));
		} else {
			LOGGER.error("The identifier {} of DOM_EXP is not registered in the database.",
					companyOfferSaveDTO.getDomExpeId());
			throw new BadRequestException(ConstantsError.ERROR_CODE_NOT_FOUND_DOM_EXP,
					ConstantsError.ERROR_MESSAGE_NOT_FOUND_DOM_EXP);
		}
	}

	private void setSkillsForCompanyOffer(CompanyOfferSaveDTO companyOfferSaveDTO, CompanyOffer companyOffer,
			List<SkillDTO> skillDTOs) {

		SkillDTO skillDTO;

		if (Objects.nonNull(companyOfferSaveDTO.getSkillsDTO()) && !companyOfferSaveDTO.getSkillsDTO().isEmpty()) {
			for (SkillDTO skillsSave : companyOfferSaveDTO.getSkillsDTO()) {

				OfferSkillsPK offerSkillsPK = new OfferSkillsPK(companyOffer.getOfferId(), skillsSave.getSkillId());
				OfferSkills offerSkills = new OfferSkills(offerSkillsPK);
				offerSkills.setCompanyOffer(companyOffer);

				Optional<Skill> skill = skillRepository.findById(skillsSave.getSkillId());

				if (skill.isPresent()) {
					offerSkills.setSkill(skill.get());
					offerSkills.setSkillNivel(skillsSave.getLevelSkill());
					offerSkills.setCreationDate(calendar.getTime());
					offerSkills.setSkillRequired(skillsSave.getSkillRequired());
					offerSkills = skillsCompanyOfferRepository.save(offerSkills);
					skillDTO = new SkillDTO();
					skillDTO.setSkillId(offerSkills.getSkill().getSkillId());
					skillDTO.setSkillDescription(offerSkills.getSkill().getSkillDesc());
					skillDTO.setLevelSkill(offerSkills.getSkillNivel());
					skillDTOs.add(skillDTO);
				} else {
					LOGGER.error("The identifier {} of SKILL is not registered in the database.",
							skillsSave.getSkillId());
				}

			}
		}

	}

	@Override
	public CompanyOfferDTO updateCompanyOffer(CompanyOfferSaveDTO companyOfferSaveDTO) throws CapsofException {
		return null;
	}

	@Override
	public String deleteCompanyOffer(Long companyOfferId) throws CapsofException {
		return null;
	}

	@Override
	public List<CompanyOfferDTO> findCompanyOffersByCompanyId(Long companyId) throws CapsofException {
		return null;
	}

}
