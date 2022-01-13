package com.parkingmanagement.parkingmanagement.model;

import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.lang.annotation.Annotation;
import java.time.LocalTime;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class ParkingSpace{
    @Id
    private Long id;
    @OneToOne
    private Car car;
    private Long clientCpf;
    private ParkingSpaceStatus parkingSpaceStatus;
    private LocalTime hourEntry;

}