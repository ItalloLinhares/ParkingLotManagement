package com.parkingmanagement.parkingmanagement.dto;

import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.validation.ValidIdAvailable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillParkingSpaceDto {
    @ValidIdAvailable
    private Long id;
    @NotNull(message = "Car is required")
    private Car car;
    @CPF(message = "Invalid CPF")
    @NotNull(message = "Client CPF is required")
    private String clientCpf;
    @NotNull(message = "Entry hour  is required")
    private LocalTime hourEntry;
}
