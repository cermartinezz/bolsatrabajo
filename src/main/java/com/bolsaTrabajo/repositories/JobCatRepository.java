package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.JobCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by keepercito on 05-01-17.
 */
@Repository("jobCatRepository")
public interface JobCatRepository extends JpaRepository<JobCat,Long> {
    JobCat findById(long id);
    JobCat findByPuesto(String job);
}
