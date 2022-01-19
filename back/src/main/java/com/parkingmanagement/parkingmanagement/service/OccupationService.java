package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import org.springframework.http.ResponseEntity;

public interface OccupationService {
    public ResponseEntity saveOccupation(VacateParkingSpaceDto vacateParkingSpaceDto);
    public ResponseEntity listAllOccupation();
    public ResponseEntity listOccupationById(Long idOccupation);
    public ResponseEntity listOccupationByLicensePlate(String licensePlate);
    public ResponseEntity listOccupationByCpf(Long cpf);
}
