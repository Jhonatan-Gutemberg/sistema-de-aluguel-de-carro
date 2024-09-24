package Lab.CarRentalSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.repository.AutomobileRepository;
import Lab.CarRentalSystem.service.interfaces.IAutomobileService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AutomobileService implements IAutomobileService {

    @Autowired
    private AutomobileRepository automobileRepository;

}
