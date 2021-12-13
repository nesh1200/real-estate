package com.example.quatro_to.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CityDto {

    private Long id;
    private String name;
    private Long population;
    private Set<Long> neighborhoodIds;
}
