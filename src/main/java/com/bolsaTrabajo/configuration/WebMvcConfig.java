package com.bolsaTrabajo.configuration;

import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.service.implementations.*;
import com.bolsaTrabajo.validator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CertificationService certificationService(){
        return new CertificationServiceImpl();
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public PostulantService postulantService(){
        return new PostulantServiceImpl();
    }

    @Bean
    public RoleService roleService(){
        return new RoleServiceImpl();
    }

    @Bean
    public SecurityService securityService(){
        return new SecurityServiceImpl();
    }

    @Bean InstitutionService institutionService() {
        return  new InstitutionServiceImpl();
    }

    @Bean
    public RecommendationService postulantRecomendationService(){
        return new RecommendationsServiceImpl();
    }

    @Bean PostulantSkillService postulantSkillService() {
        return new PostulantSkillServiceImpl();
    }

    @Bean
    PostulantCertificationService postulantCertificationService() {
        return new PostulantCertificationServiceImpl();
    }

    @Bean
    public PublicationService publicationService(){ return  new PublicationService();}

    @Bean
    public PublicationValidator publicationValidator(){
        return new PublicationValidator();
    }
    @Bean
    public SkillService skillService(){
        return new SkillService();
    }

    @Bean
    public CompanyCatService companyService(){ return new CompanyCatService(); }

    @Bean
    public JobCatService jobCatService(){ return new JobCatService(); }

    @Bean
    public AcademicTitleCatService academicTitleCatService(){ return new AcademicTitleCatService(); }

    @Bean
    public SkillCategoryService skillCategoryService(){
        return new SkillCategoryService();
    }

    @Bean
    public SkillValidator skillValidator(){
        return new SkillValidator();
    }

    @Bean
    public CertificationValidator certificationValidator() {

        return new CertificationValidator();
    }

    @Bean
    public InstitutionValidator institutionValidator(){

        return new InstitutionValidator();
    }

    @Bean
    public RecommendationValidator recomendationsValidator(){
        return new RecommendationValidator();
    }

    @Bean
    public PostulantCertification postulantCertification(){
        return new PostulantCertification();
    }


}

