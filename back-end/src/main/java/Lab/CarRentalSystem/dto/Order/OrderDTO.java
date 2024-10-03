package Lab.CarRentalSystem.dto.Order;

import java.time.LocalDate;

import Lab.CarRentalSystem.enums.PlanType;

public record OrderDTO(
    Long id,
    LocalDate creationDate,
    LocalDate deliveryDate,
    boolean status,
    Long customerId,  
    Long automobileId,  
    Double value,
    PlanType planType,
    Long agencyId  
) {}
