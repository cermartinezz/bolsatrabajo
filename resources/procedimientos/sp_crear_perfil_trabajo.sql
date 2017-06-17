create or replace PROCEDURE SP_CREAR_PERFIL_TRABAJO
  (
      NOMBRE IN VARCHAR2
    , DESCRIPCION IN VARCHAR2
    , EDAD_MAX IN NUMBER
    , EDAD_MIN IN NUMBER
    , COMPAÑIA_ID IN NUMBER
    , EDUC_MIN IN VARCHAR2
    , ID_JP OUT NUMBER
  ) IS

  V_RESPUESTA NUMBER;
  BEGIN

    V_RESPUESTA := CREAR_PERFIL_TRABAJO(NOMBRE,DESCRIPCION,EDAD_MAX,EDAD_MIN,EDUC_MIN,COMPAÑIA_ID);

    IF V_RESPUESTA = -1 THEN
      RAISE_APPLICATION_ERROR(-20001, 'El perfil de trabajo ya existe para esta empresa');
    END IF;
    IF V_RESPUESTA = -2 THEN
      RAISE_APPLICATION_ERROR(-20002, 'El rango de la edad debe de estar entre 18 y 99 años');
    END IF;
    IF V_RESPUESTA = -3 THEN
      RAISE_APPLICATION_ERROR(-20003, 'El grado de educacion minima no es permitido');
    END IF;
    IF V_RESPUESTA = -4 THEN
      RAISE_APPLICATION_ERROR(-20004, 'La compañia no existe');
    END IF;
    IF V_RESPUESTA = -5 THEN
      RAISE_APPLICATION_ERROR(-20005, 'La edad minima no puede superar la edad maxima');
    END IF;

    ID_JP := V_RESPUESTA;


  END SP_CREAR_PERFIL_TRABAJO;