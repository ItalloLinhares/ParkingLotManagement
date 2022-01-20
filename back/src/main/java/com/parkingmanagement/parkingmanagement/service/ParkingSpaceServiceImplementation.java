package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.OccupationDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.CarRepository;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepository;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;
import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;

@Transactional @AllArgsConstructor @Service
public class ParkingSpaceServiceImplementation implements ParkingSpaceService{

    private final ParkingSpaceRespository parkingSpaceRespository;

    private final CarRepository carRepository;

    @Override
    public void createParkingSpace() {
        for (int id = 1; id <= 25; id++) {
            ParkingSpace parkingSpace = new ParkingSpace(Long.valueOf(id), null, null, AVAILABLE, null);
            parkingSpaceRespository.save(parkingSpace);
        }

    }

    @Override
    public ResponseEntity listParkingSpace() {

        return ResponseEntity.ok(parkingSpaceRespository.findAll());
    }

    @Override
    public ResponseEntity listParkingSpaceEmpty() {
        List<ParkingSpace> listParkingSpaceAvailable = parkingSpaceRespository.findParkingSpacebyStatus(AVAILABLE);

        //Validating the data passed
        if (listParkingSpaceAvailable.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available");
        }

        List<EmptyParkingSpaceDto> emptyParkingSpaceslist = new ArrayList<EmptyParkingSpaceDto>();

        for(int i = 0; i < listParkingSpaceAvailable.size(); i++){
            EmptyParkingSpaceDto parkingSpaceAvailable = new EmptyParkingSpaceDto();
            parkingSpaceAvailable.setId(listParkingSpaceAvailable.get(i).getId());
            emptyParkingSpaceslist.add(parkingSpaceAvailable);
        }

        return ResponseEntity.ok(emptyParkingSpaceslist);
    }

    @Override
    public ResponseEntity listParkingSpaceFilled() {
        List<ParkingSpace> listParkingSpaceUnavailable = parkingSpaceRespository.findParkingSpacebyStatus(UNAVAILABLE);

        //Validating the data passed
        if (listParkingSpaceUnavailable.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available");
        }

        List<FillParkingSpaceDto> filledParkingSpaceslist = new ArrayList<FillParkingSpaceDto>();

        for(int i = 0; i < listParkingSpaceUnavailable.size(); i++){
            FillParkingSpaceDto parkingSpaceFilled = new FillParkingSpaceDto();
            parkingSpaceFilled.setId(listParkingSpaceUnavailable.get(i).getId());
            parkingSpaceFilled.setClientCpf(listParkingSpaceUnavailable.get(i).getClientCpf());
            parkingSpaceFilled.setCar(listParkingSpaceUnavailable.get(i).getCar());
            parkingSpaceFilled.setHourEntry(listParkingSpaceUnavailable.get(i).getHourEntry());
            filledParkingSpaceslist.add(parkingSpaceFilled);
        }

        return ResponseEntity.ok(filledParkingSpaceslist);
    }


    @Override
    public ResponseEntity fillParkingSpace(FillParkingSpaceDto parkingSpaceFilled) {
        Optional<ParkingSpace> parkingSpaceToBeFilled = parkingSpaceRespository.findById(parkingSpaceFilled.getId());

        //Validating the data passed
        if (!parkingSpaceToBeFilled.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available");
        }
        if (parkingSpaceToBeFilled.get().getParkingSpaceStatus() != AVAILABLE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This ParkingSpace is already used");
        }

        carRepository.save(parkingSpaceFilled.getCar());

        ParkingSpace parkingSpaceUpdated = new ParkingSpace();
        parkingSpaceUpdated.setId(parkingSpaceFilled.getId());
        parkingSpaceUpdated.setCar(parkingSpaceFilled.getCar());
        parkingSpaceUpdated.setClientCpf(parkingSpaceFilled.getClientCpf());
        parkingSpaceUpdated.setHourEntry(parkingSpaceFilled.getHourEntry());
        parkingSpaceUpdated.setParkingSpaceStatus(UNAVAILABLE);
        parkingSpaceRespository.save(parkingSpaceUpdated);

        return ResponseEntity.ok(parkingSpaceUpdated);
    }
}
