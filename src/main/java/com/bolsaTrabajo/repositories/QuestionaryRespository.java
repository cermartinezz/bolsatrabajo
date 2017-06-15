package com.bolsaTrabajo.repositories;


import com.bolsaTrabajo.model.Questionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface QuestionaryRespository extends JpaRepository<Questionary,Long> {
    @Procedure(name="CA_CUESTIONARIO") //el mismo nombre que el proc.
    void saveQues(@Param("CU_ID") long cu_id,
                  @Param("CU_ID_EXAM") long cu_id_exam,
                  @Param("CU_ID_QUESTION") long cu_id_question);

    Questionary findById(long id);
}
