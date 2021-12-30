package com.parkingmanagement.parkingmanagement;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
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

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;
import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;

@RunWith(SpringRunner.class)
public class ListParkingSpaceTest {
    @InjectMocks
    ParkingSpaceServiceImplementation parkingSpaceService;

    @Mock
    ParkingSpaceRespository parkingSpaceRespository;

    @Before
    public void setup(){
        ParkingSpace parkingSpace1 = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace parkingSpace2 = new ParkingSpace(Long.valueOf(2), null, null, AVAILABLE, null);
        ParkingSpace parkingSpace3 = new ParkingSpace(Long.valueOf(3), new Car("xyz-6789", "Renault Sandero 2015"), Long.valueOf(789), UNAVAILABLE, LocalTime.of(9, 0));

        Mockito.when(parkingSpaceRespository.count()).thenReturn(Long.valueOf(3));

        Mockito.when(parkingSpaceRespository.findById(Long.valueOf(1))).thenReturn(Optional.of(parkingSpace1));
        Mockito.when(parkingSpaceRespository.findById(Long.valueOf(2))).thenReturn(Optional.of(parkingSpace2));
        Mockito.when(parkingSpaceRespository.findById(Long.valueOf(3))).thenReturn(Optional.of(parkingSpace3));

    }

    @Test
    public void listParkingSpaceFilled(){
        FillParkingSpaceDto parkingSpace1 = new FillParkingSpaceDto(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), UNAVAILABLE, LocalTime.of(13, 0));
        FillParkingSpaceDto parkingSpace3 = new FillParkingSpaceDto(Long.valueOf(3), new Car("xyz-6789", "Renault Sandero 2015"), Long.valueOf(789), UNAVAILABLE, LocalTime.of(9, 0));
        
        List<FillParkingSpaceDto> fillParkingSpaceDTOListActual = new ArrayList<FillParkingSpaceDto>();
        fillParkingSpaceDTOListActual.add(parkingSpace1);
        fillParkingSpaceDTOListActual.add(parkingSpace3);

        List<FillParkingSpaceDto> fillParkingSpaceDTOListExpected = parkingSpaceService.listParkingSpaceFilled();


        Assertions.assertEquals(fillParkingSpaceDTOListExpected, fillParkingSpaceDTOListActual);
    }

    @Test
    public void listParkingSpaceEmpty(){
        EmptyParkingSpaceDto parkingSpace2 = new EmptyParkingSpaceDto(Long.valueOf(2));

        List<EmptyParkingSpaceDto> emptyParkingSpaceDtoListActual = new ArrayList<EmptyParkingSpaceDto>();
        emptyParkingSpaceDtoListActual.add(parkingSpace2);

        List<EmptyParkingSpaceDto> emptyParkingSpaceDtoListExpected = parkingSpaceService.listParkingSpaceEmpty();

        Assertions.assertEquals(emptyParkingSpaceDtoListExpected, emptyParkingSpaceDtoListActual);
    }

}
