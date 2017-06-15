package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Question;
import com.bolsaTrabajo.service.QuestionService;
import com.bolsaTrabajo.service.SubAreaService;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/question")
public class QuestionRestController {
    @Autowired
    QuestionService questionService;

    @Autowired
    SubAreaService subAreaService;

    // -------------------Crear Pregunta-------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(Question question, RedirectAttributes attributes){

        try{
            questionService.spSaveQuestion(question);
        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            attributes.addFlashAttribute("message", StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/preguntas/crear");
        }
        attributes.addFlashAttribute("messageSuccess","La pregunta se añadio correctamente");
        return new RedirectView("/examenes/preguntas");
    }

    // -------------------Actualizar Pregunta-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("id") long id, @RequestParam("subareaid") long subareaid, Question question, RedirectAttributes attributes) {
        question.setSubArea(subAreaService.findById(subareaid));
        if (question==null) {
            attributes.addFlashAttribute("message","No se pudo actualizar el Area");
            return new RedirectView("/examenes/preguntas/"+id);
        }
        try{
            questionService.spSaveQuestion(question);
        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            attributes.addFlashAttribute("message",StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/preguntas/"+id);
        }
        attributes.addFlashAttribute("messageSuccess","La pregunta se actualizo correctamente");
        return new RedirectView("/examenes/preguntas");
    }

    // -------------------Eliminar Pregunta-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") long id,RedirectAttributes attributes) {
        questionService.deleteById(id);
        attributes.addFlashAttribute("messageSuccess","La se elimino correctamente");
        return new RedirectView("/examenes/preguntas");
        
    }
}
