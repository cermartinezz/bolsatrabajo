package com.bolsaTrabajo.model.jobInfo;

import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.compositeKeys.CandidateId;
import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;
import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;
import com.bolsaTrabajo.service.CandidateService;
import com.bolsaTrabajo.service.JobService;
import com.bolsaTrabajo.service.PostulantService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by mvip on 06-14-17.
 */
@Entity
@Table(name = "candidate")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.postulant", joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name = "primaryKey.job", joinColumns = @JoinColumn(name = "id_job"))
})

public class Candidate implements Serializable{
    private Logger logger = LoggerFactory.getLogger(Candidate.class);

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private JobService jobService;

    @Autowired
    Candidate candidate;

    private CandidateId primaryKey = new CandidateId();

    private boolean seleccionado;

    @Autowired
    public Candidate(CandidateService candidateService, PostulantService postulantService) {
        this.candidateService = candidateService;
        this.postulantService = postulantService;
    }

    public Candidate() {
    }

    @EmbeddedId
    public CandidateId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CandidateId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Postulant getPostulant(){
        return getPrimaryKey().getPostulant();
    }

    public void setPostulant(Postulant postulant){
        getPrimaryKey().setPostulant(postulant);
    }

    @Transient
    @JsonIgnore
    public Job getJob(){
        return getPrimaryKey().getJob();
    }

    public void setJob(Job job){
        getPrimaryKey().setJob(job);
    }

    @Column(name = "seleccionado")
    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Candidate save(String username, Candidate candidateFromRequest){
        Postulant postulant = postulantService.findByUsername(username);
        Job job = jobService.findById(candidateFromRequest.getJob().getId());
        CandidateId primaryKey = new CandidateId(postulant,job);

        Candidate newcandidate = new Candidate();
        newcandidate.setPostulant(primaryKey.getPostulant());
        newcandidate.setJob(primaryKey.getJob());
        newcandidate.setSeleccionado(candidateFromRequest.isSeleccionado());

        postulant.getCandidates().add(newcandidate);
        postulantService.save(postulant);
        return  newcandidate;
    }

    public List<Postulant> extraerCandidatosPorLenguaje(Set<Candidate> candidates, String code){
        List<Postulant> postulants = new ArrayList<>();
        for (Candidate candidate: candidates) {
            for (PostulantLanguage postulantLanguage: candidate.getPostulant().getPostulantLanguages()) {
                String codigo = postulantLanguage.getPrimaryKey().getLanguage().getCodigo();
                Boolean iguales = Objects.equals(codigo, code);
                if(iguales){
                    postulants.add(candidate.getPostulant());
                }
            }
        }
        return postulants;
    }

    public Set<Postulant> extraerCandidatosPorHabilidad(Set<Candidate> candidates, Integer skill_id){
        Set<Postulant> postulants = new HashSet<>();
        for (Candidate candidate: candidates) {
            for (PostulantSkill postulantSkill: candidate.getPostulant().getSkills()) {
                int id = postulantSkill.getPrimaryKey().getSkill().getId();
                boolean iguales = id == skill_id;
                if(iguales){
                    postulants.add(candidate.getPostulant());
                }
            }
        }
        return postulants;
    }

    public Set<Postulant> extraerCandidatosPorEducacion(Set<Candidate> candidates, String state){
        Set<Postulant> postulants = new HashSet<>();
        for (Candidate candidate: candidates) {
                String name = candidate.getPostulant().getStateOfEducation().name();
                boolean iguales = Objects.equals(name, state);
                if(iguales){
                    postulants.add(candidate.getPostulant());
                }
        }
        return postulants;
    }

    public Set<Postulant> extraerCandidatosPorHabilidadEIdiomas(Set<Candidate> candidates, Integer skill_id, String code){
        Set<Postulant> postulants = new HashSet<>();
        for (Candidate candidate: candidates) {
            for (PostulantSkill postulantSkill: candidate.getPostulant().getSkills()) {
                for(PostulantLanguage postulantLanguage: candidate.getPostulant().getPostulantLanguages()){
                    int id_skill = postulantSkill.getPrimaryKey().getSkill().getId();
                    String code_language = postulantLanguage.getPrimaryKey().getLanguage().getCodigo();
                    boolean iguales_en_habilidad = id_skill == skill_id;
                    boolean iguales_en_idioma = Objects.equals(code_language, code);
                    if(iguales_en_habilidad && iguales_en_idioma){
                        postulants.add(candidate.getPostulant());
                    }
                }
            }
        }
        return postulants;
    }

    public Set<Postulant> extraerCandidatosPorHabilidadYEducacion(Set<Candidate> candidates, Integer skill_id, String educacion){
        Set<Postulant> postulants = new HashSet<>();
        for (Candidate candidate: candidates) {
            for (PostulantSkill postulantSkill: candidate.getPostulant().getSkills()) {
                int id = postulantSkill.getPrimaryKey().getSkill().getId();
                String name = candidate.getPrimaryKey().getPostulant().getStateOfEducation().name();
                boolean igual_en_skill = id == skill_id;
                boolean igual_en_educacion = Objects.equals(name, educacion);
                if(igual_en_skill && igual_en_educacion){
                    postulants.add(candidate.getPostulant());
                }
            }
        }
        return postulants;
    }

    public Set<Postulant> extraerCandidatosPorIdiomaYEducacion(Set<Candidate> candidates, String lan_code, String educacion){
        Set<Postulant> postulants = new HashSet<>();
        for (Candidate candidate: candidates) {
            for (PostulantLanguage postulantLanguage: candidate.getPostulant().getPostulantLanguages()) {
                String code = postulantLanguage.getPrimaryKey().getLanguage().getCodigo();
                String name = candidate.getPrimaryKey().getPostulant().getStateOfEducation().name();
                boolean igual_en_idiomas = Objects.equals(code, lan_code);
                boolean igual_en_educacion = Objects.equals(name, educacion);
                if(igual_en_idiomas && igual_en_educacion){
                    postulants.add(candidate.getPostulant());
                }
            }
        }
        return postulants;
    }

    public Set<Postulant> extraerCandidatosPorIdiomaYEducacionYHabildiad(Set<Candidate> candidates, String lan_code, String educacion,Integer skill_id){
        Set<Postulant> postulants = new HashSet<>();
        for (Candidate candidate: candidates) {
            for (PostulantLanguage postulantLanguage: candidate.getPostulant().getPostulantLanguages()) {
                for(PostulantSkill postulantSkill: candidate.getPostulant().getSkills()){
                    String name = candidate.getPrimaryKey().getPostulant().getStateOfEducation().name();
                    String code = postulantLanguage.getPrimaryKey().getLanguage().getCodigo();
                    Integer id = postulantSkill.getPrimaryKey().getSkill().getId();
                    boolean igual_en_idiomas = Objects.equals(code, lan_code);
                    boolean igual_en_educacion = Objects.equals(name, educacion);
                    boolean igual_en_habilidad = Objects.equals(id, skill_id);
                    if(igual_en_idiomas && igual_en_educacion && igual_en_habilidad){
                        postulants.add(candidate.getPostulant());
                    }
                }
            }
        }
        return postulants;
    }
}
