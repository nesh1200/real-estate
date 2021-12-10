package com.example.quatro_to.service;

import com.example.quatro_to.model.Neighborhood;

import java.util.Set;

public interface NeighborhoodService {
    Neighborhood save(Neighborhood neighborhood);
    Neighborhood findByName(String name);
    Neighborhood findById(Long id);
    Neighborhood update(Neighborhood neighborhood, Long id);
    void delete(Long id);
    Set<Neighborhood> findAll();
}
