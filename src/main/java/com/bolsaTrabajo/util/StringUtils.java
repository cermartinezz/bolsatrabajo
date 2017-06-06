package com.bolsaTrabajo.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

    /**
     * Create slug
     * @param text
     * @return
     */
    public static String toSlug(String text) {
        String nowhitespace = WHITESPACE.matcher(text).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = EDGESDHASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    //Obtiene el texto importate del mensaje de error que manda el procedimiento
    public static String clearMessage(String message){
        message = message.toUpperCase().replaceAll("((ORA-).+:(.(AT)\"?.+)?).\\n?","");
        message = message.replaceAll("\\n","");
        return message;
    }
}
