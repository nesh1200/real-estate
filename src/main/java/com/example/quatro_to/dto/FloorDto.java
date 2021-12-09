package com.example.quatro_to.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class FloorDto {

    private Long id;

    @Min(value = -3, message = "Value shpould be NOT under -3")
    @Max(value = 164, message = "Value should be not over 164")
    @Range(min=-3, max=164, message = "Floor shpuld be between -3 and 164")
    private Integer number;
}
