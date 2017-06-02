package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.compositeKeys.PostulantSkillId;
import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;

import java.util.List;

public interface PostulantSkillService {

    PostulantSkill postulantSkill(PostulantSkillId postulantSkillId);

    void save(PostulantSkill postulantSkill);

    void delete(PostulantSkill postulantSkill);


}
