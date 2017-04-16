package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    Role findById(long id);
}
