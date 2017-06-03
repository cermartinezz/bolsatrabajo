package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.compositeKeys.PostulantSkillId;
import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postulantSkillRepository")
public interface PostulantSkillRepository extends JpaRepository<PostulantSkill, PostulantSkillId>{

    PostulantSkill findByPrimaryKey(PostulantSkillId postulantSkillId);

}
