package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.compositeKeys.PostulantSkillId;
import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;
import com.bolsaTrabajo.repositories.PostulantSkillRepository;
import com.bolsaTrabajo.service.PostulantSkillService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostulantSkillServiceImpl implements PostulantSkillService{

    @Autowired
    private
    PostulantSkillRepository postulantSkillRepository;

    @Override
    public PostulantSkill postulantSkill(PostulantSkillId postulantSkillId) {
        return postulantSkillRepository.findByPrimaryKey(postulantSkillId);
    }

    @Override
    public void save(PostulantSkill postulantSkill) {
        postulantSkillRepository.save(postulantSkill);
    }

    @Override
    public void delete(PostulantSkill postulantSkill) {
        postulantSkillRepository.delete(postulantSkill);
    }
}
