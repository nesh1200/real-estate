package com.example.quatro_to.dto;

import com.example.quatro_to.model.City;
import com.example.quatro_to.model.Feature;
import com.example.quatro_to.model.Floor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EstateDto {

    private Long id;

    private String description;

    private Long floorId;

    private Long cityId;

    private Set<Long> featureIds;

    private BigDecimal builtUpArea;

    private BigDecimal pureArea;
}
