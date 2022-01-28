package com.parkingmanagement.parkingmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id @Pattern(regexp = "^[a-zA-Z]{3}\\-\\d{4}$")
    private String carLicensePlate;
    @NotBlank
    private String carModelName;
}
