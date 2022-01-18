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
    public ResponseEntity listParkingSpaceEmpty() {
        List<ParkingSpace> listParkingSpaceAvailable = parkingSpaceRespository.findParkingSpacebyStatus(AVAILABLE);
        if (listParkingSpaceAvailable.size() == 0) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available"); }
        List<EmptyParkingSpaceDto> emptyParkingSpaceslist = new ArrayList<EmptyParkingSpaceDto>();
        for(int i = 0; i < listParkingSpaceAvailable.size(); i++){
            EmptyParkingSpaceDto parkingSpaceAvailable = new EmptyParkingSpaceDto();
            parkingSpaceAvailable.setId(listParkingSpaceAvailable.get(i).getId());
            emptyParkingSpaceslist.add(parkingSpaceAvailable);
        }
        return ResponseEntity.ok(emptyParkingSpaceslist);

//        Long totalParkingSpaces = parkingSpaceRespository.count();
//        List<EmptyParkingSpaceDto> listParkingSpaceAvailable = new ArrayList<EmptyParkingSpaceDto>();
//        for (int i = 1; i <= totalParkingSpaces; i++){
//            Optional<ParkingSpace> parkingSpace = parkingSpaceRespository.findById(Long.valueOf(i));
//            if (parkingSpace.get().getParkingSpaceStatus() == AVAILABLE){
//                parkingSpace.ifPresent(new Consumer<ParkingSpace>() {
//                    @Override
//                    public void accept(ParkingSpace parkingSpace) {
//                        EmptyParkingSpaceDto parkingSpaceAvailable = new EmptyParkingSpaceDto();
//                        parkingSpaceAvailable.setId(parkingSpace.getId());
//                        listParkingSpaceAvailable.add(parkingSpaceAvailable);
//                    }
//                });
//            }
//        }
//        return ResponseEntity.ok(listParkingSpaceAvailable);
    }

    @Override
    public ResponseEntity listParkingSpaceFilled() {
        List<ParkingSpace> listParkingSpaceUnavailable = parkingSpaceRespository.findParkingSpacebyStatus(UNAVAILABLE);
        if (listParkingSpaceUnavailable.size() == 0) { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking space Available"); }
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

//        Long totalParkingSpaces = parkingSpaceRespository.count();
//        List<FillParkingSpaceDto> listParkingSpaceUnvailable = new ArrayList<FillParkingSpaceDto>();
//        for (int i = 1; i <= totalParkingSpaces; i++){
//            Optional<ParkingSpace> parkingSpace = parkingSpaceRespository.findById(Long.valueOf(i));
//            if (parkingSpace.get().getParkingSpaceStatus() == UNAVAILABLE){
//                parkingSpace.ifPresent(parkingSpacePresent -> {
//                    FillParkingSpaceDto parkingSpaceFilled = new FillParkingSpaceDto();
//                    parkingSpaceFilled.setId(parkingSpacePresent.getId());
//                    parkingSpaceFilled.setParkingSpaceStatus(parkingSpacePresent.getParkingSpaceStatus());
//                    parkingSpaceFilled.setClientCpf(parkingSpacePresent.getClientCpf());
//                    parkingSpaceFilled.setCar(parkingSpacePresent.getCar());
//                    parkingSpaceFilled.setHourEntry(parkingSpacePresent.getHourEntry());
//                    listParkingSpaceUnvailable.add(parkingSpaceFilled);
//                });
//            }
//        }
//        return listParkingSpaceUnvailable;
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
    public com.parkingmanagement.parkingmanagement.model.Occupation vacateParkingSpace(VacateParkingSpaceDto vacateParkingSpaceDto) {
        Optional<ParkingSpace> parkingSpaceFilled = parkingSpaceRespository.findById(vacateParkingSpaceDto.getId());
        if(parkingSpaceFilled.isPresent()){
            com.parkingmanagement.parkingmanagement.model.Occupation occupation = new com.parkingmanagement.parkingmanagement.model.Occupation();
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
    public List<ParkingSpace> listParkingSpace() {
        return null;
    }

    @Override
    public List<com.parkingmanagement.parkingmanagement.model.Occupation> listAllOccupation() {
        return occupationRepository.findAll();
    }

    @Override
    public OccupationDto listOccupationById(Long id) {
        Long n = occupationRepository.count();
        for (int i = 1; i <= n; i++){
            Optional<com.parkingmanagement.parkingmanagement.model.Occupation> occupation = occupationRepository.findById(Long.valueOf(i));
            if(occupation.get().getId() == id){
                OccupationDto occupationDto = new OccupationDto(occupation.get().getId(), occupation.get().getClientCpf(), occupation.get().getCar(), occupation.get().getHourEntry(), occupation.get().getHourExit(), occupation.get().getPrice());
                return occupationDto;
            }
        }
        return null;
    }

//    @Override
//    public List<com.parkingmanagement.parkingmanagement.model.Occupation> listOccupationByLicensePlate(String licensePlate) {
//        return occupationRepository.findAllbyLicensePlate(licensePlate);
////        Long n = occupationRepository.count();
////        List<OccupationDto> occupationDtoList = new ArrayList<OccupationDto>();
////        for (int i = 1; i <= n; i++){
////            Optional<Occupation> occupation = occupationRepository.findById(Long.valueOf(i));
////            if(occupation.get().getCar().getCarLicensePlate() == licensePlate){
////                OccupationDto occupationDto = new OccupationDto(occupation.get().getId(), occupation.get().getClientCpf(), occupation.get().getCar(), occupation.get().getHourEntry(), occupation.get().getHourExit(), occupation.get().getPrice());
////                occupationDtoList.add(occupationDto);
////            }
////        }
////        return occupationDtoList;
//}

    @Override
    public List<OccupationDto> listOccupationByCpf(Long cpf) {
        //List<OccupationDto> = occupationRepository.findAll();
        Long n = occupationRepository.count();
        List<OccupationDto> occupationDtoList = new ArrayList<OccupationDto>();
        for (int i = 1; i <= n; i++){

            Optional<Occupation> occupation = occupationRepository.findById(Long.valueOf(i));
            occupation.ifPresent(occupationPresent -> {
                if(occupationPresent.getClientCpf() == cpf){
                    OccupationDto occupationDto = new OccupationDto(occupationPresent.getId(), occupationPresent.getClientCpf(), occupationPresent.getCar(), occupationPresent.getHourEntry(), occupationPresent.getHourExit(), occupationPresent.getPrice());
                    occupationDtoList.add(occupationDto);
                }
            });

        }
        return occupationDtoList;
    }


}
