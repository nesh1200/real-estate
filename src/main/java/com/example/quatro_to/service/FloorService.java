package com.example.quatro_to.service;

import com.example.quatro_to.model.Floor;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface FloorService {

    Floor save(Floor floor);
    Floor findByNumber(Integer number);
    Floor findById(Long id);
    Floor update(Floor floor, Long id);
    void delete(Long id);
    Set<Floor> findAll();
}
