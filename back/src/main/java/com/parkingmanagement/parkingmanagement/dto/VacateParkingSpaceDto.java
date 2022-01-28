package com.parkingmanagement.parkingmanagement.dto;

import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import com.parkingmanagement.parkingmanagement.validation.ValidIdUnavailable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacateParkingSpaceDto {
    @ValidIdUnavailable
    private Long id;
    private LocalTime hourExity;

}
