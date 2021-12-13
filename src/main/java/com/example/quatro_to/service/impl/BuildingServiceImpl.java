package com.example.quatro_to.service.impl;

import com.example.quatro_to.exception.ResourceNotFoundException;
import com.example.quatro_to.model.Building;
import com.example.quatro_to.repository.BuildingRepository;
import com.example.quatro_to.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public Building save(Building building) {
        return buildingRepository.save(building);
    }

    @Override
    public Building findById(Long id) {
        return buildingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("There is no such object with ID %d", id)));
    }

    @Override
    public Building update(Building building, Long id) {
        Building foundBuilding = findById(id);
        Building toUpdate = Building.builder()
                .id(foundBuilding.getId())
                .name(foundBuilding.getName())
                // how could id rename the property name in
                .has_yard(building.isHas_yard())
                .build();

        return  buildingRepository.save(toUpdate);
    }

    @Override
    public void delete(Long id) {
        Building foundBuilding = findById(id);
        buildingRepository.delete(foundBuilding);
    }

    @Override
    public Set<Building> findAll() {
        return new HashSet<>(buildingRepository.findAll());
    }
}
