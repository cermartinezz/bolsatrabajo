package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jobRepository")
public interface JobRepository extends JpaRepository<Job,Long> {
    Job findById(long id);
    List<Job> findAllByCompany(Company company);
    Job findByCodJ(String cod);
}
