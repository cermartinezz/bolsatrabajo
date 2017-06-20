package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long>{
    @Procedure(name="CA_PREGUNTA") //el mismo nombre que el proc.
    void saveQuestion(@Param("Q_ID") long q_id,
                     @Param("Q_D1") String q_d1,
                     @Param("Q_D2") String q_d2,
                     @Param("Q_D3") String q_d3,
                     @Param("Q_D4") String q_d4,
                     @Param("Q_ENUNCIADO") String q_enunciado,
                     @Param("Q_PESO") int q_peso,
                     @Param("Q_RESPUESTA") String q_respuesta,
                     @Param("Q_SUBAREA_ID") long q_subarea_id);
    Question findById(long id);
    List<Question> findAllByOrderByIdAsc();
}
