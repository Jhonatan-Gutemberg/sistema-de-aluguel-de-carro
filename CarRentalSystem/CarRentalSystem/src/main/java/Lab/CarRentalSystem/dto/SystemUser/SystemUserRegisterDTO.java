package Lab.CarRentalSystem.dto.SystemUser;

import Lab.CarRentalSystem.enums.SystemUserType;

public record SystemUserRegisterDTO(

        String name,

        String address,

        SystemUserType type,

        String password) {

}
