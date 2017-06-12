package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.bolsaTrabajo.repositories.JobProfileRepository;
import com.bolsaTrabajo.service.JobProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JobProfileServiceImpl implements JobProfileService {

    @Autowired
    private JobProfileRepository jobProfileRepository;

    @Override
    public Integer save(JobProfile jobProfile) {
        Integer id= jobProfileRepository.store( jobProfile.getName(),
                                    jobProfile.getDescription(),
                                    jobProfile.getMaxAge(),
                                    jobProfile.getMinAge(),
                                    jobProfile.getCompany().getId());
        return id;
    }

    @Override
    public void update(JobProfile jobProfile) {
        jobProfileRepository.update(jobProfile.getId(),
                jobProfile.getName(),
                jobProfile.getDescription(),
                jobProfile.getMaxAge(),
                jobProfile.getMinAge(),
                jobProfile.getCompany().getId());
    }

    @Override
    public void delete(JobProfile jobProfile) {

    }

    @Override
    public List<JobProfile> findAllByCompany(Company company) {
        return jobProfileRepository.findAllByCompany(company);
    }

    @Override
    public JobProfile findById(int id) {
        return jobProfileRepository.findById(id);
    }
}
