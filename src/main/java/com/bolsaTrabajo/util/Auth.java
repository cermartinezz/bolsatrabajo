package com.bolsaTrabajo.util;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.User;
import com.bolsaTrabajo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Iterator;

public class Auth {
    @Autowired
    UserService userService;

    public static final Logger logger = LoggerFactory.getLogger(Auth.class);

    public static Authentication auth(){
        logger.warn("Auth: -----------------: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
