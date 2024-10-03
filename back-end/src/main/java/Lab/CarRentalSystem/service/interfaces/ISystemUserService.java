package Lab.CarRentalSystem.service.interfaces;

import java.util.List;
import java.util.Optional;

import Lab.CarRentalSystem.dto.SystemUser.SystemUserLoginDTO;
import Lab.CarRentalSystem.dto.SystemUser.SystemUserRegisterDTO;
import Lab.CarRentalSystem.dto.SystemUser.UserReturnLoginDTO;
import Lab.CarRentalSystem.models.SystemUser;

public interface ISystemUserService {

    UserReturnLoginDTO login(SystemUserLoginDTO userLoginDTO);

    SystemUser register(SystemUserRegisterDTO systemUserDTO);

    List<SystemUser> getAllUsers();

    SystemUser getUserById(Long id);

    SystemUser updateUser(Long id, SystemUserRegisterDTO systemUserDTO);

    boolean deleteUser(Long id);

    Optional<SystemUser> getUserById1(Long id);

}
