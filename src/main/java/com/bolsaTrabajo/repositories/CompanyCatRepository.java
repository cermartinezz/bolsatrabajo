package com.bolsaTrabajo.repositories;

import com.bolsaTrabajo.model.CompanyCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by enan0 on 13/4/2017.
 */
@Repository("companyCatRepository")
public interface CompanyCatRepository extends JpaRepository<CompanyCat,Long> {

    CompanyCat findById(long id);
    CompanyCat findByCompanyName(String comp_name);

}
