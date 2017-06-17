package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mvip on 06-12-17.
 */
@Repository("departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department,Integer>{
    Department findByNombre(String nombre);
    Department findByCodigo(String codigo);


}
