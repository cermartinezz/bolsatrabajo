package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postulantSkillRepository")
public interface PostulantSkillRepository extends JpaRepository<PostulantSkill, Integer>{

    List<PostulantSkill> findAllByPostulant_Id(Integer postulant_id);

}
