package Lab.CarRentalSystem.service.interfaces;

import java.util.List;

import Lab.CarRentalSystem.dto.SystemUser.SystemUserRegisterDTO;
import Lab.CarRentalSystem.models.SystemUser;

public interface ISystemUserService {

    SystemUser register(SystemUserRegisterDTO systemUserDTO);

    List<SystemUser> getAllUsers();

    SystemUser getUserById(Long id);

    SystemUser updateUser(Long id, SystemUserRegisterDTO systemUserDTO);

}
