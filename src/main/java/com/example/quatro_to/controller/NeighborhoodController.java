package com.example.quatro_to.controller;

import com.example.quatro_to.converter.NeighborhoodConverter;
import com.example.quatro_to.dto.NeighborhoodDto;
import com.example.quatro_to.model.Neighborhood;
import com.example.quatro_to.service.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/neighborhoods")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;
    private final NeighborhoodConverter neighborhoodConverter;

    @Autowired
    public NeighborhoodController(NeighborhoodService neighborhoodService, NeighborhoodConverter neighborhoodConverter) {
        this.neighborhoodService = neighborhoodService;
        this.neighborhoodConverter = neighborhoodConverter;
    }

    @GetMapping
    public ResponseEntity<Set<NeighborhoodDto>> findAll(){
        return ResponseEntity.ok(
                neighborhoodService.findAll().stream()
                        .map(neighborhoodConverter::toNeighborhoodDto)
                        .collect(Collectors.toSet())
        );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NeighborhoodDto> update(@RequestBody @Valid NeighborhoodDto neighborhoodDto, @PathVariable Long id){
        Neighborhood convertedNeighborhood = neighborhoodConverter.toNeighborhood(neighborhoodDto);
        Neighborhood updatedNeighborhood = neighborhoodService.update(convertedNeighborhood,id);
        return ResponseEntity.ok(neighborhoodConverter.toNeighborhoodDto(updatedNeighborhood));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NeighborhoodDto> findById(@PathVariable Long id){
        Neighborhood neighborhood = neighborhoodService.findById(id);
        return ResponseEntity.ok(neighborhoodConverter.toNeighborhoodDto(neighborhood));
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<NeighborhoodDto> findByName(@PathVariable String name){

        return ResponseEntity.ok(
                neighborhoodConverter.toNeighborhoodDto(
                        neighborhoodService.findByName(name)
                )
        );
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        neighborhoodService.delete(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
