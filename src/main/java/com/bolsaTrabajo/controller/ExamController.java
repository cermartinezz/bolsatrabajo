package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.*;
import com.bolsaTrabajo.model.catalog.SubArea;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
public class ExamController {
    @Autowired
    UserService userService;

    @Autowired
    PostulantService postulantService;

    @Autowired
    SubAreaService subAreaService;

    @Autowired
    ExamService examService;

    @Autowired
    ExamResultService examResultService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionaryService questionaryService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/examenes", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("examenes",examService.getAll());
        return "admin/exams/index";
    }

    @RequestMapping(value = "/examenes/crear", method = RequestMethod.GET)
    public String crear(Model model){
        model.addAttribute("user",Auth.auth());
        model.addAttribute("examen",new Exam());
        model.addAttribute("subareas",subAreaService.getAll());
        return "admin/exams/crear";
    }

    @RequestMapping(value = "/examenes/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model){
        Exam exam = examService.findById(id);
        model.addAttribute("user", Auth.auth());
        model.addAttribute("exam", exam);
        return "admin/exams/editar";
    }

    //Examenes para Postulantes
    @RequestMapping(value = "/examenes/postulante", method = RequestMethod.GET)
    public String indexPostulan(Model model){

        HashSet<Exam> examsCollection = new HashSet<>(examService.findByPublicado(1));
        List<ExamResult> examResults = examResultService.findAllByPostulant(postulantService.findByUsername(Auth.auth().getName()));

        List<Exam> exams = new ArrayList<>();
        for (int i = 0; i<=examResults.size()-1;i++){
            exams.add(examResults.get(i).getQuestionary().getExam());
        }

        Set<Exam> examSet = new HashSet<>(exams);
        exams = new ArrayList<>(examSet);
        Map<Exam,List<Questionary>> result = new HashMap<>();
        for (int i=0;i<=exams.size()-1;i++){
            if (examsCollection.contains(exams.get(i)))
                examsCollection.remove(exams.get(i));

            result.put(exams.get(i),questionaryService.findAllByExam(exams.get(i)));
        }


        model.addAttribute("user", Auth.auth());
        model.addAttribute("resultados",exams);
        model.addAttribute("examenes",examsCollection);//Si el examen esta publicado entonces el atributo es 1
        return "admin/exams/index_postulant";
    }

    @RequestMapping(value = "/examenes/postulante/{id}", method = RequestMethod.GET)
    public String show(@PathVariable long id, Model model){
        //el id que recibe es el de examen
        Exam exam = examService.findById(id);
        List<ExamResult> examResults = new ArrayList<>();
        model.addAttribute("user", Auth.auth());

        model.addAttribute("examResults", questionService.getAll());
        model.addAttribute("examen",exam);
        return "admin/exams/resolver_exam";
    }
}
