package com.bolsaTrabajo.configuration;

import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.service.implementations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CertificationService certificationService(){
        return new CertificationService();
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
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityService securityService(){
        return new SecurityServiceImpl();
    }

    @Bean InstitutionService institutionService() { return  new InstitutionServiceImpl(); }


    @Bean
    public PublicationService publicationService(){ return  new PublicationService();}

    @Bean
    public SkillService skillService(){
        return new SkillService();
    }
}
