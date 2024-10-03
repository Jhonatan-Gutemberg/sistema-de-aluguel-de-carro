package Lab.CarRentalSystem.dto.Bank;

import Lab.CarRentalSystem.enums.SystemUserType;

public record BankDTO(
        String name,
        String address,
        SystemUserType type,
        double limitValue,
        String password) {
}
