package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;

import java.util.List;

public interface PostulantSkillService {

    public List<PostulantSkill> postulantSkills(Integer postulant_id);

    public void save(PostulantSkill postulantSkill);


}
