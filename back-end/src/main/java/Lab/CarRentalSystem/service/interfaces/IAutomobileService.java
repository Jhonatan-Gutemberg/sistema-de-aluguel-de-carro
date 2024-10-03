package Lab.CarRentalSystem.service.interfaces;

import Lab.CarRentalSystem.dto.Automobile.AutomobileDTO;
import Lab.CarRentalSystem.models.Automobile;

import java.util.List;

public interface IAutomobileService {
    Automobile createAutomobile(AutomobileDTO automobileDTO);

    List<Automobile> getAllAutomobiles();

    Automobile getAutomobileById(Long id);

    Automobile updateAutomobile(Long id, AutomobileDTO automobileDTO);

    void deleteAutomobile(Long id);
}
