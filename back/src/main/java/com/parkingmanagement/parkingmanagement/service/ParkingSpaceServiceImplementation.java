package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.OccupationDto;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;
import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;

@Transactional @AllArgsConstructor @Service
public class ParkingSpaceServiceImplementation implements ParkingSpaceService{

    private final ParkingSpaceRespository parkingSpaceRespository;

    private final OccupationRepository occupationRepository;

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

        //Validating the data passed
        if (listParkingSpaceAvailable.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available");
        }

        List<EmptyParkingSpaceDto> emptyParkingSpaceslist = new ArrayList<EmptyParkingSpaceDto>();

        for(int i = 0; i < listParkingSpaceAvailable.size(); i++){
            EmptyParkingSpaceDto parkingSpaceAvailable = new EmptyParkingSpaceDto();
            parkingSpaceAvailable.setId(listParkingSpaceAvailable.get(i).getId());
            emptyParkingSpaceslist.add(parkingSpaceAvailable);
        }

        return ResponseEntity.ok(emptyParkingSpaceslist);
    }

    @Override
    public ResponseEntity listParkingSpaceFilled() {
        List<ParkingSpace> listParkingSpaceUnavailable = parkingSpaceRespository.findParkingSpacebyStatus(UNAVAILABLE);

        //Validating the data passed
        if (listParkingSpaceUnavailable.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available");
        }

        List<FillParkingSpaceDto> filledParkingSpaceslist = new ArrayList<FillParkingSpaceDto>();

        for(int i = 0; i < listParkingSpaceUnavailable.size(); i++){
            FillParkingSpaceDto parkingSpaceFilled = new FillParkingSpaceDto();
            parkingSpaceFilled.setId(listParkingSpaceUnavailable.get(i).getId());
            parkingSpaceFilled.setParkingSpaceStatus(listParkingSpaceUnavailable.get(i).getParkingSpaceStatus());
            parkingSpaceFilled.setClientCpf(listParkingSpaceUnavailable.get(i).getClientCpf());
            parkingSpaceFilled.setCar(listParkingSpaceUnavailable.get(i).getCar());
            parkingSpaceFilled.setHourEntry(listParkingSpaceUnavailable.get(i).getHourEntry());
            filledParkingSpaceslist.add(parkingSpaceFilled);
        }

        return ResponseEntity.ok(filledParkingSpaceslist);
    }


    @Override
    public ResponseEntity fillParkingSpace(FillParkingSpaceDto parkingSpaceFilled) {
        Optional<ParkingSpace> parkingSpaceToBeFilled = parkingSpaceRespository.findById(parkingSpaceFilled.getId());

        //Validating the data passed
        if (!parkingSpaceToBeFilled.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available");
        }
        if (parkingSpaceToBeFilled.get().getParkingSpaceStatus() != AVAILABLE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This ParkingSpace is already used");
        }

        ParkingSpace parkingSpaceUpdated = new ParkingSpace();
        parkingSpaceUpdated.setId(parkingSpaceFilled.getId());
        parkingSpaceUpdated.setCar(parkingSpaceFilled.getCar());
        parkingSpaceUpdated.setClientCpf(parkingSpaceFilled.getClientCpf());
        parkingSpaceUpdated.setHourEntry(parkingSpaceFilled.getHourEntry());
        parkingSpaceUpdated.setParkingSpaceStatus(parkingSpaceFilled.getParkingSpaceStatus());
        parkingSpaceRespository.save(parkingSpaceUpdated);

        return ResponseEntity.ok(parkingSpaceUpdated);
    }

    @Override
    public ResponseEntity saveOccupation(VacateParkingSpaceDto vacateParkingSpaceDto) {
        Optional<ParkingSpace> parkingSpaceToBeEmpty = parkingSpaceRespository.findById(vacateParkingSpaceDto.getId());

        //Validating the data passed
        if (parkingSpaceToBeEmpty.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available");
        }

        if (parkingSpaceToBeEmpty.get().getParkingSpaceStatus() != UNAVAILABLE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This ParkingSpace is already empty");
        }

        int n = vacateParkingSpaceDto.getHourExity().compareTo(parkingSpaceToBeEmpty.get().getHourEntry());
        if (n <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exit time is equals or minor than Entry time");
        }


        float price = ((vacateParkingSpaceDto.getHourExity().getHour() - parkingSpaceToBeEmpty.get().getHourEntry().getHour())*60) +
                vacateParkingSpaceDto.getHourExity().getMinute() - parkingSpaceToBeEmpty.get().getHourEntry().getMinute();

        Occupation occupation = new Occupation();
        occupation.setCar(parkingSpaceToBeEmpty.get().getCar());
        occupation.setClientCpf(parkingSpaceToBeEmpty.get().getClientCpf());
        occupation.setHourEntry(parkingSpaceToBeEmpty.get().getHourEntry());
        occupation.setHourExit(vacateParkingSpaceDto.getHourExity());
        occupation.setPrice(price);

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
        List<Occupation> occupationList = this.occupationRepository.findAll();
        List<Occupation> listOccupationByLicensePlate = new ArrayList<>();


        for (int i = 0; i < occupationList.size(); i++){
            if(occupationList.get(i).getCar().getCarLicensePlate() == licensePlate){
                listOccupationByLicensePlate.add(occupationList.get(i));
            }
        }

        if(listOccupationByLicensePlate.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Occupation with this LicensePlate");
        }

        return ResponseEntity.ok(listOccupationByLicensePlate);
}

    @Override
    public ResponseEntity listOccupationByCpf(Long cpf) {

        List<Occupation> occupationList = this.occupationRepository.findAll();
        List<Occupation> listOccupationByCpf = new ArrayList<>();

        for (int i = 0; i < occupationList.size(); i++){
            if(occupationList.get(i).getClientCpf() == cpf){
                listOccupationByCpf.add(occupationList.get(i));
            }
        }

        if(listOccupationByCpf.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Occupation with this CPF");
        }

        return ResponseEntity.ok(listOccupationByCpf);
    }


}
