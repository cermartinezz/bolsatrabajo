package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.Exam;
import com.bolsaTrabajo.repositories.ExamRespository;
import com.bolsaTrabajo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExamServiceImpl implements ExamService{
    @Autowired
    ExamRespository examRespository;

    public void spSaveExam(Exam exam){
        examRespository.saveExam(exam.getId(), //primero el id
                exam.getFecha(),
                exam.getInstrucciones(),
                exam.getPublicado(),
                exam.getSubArea().getId(),
                exam.getTitulo());   //titulo del examen
    }

    public List<Exam> getAll(){ return examRespository.findAll();}

    public Exam findById(long id){return examRespository.findById(id);}

    public void deleteById(long id){ examRespository.deleteById(id);}

    public List<Exam> findByPublicado(int publicado){return examRespository.findByPublicado(publicado); }
}

