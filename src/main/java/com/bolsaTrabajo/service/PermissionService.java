package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public Permission findByName(String name){ return permissionRepository.findByName(name); }

    public Permission findById(long id){ return permissionRepository.findById(id); }

    public List<Permission> getAllPermissions(){ return permissionRepository.findAll(); }

    public void save(Permission permission){ permissionRepository.save(permission); }

    public void delete(long id){ permissionRepository.delete(id); }
}
