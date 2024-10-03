package Lab.CarRentalSystem.dto.CreditContract;

public record CreditContractDTO(
                Long userId,

                Double amount,

                Long orderId,

                Long bankId,
                boolean approved

) {
}
