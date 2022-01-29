package com.parkingmanagement.parkingmanagement.repository;


import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OccupationRepositoryTest {
    @Autowired
    OccupationRepository occupationRepository;

    @Autowired
    CarRepository carRepository;

    @Test
    public void itShouldReturnListOccupationByLicensePlate(){
        Car car1 = new Car("abc-1234", "Honda Civic 2009");
        Car car2 = new Car("xyz-6789", "Renault Sandero 2009");
        carRepository.save(car1);
        carRepository.save(car2);

        Occupation occupation1 = new Occupation(Long.valueOf(1), "525.721.680-90", car1, LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation2 = new Occupation(Long.valueOf(2), "525.721.680-90", car2, LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation3 = new Occupation(Long.valueOf(3), "043.777.480-50", car1, LocalTime.of(17, 0), LocalTime.of(19,0), 120);

        occupationRepository.save(occupation1);
        occupationRepository.save(occupation2);
        occupationRepository.save(occupation3);

        List<Occupation> occupationListExpected = new ArrayList<>();
        occupationListExpected.add(occupation1);
        occupationListExpected.add(occupation3);

        Pageable page = PageRequest.of(0, 2);

        Page<Occupation> occupationListActual = occupationRepository.findAllbyLicensePlate("abc-1234", page);

        Assertions.assertEquals(occupationListExpected, occupationListActual.toList());
    }


    @Test
    public void itShouldReturnListOccupationByCpf(){
        Car car1 = new Car("abc-1234", "Honda Civic 2009");
        Car car2 = new Car("xyz-6789", "Renault Sandero 2009");
        carRepository.save(car1);
        carRepository.save(car2);

        Occupation occupation1 = new Occupation(Long.valueOf(1), "525.721.680-90", car1, LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation2 = new Occupation(Long.valueOf(2), "525.721.680-90", car2, LocalTime.of(13, 0), LocalTime.of(15,0), 120);
        Occupation occupation3 = new Occupation(Long.valueOf(3), "043.777.480-50", car1, LocalTime.of(17, 0), LocalTime.of(19,0), 120);

        occupationRepository.save(occupation1);
        occupationRepository.save(occupation2);
        occupationRepository.save(occupation3);

        List<Occupation> occupationListExpected = new ArrayList<>();
        occupationListExpected.add(occupation1);
        occupationListExpected.add(occupation2);

        Pageable page = PageRequest.of(0, 5);

        Page<Occupation> occupationListActual = occupationRepository.findAllbyCPF("525.721.680-90", page);

        Assertions.assertEquals(occupationListExpected, occupationListActual.toList());
    }
}
