package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.Department;

import java.util.List;

/**
 * Created by mvip on 06-12-17.
 */
public interface DepartmentService {
    List<Department> getAllDepartments();
    Department findByNombre(String nombre);
    Department findByCodigo(String codigo);
    Long count();
    void store(Department department);
}
