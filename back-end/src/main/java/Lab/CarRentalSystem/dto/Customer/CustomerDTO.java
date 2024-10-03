package Lab.CarRentalSystem.dto.Customer;

import java.util.List;

import Lab.CarRentalSystem.dto.Employment.EmploymentDTO;
import Lab.CarRentalSystem.dto.Order.OrderDTO;
import Lab.CarRentalSystem.enums.SystemUserType;

public record CustomerDTO(

        String name,

        String address,

        SystemUserType type,

        boolean status,
        
        String password,

        String registration,

        String cpf,

        List<OrderDTO> orders,

        List<EmploymentDTO> employments

) {
}
