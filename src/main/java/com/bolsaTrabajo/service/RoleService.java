
package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Role;

import java.util.List;

public interface RoleService {
    public Role findByName(String name);
    List<Role> getAllRoles();
    Long count();
    Role findById(long id);
    void delete(long id);
    void save(Role role);
}
