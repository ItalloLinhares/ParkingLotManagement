package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Car;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepository;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Transactional @AllArgsConstructor  @Service
public class OccupationServiceImplementation implements OccupationService{
    private final OccupationRepository occupationRepository;
    private final ParkingSpaceRespository parkingSpaceRespository;

    @Override
    public void createOccupation() {
        for (int id = 1; id <= 25; id++) {
            Occupation occupation = new Occupation(Long.valueOf(id), "525.721.680-90", new Car("abc-1234", "Honda Civic 2009"), LocalTime.of(13, 0), LocalTime.of(15,0), 120);
            occupationRepository.save(occupation);
        }
    }

    @Override
    public ResponseEntity saveOccupation(VacateParkingSpaceDto vacateParkingSpaceDto) {
        Optional<ParkingSpace> parkingSpaceToBeEmpty = parkingSpaceRespository.findById(vacateParkingSpaceDto.getId());

        int n = vacateParkingSpaceDto.getHourExity().compareTo(parkingSpaceToBeEmpty.get().getHourEntry());
        if (n <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exit time is equals or minor than Entry time");
        }


        float price = ((vacateParkingSpaceDto.getHourExity().getHour() - parkingSpaceToBeEmpty.get().getHourEntry().getHour())*60) +
                vacateParkingSpaceDto.getHourExity().getMinute() - parkingSpaceToBeEmpty.get().getHourEntry().getMinute();

        Occupation occupation = new Occupation(
                null,
                parkingSpaceToBeEmpty.get().getClientCpf(),
                parkingSpaceToBeEmpty.get().getCar(),
                parkingSpaceToBeEmpty.get().getHourEntry(),
                vacateParkingSpaceDto.getHourExity(),
                price
        );

        occupationRepository.save(occupation);

        parkingSpaceRespository.vacateParkingSpace(vacateParkingSpaceDto.getId());

        return ResponseEntity.ok(occupation);

    }

    @Override
    public ResponseEntity listAllOccupation(int page, int size) {
        return ResponseEntity.ok(occupationRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public ResponseEntity listOccupationById(Long idOccupation) {
        Optional<Occupation> occupation = occupationRepository.findById(idOccupation);

        if (occupation.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Occupation with this Id");
        }

        return ResponseEntity.ok(occupation.get());
    }

    @Override
    public ResponseEntity listOccupationByLicensePlate(String licensePlate, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Occupation> listOccupationByLicensePlate = occupationRepository.findAllbyLicensePlate(licensePlate, pageRequest);

        if(listOccupationByLicensePlate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Occupation with this LicensePlate");
        }

        return ResponseEntity.ok(listOccupationByLicensePlate);
    }

    @Override
    public ResponseEntity listOccupationByCpf(Long cpf, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<Occupation> listOccupationByCpf = occupationRepository.findAllbyCPF(cpf, pageRequest);

        if(listOccupationByCpf.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Occupation with this CPF");
        }

        return ResponseEntity.ok(listOccupationByCpf);
    }


}
