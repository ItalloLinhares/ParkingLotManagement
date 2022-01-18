package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.OccupationDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParkingSpaceService {
    public void createParkingSpace();
    public ResponseEntity listParkingSpace();
    public ResponseEntity listParkingSpaceFilled();
    public ResponseEntity listParkingSpaceEmpty();
    public ResponseEntity fillParkingSpace(FillParkingSpaceDto fillParkingSpaceDTO);
    public ResponseEntity vacateParkingSpace(VacateParkingSpaceDto vacateParkingSpaceDto);


    public List<com.parkingmanagement.parkingmanagement.model.Occupation> listAllOccupation();
    public OccupationDto listOccupationById(Long id);
//  public List<com.parkingmanagement.parkingmanagement.model.Occupation> listOccupationByLicensePlate(String licensePlate);
    public List<OccupationDto> listOccupationByCpf(Long cpf);



}
