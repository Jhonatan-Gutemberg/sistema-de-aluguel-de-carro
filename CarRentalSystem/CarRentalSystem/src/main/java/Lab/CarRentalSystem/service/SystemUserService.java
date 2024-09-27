package Lab.CarRentalSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.dto.SystemUser.SystemUserRegisterDTO;
import Lab.CarRentalSystem.mappers.SystemUserMapper;
import Lab.CarRentalSystem.models.SystemUser;
import Lab.CarRentalSystem.repository.SystemUserRepository;
import Lab.CarRentalSystem.service.interfaces.ISystemUserService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SystemUserService implements ISystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Override
    public SystemUser register(SystemUserRegisterDTO systemUserDTO) {

        SystemUser systemUser = SystemUserMapper.dtoToModel(systemUserDTO);
        systemUserRepository.save(systemUser);
        return systemUser;
    }

}
