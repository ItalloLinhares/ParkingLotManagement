package com.parkingmanagement.parkingmanagement.dto;

import com.parkingmanagement.parkingmanagement.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class OccupationDto {
    private Long clientCpf;
    private Car car;
    private LocalTime hourEntry;
    private LocalTime hourExit;
    private float price;
}
