package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.catalog.Language;
import com.bolsaTrabajo.model.catalog.LanguageLevel;
import com.bolsaTrabajo.model.compositeKeys.JobProfileLanguageId;
import com.bolsaTrabajo.model.jobInfo.JobProfile;
import com.bolsaTrabajo.model.jobInfo.JobProfileLanguage;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfil/{id}/languajes")
public class JobProfileLanguageRestController {

    @Autowired
    private JobProfileLanguage JobProfileLanguage;
    @Autowired
    private JobProfileLanguageService jobProfileLanguageService;
    @Autowired
    private LanguageLevelService languageLevelService;
    @Autowired
    private JobProfileService jobProfileService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private SkillService skillService;

    private HttpHeaders headers;

    public JobProfileLanguageRestController() {
        this.headers = new HttpHeaders();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody JobProfileLanguage jobProfileLanguage, @PathVariable Integer id) {

        try {
            this.JobProfileLanguage.save(jobProfileLanguage, id);
        } catch (Exception e) {
            String message = StringUtils.clearMessage(e.getCause().getCause().getMessage());
            this.headers.set("message", message);
            return new ResponseEntity(this.headers, HttpStatus.CONFLICT);
        }
        this.headers.set("message", "Se guardo su requisito de idioma");
        return new ResponseEntity(this.headers, HttpStatus.OK);

    }

    @PutMapping("/actualizar")
    public void update() {

    }

    @DeleteMapping("/{id_lang}/nivel/{level_id}eliminar")
    public void delete(@PathVariable Integer id,@PathVariable Integer id_lang,@PathVariable Integer level_id) {
        Language lan = languageService.findById(id_lang);
        LanguageLevel  lvl = languageLevelService.findById(level_id);
        JobProfile profile = jobProfileService.findById(id);

        JobProfileLanguageId jobProfileLanguageId = new JobProfileLanguageId();
        jobProfileLanguageId.setJobProfile(profile);
        jobProfileLanguageId.setLanguage(lan);
        jobProfileLanguageId.setLanguageLevel(lvl);

        JobProfileLanguage jobProfileLanguage = new JobProfileLanguage();
        jobProfileLanguage.setPrimaryKey(jobProfileLanguageId);

        jobProfileLanguageService.delete(jobProfileLanguage);
    }
}
