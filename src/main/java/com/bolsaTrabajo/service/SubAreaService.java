package com.bolsaTrabajo.service;

import com.bolsaTrabajo.model.catalog.SubArea;

import java.util.List;

public interface SubAreaService {
    void spSaveSubArea(SubArea subArea);
    List<SubArea> getAll();
    SubArea findById(long id);
    void deleteById(long id);
}
