package com.parkingmanagement.parkingmanagement.controller;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import com.parkingmanagement.parkingmanagement.service.ParkingSpaceService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController @RequestMapping("/api") @AllArgsConstructor
public class IndexController {
    private final ParkingSpaceService parkingSpaceService;
    private final ParkingSpaceRespository parkingSpaceRespository;

    @PostMapping("/create/parkingspaces")
    public void createParkingSpaces() {
        parkingSpaceService.createParkingSpace();
    }

    @GetMapping("/listParkingSpace")
    public ResponseEntity listParkingSpace() { return parkingSpaceService.listParkingSpace(); }

    @GetMapping("/listParkingSpaceAvailable")
    public ResponseEntity listParkingSpaceEmpty() {
        return parkingSpaceService.listParkingSpaceEmpty();
    }

    @GetMapping("/listParkingSpaceUnavailable")
    public ResponseEntity listParkingSpaceFilled() {
        return parkingSpaceService.listParkingSpaceFilled();
    }

    @PutMapping("/fillParkingSpace")
    public ResponseEntity fillParkingSpace(FillParkingSpaceDto parkingSpaceFilled) {
        return parkingSpaceService.fillParkingSpace(parkingSpaceFilled);
    }

    @PutMapping("/vacateParkingSpace")
    public ResponseEntity vacateParkingSpace(VacateParkingSpaceDto vacateParkingSpaceDto) {
        return parkingSpaceService.saveOccupation(vacateParkingSpaceDto);
    }

    @GetMapping("/listOccupation")
    public ResponseEntity listOccupation(){
        return parkingSpaceService.listAllOccupation();
    }

    @GetMapping("/listOccupationById/{id}")
    public ResponseEntity listOccupationById(@Param("id")Long id){
        return parkingSpaceService.listOccupationById(id);
    }

}
