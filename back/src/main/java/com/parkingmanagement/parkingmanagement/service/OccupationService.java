package com.parkingmanagement.parkingmanagement.service;

import com.parkingmanagement.parkingmanagement.dto.VacateParkingSpaceDto;
import org.springframework.http.ResponseEntity;

public interface OccupationService {
    public ResponseEntity saveOccupation(VacateParkingSpaceDto vacateParkingSpaceDto);
    public ResponseEntity listAllOccupation(int page, int size);
    public ResponseEntity listOccupationById(Long idOccupation);
    public ResponseEntity listOccupationByLicensePlate(String licensePlate, int page, int size);
    public ResponseEntity listOccupationByCpf(String cpf, int page, int size);
    public void createOccupation();
}
