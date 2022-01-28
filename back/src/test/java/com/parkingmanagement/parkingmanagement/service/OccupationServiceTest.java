//package com.parkingmanagement.parkingmanagement.service;
//
//import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
//import com.parkingmanagement.parkingmanagement.model.Car;
//import com.parkingmanagement.parkingmanagement.model.Occupation;
//import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
//import com.parkingmanagement.parkingmanagement.repository.OccupationRepository;
//import com.parkingmanagement.parkingmanagement.repository.OccupationRepositoryTest;
//import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;
//import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;
//
//@RunWith(SpringRunner.class)
//public class OccupationServiceTest {
//
//    @InjectMocks
//    OccupationServiceImplementation occupationService;
//
//    @Mock
//    ParkingSpaceServiceImplementation parkingSpaceService;
//
//    @Mock
//    ParkingSpaceRespository parkingSpaceRespository;
//
//    @Mock
//    OccupationRepository occupationRepository;
//
//    @Test
//    public void itShouldSaveTheOccupation(){
//        ParkingSpace filledParkingSpace = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), UNAVAILABLE, LocalTime.of(13, 0));
//        ParkingSpace emptyParkingSpaceExpected = new ParkingSpace(Long.valueOf(1), null , null, AVAILABLE, null);
//        VacateParkingSpaceDto vacateParkingSpace = new VacateParkingSpaceDto(Long.valueOf(1), LocalTime.of(15, 0));
//        Occupation occupationExpected = new Occupation(null, Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
//
//
//        Mockito.when(parkingSpaceRespository.findById(vacateParkingSpace.getId())).thenReturn(Optional.of(filledParkingSpace));
//
//
//        ResponseEntity occupationActual = occupationService.saveOccupation(vacateParkingSpace);
//
//        Assert.assertEquals(occupationExpected, occupationActual.getBody());
//    }
//
//    @Test
//    public void itShouldFindOccupationById(){
//        Occupation occupationExpected = new Occupation(Long.valueOf(1), Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
//        Mockito.when(occupationRepository.findById(occupationExpected.getId())).thenReturn(Optional.of(occupationExpected));
//
//        ResponseEntity occupationActual = occupationService.listOccupationById(occupationExpected.getId());
//
//
//        Assert.assertEquals(occupationExpected, occupationActual.getBody());
//    }
//
//    @Test
//    public void itShouldReturnListOfOccupationByLicensePlate(){
//        Occupation occupation1 = new Occupation(Long.valueOf(1), Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
//        Occupation occupation2 = new Occupation(Long.valueOf(2), Long.valueOf(123), new Car("xyz-6789", "Renault Sandero 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
//        Occupation occupation3 = new Occupation(Long.valueOf(3), Long.valueOf(456), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(17, 0), LocalTime.of(19,0), 120);
//
//        List<Occupation> occupationList = new ArrayList<>();
//        occupationList.add(occupation1);
//        occupationList.add(occupation2);
//        occupationList.add(occupation3);
//
//        Mockito.when(occupationRepository.findAllbyLicensePlate("abc-1234")).thenReturn(occupationList);
//
//        List<Occupation> occupationListExpected = new ArrayList<>();
//        occupationListExpected.add(occupation1);
//        occupationListExpected.add(occupation3);
//
//        ResponseEntity occupationListActual = occupationService.listOccupationByLicensePlate("abc-1234");
//
//
//        Assertions.assertEquals(occupationListExpected, occupationListActual.getBody());
//    }
//
//    @Test
//    public void itShouldReturnListOfOccupationByCpf(){
//        Occupation occupation1 = new Occupation(Long.valueOf(1), Long.valueOf(123), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
//        Occupation occupation2 = new Occupation(Long.valueOf(2), Long.valueOf(123), new Car("xyz-6789", "Renault Sandero 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
//        Occupation occupation3 = new Occupation(Long.valueOf(3), Long.valueOf(456), new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(17, 0), LocalTime.of(19,0), 120);
//
//        List<Occupation> occupationList = new ArrayList<>();
//        occupationList.add(occupation1);
//        occupationList.add(occupation2);
//
//
//        Mockito.when(occupationRepository.findAllbyCPF(Long.valueOf(123))).thenReturn(occupationList);
//
//        List<Occupation> occupationListExpected = new ArrayList<>();
//        occupationListExpected.add(occupation1);
//        occupationListExpected.add(occupation2);
//
//        ResponseEntity occupationListActual = occupationService.listOccupationByCpf(Long.valueOf(123));
//
//        Assertions.assertEquals(occupationListExpected, occupationListActual.getBody());
//    }
//}
