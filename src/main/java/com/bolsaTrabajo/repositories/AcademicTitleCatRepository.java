package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by keepercito on 05-01-17.
 */
@Repository("academicTitleCatRepository")
public interface AcademicTitleCatRepository extends JpaRepository<AcademicTitleCat,Long> {
    AcademicTitleCat findById(long id);
    AcademicTitleCat findByTitulo(String title);
}
