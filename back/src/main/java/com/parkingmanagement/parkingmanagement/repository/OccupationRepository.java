package com.parkingmanagement.parkingmanagement.repository;

import com.parkingmanagement.parkingmanagement.model.Occupation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccupationRepository extends PagingAndSortingRepository<Occupation, Long> {
    @Query("SELECT o FROM Occupation o WHERE o.clientCpf = :cpf")
    Page<Occupation> findAllbyCPF(@Param("cpf") String cpf, Pageable pageable);

    @Query("SELECT o FROM Occupation o WHERE o.car.carLicensePlate = :licensePlate")
    Page<Occupation> findAllbyLicensePlate(@Param("licensePlate") String licensePlate, Pageable pageable);
}
