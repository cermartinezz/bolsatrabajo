package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.Area;

import java.util.List;

public interface AreaService {
    void spSaveArea(Area area);
    List<Area> getAll();
    Area findById(long id);
    void deleteById(long id);
}
