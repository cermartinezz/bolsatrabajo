package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.catalog.Department;
import com.bolsaTrabajo.repositories.DepartmentRepository;
import com.bolsaTrabajo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mvip on 06-12-17.
 */
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findByNombre(String nombre) {
        return departmentRepository.findByNombre(nombre);
    }

    @Override
    public Department findByCodigo(String codigo) {
        return departmentRepository.findByCodigo(codigo);
    }


    @Override
    public Long count() {
        return departmentRepository.count();
    }

    @Override
    public void store(Department department) {
        departmentRepository.save(department);

    }
}
