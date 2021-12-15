package com.example.quatro_to.service;

import com.example.quatro_to.model.City;

import java.util.Set;

public interface CityService {
    City save(City city);
    City findByName(String name);
    City findById(Long id);
    City update(City city, Long id);
    void delete(Long id);
    Set<City> findAll();
    void detachCityNeighborhood(Long cityId, Set<Long> neighborhoodIds);

}
