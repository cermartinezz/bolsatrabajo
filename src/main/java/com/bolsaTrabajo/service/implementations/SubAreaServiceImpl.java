package com.bolsaTrabajo.service.implementations;


import com.bolsaTrabajo.model.catalog.SubArea;
import com.bolsaTrabajo.repositories.SubAreaRepository;
import com.bolsaTrabajo.service.SubAreaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubAreaServiceImpl implements SubAreaService{
    @Autowired
    SubAreaRepository subAreaRepository;

    public void spSaveSubArea(SubArea subArea){
        subAreaRepository.saveSubArea(subArea.getId(), //primero el id
                subArea.getArea().getId(),  //luego el id del area
                subArea.getNombre());   //luego el nombre de la subarea
    }

    public List<SubArea> getAll(){ return subAreaRepository.findAll();}

    public SubArea findById(long id){return subAreaRepository.findById(id);}

    public void deleteById(long id){ subAreaRepository.deleteById(id);}
}
