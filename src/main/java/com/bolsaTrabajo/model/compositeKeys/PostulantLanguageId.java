package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.catalog.Language;
import com.bolsaTrabajo.model.Postulant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class PostulantLanguageId implements Serializable{

    private Postulant postulant;
    private Language language;

    public PostulantLanguageId() {
    }

    public PostulantLanguageId(Postulant postulant, Language language) {
        this.postulant = postulant;
        this.language = language;
    }

    @ManyToOne
    @JsonIgnore
    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @ManyToOne
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "PostulantLanguageId{" +
                "postulant=" + postulant +
                ", language=" + language +
                '}';
    }
}
