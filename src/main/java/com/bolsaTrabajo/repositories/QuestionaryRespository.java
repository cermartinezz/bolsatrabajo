package com.bolsaTrabajo.repositories;


import com.bolsaTrabajo.model.Exam;
import com.bolsaTrabajo.model.Question;
import com.bolsaTrabajo.model.Questionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public interface QuestionaryRespository extends JpaRepository<Questionary,Long> {
    @Procedure(name="CA_CUESTIONARIO") //el mismo nombre que el proc.
    void saveQues(@Param("CU_ID") long cu_id,
                  @Param("CU_ID_EXAM") long cu_id_exam,
                  @Param("CU_ID_QUESTION") long cu_id_question);

    Questionary findById(long id);

    List<Questionary> findAllByExam(Exam exam);

    //@Query("select Q from Questionary Q where Q.exam = :EXAM_ID")
    //Stream<Questionary> findQuestionsIdsForExam(@Param("EXAM_ID") long examId);
}
