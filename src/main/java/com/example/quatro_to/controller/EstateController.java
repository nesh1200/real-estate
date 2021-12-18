package com.example.quatro_to.controller;

import com.example.quatro_to.converter.EstateConverter;
import com.example.quatro_to.dto.EstateDto;
import com.example.quatro_to.dto.EstateResponse;
import com.example.quatro_to.model.Estate;
import com.example.quatro_to.service.EstateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estates")
public class EstateController {
    private final EstateService estateService;
    private final EstateConverter estateConverter;

    public EstateController(EstateService estateService, EstateConverter estateConverter) {
        this.estateService = estateService;
        this.estateConverter = estateConverter;
    }

    @PostMapping
    public ResponseEntity<Estate> save(@RequestBody EstateDto estateDto){
        Estate estate = estateConverter.toEstate(estateDto);

                return ResponseEntity.ok().body(estateService.save(estate));
    }
}
