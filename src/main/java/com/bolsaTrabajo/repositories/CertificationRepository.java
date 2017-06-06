package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.catalog.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("certificationRepository")
public interface CertificationRepository extends JpaRepository<Certification,Integer> {

    @Procedure(name="new_certification")
    void newCertification(@Param("C_NOMBRE") String c_nombre,
                          @Param("I_ID") Integer i_id,
                          @Param("I_NOMBRE") String i_nombre,
                          @Param("I_TIPO") String i_tipo);
    @Procedure(name="ACTUALIZAR_CERTIFICACION")
    void updateCertification(   @Param("C_ID") Integer c_id,
                                @Param("C_TITULO") String  c_titulo,
                                @Param("I_ID") Integer i_id);

    Certification findByCertificationCode(String code);

    Certification findByCertificationId(int id);

    List<Certification> findByInstitution_Id(Integer institution);

}
