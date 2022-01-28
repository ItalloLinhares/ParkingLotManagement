package com.parkingmanagement.parkingmanagement.dto;

import com.parkingmanagement.parkingmanagement.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class OccupationDto {
    @CPF
    private String clientCpf;
    @NotNull
    private Car car;
    private LocalTime hourEntry;
    private LocalTime hourExit;
    private float price;
}
