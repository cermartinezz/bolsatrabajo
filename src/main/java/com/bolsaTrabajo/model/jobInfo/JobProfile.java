package com.bolsaTrabajo.model.jobInfo;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Job;
import com.bolsaTrabajo.service.JobProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="job_profiles")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SP_CREAR_PERFIL_TRABAJO",
                                    procedureName = "SP_CREAR_PERFIL_TRABAJO",
                                    parameters = {
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "NOMBRE", type = String.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "DESCRIPCION", type = String.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "EDAD_MAX", type = Integer.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "EDAD_MIN", type = Integer.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "COMPAÑIA_ID", type = Long.class),
                                        @StoredProcedureParameter(mode = ParameterMode.OUT,name = "ID_JP", type = Integer.class)
                                    }),
        @NamedStoredProcedureQuery(name="SP_ACTUALIZAR_PERFIL_TRABAJO",
                                    procedureName = "SP_ACTUALIZAR_PERFIL_TRABAJO",
                                    parameters = {
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "ID_JP", type=Integer.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "NOMBRE", type=String.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "DESCRIPCION", type=String.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "EDAD_MAX", type=Integer.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "EDAD_MIN", type=Integer.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN,name = "COMPAÑIA_ID", type=Long.class)
                                    })
})
public class JobProfile {

    @Autowired
    private JobProfileService jobProfileService;

    private Integer id;
    private String name;
    private String code;
    private String description;
    private Integer minAge;
    private Integer maxAge;
    private Company company;
    private Job job;

    @Autowired
    public JobProfile(JobProfileService jobProfileService){
        this.jobProfileService = jobProfileService;
    }

    public JobProfile() {
    }

    public JobProfile(String name, String description, Integer minAge, Integer maxAge) {
        this.name = name;
        this.description = description;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Size(max = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Min(value = 18)
    @Max(value = 100)
    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }


    @Min(value = 18)
    @Max(value = 100)
    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @ManyToOne
    @JoinColumn(name="company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToOne(mappedBy = "jobProfile")
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Integer save(JobProfile jobProfile){
        // TODO -- SI HUBIESE UNA LOGICA AQUI P.E ALGUN CALCULO SERIA IMPORTANTE ESTE METODO

        return this.jobProfileService.save(jobProfile); // TODO -- AQUI LLAMO AL SERVICIO QUE ES UN PROCEDIMIENTO EN LA BASE
    }

    public void update(JobProfile jobProfile){
        // TODO -- SI HUBIESE UNA LOGICA AQUI P.E ALGUN CALCULO SERIA IMPORTANTE ESTE METODO

        this.jobProfileService.update(jobProfile); // TODO -- AQUI LLAMO AL SERVICIO QUE ES UN PROCEDIMIENTO EN LA BASE
    }
}
