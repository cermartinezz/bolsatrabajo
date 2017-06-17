create or replace FUNCTION CREAR_HABILIDAD
  (
      NOMBRE        IN SKILL.TITULO%TYPE
    , CATEGORIA_ID  IN SKILL.SKILL_CATEGORY_ID%TYPE
  ) RETURN NUMBER IS

  V_BOOL NUMBER;
  V_SKILL_ID SKILL.TITULO%TYPE;
  BEGIN

    SELECT HIBERNATE_SEQUENCE.NEXTVAL INTO V_SKILL_ID FROM DUAL;


    INSERT INTO SKILL(SKILL_ID,CODIGO,TITULO,SKILL_CATEGORY_ID) VALUES
      (V_SKILL_ID,CREATE_SLUG(NOMBRE),lower(NOMBRE),CATEGORIA_ID);

    RETURN V_SKILL_ID;

    EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
    RETURN -1; --Valor duplicado

  END CREAR_HABILIDAD;