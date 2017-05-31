package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.repositories.RoleRepository;
import com.bolsaTrabajo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Long count() {
        return roleRepository.count();
    }

    public Role findById(long id){ return roleRepository.findById(id);}

    public List<Role> getAllRoles(){ return roleRepository.findAll(); }

    public void save(Role role){ roleRepository.save(role); }

    public void delete(long id){ roleRepository.delete(id);}

}
