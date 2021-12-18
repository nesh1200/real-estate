package com.example.quatro_to.service.impl;

import com.example.quatro_to.exception.ResourceNotFoundException;
import com.example.quatro_to.model.City;
import com.example.quatro_to.model.Estate;
import com.example.quatro_to.model.Feature;
import com.example.quatro_to.model.Floor;
import com.example.quatro_to.repository.EstateRepository;
import com.example.quatro_to.service.CityService;
import com.example.quatro_to.service.EstateService;
import com.example.quatro_to.service.FeatureService;
import com.example.quatro_to.service.FloorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EstateServiceImpl implements EstateService {
    private final EstateRepository estateRepository;
    private final FeatureService featureService;
    private final CityService cityService;
    private final FloorService floorService;


    public EstateServiceImpl(EstateRepository estateRepository, FeatureService featureService, CityService cityService, FloorService floorService) {
        this.estateRepository = estateRepository;
        this.featureService = featureService;
        this.cityService = cityService;
        this.floorService = floorService;
    }

    @Override
    public Estate save(Estate estate) {
        Set<Feature> features  = estate.getFeatures().stream().map(featureId -> featureService.findById(featureId.getId())).collect(Collectors.toSet());
        City city = cityService.findById(estate.getCity().getId());
        Floor floor = floorService.findById(estate.getFloor().getId());
        return estateRepository.save(Estate.builder()
                        .features(features)
                        .city(city)
                        .floor(floor)
                        .quadrature(estate.getQuadrature())
                        .description(estate.getDescription())
                .build());
    }

    @Override
    public Estate findById(Long id) {
        return estateRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("There is no feature with this id: %d",id)));
    }

    @Override
    public Estate update(Estate estate, Long id) {
        Estate foundEstate = findById(id);
        Estate toUpdateEstate = Estate.builder().id(foundEstate.getId()).build();
        return estateRepository.save(toUpdateEstate);
    }

    @Override
    public void delete(Long id) {
        Estate foundEstate = findById(id);
        estateRepository.delete(foundEstate);
    }

    @Override
    public Set<Estate> findAll() {
        return new HashSet<>(estateRepository.findAll());
    }
}
