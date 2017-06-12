package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.jobInfo.JobProfile;

import java.util.List;

public interface JobProfileService {

    Integer save(JobProfile jobProfile);
    void update(JobProfile jobProfile);
    void delete(JobProfile jobProfile);

    List<JobProfile> findAllByCompany(Company company);

    JobProfile findById(int id);
}
