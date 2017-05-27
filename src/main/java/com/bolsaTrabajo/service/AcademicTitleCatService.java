package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.repositories.AcademicTitleCatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Created by keepercito on 05-21-17.
 */
public class AcademicTitleCatService {

    @Autowired
    private AcademicTitleCatRepository academicTitleCatRepository;

    public List<AcademicTitleCat> getAllTitles(){return academicTitleCatRepository.findAll();}

    //public Optional<AcademicTitleCat> getTitle(Long id){return academicTitleCatRepository.findById(id);}

    public Optional<AcademicTitleCat> getTitle(Long id){return Optional.ofNullable(academicTitleCatRepository.findById(id));}

    public AcademicTitleCat getTitle(String nom){return academicTitleCatRepository.findByTitulo(nom);}

    public void saveTitle(AcademicTitleCat title){ academicTitleCatRepository.save(title);}

    public void deleteTitle(AcademicTitleCat title){ academicTitleCatRepository.delete(title);}
}
