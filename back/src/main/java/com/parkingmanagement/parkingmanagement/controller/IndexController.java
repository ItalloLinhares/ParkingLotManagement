package com.parkingmanagement.parkingmanagement.controller;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import com.parkingmanagement.parkingmanagement.service.ParkingSpaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController @AllArgsConstructor @RequestMapping("/management") @Transactional
public class IndexController {
    private final ParkingSpaceService parkingSpaceService;
    private final ParkingSpaceRespository parkingSpaceRespository;


    @RequestMapping("/create/parkingspaces")
    public void createParkingSpaces() {
        parkingSpaceService.createParkingSpace();
    }

    @RequestMapping("/listOccupation")
    public List<ParkingSpace> listParkingSpace(@RequestBody String findBy){
        return parkingSpaceService.listParkingSpace();
    }

    @GetMapping("/listParkingSpace")
    public List<ParkingSpace> listParkingSpace() {
        return parkingSpaceRespository.findAll();
    }

    @GetMapping("/listParkingSpaceAvailable")
    public List<EmptyParkingSpaceDto> listParkingSpaceEmpty() {
        return parkingSpaceService.listParkingSpaceEmpty();
    }

    @GetMapping("/listParkingSpaceUnavailable")
    public List<FillParkingSpaceDto> listParkingSpaceFilled() {
        return parkingSpaceService.listParkingSpaceFilled();
    }

    @RequestMapping("/fillParkingSpace")
    public ParkingSpace fillParkingSpace(FillParkingSpaceDto parkingSpaceFilled) {
        return parkingSpaceService.fillParkingSpace(parkingSpaceFilled);
    }

    @RequestMapping("/vacateParkingSpcae")
    public Occupation vacateParkingSpace(VacateParkingSpaceDto vacateParkingSpaceDto) {
        return parkingSpaceService.vacateParkingSpace(vacateParkingSpaceDto);
    }

}
