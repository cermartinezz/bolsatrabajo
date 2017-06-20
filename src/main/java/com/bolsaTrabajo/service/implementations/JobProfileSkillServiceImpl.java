package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.jobInfo.JobProfileSkill;
import com.bolsaTrabajo.repositories.JobProfileSkillRepository;
import com.bolsaTrabajo.service.JobProfileSkillService;
import org.springframework.beans.factory.annotation.Autowired;

public class JobProfileSkillServiceImpl implements JobProfileSkillService {

    @Autowired
    private JobProfileSkillRepository jobProfileSkillRepository;

    @Override
    public void save(JobProfileSkill jobProfileSkill) {
        jobProfileSkillRepository.store(
                jobProfileSkill.getSkill().getId(),
                jobProfileSkill.getSkill().getTitulo(),
                jobProfileSkill.getJobProfile().getId(),
                jobProfileSkill.getSkill().getSkillCategory().getId()
        );
    }

    @Override
    public void update() {

    }

    @Override
    public void delete(JobProfileSkill jobProfileSkill) {
        jobProfileSkillRepository.delete(jobProfileSkill);
    }


}
