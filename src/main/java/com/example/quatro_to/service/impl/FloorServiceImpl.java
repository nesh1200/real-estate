package com.example.quatro_to.service.impl;

import com.example.quatro_to.exception.ResourceNotFoundException;
import com.example.quatro_to.model.Floor;
import com.example.quatro_to.repository.FloorRepository;
import com.example.quatro_to.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    @Autowired
    public FloorServiceImpl(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    @Override
    public Floor save(Floor floor) {

        try {
            return floorRepository.save(floor);
        }
        catch(DataIntegrityViolationException e){
            throw new DuplicateFormatFlagsException(String.format("Floor with this number %d already exists", floor.getNumber()));
        }

    }

    @Override
    public Floor findByNumber(Integer number) {
        return floorRepository.findByNumber(number)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Floor with this number %d doesnt exists", number)));
    }

    @Override
    public Floor findById(Long id) {
        return floorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Floor with this number %d doesnt exists", id)));
    }

    @Override
    public Floor update(Floor floor, Long id) {
        Floor foundFloor = findById(id);
        Floor updateFloor = Floor.builder()
                .id(foundFloor.getId())
                .number(floor.getNumber())
                .build();
        return save(updateFloor);
    }

    @Override
    public void delete(Long id) {
        Floor foundFloor = findById(id);
        floorRepository.delete(foundFloor);
    }

    @Override
    public Set<Floor> findAll() {
        return new HashSet<>(floorRepository.findAll());
    }
}
