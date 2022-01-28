package com.parkingmanagement.parkingmanagement.validation;

import com.parkingmanagement.parkingmanagement.model.ParkingSpace;
import com.parkingmanagement.parkingmanagement.repository.ParkingSpaceRespository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.AVAILABLE;
import static com.parkingmanagement.parkingmanagement.status.ParkingSpaceStatus.UNAVAILABLE;


@AllArgsConstructor
public class ValidIdUnavailableValidator  implements ConstraintValidator<ValidIdUnavailable, Long> {
    private final ParkingSpaceRespository parkingSpaceRespository;
    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        Optional<ParkingSpace> parkingSpaceToBeFilled = parkingSpaceRespository.findById(id);
        if (!parkingSpaceToBeFilled.isPresent() || parkingSpaceToBeFilled.get().getParkingSpaceStatus() == AVAILABLE) {
            return false;
        }
        return true;
    }
}
