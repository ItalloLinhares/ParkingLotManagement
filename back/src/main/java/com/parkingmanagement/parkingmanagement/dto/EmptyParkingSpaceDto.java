package com.parkingmanagement.parkingmanagement.dto;

import com.parkingmanagement.parkingmanagement.validation.ValidIdAvailable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmptyParkingSpaceDto {
    @ValidIdAvailable
    private Long id;
}
