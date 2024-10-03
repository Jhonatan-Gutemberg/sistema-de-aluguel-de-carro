package Lab.CarRentalSystem.dto.Agency;

import java.util.List;

import Lab.CarRentalSystem.enums.SystemUserType;
import Lab.CarRentalSystem.models.Order;

public record AgencyDTO(

        String name,

        String address,

        SystemUserType type,

        String password,

        boolean status,

        List<Order> orders) {

}
