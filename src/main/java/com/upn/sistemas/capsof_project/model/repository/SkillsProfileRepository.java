package com.upn.sistemas.capsof_project.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.SkillsProfile;

@Repository
public interface SkillsProfileRepository extends JpaRepository<SkillsProfile, Long> {

	Optional<SkillsProfile> findByProfile_ProfileIdAndSkill_SkillId(Long profileId, Long skillId);
	
	void deleteByProfile_ProfileIdAndSkill_SkillId(Long profileId, Long skillId);
	
	void deleteByProfile_ProfileId(Long profileId);

}
