package Lab.CarRentalSystem.service;

import Lab.CarRentalSystem.dto.Automobile.AutomobileDTO;
import Lab.CarRentalSystem.models.Automobile;
import Lab.CarRentalSystem.repository.AutomobileRepository;
import Lab.CarRentalSystem.service.interfaces.IAutomobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AutomobileService implements IAutomobileService {

    @Autowired
    private AutomobileRepository automobileRepository;

    @Override
    public Automobile createAutomobile(AutomobileDTO automobileDTO) {
        Automobile automobile = new Automobile();
        automobile.setName(automobileDTO.name());
        automobile.setValuePerDay(automobileDTO.valuePerDay());
        automobile.setYear(automobileDTO.year());
        automobile.setRegistrationNumber(automobileDTO.registrationNumber());
        automobile.setBrand(automobileDTO.brand());
        automobile.setModel(automobileDTO.model());
        automobile.setPlate(automobileDTO.plate());
        return automobileRepository.save(automobile);
    }

    @Override
    public List<Automobile> getAllAutomobiles() {
        return automobileRepository.findAll();
    }

    @Override
    public Automobile getAutomobileById(Long id) {
        return automobileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Automobile not found"));
    }

    @Override
    public Automobile updateAutomobile(Long id, AutomobileDTO automobileDTO) {
        Automobile automobile = getAutomobileById(id);
        automobile.setName(automobile.getName());
        automobile.setValuePerDay(automobileDTO.valuePerDay());
        automobile.setYear(automobileDTO.year());
        automobile.setRegistrationNumber(automobileDTO.registrationNumber());
        automobile.setBrand(automobileDTO.brand());
        automobile.setModel(automobileDTO.model());
        automobile.setPlate(automobileDTO.plate());
        return automobileRepository.save(automobile);
    }

    @Override
    public void deleteAutomobile(Long id) {
        automobileRepository.deleteById(id);
    }
}
