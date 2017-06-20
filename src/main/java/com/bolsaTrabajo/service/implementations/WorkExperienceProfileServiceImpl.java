package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.jobInfo.WorkExperienceProfile;
import com.bolsaTrabajo.repositories.WorkExperienceProfileRepository;
import com.bolsaTrabajo.service.WorkExperienceProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

public class WorkExperienceProfileServiceImpl implements WorkExperienceProfileService {

    @Autowired
    WorkExperienceProfileRepository workExperienceProfileRepository;

    private HttpHeaders httpHeaders;
    public WorkExperienceProfileServiceImpl() {
        this.httpHeaders = new HttpHeaders();
    }

    @Override
    public void save(WorkExperienceProfile workExperienceProfile) {
            this.workExperienceProfileRepository.store(
                    workExperienceProfile.getJobProfile().getId(),
                    (int) workExperienceProfile.getJobCat().getId(),
                    workExperienceProfile.getYearOfExperience(),
                    workExperienceProfile.getJobCat().getPuesto()
            );
    }

    @Override
    public void delete(WorkExperienceProfile workExperienceProfile) {
        workExperienceProfileRepository.delete(workExperienceProfile);
    }
}
