package com.parkingmanagement.parkingmanagement;

import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepository;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import com.parkingmanagement.parkingmanagement.service.ParkingSpaceServiceImplementation;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class VacateParkingSpaceTest {
    @InjectMocks
    ParkingSpaceServiceImplementation parkingSpaceService;

    @Mock
    ParkingSpaceRespository parkingSpaceRespository;

    @Mock
    OccupationRepository occupationRepository;

    @Before
    public void setup(){
        ParkingSpace parkingSpaceFilled = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), ParkingSpaceStatus.UNAVAILABLE, LocalTime.of(13, 0));
        Mockito.when(parkingSpaceRespository.findById(parkingSpaceFilled.getId())).thenReturn(Optional.of(parkingSpaceFilled));
    }

    @Test
    public void vacateParkingSpaceTest(){
        Occupation occupationActual = new Occupation(null , Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15, 0), 120);
        VacateParkingSpaceDto vacateParkingSpaceDto = new VacateParkingSpaceDto(Long.valueOf(1), LocalTime.of(15,0));
        Occupation occupationExpected = parkingSpaceService.vacateParkingSpace(vacateParkingSpaceDto);

        Assertions.assertEquals(occupationExpected, occupationActual);
    }

}
