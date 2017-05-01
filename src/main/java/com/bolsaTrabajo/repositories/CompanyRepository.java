package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByEmail(String email);
    Company findByUsername(String username);
}
