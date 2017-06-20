package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.ExamResult;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.Questionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult,Long>{
    ExamResult findById(long id);
    List<ExamResult> findAllByPostulant(Postulant postulant);
}
