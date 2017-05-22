package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name){return roleRepository.findByName(name); }

    public Role findById(long id){ return roleRepository.findById(id);}

    public List<Role> getAllRoles(){ return roleRepository.findAll(); }

    public void save(Role role){ roleRepository.save(role); }

    public void delete(long id){ roleRepository.deleteById(id);}

}
