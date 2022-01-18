package com.parkingmanagement.parkingmanagement.repository;


import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;
import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;

@RunWith(SpringRunner.class)
@DataJpaTest
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ParkingSpaceRepositoryTest {
    @Autowired
    ParkingSpaceRespository parkingSpaceRespository;

    @Autowired
    CarRepository carRepository;

    @Test
    public void itShouldReturnOnlyAvailableParkingSpaces(){

        //Given
        Car car = new Car("abc-1234", "Honda Civic 2009");
        carRepository.save(car);
        ParkingSpace parkingSpace1 = new ParkingSpace(Long.valueOf(1), car, Long.valueOf(123), ParkingSpaceStatus.UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace parkingSpace2 = new ParkingSpace(Long.valueOf(2), null, null, AVAILABLE, null);
        ParkingSpace parkingSpace3 = new ParkingSpace(Long.valueOf(3), null , null, AVAILABLE, null);

        parkingSpaceRespository.save(parkingSpace1);
        parkingSpaceRespository.save(parkingSpace2);
        parkingSpaceRespository.save(parkingSpace3);

        List<ParkingSpace> parkingSpaceListAvailableExpected = new ArrayList<>();
        parkingSpaceListAvailableExpected.add(parkingSpace2);
        parkingSpaceListAvailableExpected.add(parkingSpace3);


        //When
        List<ParkingSpace> parkingSpaceListAvailableActual = parkingSpaceRespository.findParkingSpacebyStatus(AVAILABLE);


        //Then
        Assertions.assertEquals(parkingSpaceListAvailableActual, parkingSpaceListAvailableExpected);

    }

    @Test
    public void itShouldReturnOnlyUnavailableParkingSpaces(){

        //Given
        Car car = new Car("abc-1234", "Honda Civic 2009");
        carRepository.save(car);
        ParkingSpace parkingSpace1 = new ParkingSpace(Long.valueOf(1), car, Long.valueOf(123), UNAVAILABLE, LocalTime.of(13, 0));
        ParkingSpace parkingSpace2 = new ParkingSpace(Long.valueOf(2), null, null, AVAILABLE, null);
        ParkingSpace parkingSpace3 = new ParkingSpace(Long.valueOf(3), null , null, AVAILABLE, null);

        parkingSpaceRespository.save(parkingSpace1);
        parkingSpaceRespository.save(parkingSpace2);
        parkingSpaceRespository.save(parkingSpace3);

        List<ParkingSpace> parkingSpaceListAvailableExpected = new ArrayList<>();
        parkingSpaceListAvailableExpected.add(parkingSpace1);


        //When
        List<ParkingSpace> parkingSpaceListAvailableActual = parkingSpaceRespository.findParkingSpacebyStatus(UNAVAILABLE);


        //Then
        Assertions.assertEquals(parkingSpaceListAvailableActual, parkingSpaceListAvailableExpected);
    }


}
