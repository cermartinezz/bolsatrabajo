package com.bolsaTrabajo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Auth {

    public static final Logger logger = LoggerFactory.getLogger(Auth.class);

    public static Authentication auth(){
        logger.warn("instanceof AnonymousAuthenticationToken: -----------------: {}", SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
        logger.warn("Aut henticated: -----------------: {}", SecurityContextHolder.getContext().getAuthentication());
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /*
        Verifica si el usuario esta logeado. si no esta loqueado el metodo auth()
        retorna una clase que es instancia de AnonymousAuthenticationToken. con
        este metodo se checkea si es una instancia de este, el cual si es lo
        es retorna true, en caso contrario retorna false y este resultado
        se niega para obtener si esta logueado o no.

        retorna true en caso de estar loguado y false si no lo esta
     */
    public static boolean check(){
        return ! (auth() instanceof AnonymousAuthenticationToken);
    }


}
