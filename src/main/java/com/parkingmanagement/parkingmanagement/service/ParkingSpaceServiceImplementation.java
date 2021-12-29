package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.EmptyParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.FillParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import com.parkingmanagement.parkingmanagement.model.Occupation;
import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.OccupationRepository;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<FillParkingSpaceDto> listParkingSpaceFilled() {
        Long totalParkingSpaces = parkingSpaceRespository.count();
        List<FillParkingSpaceDto> listParkingSpaceUnvailable = new ArrayList<FillParkingSpaceDto>();
        for (int i = 1; i <= totalParkingSpaces; i++){
            Optional<ParkingSpace> parkingSpace = parkingSpaceRespository.findById(Long.valueOf(i));
            if (parkingSpace.get().getParkingSpaceStatus() == UNAVAILABLE){
                parkingSpace.ifPresent(parkingSpacePresent -> {
                    FillParkingSpaceDto parkingSpaceFilled = new FillParkingSpaceDto();
                    parkingSpaceFilled.setId(parkingSpacePresent.getId());
                    parkingSpaceFilled.setParkingSpaceStatus(parkingSpacePresent.getParkingSpaceStatus());
                    parkingSpaceFilled.setClientCpf(parkingSpacePresent.getClientCpf());
                    parkingSpaceFilled.setCar(parkingSpacePresent.getCar());
                    parkingSpaceFilled.setHourEntry(parkingSpacePresent.getHourEntry());
                    listParkingSpaceUnvailable.add(parkingSpaceFilled);
                });
            }
        }
        return listParkingSpaceUnvailable;
    }


    @Override
    public ParkingSpace fillParkingSpace(FillParkingSpaceDto parkingSpaceFilled) {
        Optional<ParkingSpace> parkingSpaceid = parkingSpaceRespository.findById(parkingSpaceFilled.getId());
        if(parkingSpaceid.isPresent()){
            ParkingSpace parkingSpaceUpdated = new ParkingSpace();
            parkingSpaceUpdated.setId(parkingSpaceFilled.getId());
            parkingSpaceUpdated.setCar(parkingSpaceFilled.getCar());
            parkingSpaceUpdated.setClientCpf(parkingSpaceFilled.getClientCpf());
            parkingSpaceUpdated.setHourEntry(parkingSpaceFilled.getHourEntry());
            parkingSpaceUpdated.setParkingSpaceStatus(parkingSpaceFilled.getParkingSpaceStatus());
            parkingSpaceRespository.save(parkingSpaceUpdated);
            return parkingSpaceUpdated;
        }
        else {
            return null;
        }
    }

    @Override
    public Occupation vacateParkingSpace(VacateParkingSpaceDto vacateParkingSpaceDto) {
        Optional<ParkingSpace> parkingSpaceFilled = parkingSpaceRespository.findById(vacateParkingSpaceDto.getId());
        if(parkingSpaceFilled.isPresent()){
            Occupation occupation = new Occupation();
            occupation.setCar(parkingSpaceFilled.get().getCar());
            occupation.setClientCpf(parkingSpaceFilled.get().getClientCpf());
            occupation.setHourEntry(parkingSpaceFilled.get().getHourEntry());
            occupation.setHourExit(vacateParkingSpaceDto.getHourExity());
            float price = ((vacateParkingSpaceDto.getHourExity().getHour() - parkingSpaceFilled.get().getHourEntry().getHour())*60) +
                    vacateParkingSpaceDto.getHourExity().getMinute() - parkingSpaceFilled.get().getHourEntry().getMinute();
            occupation.setPrice(price);
            occupationRepository.save(occupation);

            ParkingSpace parkingSpaceUpdated = new ParkingSpace();
            parkingSpaceUpdated.setId(vacateParkingSpaceDto.getId());
            parkingSpaceUpdated.setParkingSpaceStatus(AVAILABLE);
            parkingSpaceRespository.save(parkingSpaceUpdated);
            return occupation;
        }
        else {
            return null;
        }
    }

    @Override
    public List<EmptyParkingSpaceDto> listParkingSpaceEmpty() {
        Long totalParkingSpaces = parkingSpaceRespository.count();
        List<EmptyParkingSpaceDto> listParkingSpaceAvailable = new ArrayList<EmptyParkingSpaceDto>();
        for (int i = 1; i <= totalParkingSpaces; i++){
            Optional<ParkingSpace> parkingSpace = parkingSpaceRespository.findById(Long.valueOf(i));
            if (parkingSpace.get().getParkingSpaceStatus() == AVAILABLE){
                parkingSpace.ifPresent(new Consumer<ParkingSpace>() {
                    @Override
                    public void accept(ParkingSpace parkingSpace) {
                        EmptyParkingSpaceDto parkingSpaceAvailable = new EmptyParkingSpaceDto();
                        parkingSpaceAvailable.setId(parkingSpace.getId());
                        listParkingSpaceAvailable.add(parkingSpaceAvailable);
                    }
                });
            }
        }
        return listParkingSpaceAvailable;
    }

    @Override
    public List<ParkingSpace> listParkingSpace() {
        return parkingSpaceRespository.findAll();
    }


}
