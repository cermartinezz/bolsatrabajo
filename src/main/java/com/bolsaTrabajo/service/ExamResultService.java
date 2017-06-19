package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.ExamResult;
import com.bolsaTrabajo.model.Postulant;

import java.util.List;

public interface ExamResultService {
    void save(ExamResult examResult);

    ExamResult findById(long id);

    List<ExamResult> getAll();

    void deleteById(long id);

    List<ExamResult> findAllByPostulant(Postulant postulant);
}
