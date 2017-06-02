package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("permissionRepository")
public interface PermissionRepository extends JpaRepository<Permission,Long>{
    Permission findByName(String name);
    Permission findById(long id);
    void deleteById(Long id);
}
