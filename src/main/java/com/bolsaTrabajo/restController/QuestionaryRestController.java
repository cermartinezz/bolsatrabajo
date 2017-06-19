package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Exam;
import com.bolsaTrabajo.model.Question;
import com.bolsaTrabajo.model.Questionary;
import com.bolsaTrabajo.model.catalog.SubArea;
import com.bolsaTrabajo.service.ExamService;
import com.bolsaTrabajo.service.QuestionService;
import com.bolsaTrabajo.service.QuestionaryService;
import com.bolsaTrabajo.service.SubAreaService;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/questionary")
public class QuestionaryRestController {
    @Autowired
    ExamService examService;

    @Autowired
    SubAreaService subAreaService;

    @Autowired
    QuestionaryService questionaryService;

    @Autowired
    QuestionService questionService;

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

    // -------------------Agregar Pregunta a Questionario-------------------------------------------
    @RequestMapping(value = "/question",method = RequestMethod.POST)
    public RedirectView addQuestion(@RequestParam("idPreg") long idPreg, @RequestParam("idExam") long idExam,RedirectAttributes attributes){
        Exam exam = examService.findById(idExam);
        Question question = questionService.findById(idPreg);

        Questionary questionary = new Questionary();

        questionary.setExam(exam);
        questionary.setQuestion(question);

        try{
            questionaryService.spSaveQues(questionary);
        }catch (Exception e){
            attributes.addFlashAttribute("message", StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/cuestionario/"+idExam);
        }
        attributes.addFlashAttribute("messageSuccess","Se Agregó correctamente la pregunta");
        return new RedirectView("/examenes/cuestionario/"+idExam);
    }

    // -------------------Eliminar Pregunta Examen-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") long id, RedirectAttributes attributes) {
        questionaryService.deleteById(id);
        attributes.addFlashAttribute("messageSuccess","La pregunta se elimino correctamente del examen pero no del banco de preguntas");
        return new RedirectView("/examenes");
    }

}
