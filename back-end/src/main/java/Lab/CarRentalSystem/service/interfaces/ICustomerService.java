package Lab.CarRentalSystem.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import Lab.CarRentalSystem.dto.Customer.CustomerDTO;
import Lab.CarRentalSystem.models.Customer;

public interface ICustomerService {

    Customer register(CustomerDTO customerDTO);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer updateCustomer(Long id, CustomerDTO customerDTO);

    boolean deleteCustomer(Long id);

    boolean processPayment(Long customerId, Long orderId, BigDecimal value);

    Customer addBalance(Long id, double value);
}
