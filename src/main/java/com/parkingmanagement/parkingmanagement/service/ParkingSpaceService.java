package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.OccupationDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParkingSpaceService {
    public ParkingSpace fillParkingSpace(FillParkingSpaceDto fillParkingSpaceDTO);
    public Occupation vacateParkingSpace(VacateParkingSpaceDto vacateParkingSpaceDto);
    public List<FillParkingSpaceDto> listParkingSpaceFilled();
    public List<EmptyParkingSpaceDto> listParkingSpaceEmpty();
    public List<ParkingSpace> listParkingSpace();
    public List<Occupation> listAll();
    public OccupationDto listOccupationById(Long id);
    public List<OccupationDto> listOccupationByLicensePlate(String licensePlate);
    public List<OccupationDto> listOccupationByCpf(Long cpf);
    public void createParkingSpace();


}
