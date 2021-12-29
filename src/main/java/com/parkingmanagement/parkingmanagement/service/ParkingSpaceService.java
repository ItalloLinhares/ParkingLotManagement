package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDTO;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParkingSpaceService {
    public ParkingSpace fillParkingSpace(FillParkingSpaceDTO fillParkingSpaceDTO);
    public Occupation vacateParkingSpace(VacateParkingSpaceDto vacateParkingSpaceDto);
    public List<FillParkingSpaceDTO> listParkingSpaceFilled();
    public void createParkingSpace();


}
