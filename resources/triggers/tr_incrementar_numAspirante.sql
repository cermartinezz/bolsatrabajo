CREATE OR REPLACE TRIGGER incrementar_numAspirante
AFTER INSERT ON candidate
FOR EACH ROW
BEGIN 
    UPDATE jobs set num_aspirante=num_aspirante+1 where jobs.id_job = :new.id_job;
END;