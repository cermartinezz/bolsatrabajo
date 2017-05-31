package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;
import com.bolsaTrabajo.repositories.PostulantSkillRepository;
import com.bolsaTrabajo.service.PostulantSkillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostulantSkillServiceImpl implements PostulantSkillService{

    @Autowired
    PostulantSkillRepository postulantSkillRepository;

    @Override
    public List<PostulantSkill> postulantSkills(Integer postulant_id) {
        return postulantSkillRepository.findAllByPostulant_Id(postulant_id);
    }

    @Override
    public void save(PostulantSkill postulantSkill) {
        postulantSkillRepository.save(postulantSkill);
    }
}
