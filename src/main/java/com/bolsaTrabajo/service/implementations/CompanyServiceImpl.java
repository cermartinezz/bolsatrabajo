package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.repositories.CompanyRepository;
import com.bolsaTrabajo.repositories.RoleRepository;
import com.bolsaTrabajo.repositories.UserRepository;
import com.bolsaTrabajo.service.CompanyService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(Company company) {
        company.setPassword(bCryptPasswordEncoder.encode(company.getPassword()));
        company.setActive(1);
        HashSet<Role> roleCollection = new HashSet<>();
        roleCollection.add(roleRepository.findByName("COMPANY"));

        company.setRoles(roleCollection);
        companyRepository.save(company);
    }

    @Override
    public Company findByUsername(String username) {
        return companyRepository.findByUsername(username);
    }

    @Override
    public void updateCompany(Company company) {
      companyRepository.save(company);
    }

    @Override
    public Company findById(long id){return companyRepository.findById(id);}

    public void delete(Company company){companyRepository.delete(company);}
}

