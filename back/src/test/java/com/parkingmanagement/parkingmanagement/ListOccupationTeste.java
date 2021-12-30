package com.parkingmanagement.parkingmanagement;

import com.parkingmanagement.parkingmanagement.dto.OccupationDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepository;
import com.parkingmanagement.parkingmanagement.service.ParkingSpaceServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ListOccupationTeste {
    @InjectMocks
    ParkingSpaceServiceImplementation parkingSpaceService;

    @Mock
    OccupationRepository occupationRepository;

    @Before
    public void setup(){
        Occupation occupation1 = new Occupation(Long.valueOf(1), Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15, 0), 120);
        Occupation occupation2 = new Occupation(Long.valueOf(2), Long.valueOf(789), new Car("xyz-6789", "Renault Sandero 2015"), LocalTime.of(9, 0), LocalTime.of(10, 0), 60);

        Mockito.when(occupationRepository.count()).thenReturn(Long.valueOf(2));

        Mockito.when(occupationRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(occupation1));
        Mockito.when(occupationRepository.findById(Long.valueOf(2))).thenReturn(Optional.of(occupation2));
    }

    @Test
    public void listOccupationById(){
        OccupationDto occupationActual = new OccupationDto(Long.valueOf(1), Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15, 0), 120);

        OccupationDto OccupationByIdExpected = parkingSpaceService.listOccupationById(Long.valueOf(1));

        Assertions.assertEquals(OccupationByIdExpected, occupationActual);
    }

    @Test
    public void listOccupationByLicensePlate(){
        OccupationDto occupation1 = new OccupationDto(Long.valueOf(1), Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15, 0), 120);

        List<OccupationDto> listOccuparionByLicensePlateActual = new ArrayList<OccupationDto>();
        listOccuparionByLicensePlateActual.add(occupation1);


        List<OccupationDto> listOccupationByLicensePlateExpected = parkingSpaceService.listOccupationByLicensePlate("abc-1234");

        Assertions.assertEquals(listOccupationByLicensePlateExpected, listOccuparionByLicensePlateActual);
    }

    @Test
    public void listOccupationByCpf(){
        //OccupationDto occupation2 = new OccupationDto(Long.valueOf(2), Long.valueOf(789), new Car("xyz-6789", "Renault Sandero 2015"), LocalTime.of(9, 0), LocalTime.of(10, 0), 60);
        Occupation occupation2 = new Occupation(Long.valueOf(2), Long.valueOf(789), new Car("xyz-6789", "Renault Sandero 2015"), LocalTime.of(9, 0), LocalTime.of(10, 0), 60);
        List<Occupation> listOccupationByCpfActual = new ArrayList<Occupation>();
        listOccupationByCpfActual.add(occupation2);


        List<Occupation> listOccupationByCpfExpected = parkingSpaceService.listOccupationByCpf(Long.valueOf(789));

        Assertions.assertEquals(listOccupationByCpfExpected, listOccupationByCpfActual);
    }
}
