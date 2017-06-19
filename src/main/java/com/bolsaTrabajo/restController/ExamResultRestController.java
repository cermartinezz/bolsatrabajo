package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.Exam;
import com.bolsaTrabajo.model.ExamResult;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.service.ExamResultService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.QuestionaryService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/examresult")
public class ExamResultRestController {

    @Autowired
    PostulantService postulantService;

    @Autowired
    ExamResultService examResultService;

    @Autowired
    QuestionaryService questionaryService;

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public RedirectView store(@RequestBody String json, @PathVariable("id") long id, RedirectAttributes attributes){
        //Separando cadena donde encuentre un & del request body
        String delimitador= "[&]+";
        String[] palabrasSeparadas = json.split(delimitador);

        //convirtiendolo a string
        List<String> palabras = new ArrayList<>(Arrays.asList(palabrasSeparadas));
        if(palabras.get(0).startsWith("_csrf"))
            palabras.remove(0);

        Postulant postulant = postulantService.findByUsername(Auth.auth().getName());

        //Nuevo delimitador
        delimitador= "[=]+";
        //Variable para el bucle
        String[]subPalabras;

        for (int i=0; i<=palabras.size()-1;i+=2){
            ExamResult examResult = new ExamResult();

            //El postulante logueado sera el unico que haga, el examen
            //un administrador no lo puede hacer por el
            examResult.setPostulant(postulant);

            //Extrayendo y guardando la respuesta
            subPalabras = palabras.get(i).split(delimitador);
            examResult.setRespuesta(subPalabras[1].replace("+"," "));
            //Extrayendo y guardando el cuestionario
            subPalabras = palabras.get(i+1).split(delimitador);
            examResult.setQuestionary(questionaryService.findById(Long.parseLong(subPalabras[1])));
            //Guardando el resultado
            examResultService.save(examResult);
        }
        attributes.addFlashAttribute("messageSuccess","Se creo el examen correctamente");
        return new RedirectView("/examenes/postulante");
    }
}
