package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Role;

public interface RoleService {
    public Role findByName(String name);
    Long count();
    void save(Role role);
}
