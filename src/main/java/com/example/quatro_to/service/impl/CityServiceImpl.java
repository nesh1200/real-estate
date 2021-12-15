package com.example.quatro_to.service.impl;

import com.example.quatro_to.exception.ResourceNotFoundException;
import com.example.quatro_to.model.City;
import com.example.quatro_to.model.Neighborhood;
import com.example.quatro_to.repository.CityRepository;
import com.example.quatro_to.service.CityService;
import com.example.quatro_to.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final NeighborhoodService neighborhoodService;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, NeighborhoodService neighborhoodService) {
        this.cityRepository = cityRepository;
        this.neighborhoodService = neighborhoodService;
    }

    @Override
    public City save(City city){

        Set<Neighborhood> neighborhoods = new HashSet<>();
        for(Neighborhood neighborhood: city.getNeighborhoods()){
            Neighborhood foundNeighborhood = neighborhoodService.findById(neighborhood.getId());
            neighborhoods.add(foundNeighborhood);
        }

       return  cityRepository.save(City.builder()
                        .name(city.getName())
                       .population(city.getPopulation())
                        .neighborhoods(neighborhoods)
                        .build());
    }

    @Override
    public City findByName(String name) {
        return cityRepository.findByName(name).orElseThrow(()-> new ResourceNotFoundException(String.format("There is no such object with ID %s", name)));
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("There is no such object with ID %d", id)));
    }

    @Override
    public City update(City city, Long id) {
        City foundCity = findById(id);
        City updateCity = City.builder()
                .id(foundCity.getId())
                .name(city.getName())
                .population(city.getPopulation())
                .neighborhoods(city.getNeighborhoods())
                .build();
        return cityRepository.save(updateCity);
    }

    @Override
    public void delete(Long id) {
        City foundCity = cityRepository.getById(id);
        cityRepository.delete(foundCity);
    }

    @Override
    public Set<City> findAll() {
        return new HashSet<>(cityRepository.findAll());
    }

    @Override
    public void detachCityNeighborhood(Long cityId, Set<Long> neighborhoodIds) {
        City foundCity = cityRepository.getById(cityId);
        foundCity.getNeighborhoods().removeIf(neighborhood -> neighborhoodIds.contains(neighborhood.getId()));
        cityRepository.save(foundCity);
    }

}
