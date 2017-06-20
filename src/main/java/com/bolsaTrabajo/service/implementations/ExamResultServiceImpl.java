package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.ExamResult;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.repositories.ExamResultRepository;
import com.bolsaTrabajo.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExamResultServiceImpl implements ExamResultService{
    @Autowired
    ExamResultRepository examResultRepository;

    public void save(ExamResult examResult){ examResultRepository.save(examResult);}

    public ExamResult findById(long id){ return examResultRepository.findById(id);}

    public List<ExamResult> getAll(){return examResultRepository.findAll();}

    public void deleteById(long id){ examResultRepository.deleteById(id);}

    public List<ExamResult> findAllByPostulant(Postulant postulant){return examResultRepository.findAllByPostulant(postulant);}
}
