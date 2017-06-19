package com.bolsaTrabajo.service;


import com.bolsaTrabajo.model.Exam;
import com.bolsaTrabajo.model.Questionary;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

public interface QuestionaryService {
    void spSaveQues(Questionary quest);
    List<Questionary> getAll();
    Questionary findById(long id);
    void deleteById(long id);
    List<Questionary> findAllByExam(Exam exam);
    //Stream<Questionary> findQuestionsIdsForExam(long examId);
}
