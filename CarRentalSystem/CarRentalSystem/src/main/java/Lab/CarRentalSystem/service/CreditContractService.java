package Lab.CarRentalSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.repository.CreditContractRepository;
import Lab.CarRentalSystem.service.interfaces.ICreditContractService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreditContractService implements ICreditContractService {

    @Autowired
    private CreditContractRepository creditContractRepository;

}
