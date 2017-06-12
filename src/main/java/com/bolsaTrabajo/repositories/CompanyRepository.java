package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    Company findByUsername(String username);
    Company findById(Long id);
}
