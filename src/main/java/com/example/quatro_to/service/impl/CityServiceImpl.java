package com.example.quatro_to.service.impl;

import com.example.quatro_to.model.City;
import com.example.quatro_to.repository.CityRepository;
import com.example.quatro_to.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City save(City city) {
        return null;
    }

    @Override
    public City findByName(String name) {
        return null;
    }

    @Override
    public City findById(Long id) {
        return null;
    }

    @Override
    public City update(City city, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Set<City> findAll() {
        return null;
    }
}
