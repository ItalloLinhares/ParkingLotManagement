package com.parkingmanagement.parkingmanagement.controller;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import com.parkingmanagement.parkingmanagement.service.OccupationService;
import com.parkingmanagement.parkingmanagement.service.ParkingSpaceService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController @RequestMapping("/api") @AllArgsConstructor
public class IndexController {
    private final ParkingSpaceService parkingSpaceService;

    private final ParkingSpaceRespository parkingSpaceRespository;

    private final OccupationService occupationService;

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
    public ResponseEntity fillParkingSpace(@Valid @RequestBody FillParkingSpaceDto parkingSpaceFilled) {
        return parkingSpaceService.fillParkingSpace(parkingSpaceFilled);
    }

    @PostMapping("/create/occupations")
    public void createOccupation() { occupationService.createOccupation();  }

    @PutMapping("/vacateParkingSpace")
    public ResponseEntity vacateParkingSpace(@RequestBody VacateParkingSpaceDto vacateParkingSpaceDto) {
        return occupationService.saveOccupation(vacateParkingSpaceDto);
    }

    @GetMapping("/listOccupation")
    public ResponseEntity listOccupation(@RequestParam("page") int page, @RequestParam("size") int size){
        return occupationService.listAllOccupation(page, size);
    }

    @GetMapping("/listOccupationById")
    public ResponseEntity listOccupationById(@RequestParam("id")Long id){
        return occupationService.listOccupationById(id);
    }

    @GetMapping("/listOccupationBylicensPlate/{licensPlate}")
    public ResponseEntity listOccupationByLicensPlate(@PathVariable("licensPlate")String licensPlate, @RequestParam("page") int page, @RequestParam("size") int size){
        return occupationService.listOccupationByLicensePlate(licensPlate, page, size);
    }

    @GetMapping("/listOccupationByCpf/{cpf}")
    public ResponseEntity listOccupationByCpf(@PathVariable("cpf")String cpf, @RequestParam("page") int page, @RequestParam("size") int size){
        return occupationService.listOccupationByCpf(cpf, page, size);
    }



}
