package com.example.quatro_to.converter;

import com.example.quatro_to.dto.NeighborhoodDto;
import com.example.quatro_to.model.Neighborhood;
import org.springframework.stereotype.Component;

@Component
public class NeighborhoodConverter {

    public NeighborhoodDto toNeighborhoodDto(Neighborhood neighborhood){
        return NeighborhoodDto.builder()
                .name(neighborhood.getName())
                .id(neighborhood.getId())
                .build();
    }

    public Neighborhood toNeighborhood(NeighborhoodDto neighborhoodDto){
        return Neighborhood.builder()
                .name(neighborhoodDto.getName())
                .id(neighborhoodDto.getId())
                .build();
    }
}
