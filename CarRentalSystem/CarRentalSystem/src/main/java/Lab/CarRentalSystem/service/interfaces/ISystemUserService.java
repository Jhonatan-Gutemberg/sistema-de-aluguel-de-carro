package Lab.CarRentalSystem.service.interfaces;

import Lab.CarRentalSystem.dto.SystemUser.SystemUserRegisterDTO;
import Lab.CarRentalSystem.models.SystemUser;

public interface ISystemUserService {

    SystemUser register(SystemUserRegisterDTO systemUserDTO);

}
