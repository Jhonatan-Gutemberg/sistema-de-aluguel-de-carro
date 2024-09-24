package Lab.CarRentalSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.repository.CustomerRepository;
import Lab.CarRentalSystem.service.interfaces.ICustomerService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
}
