package com.parkingmanagement.parkingmanagement;

import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDTO;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import com.parkingmanagement.parkingmanagement.service.ParkingSpaceService;
import com.parkingmanagement.parkingmanagement.service.ParkingSpaceServiceImplementation;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
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
        public void fillParkingSpaceTest(){
            ParkingSpace parkingSpaceActual = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), ParkingSpaceStatus.UNAVAILABLE, LocalTime.of(13, 0));
            FillParkingSpaceDTO fillParkingSpaceDTO = new FillParkingSpaceDTO(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), ParkingSpaceStatus.UNAVAILABLE, LocalTime.of(13, 0));

            ParkingSpace parkingSpaceServiceExpected = parkingSpaceService.fillParkingSpace(fillParkingSpaceDTO);

            Assertions.assertEquals(parkingSpaceServiceExpected, parkingSpaceActual);
        }





}

