package com.parkingmanagement.parkingmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    @Pattern(regexp = "^[a-zA-Z]{3}\\-\\d{4}$")
    private String carLicensePlate;
    @NotBlank
    private String carModelName;
}
