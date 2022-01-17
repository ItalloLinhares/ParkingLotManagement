package com.parkingmanagement;

import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import com.parkingmanagement.parkingmanagement.service.ParkingSpaceServiceImplementation;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ParkingSpaceServiceTest {

    @InjectMocks
    ParkingSpaceServiceImplementation parkingSpaceService;

    @Mock
    ParkingSpaceRespository parkingSpaceRespository;

    @Before
    public void setup(){
        ParkingSpace parkingSpaceFilled = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), ParkingSpaceStatus.UNAVAILABLE, LocalTime.of(13, 0));
        Mockito.when(parkingSpaceRespository.findById(parkingSpaceFilled.getId())).thenReturn(Optional.of(parkingSpaceFilled));
    }

    @Test
    public void itShouldReturnOnlyEmptyParkingSpacesinlistParkingSpaceEmpty(){
        ParkingSpace parkingSpace1 = new ParkingSpace(Long.valueOf(1), new Car("abc-1234", "Honda Civic 2009"), Long.valueOf(123), ParkingSpaceStatus.UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace parkingSpace2 = new ParkingSpace(Long.valueOf(2), null, null, ParkingSpaceStatus.AVAILABLE, null);
        ParkingSpace parkingSpace3 = new ParkingSpace(Long.valueOf(3), null , null, ParkingSpaceStatus.AVAILABLE, null);

        Mockito.when(parkingSpaceRespository.findById(parkingSpaceFilled.getId())).thenReturn(Optional.of(parkingSpaceFilled));


    }
}
