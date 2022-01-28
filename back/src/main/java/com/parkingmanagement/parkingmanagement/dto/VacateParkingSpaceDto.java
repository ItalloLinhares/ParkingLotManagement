package com.parkingmanagement.parkingmanagement.dto;

import com.parkingmanagement.parkingmanagement.validation.ValidIdUnavailable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacateParkingSpaceDto {
    @ValidIdUnavailable
    private Long id;
    private LocalTime hourExity;

}
