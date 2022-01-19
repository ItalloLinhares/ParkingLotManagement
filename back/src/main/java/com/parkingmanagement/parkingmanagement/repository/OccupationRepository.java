package com.parkingmanagement.parkingmanagement.repository;

import com.parkingmanagement.parkingmanagement.model.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Long> {
    @Query("SELECT o FROM Occupation o WHERE o.clientCpf = :cpf")
    List<Occupation> findAllbyCPF(@Param("cpf") Long cpf);

    @Query("SELECT o FROM Occupation o WHERE o.car.carLicensePlate = :licensePlate")
    List<Occupation> findAllbyLicensePlate(@Param("licensePlate") String licensePlate);



}
