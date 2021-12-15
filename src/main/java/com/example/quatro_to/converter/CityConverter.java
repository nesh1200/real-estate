package com.example.quatro_to.converter;

import com.example.quatro_to.dto.CityDto;
import com.example.quatro_to.model.City;
import com.example.quatro_to.model.Neighborhood;
import com.example.quatro_to.repository.CityRepository;
import com.example.quatro_to.service.CityService;
import com.example.quatro_to.service.NeighborhoodService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CityConverter {

    public City toCity(CityDto cityDto){

        return City.builder()
                .name(cityDto.getName())
                .population(cityDto.getPopulation())
                .neighborhoods(cityDto.getNeighborhoodIds()
                        .stream()
                        .map((neighborhoodId)-> Neighborhood.builder()
                                .id(neighborhoodId)
                                .build()).collect(Collectors.toSet()))
                .build();
    }

    public CityDto toCityDto(City city){

        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .population(city.getPopulation())
                .neighborhoodIds(city
                        .getNeighborhoods()
                        .stream()
                        .map(Neighborhood::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
