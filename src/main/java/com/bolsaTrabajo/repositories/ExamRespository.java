package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ExamRespository extends JpaRepository<Exam,Long> {
    @Procedure(name="CA_EXAM") //el mismo nombre que el proc.
    void saveExam(@Param("E_ID") long e_id,
                     @Param("E_FECHA") Date e_fecha,
                     @Param("E_INSTRUCCIONES") String e_instrucciones,
                     @Param("E_PUBLICADO") int e_publicado,
                     @Param("E_SUBAREA_ID") long e_subarea_id,
                     @Param("E_TITULO") String e_titulo);
    Exam findById(long id);
}
