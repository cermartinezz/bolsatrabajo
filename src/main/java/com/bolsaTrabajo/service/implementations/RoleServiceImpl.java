package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.repositories.RoleRepository;
import com.bolsaTrabajo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
