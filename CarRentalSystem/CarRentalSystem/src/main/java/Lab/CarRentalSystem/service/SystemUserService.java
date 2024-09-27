package Lab.CarRentalSystem.service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<SystemUser> getAllUsers() {
        return systemUserRepository.findAll();
    }

    @Override
    public SystemUser getUserById(Long id) {
        Optional<SystemUser> user = systemUserRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public SystemUser updateUser(Long id, SystemUserRegisterDTO systemUserDTO) {
        Optional<SystemUser> optionalUser = systemUserRepository.findById(id);
        if (optionalUser.isPresent()) {
            SystemUser existingUser = optionalUser.get();
            existingUser.setName(systemUserDTO.name());
            existingUser.setAddress(systemUserDTO.address());
            existingUser.setType(systemUserDTO.type());
            existingUser.setPassword(systemUserDTO.password()); 
            
            systemUserRepository.save(existingUser);
            return existingUser;
        } else {
            return null; 
        }
    }

}
