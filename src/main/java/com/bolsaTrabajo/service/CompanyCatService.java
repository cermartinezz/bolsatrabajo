package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.CompanyCat;
import com.bolsaTrabajo.repositories.CompanyCatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by enan0 on 13/4/2017.
 */
public class CompanyCatService {

    @Autowired
    private CompanyCatRepository companyCatRepository;

    public List<CompanyCat> getAllCompanies(){
        return companyCatRepository.findAll();
    }

    public CompanyCat getCompany(long id){
        return companyCatRepository.findById(id);
    }

    public CompanyCat getCompany(String nom){
        return companyCatRepository.findByCompanyName(nom);
    }

    public void saveCompany(CompanyCat c){
        companyCatRepository.save(c);
    }

    public void deleteCompany(CompanyCat c){ companyCatRepository.delete(c);}
}
