create or replace FUNCTION CREATE_SLUG (NAME IN VARCHAR2 ) RETURN VARCHAR2
IS
  V_NAME VARCHAR2(255);
  BEGIN

    --trasnforma HolA MundO -> hola-mundo
    V_NAME := lower(translate (NAME, ' ', '-'));

    RETURN V_NAME;

  END CREATE_SLUG;