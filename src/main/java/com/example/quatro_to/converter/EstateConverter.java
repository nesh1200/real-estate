package com.example.quatro_to.converter;

import com.example.quatro_to.dto.EstateDto;
import com.example.quatro_to.dto.EstateResponse;
import com.example.quatro_to.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EstateConverter {

    public Estate toEstate(EstateDto estateDto){
        return Estate.builder()
                .city(City.builder().id(estateDto.getCityId()).build())
                .floor(Floor.builder().id(estateDto.getFloorId()).build())
                .description(estateDto.getDescription())
                .quadrature(Quadrature.builder()
                        .builtUpArea(estateDto.getBuiltUpArea())
                        .pureArea(estateDto.getPureArea())
                        .build())
                .features(estateDto
                            .getFeatureIds()
                            .stream()
                            .map(featureId -> Feature.builder().id(featureId).build())
                            .collect(Collectors.toSet()))
                .build();
    }

    public EstateResponse toEstateDto(Estate estate){
        return null;
    }
}
