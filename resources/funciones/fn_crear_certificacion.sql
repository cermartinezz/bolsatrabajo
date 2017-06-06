create or replace FUNCTION CREAR_CERTIFICACION
  (
      ID_C IN NUMBER
    , NOMBRE IN VARCHAR2
    , CODIGO IN VARCHAR2
    , INSTITUCION IN VARCHAR2
  ) RETURN NUMBER
IS
  V_C_CODIGO Certification.Certification_Code%TYPE;
  BEGIN

    /*  crear un codigo con el codigo mas el id de la certificacion,
        para que se puedan crear certificaciones iguales en nombre
        pero de difernte institucion

        sql-server-362
        sql-server-361

        serian de diferente institucion
        */
    V_C_CODIGO := CREATE_SLUG(CODIGO || ' ' || INSTITUCION);

    INSERT INTO CERTIFICATION (CERTIFICATION_ID,CERTIFICATION_CODE,CERTIFICATION_TITLE,INSTITUTION_ID)
    VALUES      (ID_C,V_C_CODIGO,NOMBRE,INSTITUCION);


    RETURN 0; --SIGNIFICA CORRECTO

    EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
    RETURN 1;  --SIGNIFICA ERROR

  END CREAR_CERTIFICACION;