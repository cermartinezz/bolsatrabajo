package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Exam;
import com.bolsaTrabajo.model.Questionary;
import com.bolsaTrabajo.service.ExamService;
import com.bolsaTrabajo.service.QuestionaryService;
import com.bolsaTrabajo.service.SubAreaService;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/cuestionario")
public class QuestionaryRestController {
    @Autowired
    ExamService examService;

    @Autowired
    QuestionaryService questionaryService;

    // -------------------Crear Cuestionario-------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(Questionary questionary, RedirectAttributes attributes){
        try{
            //questionary.setPublicado(0);
            questionaryService.spSaveQues(questionary);
        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            attributes.addFlashAttribute("message", StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/questionary/crear");
        }
        attributes.addFlashAttribute("messageSuccess","Se creo el examen correctamente");
        return new RedirectView("/examenes/cuestionario");
    }

}
