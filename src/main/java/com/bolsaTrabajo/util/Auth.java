package com.bolsaTrabajo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class Auth {

    public static final Logger logger = LoggerFactory.getLogger(Auth.class);

    public static Authentication auth(){
        logger.warn("Auth: -----------------: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
