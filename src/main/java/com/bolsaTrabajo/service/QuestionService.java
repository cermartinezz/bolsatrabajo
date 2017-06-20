package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.Question;

import java.util.List;

public interface QuestionService {
    void spSaveQuestion(Question question);
    //void save(Question question);
    List<Question> getAll();
    Question findById(long id);
    void deleteById(long id);
}
