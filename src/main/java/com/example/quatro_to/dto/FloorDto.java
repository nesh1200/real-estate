package com.example.quatro_to.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@NotNull
@Getter
public class FloorDto {

    private Integer number;
}
