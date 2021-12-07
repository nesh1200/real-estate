package com.example.quatro_to.controller;

import com.example.quatro_to.converter.FloorConverter;
import com.example.quatro_to.dto.FloorDto;
import com.example.quatro_to.model.Floor;
import com.example.quatro_to.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/floors")
public class FloorController {
    private final FloorService floorService;
    private final FloorConverter floorConverter;

    @Autowired
    public FloorController(FloorService floorService, FloorConverter floorConverter) {
        this.floorService = floorService;
        this.floorConverter = floorConverter;
    }

    @GetMapping
    public ResponseEntity<Set<FloorDto>> findAll() {
        Set<FloorDto> floorDtos = new HashSet<>();
        Set<Floor> floors = floorService.findAll();
        for (Floor floor: floors){
            FloorDto floorDto = floorConverter.toFloorDto(floor);
            floorDtos.add(floorDto);
        }
        return ResponseEntity.ok(floorDtos);
    }

    @PostMapping
    public ResponseEntity<FloorDto> save(@RequestBody @Valid FloorDto floorDto){
        Floor floor = Floor.builder().number(floorDto.getNumber()).build();
        Floor storedFloor  = floorService.save(floor);
        return ResponseEntity.ok(floorConverter.toFloorDto(storedFloor));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        floorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
