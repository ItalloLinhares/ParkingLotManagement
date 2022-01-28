package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepository;
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

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;

@Transactional @AllArgsConstructor  @Service
public class OccupationServiceImplementation implements OccupationService{
    private final OccupationRepository occupationRepository;
    private final ParkingSpaceRespository parkingSpaceRespository;

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
//        occupation.setCar(parkingSpaceToBeEmpty.get().getCar());
//        occupation.setClientCpf(parkingSpaceToBeEmpty.get().getClientCpf());
//        occupation.setHourEntry(parkingSpaceToBeEmpty.get().getHourEntry());
//        occupation.setHourExit(vacateParkingSpaceDto.getHourExity());
//        occupation.setPrice(price);

        occupationRepository.save(occupation);

        parkingSpaceRespository.vacateParkingSpace(vacateParkingSpaceDto.getId());

        return ResponseEntity.ok(occupation);

    }

    @Override
    public ResponseEntity listAllOccupation() {
        return ResponseEntity.ok(occupationRepository.findAll());
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
    public ResponseEntity listOccupationByLicensePlate(String licensePlate) {
        List<Occupation> listOccupationByLicensePlate = occupationRepository.findAllbyLicensePlate(licensePlate);

        if(listOccupationByLicensePlate.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Occupation with this LicensePlate");
        }

        return ResponseEntity.ok(listOccupationByLicensePlate);
    }

    @Override
    public ResponseEntity listOccupationByCpf(Long cpf) {
        List<Occupation> listOccupationByCpf = occupationRepository.findAllbyCPF(cpf);

        if(listOccupationByCpf.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Occupation with this CPF");
        }

        return ResponseEntity.ok(listOccupationByCpf);
    }
}
