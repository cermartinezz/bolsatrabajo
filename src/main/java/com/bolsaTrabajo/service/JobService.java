package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.catalog.Department;

import java.util.List;

/**
 * Created by mendez on 06-02-17.
 */
public interface JobService {
    void save(Job job);
    List<Job> findAllByCompany(Company company);
    Job findById(long id);
    Job findByCodJ(String cod);
    List<Job> findByCategory(String category);
    List<Job> findByDepartment(Department department);
    List<Job> findByCategoryAndDepartment(String category,Department department);
    void updateJob(Job job);
    void deleteById(long id);
    List<Job> getAllJobs();

}
