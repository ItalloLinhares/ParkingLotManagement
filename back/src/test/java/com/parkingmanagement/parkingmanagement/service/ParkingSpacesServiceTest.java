package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
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
import static org.junit.Assert.assertThat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;

@RunWith(SpringRunner.class)
public class ParkingSpacesServiceTest {
    @InjectMocks
    ParkingSpaceServiceImplementation parkingSpaceService;

    @Mock
    ParkingSpaceRespository parkingSpaceRespository;

    @Mock
    OccupationRepository occupationRepository;

    @Nested
    class ShouldListParkingSpacesByStatus{
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
            Assert.assertEquals( listParkingSpaceAvailableActual.getBody(), ResponseEntity.ok(listParkingSpaceAvailableExpected).getBody());
        }

    }
}
