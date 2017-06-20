package com.bolsaTrabajo.service.implementations;

import com.bolsaTrabajo.model.catalog.Area;
import com.bolsaTrabajo.repositories.AreaRepository;
import com.bolsaTrabajo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;

    public void spSaveArea(Area area){
        areaRepository.saveArea(area.getNombre(),area.getId());
    }

    public List<Area> getAll(){ return areaRepository.findAll();}

    public Area findById(long id){return areaRepository.findById(id);}

    public void deleteById(long id){ areaRepository.deleteById(id);}
}
