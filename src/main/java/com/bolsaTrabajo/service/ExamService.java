package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Exam;

import java.util.List;

public interface ExamService {
    void spSaveExam(Exam exam);
    List<Exam> getAll();
    Exam findById(long id);
    void deleteById(long id);
}
