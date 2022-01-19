package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.OccupationDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public interface ParkingSpaceService {
    public void createParkingSpace();
    public ResponseEntity listParkingSpace();
    public ResponseEntity listParkingSpaceFilled();
    public ResponseEntity listParkingSpaceEmpty();
    public ResponseEntity fillParkingSpace(FillParkingSpaceDto fillParkingSpaceDTO);
    public ResponseEntity saveOccupation(VacateParkingSpaceDto vacateParkingSpaceDto);
    public ResponseEntity listAllOccupation();
    public ResponseEntity listOccupationById(Long idOccupation);
    public ResponseEntity listOccupationByLicensePlate(String licensePlate);
    public ResponseEntity listOccupationByCpf(Long cpf);



}
