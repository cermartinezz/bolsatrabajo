package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by enan0 on 13/4/2017.
 */
@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository<Company,Long> {

    Company findById(long id);
    Company findByCompanyName(String comp_name);

}
