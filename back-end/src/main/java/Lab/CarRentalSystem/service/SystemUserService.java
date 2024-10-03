package Lab.CarRentalSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.config.SecurityConfig;
import Lab.CarRentalSystem.dto.SystemUser.SystemUserLoginDTO;
import Lab.CarRentalSystem.dto.SystemUser.SystemUserRegisterDTO;
import Lab.CarRentalSystem.dto.SystemUser.UserReturnLoginDTO;
import Lab.CarRentalSystem.enums.SystemUserType;
import Lab.CarRentalSystem.mappers.SystemUserMapper;
import Lab.CarRentalSystem.models.Agency;
import Lab.CarRentalSystem.models.Bank;
import Lab.CarRentalSystem.models.Customer;
import Lab.CarRentalSystem.models.SystemUser;
import Lab.CarRentalSystem.repository.SystemUserRepository;
import Lab.CarRentalSystem.service.interfaces.ISystemUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SystemUserService implements ISystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Override
    public UserReturnLoginDTO login(SystemUserLoginDTO userLoginDTO) {

        SystemUser systemUser = systemUserRepository.findByName(userLoginDTO.name());

        // Verifica se o usuário foi encontrado
        if (systemUser == null) {
            throw new EntityNotFoundException("User not found");
        }

        // Compara a senha fornecida com a senha armazenada encriptada
        // if (!SecurityConfig.passwordEncoder().matches(userLoginDTO.password(),
        // systemUser.getPassword())) {
        // throw new IllegalArgumentException("Invalid password");
        // }

        SystemUserType userType;
        SystemUserType userTypeString = systemUser.getType();

        switch (userTypeString) {
            case SYSTEMUSER:
                userType = SystemUserType.SYSTEMUSER;
                break;
            case AGENCY:
                userType = SystemUserType.AGENCY;
                break;
            case CUSTOMER:
                userType = SystemUserType.CUSTOMER;

                break;
            case BANK:
                userType = SystemUserType.BANK;
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuário não reconhecido");
        }

        // Retorna o DTO mapeado com as informações do usuário
        return SystemUserMapper.modelToDto(systemUser, userType);
    }

    @Override
    public SystemUser register(SystemUserRegisterDTO systemUserDTO) {

        // Mapeia o DTO para o modelo
        SystemUser systemUser = SystemUserMapper.systemUserDtoToModel(systemUserDTO);

        // Criptografa a senha
        String encryptedPassword = SecurityConfig.passwordEncoder().encode(systemUserDTO.password());

        // Define a senha criptografada no objeto SystemUser
        systemUser.setPassword(encryptedPassword);

        // Salva o usuário
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
    public Optional<SystemUser> getUserById1(Long id) {
        return systemUserRepository.findById(id); // Presumindo que findById já retorna um Optional
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

    @Override
    public boolean deleteUser(Long id) {
        Optional<SystemUser> optionalUser = systemUserRepository.findById(id);
        if (optionalUser.isPresent()) {
            systemUserRepository.delete(optionalUser.get());
            return true;
        } else {
            return false;
        }
    }

}
