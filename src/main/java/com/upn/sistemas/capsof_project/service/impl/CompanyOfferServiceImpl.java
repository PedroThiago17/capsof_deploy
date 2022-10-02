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
import com.upn.sistemas.capsof_project.utils.Constants;
import com.upn.sistemas.capsof_project.utils.ConstantsError;

@Service
@Transactional
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

		CompanyOfferDTO companyOfferDTO = new CompanyOfferDTO();
		Optional<CompanyOffer> companyOffer = companyOfferRepository
				.findByOfferId(companyOfferSaveDTO.getCompanyOfferId());

		if (companyOffer.isPresent()) {

			companyOfferDTO = this.maper.map(companyOfferSaveDTO, CompanyOfferDTO.class);
			setDataExperienceAndDomCompanyOffer(companyOfferSaveDTO, companyOfferDTO, companyOffer.get());
			companyOffer.get().setOfferTitle(companyOfferSaveDTO.getOfferTitle());
			companyOffer.get().setOfferDesc(companyOfferSaveDTO.getOfferDescription());
			companyOffer.get().setQuantVacants(companyOfferSaveDTO.getQuantityVacants());
			companyOffer.get().setOfferApps(companyOfferSaveDTO.getApplicationsOffers());
			companyOffer.get().setExpDate(companyOfferSaveDTO.getDateExpiry());
			companyOffer.get().setOfferState(companyOfferSaveDTO.getStatusOffer());
			companyOffer.get().setUpdateDate(calendar.getTime());
			Optional<Company> company = companyRepository.findById(companyOfferSaveDTO.getCompanyId());
			if (company.isPresent()) {
				companyOffer.get().setCompanyId(company.get());
			}
			CompanyOffer companyOfferSave = companyOfferRepository.save(companyOffer.get());
			skillsCompanyOfferRepository.deleteByCompanyOffer_OfferId(companyOfferSave.getOfferId());
			List<SkillDTO> skillsAddDTO = new ArrayList<>();
			setSkillsForCompanyOffer(companyOfferSaveDTO, companyOfferSave, skillsAddDTO);
			companyOfferDTO.setSkillsDTO(skillsAddDTO);
		} else {
			throw new CapsofException(String.valueOf(HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND.value(),
					StringUtils.join("Company Offer", StringUtils.SPACE, StringUtils.SPACE, "not found"));
		}

		return companyOfferDTO;

	}

	@Override
	public String deleteCompanyOffer(Long companyOfferId) throws CapsofException {

		Optional<CompanyOffer> companyOffer = companyOfferRepository.findByOfferId(companyOfferId);

		if (companyOffer.isPresent()) {
			companyOfferRepository.delete(companyOffer.get());
		} else {
			throw new CapsofException(String.valueOf(HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND.value(), StringUtils
					.join("Company Offer", StringUtils.SPACE, companyOfferId, StringUtils.SPACE, "not found"));
		}

		return Constants.OK;
	}

	@Override
	public List<CompanyOfferDTO> findCompanyOffersByCompanyId(Long companyId) throws CapsofException {

		List<CompanyOfferDTO> companyOfferDTOs = new ArrayList<>();

		List<CompanyOffer> companyOffers = companyOfferRepository.findByCompanyId_CompanyId(companyId);
		SkillDTO skillDTO = new SkillDTO();
		List<SkillDTO> skillDTOs = new ArrayList<>();

		if (!companyOffers.isEmpty()) {
			CompanyOfferDTO companyOfferDTO = new CompanyOfferDTO();
			for (CompanyOffer companyOffer : companyOffers) {
				companyOfferDTO = new CompanyOfferDTO();
				companyOfferDTO.setOfferTitle(companyOffer.getOfferTitle());
				companyOfferDTO.setCompanyOfferId(companyOffer.getOfferId());
				companyOfferDTO.setOfferDescription(companyOffer.getOfferDesc());
				companyOfferDTO.setQuantityVacants(companyOffer.getQuantVacants());
				companyOfferDTO.setApplicationsOffers(companyOffer.getOfferApps());
				companyOfferDTO.setDateExpiry(companyOffer.getExpDate());
				companyOfferDTO.setStatusOffer(companyOffer.getOfferState());
				Optional<Company> company = companyRepository.findById(companyOffer.getCompanyId().getCompanyId());
				if (company.isPresent()) {
					companyOfferDTO.setCompanyId(company.get().getCompanyId());
				}
				companyOfferDTO.setDomExpe(this.maper.map(companyOffer.getDomExpId(), ParamDomainDTO.class));
				companyOfferDTO.setDomTpPerfil(this.maper.map(companyOffer.getDomTpProfileId(), ParamDomainDTO.class));

				if (Objects.nonNull(companyOffer.getOfferSkillsList())
						&& !companyOffer.getOfferSkillsList().isEmpty()) {
					skillDTOs = new ArrayList<>();
					for (OfferSkills offerSkills : companyOffer.getOfferSkillsList()) {
						skillDTO = new SkillDTO();
						skillDTO.setLevelSkill(offerSkills.getSkillNivel());
						skillDTO.setSkillDescription(offerSkills.getSkill().getSkillDesc());
						skillDTO.setSkillId(offerSkills.getSkill().getSkillId());
						skillDTOs.add(skillDTO);
					}
					companyOfferDTO.setSkillsDTO(skillDTOs);
				}
				companyOfferDTOs.add(companyOfferDTO);
			}
		}

		return companyOfferDTOs;
	}

}
