package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CompanyService {
    void save(Company company);
    Company findByUsername(String username);
    Company findById(long id);
    void updateCompany(Company company);


}


