package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;
import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;

@Transactional @AllArgsConstructor @Service
public class ParkingSpaceServiceImplementation implements ParkingSpaceService{

    private final ParkingSpaceRespository parkingSpaceRespository;

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

        if (listParkingSpaceAvailable.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available");
        }

        List<EmptyParkingSpaceDto> emptyParkingSpaceslist = new ArrayList<EmptyParkingSpaceDto>();

        for(int i = 0; i < listParkingSpaceAvailable.size(); i++){
            EmptyParkingSpaceDto parkingSpaceAvailable = new EmptyParkingSpaceDto(
                    listParkingSpaceAvailable.get(i).getId()
            );
            emptyParkingSpaceslist.add(parkingSpaceAvailable);
        }

        return ResponseEntity.ok(emptyParkingSpaceslist);
    }

    @Override
    public ResponseEntity listParkingSpaceFilled() {
        List<ParkingSpace> listParkingSpaceUnavailable = parkingSpaceRespository.findParkingSpacebyStatus(UNAVAILABLE);

        if (listParkingSpaceUnavailable.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available");
        }

        List<FillParkingSpaceDto> filledParkingSpaceslist = new ArrayList<FillParkingSpaceDto>();

        for(int i = 0; i < listParkingSpaceUnavailable.size(); i++){
            FillParkingSpaceDto parkingSpaceFilled = new FillParkingSpaceDto(
                    listParkingSpaceUnavailable.get(i).getId(),
                    listParkingSpaceUnavailable.get(i).getCar(),
                    listParkingSpaceUnavailable.get(i).getClientCpf(),
                    listParkingSpaceUnavailable.get(i).getHourEntry()
            );

            filledParkingSpaceslist.add(parkingSpaceFilled);
        }

        return ResponseEntity.ok(filledParkingSpaceslist);
    }


    @Override
    public ResponseEntity fillParkingSpace(FillParkingSpaceDto parkingSpaceFilled) {
        Optional<ParkingSpace> parkingSpaceToBeFilled = parkingSpaceRespository.findById(parkingSpaceFilled.getId());

        ParkingSpace parkingSpaceUpdated = new ParkingSpace(
                parkingSpaceFilled.getId(),
                parkingSpaceFilled.getCar(),
                parkingSpaceFilled.getClientCpf(),
                UNAVAILABLE,
                parkingSpaceFilled.getHourEntry()
        );

        parkingSpaceRespository.save(parkingSpaceUpdated);

        return ResponseEntity.ok(parkingSpaceUpdated);
    }
}
