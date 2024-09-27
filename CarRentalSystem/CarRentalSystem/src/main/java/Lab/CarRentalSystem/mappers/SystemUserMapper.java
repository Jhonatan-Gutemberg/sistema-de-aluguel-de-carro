package Lab.CarRentalSystem.mappers;

import Lab.CarRentalSystem.dto.SystemUser.SystemUserRegisterDTO;
import Lab.CarRentalSystem.models.Bank;
import Lab.CarRentalSystem.models.Agency;
import Lab.CarRentalSystem.models.SystemUser;

public class SystemUserMapper {
    public static SystemUser dtoToModel(SystemUserRegisterDTO UserDTO) {
        SystemUser systemUser;
        switch (UserDTO.type()) {
            case CUSTOMER:
                systemUser = new SystemUser(UserDTO.name(), UserDTO.address(), UserDTO.type(), true,
                        UserDTO.password());
                break;
            // case BANK:
            //     systemUser = new Bank();

            //     break;
            // case AGENCY:
            //     systemUser = new Agency();
            //     break;

            default:
                throw new IllegalArgumentException("User type not specified");
        }

        return systemUser;
    }
}
