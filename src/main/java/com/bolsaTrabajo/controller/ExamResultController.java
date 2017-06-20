package com.bolsaTrabajo.controller;

import com.bolsaTrabajo.model.*;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class ExamResultController {
    @Autowired
    UserService userService;

    @Autowired
    PostulantService postulantService;

    @Autowired
    ExamService examService;

    @Autowired
    ExamResultService examResultService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/examenes/resultado/{id}", method = RequestMethod.GET)
    public String show(@PathVariable long id, Model model){
        int pesoTotal=0;
        double factor, nota = 0;

        //el id que recibe es el de examen
        Exam exam = examService.findById(id);
        HashSet<Questionary> questionaries = new HashSet<>(exam.getQuestionaries());
        List<ExamResult> examResults = examResultService.findAllByPostulant(postulantService.findByUsername(Auth.auth().getName()));
        List<ExamResult> resultados = new ArrayList<>();

        for (int i = 0; i<=examResults.size()-1;i++){
            if (questionaries.contains(examResults.get(i).getQuestionary())){
                resultados.add(examResults.get(i));
                pesoTotal+=examResults.get(i).getQuestionary().getQuestion().getPeso();
            }
        }

        factor = 10/pesoTotal;

        for (int i=0;i<=resultados.size()-1;i++){
            nota = nota + (resultados.get(i).getQuestionary().getQuestion().getPeso()*factor);
        }
        model.addAttribute("user", Auth.auth());
        model.addAttribute("resultados",resultados);
        model.addAttribute("calificacion",nota);
        model.addAttribute("examen",exam);
        return "admin/exams/results_exam";
    }
}
