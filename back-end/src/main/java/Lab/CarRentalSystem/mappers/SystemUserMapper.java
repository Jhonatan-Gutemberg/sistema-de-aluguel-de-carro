package Lab.CarRentalSystem.mappers;

import Lab.CarRentalSystem.dto.SystemUser.SystemUserRegisterDTO;
import Lab.CarRentalSystem.dto.SystemUser.UserReturnLoginDTO;
import Lab.CarRentalSystem.enums.SystemUserType;
import Lab.CarRentalSystem.dto.Agency.AgencyDTO;
import Lab.CarRentalSystem.dto.Bank.BankDTO;
import Lab.CarRentalSystem.dto.Customer.CustomerDTO;
import Lab.CarRentalSystem.models.Agency;
import Lab.CarRentalSystem.models.Bank;
import Lab.CarRentalSystem.models.Customer;
import Lab.CarRentalSystem.models.SystemUser;

public class SystemUserMapper {

    public static SystemUser systemUserDtoToModel(SystemUserRegisterDTO userDTO) {
        return new SystemUser(userDTO.name(), userDTO.address(), SystemUserType.SYSTEMUSER, true, userDTO.password());
    }

    public static Bank bankDtoToModel(BankDTO bankDTO) {
        return new Bank(bankDTO.name(), bankDTO.address(), SystemUserType.BANK, true, bankDTO.password(),
                bankDTO.limitValue(), null);
    }

    public static Agency agencyDtoToModel(AgencyDTO agencyDTO) {
        return new Agency(agencyDTO.name(), agencyDTO.address(), SystemUserType.AGENCY, true, agencyDTO.password(),
                0, null);
    }

    public static Customer customerDtoToModel(CustomerDTO customerDTO) {
        return new Customer(customerDTO.name(), customerDTO.address(), SystemUserType.CUSTOMER, true,
                customerDTO.password(), "empty string", "empty string", null, null, 10000);
    }

    public static UserReturnLoginDTO modelToDto(SystemUser user, SystemUserType userType) {
        UserReturnLoginDTO usuario = new UserReturnLoginDTO(user.getId(), user.getName(), userType, true);
        return usuario;
    }
}
