package com.example.quatro_to.controller;

import com.example.quatro_to.converter.FloorConverter;
import com.example.quatro_to.dto.FloorDto;
import com.example.quatro_to.model.Floor;
import com.example.quatro_to.service.FloorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/floors")
public class FloorController {
    private final FloorService floorService;
    private final FloorConverter floorConverter;
    private final ModelMapper modelMapper;

    @Autowired
    public FloorController(FloorService floorService, FloorConverter floorConverter, ModelMapper modelMapper) {
        this.floorService = floorService;
        this.floorConverter = floorConverter;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<Set<FloorDto>> findAll() {
        /*
        Set<FloorDto> floorDtos = new HashSet<>();
        Set<Floor> floors = floorService.findAll();
        for (Floor floor: floors){
            FloorDto floorDto = floorConverter.toFloorDto(floor);
            floorDtos.add(floorDto);
        }
        return ResponseEntity.ok(floorDtos);
        */
        return ResponseEntity.ok(floorService.findAll()
                .stream()
                .map(floorConverter::toFloorDto)
                .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<FloorDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                floorConverter.toFloorDto(
                        floorService.findById(id)
                )
        );
    }

    @GetMapping(value = "/{number}")
    public ResponseEntity<FloorDto> findByNumber(@PathVariable Integer number){
        return ResponseEntity.ok(floorConverter.toFloorDto(floorService.findByNumber(number)));
    }


    @PostMapping
    public ResponseEntity<FloorDto> save(@RequestBody @Valid FloorDto floorDto){
        Floor floor = floorConverter.toFloor(floorDto);
        Floor storedFloor  = floorService.save(floor);
        return ResponseEntity.ok(floorConverter.toFloorDto(storedFloor));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FloorDto> update(@RequestBody @Valid FloorDto floorDto,
                                           @PathVariable Long id){
        Floor floor = floorConverter.toFloor(floorDto);
        Floor updatedFloor = floorService.update(floor,id);
        return ResponseEntity.ok(floorConverter.toFloorDto(updatedFloor));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        floorService.delete(id);
        return ResponseEntity.ok().build();
    }

}
