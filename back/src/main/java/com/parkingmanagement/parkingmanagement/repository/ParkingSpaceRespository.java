package com.parkingmanagement.parkingmanagement.repository;

import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRespository extends PagingAndSortingRepository<ParkingSpace, Long> {
    @Query(value = "SELECT p FROM ParkingSpace p WHERE p.parkingSpaceStatus = :parkingSpaceStatus")
    List<ParkingSpace> findParkingSpacebyStatus(@Param("parkingSpaceStatus")ParkingSpaceStatus parkingSpaceStatus);
}
