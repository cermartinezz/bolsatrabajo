package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Question;
import com.bolsaTrabajo.repositories.QuestionRepository;
import com.bolsaTrabajo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by or.merino on 12/06/2017.
 */
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    public void spSaveQuestion(Question question){
        questionRepository.saveQuestion(question.getId(),
                question.getD1(),
                question.getD2(),
                question.getD3(),
                question.getD4(),
                question.getEnunciado(),
                question.getPeso(),
                question.getRespuesta(),
                question.getSubArea().getId());
    }
    //public void save(Question question){ questionRepository.save(question); }

    public List<Question> getAll(){ return questionRepository.findAllByOrderByIdAsc();}

    public Question findById(long id){ return questionRepository.findById(id);}

    public void deleteById(long id){ questionRepository.deleteById(id);}
}
