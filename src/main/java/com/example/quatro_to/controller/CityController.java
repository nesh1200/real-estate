package com.example.quatro_to.controller;

import com.example.quatro_to.converter.CityConverter;
import com.example.quatro_to.dto.CityDto;
import com.example.quatro_to.model.City;
import com.example.quatro_to.service.CityService;
import com.example.quatro_to.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;
    private final CityConverter cityConverter;

    @Autowired
    public CityController(CityService cityService, CityConverter cityConverter) {
        this.cityService = cityService;
        this.cityConverter = cityConverter;
    }

    @PostMapping
    public ResponseEntity<CityDto> save(@RequestBody @Valid CityDto cityDto){
        City city  = cityConverter.toCityTest(cityDto);
        City savedCity = cityService.save(city);
        return  ResponseEntity.ok(cityConverter.toCityDto(savedCity));
    }

    @GetMapping
    public ResponseEntity<Set<CityDto>> findAll(){

        return ResponseEntity.ok(
                cityService.findAll()
                        .stream()
                        .map(cityConverter::toCityDto)
                        .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CityDto> findById(@PathVariable Long id){
        City foundCity  = cityService.findById(id);
        return ResponseEntity.ok(cityConverter.toCityDto(foundCity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        //City foundCity = cityService.findById(id);
        cityService.delete(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> update(@RequestBody @Valid CityDto cityDto, @PathVariable Long id){

        City convertedCity  = cityConverter.toCityTest(cityDto);
        City updateCity = cityService.update(convertedCity,id);
        return ResponseEntity.ok(cityConverter.toCityDto(updateCity));
    }

}
