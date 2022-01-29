package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepository;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepositoryTest;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;
import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;

@RunWith(SpringRunner.class)
public class OccupationServiceTest {

    @InjectMocks
    OccupationServiceImplementation occupationService;

    @Mock
    ParkingSpaceServiceImplementation parkingSpaceService;

    @Mock
    ParkingSpaceRespository parkingSpaceRespository;

    @Mock
    OccupationRepository occupationRepository;

    @Test
    public void itShouldSaveTheOccupation(){
        ParkingSpace filledParkingSpace = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), "525.721.680-90", UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace emptyParkingSpaceExpected = new ParkingSpace(Long.valueOf(1), null , null, AVAILABLE, null);
        VacateParkingSpaceDto vacateParkingSpace = new VacateParkingSpaceDto(Long.valueOf(1), LocalTime.of(15, 0));
        Occupation occupationExpected = new Occupation(null, "525.721.680-90", new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);


        Mockito.when(parkingSpaceRespository.findById(vacateParkingSpace.getId())).thenReturn(Optional.of(filledParkingSpace));


        ResponseEntity occupationActual = occupationService.saveOccupation(vacateParkingSpace);

        Assert.assertEquals(occupationExpected, occupationActual.getBody());
    }

    @Test
    public void itShouldFindOccupationById(){
        Occupation occupationExpected = new Occupation(Long.valueOf(1), "525.721.680-90", new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Mockito.when(occupationRepository.findById(occupationExpected.getId())).thenReturn(Optional.of(occupationExpected));

        ResponseEntity occupationActual = occupationService.listOccupationById(occupationExpected.getId());


        Assert.assertEquals(occupationExpected, occupationActual.getBody());
    }

    @Test
    public void itShouldReturnListOfOccupationByLicensePlate(){
        Occupation occupation1 = new Occupation(Long.valueOf(1), "525.721.680-90", new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation2 = new Occupation(Long.valueOf(2), "525.721.680-90", new Car("xyz-6789", "Renault Sandero 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation3 = new Occupation(Long.valueOf(3), "043.777.480-50", new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(17, 0), LocalTime.of(19,0), 120);

        List<Occupation> occupationList = new ArrayList<>();
        occupationList.add(occupation1);
        occupationList.add(occupation3);

        Pageable page = PageRequest.of(0, 2);
        Page occupationListPage = new PageImpl(occupationList, page, occupationList.size());
        Mockito.when(occupationRepository.findAllbyLicensePlate("abc-1234", page)).thenReturn(occupationListPage);

        List<Occupation> occupationListExpectedList = new ArrayList<>();
        occupationListExpectedList.add(occupation1);
        occupationListExpectedList.add(occupation3);

        Page occupationListExpected = new PageImpl(occupationListExpectedList, page, occupationList.size());

        ResponseEntity occupationListActual = occupationService.listOccupationByLicensePlate("abc-1234", 0, 2);


        Assertions.assertEquals(occupationListExpected, occupationListActual.getBody());
    }

    @Test
    public void itShouldReturnListOfOccupationByCpf(){
        Occupation occupation1 = new Occupation(Long.valueOf(1), "525.721.680-90", new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation2 = new Occupation(Long.valueOf(2), "525.721.680-90", new Car("xyz-6789", "Renault Sandero 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation3 = new Occupation(Long.valueOf(3), "043.777.480-50", new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(17, 0), LocalTime.of(19,0), 120);

        List<Occupation> occupationList = new ArrayList<>();
        occupationList.add(occupation1);
        occupationList.add(occupation2);


        Pageable page = PageRequest.of(0, 2);
        Page occupationListPage = new PageImpl(occupationList, page, occupationList.size());
        Mockito.when(occupationRepository.findAllbyCPF("525.721.680-90", page)).thenReturn(occupationListPage);

        List<Occupation> occupationListExpectedList = new ArrayList<>();
        occupationListExpectedList.add(occupation1);
        occupationListExpectedList.add(occupation2);

        ResponseEntity occupationListActual = occupationService.listOccupationByCpf("525.721.680-90", 0, 2);

        Page occupationListExpected = new PageImpl(occupationListExpectedList, page, occupationList.size());

        Assertions.assertEquals(occupationListExpected, occupationListActual.getBody());
    }
}
