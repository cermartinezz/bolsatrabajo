package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.repositories.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by enan0 on 13/4/2017.
 */
public class CompanyService {

    public static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompany(long id){
        return companyRepository.findById(id);
    }

    public Company getCompany(String nom){
        return companyRepository.findByCompanyName(nom);
    }

    public void saveCompany(Company c){
        companyRepository.save(c);
    }

    public void deleteCompany(Company c){ companyRepository.delete(c);}
}
