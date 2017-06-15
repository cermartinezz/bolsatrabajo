package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Exam;
import com.bolsaTrabajo.service.ExamService;
import com.bolsaTrabajo.service.SubAreaService;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/exam")
public class ExamRestController {
    @Autowired
    ExamService examService;

    @Autowired
    SubAreaService subAreaService;

    // -------------------Crear Examen-------------------------------------------
    @RequestMapping(method = RequestMethod.POST)
    public RedirectView store(Exam exam, RedirectAttributes attributes){
        try{
            exam.setPublicado(0);
            examService.spSaveExam(exam);
        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            attributes.addFlashAttribute("message", StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/crear");
        }
        attributes.addFlashAttribute("messageSuccess","Se creo el examen correctamente");
        return new RedirectView("/examenes");
    }

    // -------------------Actualizar Examen-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public RedirectView update(@PathVariable("id") long id, @RequestParam("ids") long ids, Exam exam, RedirectAttributes attributes) {
        //Exam newExam = examService.findById(id);

        if (exam==null) {
            attributes.addFlashAttribute("message","No se pudo actualizar el examen");
            return new RedirectView("/examenes/"+id);
        }

        try{

            exam.setSubArea(subAreaService.findById(ids));
            examService.spSaveExam(exam);
        }catch(Exception e){
            // Esto es porq regresa ora-20001 mensaje importante ora-20001 "spring....."
            // solo deja el mensaje importante
            attributes.addFlashAttribute("message",StringUtils.clearMessage(e.getCause().getCause().getMessage()));
            return new RedirectView("/examenes/"+id);
        }
        attributes.addFlashAttribute("messageSuccess","El Examen se actualizo correctamente");
        return new RedirectView("/examenes");
    }
    // -------------------Eliminar Examen-------------------------------------------
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable("id") long id,RedirectAttributes attributes) {
        examService.deleteById(id);
        attributes.addFlashAttribute("messageSuccess","El Examen se elimino correctamente");
        return new RedirectView("/examenes");
    }

}
