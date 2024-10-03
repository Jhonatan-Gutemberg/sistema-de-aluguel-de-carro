package Lab.CarRentalSystem.dto.SystemUser;

import Lab.CarRentalSystem.enums.SystemUserType;

public record UserReturnLoginDTO(Long id, String name, SystemUserType userType, boolean estaLogado) {

}
