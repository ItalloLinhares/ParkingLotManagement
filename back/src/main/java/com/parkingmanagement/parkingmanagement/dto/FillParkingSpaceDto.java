package com.parkingmanagement.parkingmanagement.dto;

import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Size;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FillParkingSpaceDto {
    private Long id;
    private Car car;
    private Long clientCpf;
    private LocalTime hourEntry;
}
