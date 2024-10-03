package Lab.CarRentalSystem.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.dto.Customer.CustomerDTO;
import Lab.CarRentalSystem.mappers.SystemUserMapper;
import Lab.CarRentalSystem.models.Agency;
import Lab.CarRentalSystem.models.Customer;
import Lab.CarRentalSystem.models.Order;
import Lab.CarRentalSystem.repository.AgencyRepository;
import Lab.CarRentalSystem.repository.CustomerRepository;
import Lab.CarRentalSystem.repository.OrderRepository;
import Lab.CarRentalSystem.service.interfaces.ICustomerService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public Customer register(CustomerDTO customerDTO) {
        Customer customer = SystemUserMapper.customerDtoToModel(customerDTO);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setName(customerDTO.name());
            existingCustomer.setAddress(customerDTO.address());
            existingCustomer.setRegistration(customerDTO.registration());
            existingCustomer.setCpf(customerDTO.cpf());

            customerRepository.save(existingCustomer);
            return existingCustomer;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.delete(optionalCustomer.get());
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean processPayment(Long customerId, Long orderId, BigDecimal value) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Ordem não encontrada"));

        Agency agency = agencyRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Agencia não encontrada"));

        if (customer.getBalance() >= order.getValue()) {

            customer.setBalance(customer.getBalance() - order.getValue());
            customerRepository.save(customer);
            agency.setBalance(BigDecimal.valueOf(agency.getBalance()).add(value).doubleValue());


            return true;
        } else {
            return false;
        }
    }

    @Override
    public Customer addBalance(Long id, double value) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setBalance(customer.getBalance() + value);
            customerRepository.save(customer);
            return customer;
        } else {
            return null;
        }
    }

}
