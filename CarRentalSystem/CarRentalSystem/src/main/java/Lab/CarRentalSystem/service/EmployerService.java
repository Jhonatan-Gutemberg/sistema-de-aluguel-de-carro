package Lab.CarRentalSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.repository.EmployerRepository;
import Lab.CarRentalSystem.service.interfaces.IEmployerService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployerService implements IEmployerService {

    @Autowired
    private EmployerRepository EmployerRepository;

}
