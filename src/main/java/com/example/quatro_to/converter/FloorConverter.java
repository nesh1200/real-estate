package com.example.quatro_to.converter;

import com.example.quatro_to.dto.FloorDto;
import com.example.quatro_to.model.Floor;
import org.springframework.stereotype.Component;

@Component
public class FloorConverter {

    public FloorDto toFloorDto(Floor floor){
        return FloorDto.builder()
                .number(floor.getNumber())
                .build();
    }

    public Floor toFloor(FloorDto floorDto){
        return Floor.builder()
                .number(floorDto.getNumber())
                .build();
    }
}
