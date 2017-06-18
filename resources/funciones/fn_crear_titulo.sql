create or replace FUNCTION CREAR_TITULO
  (
    NOMBRE IN VARCHAR2
  ) RETURN NUMBER
IS
  V_BOOL      NUMBER;
  V_TITLE_ID  ACADEMIC_TITLES.TITLE_ID%TYPE;
  BEGIN

    SELECT HIBERNATE_SEQUENCE.NEXTVAL INTO V_TITLE_ID FROM DUAL;

    INSERT INTO ACADEMIC_TITLES (TITLE_ID,TITLE) VALUES
      (V_TITLE_ID,LOWER(NOMBRE));

    return V_TITLE_ID;

    EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
    RETURN -1; --Valor duplicado


  END CREAR_TITULO;