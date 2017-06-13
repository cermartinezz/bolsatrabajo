package com.bolsaTrabajo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/","/registro","/registrar/**","/job/list/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login").permitAll()
            .and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/assets/**")
            .antMatchers("/fonts/**")
            .antMatchers("/entypo/**")
            .antMatchers("/font-awesome/**")
            .antMatchers("/css/**")
            .antMatchers("/js/**")
            .antMatchers("/vendor/**")
            .antMatchers("/vendor/jquery.**")
            .antMatchers("/countdown/**")
            .antMatchers("/flexslider/**")
            .antMatchers("/job-manager/**")
            .antMatchers("/mangnific-popup/**")
            .antMatchers("/owl-carousel/**")
            .antMatchers("/images/**")
            .antMatchers("/favicons/**")
            .antMatchers("/fsamples/**")
            .antMatchers("/patterns/**")


        ;

    }
}
