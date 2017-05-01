package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Company;

public interface CompanyService {
    void save(Company company);
    Company findByUsername(String username);
}
