package Lab.CarRentalSystem.dto.Order;

import java.time.LocalDate;

public record RegisterOrderDTO(
        LocalDate creationDate,
        LocalDate deliveryDate,
        Long customerId,
        Long automobileId
        // Long bankId
        ) {
}
