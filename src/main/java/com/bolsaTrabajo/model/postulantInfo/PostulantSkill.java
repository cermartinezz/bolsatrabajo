package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.compositeKeys.PostulantSkillId;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.PostulantSkillService;
import com.bolsaTrabajo.service.SkillService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="postulants_skills")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.postulant",
                joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name = "primaryKey.skill",
                joinColumns = @JoinColumn(name = "skill_id"))
})
public class PostulantSkill implements Serializable{

    @Autowired
    private  PostulantSkillService postulantSkillService;

    @Autowired
    private  PostulantService postulantService;

    @Autowired
    private  SkillService skillService;

    @Autowired
    private PostulantSkill postulantSkill;


    private PostulantSkillId primaryKey = new PostulantSkillId();


    @Autowired
    public PostulantSkill(PostulantSkillService postulantSkillService, PostulantService postulantService, SkillService skillService) {
        this.postulantSkillService = postulantSkillService;
        this.postulantService = postulantService;
        this.skillService = skillService;
    }

    public PostulantSkill() {
    }

    @EmbeddedId
    public PostulantSkillId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PostulantSkillId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    @JsonIgnore
    public Postulant getPostulant() {
        return getPrimaryKey().getPostulant();
    }

    public void setPostulant(Postulant postulant) {
        getPrimaryKey().setPostulant(postulant);
    }

    @Transient
    public Skill getSkill() {
        return getPrimaryKey().getSkill();
    }

    public void setSkill(Skill skill) {
        getPrimaryKey().setSkill(skill);
    }


    public PostulantSkill save(String username, PostulantSkill postulantSkillFromRequest) {

        Postulant postulant = postulantService.findByUsername(username);

        Skill skill = skillService.findSkillByCodigo(postulantSkillFromRequest.getSkill().getCodigo());

        PostulantSkillId primaryKey = new PostulantSkillId(postulant,skill);

        PostulantSkill newSkill = new PostulantSkill();

        newSkill.setPostulant(primaryKey.getPostulant());

        newSkill.setSkill(primaryKey.getSkill());


        postulant.getSkills().add(newSkill);

        postulantService.save(postulant);

        return newSkill;
    }

    public boolean delete(String username,String code){

        Postulant postulant = postulantService.findByUsername(username);

        Skill skill = skillService.findSkillByCodigo(code);

        PostulantSkillId postulantSkillId = new PostulantSkillId();

        postulantSkillId.setPostulant(postulant);

        postulantSkillId.setSkill(skill);

        PostulantSkill postulantSkill = postulantSkillService.postulantSkill(postulantSkillId);

        try{
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
