package com.bolsaTrabajo.service;


import com.bolsaTrabajo.model.Questionary;

import java.util.List;

public interface QuestionaryService {
    void spSaveQues(Questionary quest);
    List<Questionary> getAll();
    Questionary findById(long id);
    void deleteById(long id);
}
