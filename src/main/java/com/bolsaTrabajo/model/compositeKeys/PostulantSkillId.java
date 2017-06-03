package com.bolsaTrabajo.model.compositeKeys;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Skill;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PostulantSkillId implements Serializable {

    private Postulant postulant;
    private Skill skill;

    public PostulantSkillId(Postulant postulant, Skill skill) {
        this.postulant = postulant;
        this.skill = skill;
    }

    public PostulantSkillId() {
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
    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
