package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepositoryTest;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;
import static org.junit.Assert.assertThat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;

@RunWith(SpringRunner.class)
public class ParkingSpacesServiceTest {
    @InjectMocks
    ParkingSpaceServiceImplementation parkingSpaceService;

    @Mock
    ParkingSpaceRespository parkingSpaceRespository;

    @Mock
    OccupationRepositoryTest occupationRepository;

    @Test
    public void itShouldReturnOnlyAvailableParkingSpaces(){
        //Given
        ParkingSpace parkingSpace1 = new ParkingSpace(Long.valueOf(1), null, null, AVAILABLE, null);
        ParkingSpace parkingSpace2 = new ParkingSpace(Long.valueOf(2), null , null, AVAILABLE, null);

        List<ParkingSpace> listParkingSpaceAvailable = new ArrayList<>();

        listParkingSpaceAvailable.add(parkingSpace1);
        listParkingSpaceAvailable.add(parkingSpace2);

        Mockito.when(parkingSpaceRespository.findParkingSpacebyStatus(AVAILABLE)).thenReturn(listParkingSpaceAvailable);

        EmptyParkingSpaceDto emptyParkingSpaceDto1 = new EmptyParkingSpaceDto(Long.valueOf(1));
        EmptyParkingSpaceDto emptyParkingSpaceDto2 = new EmptyParkingSpaceDto(Long.valueOf(2));

        List<EmptyParkingSpaceDto> listParkingSpaceAvailableExpected = new ArrayList<>();
        listParkingSpaceAvailableExpected.add(emptyParkingSpaceDto1);
        listParkingSpaceAvailableExpected.add(emptyParkingSpaceDto2);

        //When
        ResponseEntity listParkingSpaceAvailableActual = parkingSpaceService.listParkingSpaceEmpty();

        //Then
        Assert.assertEquals(listParkingSpaceAvailableExpected, listParkingSpaceAvailableActual.getBody());
    }

    @Test
    public void itShouldReturnOnlyUnavailableParkingSpaces(){
        //Given

        ParkingSpace parkingSpace1 = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), "525.721.680-90", UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace parkingSpace2 = new ParkingSpace(Long.valueOf(2), new Car("xyz-6789", "Renault Sandero 2010"), "043.777.480-50", UNAVAILABLE, LocalTime.of(15, 0));

        List<ParkingSpace> listParkingSpaceUnavailable = new ArrayList<>();

        listParkingSpaceUnavailable.add(parkingSpace1);
        listParkingSpaceUnavailable.add(parkingSpace2);

        Mockito.when(parkingSpaceRespository.findParkingSpacebyStatus(UNAVAILABLE)).thenReturn(listParkingSpaceUnavailable);

        FillParkingSpaceDto filledParkingSpaceDto1 = new FillParkingSpaceDto(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), "525.721.680-90", LocalTime.of(13, 0));
        FillParkingSpaceDto filledParkingSpaceDto2 = new FillParkingSpaceDto(Long.valueOf(2), new Car("xyz-6789", "Renault Sandero 2010"), "043.777.480-50", LocalTime.of(15, 0));

        List<FillParkingSpaceDto> listParkingSpaceUnavailableExpected = new ArrayList<>();
        listParkingSpaceUnavailableExpected.add(filledParkingSpaceDto1);
        listParkingSpaceUnavailableExpected.add(filledParkingSpaceDto2);

        //When
        ResponseEntity listParkingSpaceUnavailableActual = parkingSpaceService.listParkingSpaceFilled();

        //Then
        Assert.assertEquals(listParkingSpaceUnavailableExpected, listParkingSpaceUnavailableActual.getBody());
    }


    @Test
    public void itShouldFillParkingSpace(){
        FillParkingSpaceDto filledParkingSpaceDto = new FillParkingSpaceDto(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), "525.721.680-90", LocalTime.of(13, 0));

        ParkingSpace filledParkingSpaceDtoExpected = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), "525.721.680-90", UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace parkingSpaceAvailable = new ParkingSpace(Long.valueOf(1), null, null, AVAILABLE, null);
        Mockito.when(parkingSpaceRespository.findById(filledParkingSpaceDto.getId())).thenReturn(Optional.of(parkingSpaceAvailable));


        ResponseEntity filledParkingSpaceDtoActual = parkingSpaceService.fillParkingSpace(filledParkingSpaceDto);

        Assert.assertEquals(filledParkingSpaceDtoExpected, filledParkingSpaceDtoActual.getBody());
    }


}

