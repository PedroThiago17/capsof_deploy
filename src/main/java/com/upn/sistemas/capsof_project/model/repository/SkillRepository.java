package com.upn.sistemas.capsof_project.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upn.sistemas.capsof_project.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

}
