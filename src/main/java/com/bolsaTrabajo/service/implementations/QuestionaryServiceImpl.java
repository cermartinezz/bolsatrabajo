package com.bolsaTrabajo.service.implementations;



import com.bolsaTrabajo.model.Questionary;
import com.bolsaTrabajo.repositories.QuestionaryRespository;
import com.bolsaTrabajo.service.QuestionService;
import com.bolsaTrabajo.service.QuestionaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionaryServiceImpl implements QuestionaryService {
    @Autowired
    QuestionaryRespository questionaryRespository;
    public void spSaveQues(Questionary ques){
        questionaryRespository.saveQues(ques.getId(), //primero el id
                ques.getExam().getId(),
                ques.getQuestion().getId()
               );
    }

    public List<Questionary> getAll(){ return questionaryRespository.findAll();}

    public Questionary findById(long id){return questionaryRespository.findById(id);}

    public void deleteById(long id){ questionaryRespository.deleteById(id);}

}
