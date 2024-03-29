package com.example.quatro_to.service;

import com.example.quatro_to.exception.DuplicateRecordException;
import com.example.quatro_to.model.Floor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


public interface FloorService {

    Floor save(Floor floor) throws DuplicateRecordException;
    Floor findByNumber(Integer number);
    Floor findById(Long id);
    Floor update(Floor floor, Long id);
    void delete(Long id);
    Set<Floor> findAll();
}
