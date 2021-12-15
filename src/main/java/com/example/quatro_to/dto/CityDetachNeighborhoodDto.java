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
public class CityDetachNeighborhoodDto {
    private  Long cityId;
    private Set<Long> neighborhoodsIds;
}
