package com.parkingmanagement.parkingmanagement;

import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
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
public class FillParkingSpaceTest {

        @InjectMocks
        ParkingSpaceServiceImplementation parkingSpaceService;

        @Mock
        ParkingSpaceRespository parkingSpaceRespository;

        @Before
        public void setup(){
            ParkingSpace parkingSpace = new ParkingSpace(Long.valueOf(1), null, null, null, null);
            Mockito.when(parkingSpaceRespository.findById(parkingSpace.getId())).thenReturn(Optional.of(parkingSpace));
        }

        @Test
        public void ShouldFillParkingSpaceServiceTest(){
            ParkingSpace parkingSpaceActual = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), ParkingSpaceStatus.UNAVAILABLE, LocalTime.of(13, 0));
            FillParkingSpaceDto fillParkingSpaceDTO = new FillParkingSpaceDto(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), ParkingSpaceStatus.UNAVAILABLE, LocalTime.of(13, 0));

            ParkingSpace parkingSpaceServiceExpected = parkingSpaceService.fillParkingSpace(fillParkingSpaceDTO);

            Assertions.assertEquals(parkingSpaceServiceExpected, parkingSpaceActual);
        }





}

