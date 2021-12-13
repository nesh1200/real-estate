package com.example.quatro_to.service.impl;

import com.example.quatro_to.exception.ResourceNotFoundException;
import com.example.quatro_to.model.Neighborhood;
import com.example.quatro_to.repository.NeighborhoodRepository;
import com.example.quatro_to.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    @Autowired
    public NeighborhoodServiceImpl(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @Override
    public Neighborhood save(Neighborhood neighborhood) {
        try {
            return neighborhoodRepository.save(neighborhood);
        }catch(DataIntegrityViolationException e){
                throw new DuplicateFormatFlagsException(String.format("Neighborhood with this name %s already exists", neighborhood.getName()));
            }
        }

    @Override
    public Neighborhood findByName(String name) {
        return neighborhoodRepository.findByName(name).orElseThrow(()-> new ResourceNotFoundException(String.format("Neighborhood with this name %s doesnt exists", name)));
    }

    @Override
    public Neighborhood findById(Long id) {
        return neighborhoodRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Neighborhood with this number %d doesnt exists", id)));
    }

    @Override
    public Neighborhood update(Neighborhood neighborhood, Long id) {
        Neighborhood foundNeighborhood = findById(id);
        Neighborhood updateNeighborhood = Neighborhood.builder()
                .id(foundNeighborhood.getId())
                .name(neighborhood.getName())
                .build();

        return save(updateNeighborhood);
    }

    @Override
    public void delete(Long id) {

        Neighborhood foundNeighborhood = findById(id);
        neighborhoodRepository.delete(foundNeighborhood);
    }

    @Override
    public Set<Neighborhood> findAll() {
        return new HashSet<>(neighborhoodRepository.findAll());
    }
}
