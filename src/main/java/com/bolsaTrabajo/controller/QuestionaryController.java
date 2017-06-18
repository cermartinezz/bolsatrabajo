package com.bolsaTrabajo.controller;


import com.bolsaTrabajo.model.Exam;
import com.bolsaTrabajo.model.Question;
import com.bolsaTrabajo.model.Questionary;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.ExamService;
import com.bolsaTrabajo.service.QuestionaryService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class QuestionaryController {
    @Autowired
    UserService userService;

    @Autowired
    ExamService examService;

    @Autowired
    QuestionaryService questionaryService;

    @ModelAttribute("usr")
    public User globalUser(Model model) {
        User u = userService.findByUsername(Auth.auth().getName());
        return u;
    }

    @RequestMapping(value = "/examenes/cuestionario", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("user", Auth.auth());
        model.addAttribute("cuestionario",questionaryService.getAll());
        return "admin/exams/questionary/index";
    }

    @RequestMapping(value = "/examenes/cuestionario/{id}", method = RequestMethod.GET)
    public String show(@PathVariable long id, Model model){
        //el id que recibe es el de examen
        Exam exam = examService.findById(id);

        HashSet<Question> questionsSubArea = new HashSet<>(exam.getSubArea().getQuestions());
        List<Questionary> questionsforExam = new ArrayList<>(exam.getQuestionaries());


        for(int i = 0; i <= questionsforExam.size()-1;i++){
            Question q = questionsforExam.get(i).getQuestion();
            if (questionsSubArea.contains(q))
                questionsSubArea.remove(q);
        }
        model.addAttribute("user", Auth.auth());
        model.addAttribute("questions",questionsSubArea);
        model.addAttribute("examen",exam);
        return "admin/exams/questionary/cuestionario";
    }
}
