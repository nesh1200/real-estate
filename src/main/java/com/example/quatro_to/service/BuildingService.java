package com.example.quatro_to.service;


import com.example.quatro_to.model.Building;
import com.example.quatro_to.model.City;

import java.util.Set;

public interface BuildingService {
    Building save(Building building);
    Building findById(Long id);
    Building update(Building building, Long id);
    void delete(Long id);
    Set<Building> findAll();
}
