package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.OccupationDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepository;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    OccupationRepository occupationRepository;

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

        ParkingSpace parkingSpace1 = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace parkingSpace2 = new ParkingSpace(Long.valueOf(2), new Car("xyz-6789", "Renault Sandero 2010"), Long.valueOf(789), UNAVAILABLE, LocalTime.of(15, 0));

        List<ParkingSpace> listParkingSpaceUnavailable = new ArrayList<>();

        listParkingSpaceUnavailable.add(parkingSpace1);
        listParkingSpaceUnavailable.add(parkingSpace2);

        Mockito.when(parkingSpaceRespository.findParkingSpacebyStatus(UNAVAILABLE)).thenReturn(listParkingSpaceUnavailable);

        FillParkingSpaceDto filledParkingSpaceDto1 = new FillParkingSpaceDto(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), UNAVAILABLE, LocalTime.of(13, 0));
        FillParkingSpaceDto filledParkingSpaceDto2 = new FillParkingSpaceDto(Long.valueOf(2), new Car("xyz-6789", "Renault Sandero 2010"), Long.valueOf(789), UNAVAILABLE, LocalTime.of(15, 0));

        List<FillParkingSpaceDto> listParkingSpaceUnavailableExpected = new ArrayList<>();
        listParkingSpaceUnavailableExpected.add(filledParkingSpaceDto1);
        listParkingSpaceUnavailableExpected.add(filledParkingSpaceDto2);

        //When
        ResponseEntity listParkingSpaceUnavailableActual = parkingSpaceService.listParkingSpaceFilled();

        //Then
        Assert.assertEquals(listParkingSpaceUnavailableExpected, listParkingSpaceUnavailableActual.getBody());
    }

    @Test
    public void itShouldSaveTheOccupation(){
        ParkingSpace filledParkingSpace = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace emptyParkingSpaceExpected = new ParkingSpace(Long.valueOf(1), null , null, AVAILABLE, null);
        VacateParkingSpaceDto vacateParkingSpace = new VacateParkingSpaceDto(Long.valueOf(1), LocalTime.of(15, 0));
        Occupation occupationExpected = new Occupation(null, Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);

        
        Mockito.when(parkingSpaceRespository.findById(vacateParkingSpace.getId())).thenReturn(Optional.of(filledParkingSpace));


        ResponseEntity occupationActual = parkingSpaceService.saveOccupation(vacateParkingSpace);

        Assert.assertEquals(occupationExpected, occupationActual.getBody());
    }

    @Test
    public void itShouldFillParkingSpace(){
        FillParkingSpaceDto filledParkingSpaceDto = new FillParkingSpaceDto(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), UNAVAILABLE, LocalTime.of(13, 0));

        ParkingSpace filledParkingSpaceDtoExpected = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace parkingSpaceAvailable = new ParkingSpace(Long.valueOf(1), null, null, AVAILABLE, null);
        Mockito.when(parkingSpaceRespository.findById(filledParkingSpaceDto.getId())).thenReturn(Optional.of(parkingSpaceAvailable));


        ResponseEntity filledParkingSpaceDtoActual = parkingSpaceService.fillParkingSpace(filledParkingSpaceDto);

        Assert.assertEquals(filledParkingSpaceDtoExpected, filledParkingSpaceDtoActual.getBody());
    }

    @Test
    public void itShouldFindOccupationById(){
        Occupation occupationExpected = new Occupation(Long.valueOf(1), Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Mockito.when(occupationRepository.findById(occupationExpected.getId())).thenReturn(Optional.of(occupationExpected));

        ResponseEntity occupationActual = parkingSpaceService.listOccupationById(occupationExpected.getId());


        Assert.assertEquals(occupationExpected, occupationActual.getBody());
    }

    @Test
    public void itShouldReturnListOfOccupationByLicensePlate(){
        Occupation occupation1 = new Occupation(Long.valueOf(1), Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation2 = new Occupation(Long.valueOf(2), Long.valueOf(123), new Car("xyz-6789", "Renault Sandero 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation3 = new Occupation(Long.valueOf(3), Long.valueOf(456), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(17, 0), LocalTime.of(19,0), 120);

        List<Occupation> occupationList = new ArrayList<>();
        occupationList.add(occupation1);
        occupationList.add(occupation2);
        occupationList.add(occupation3);

        Mockito.when(occupationRepository.findAll()).thenReturn(occupationList);

        List<Occupation> occupationListExpected = new ArrayList<>();
        occupationListExpected.add(occupation1);
        occupationListExpected.add(occupation3);

        ResponseEntity occupationListActual = parkingSpaceService.listOccupationByLicensePlate("abc-1234");


        Assertions.assertEquals(occupationListExpected, occupationListActual.getBody());
    }

    @Test
    public void itShouldReturnListOfOccupationByCpf(){
        Occupation occupation1 = new Occupation(Long.valueOf(1), Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation2 = new Occupation(Long.valueOf(2), Long.valueOf(123), new Car("xyz-6789", "Renault Sandero 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation3 = new Occupation(Long.valueOf(3), Long.valueOf(456), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(17, 0), LocalTime.of(19,0), 120);

        List<Occupation> occupationList = new ArrayList<>();
        occupationList.add(occupation1);
        occupationList.add(occupation2);
        occupationList.add(occupation3);

        Mockito.when(occupationRepository.findAll()).thenReturn(occupationList);

        List<Occupation> occupationListExpected = new ArrayList<>();
        occupationListExpected.add(occupation1);
        occupationListExpected.add(occupation2);

        ResponseEntity occupationListActual = parkingSpaceService.listOccupationByCpf(Long.valueOf(123));

        Assertions.assertEquals(occupationListExpected, occupationListActual.getBody());
    }


}
