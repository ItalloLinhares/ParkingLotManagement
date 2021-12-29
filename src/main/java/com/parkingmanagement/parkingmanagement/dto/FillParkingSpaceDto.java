package com.parkingmanagement.parkingmanagement.dto;

import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillParkingSpaceDto {
    private Long id;
    private Car car;
    private Long clientCpf;
    private ParkingSpaceStatus parkingSpaceStatus;
    private LocalTime hourEntry;
}
