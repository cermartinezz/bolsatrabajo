package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Language;
import com.bolsaTrabajo.model.LanguageLevel;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.compositeKeys.PostulantLanguageId;
import com.bolsaTrabajo.service.LanguageService;
import com.bolsaTrabajo.service.PostulantLanguageService;
import com.bolsaTrabajo.service.PostulantService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="postulants_languages")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.postulant", joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name = "primaryKey.language", joinColumns = @JoinColumn(name = "language_id"))
})
public class PostulantLanguage implements Serializable{

    private Logger logger = LoggerFactory.getLogger(PostulantLanguage.class);


    @Autowired
    private PostulantLanguageService postulantLanguageService;

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    PostulantLanguage postulantLanguage;

    private PostulantLanguageId primaryKey = new PostulantLanguageId();

    private LanguageLevel languageLevel;

    @Autowired
    public PostulantLanguage(PostulantLanguageService postulantLanguageService, PostulantService postulantService) {
        this.postulantLanguageService = postulantLanguageService;
        this.postulantService = postulantService;
    }

    public PostulantLanguage() {
    }

    @EmbeddedId
    public PostulantLanguageId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PostulantLanguageId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    @JsonIgnore
    public Postulant getPostulant(){
        return getPrimaryKey().getPostulant();
    }

    public void setPostulant(Postulant postulant){
        getPrimaryKey().setPostulant(postulant);
    }

    @Transient
    public Language getLanguage() {
        return getPrimaryKey().getLanguage();
    }

    public void setLanguage(Language language) {
        getPrimaryKey().setLanguage(language);
    }

    @OneToOne
    @JoinColumn(name = "languageLevel_id")
    public LanguageLevel getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(LanguageLevel languageLevel) {
        this.languageLevel = languageLevel;
    }

    public PostulantLanguage save(String username, PostulantLanguage postulantLanguageFromRequest){
        Postulant postulant = postulantService.findByUsername(username);
        Language language = languageService.findById(postulantLanguageFromRequest.getLanguage().getId());
        PostulantLanguageId primaryKey = new PostulantLanguageId(postulant,language);

        PostulantLanguage newLang = new PostulantLanguage();
        newLang.setPostulant(primaryKey.getPostulant());
        newLang.setLanguage(primaryKey.getLanguage());
        newLang.setLanguageLevel(postulantLanguageFromRequest.getLanguageLevel());

        postulant.getPostulantLanguages().add(newLang);
        postulantService.save(postulant);
        return newLang;
    }

    public boolean delete(String username,String code){

        Postulant postulant = postulantService.findByUsername(username);

        Language language = languageService.findByCodigo(code);

        PostulantLanguageId postulantLanguageId = new PostulantLanguageId();

        postulantLanguageId.setPostulant(postulant);
        postulantLanguageId.setLanguage(language);

        PostulantLanguage postulantLanguage = postulantLanguageService.postulantLanguage(postulantLanguageId);

        try{
            postulantLanguageService.delete(postulantLanguage);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
