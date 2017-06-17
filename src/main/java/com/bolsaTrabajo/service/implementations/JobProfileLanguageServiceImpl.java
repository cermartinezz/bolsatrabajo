package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.jobInfo.JobProfileLanguage;
import com.bolsaTrabajo.repositories.JobProfileLanguageRepository;
import com.bolsaTrabajo.service.JobProfileLanguageService;
import org.springframework.beans.factory.annotation.Autowired;

public class  JobProfileLanguageServiceImpl implements JobProfileLanguageService {

    @Autowired
    private JobProfileLanguageRepository jobProfileLanguageRepository;

    @Override
    public void save(JobProfileLanguage jobProfileLanguage) {
        jobProfileLanguageRepository.store(jobProfileLanguage.getJobProfile().getId(),jobProfileLanguage.getLanguageLevel().getId(),jobProfileLanguage.getLanguage().getId());
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }


}
