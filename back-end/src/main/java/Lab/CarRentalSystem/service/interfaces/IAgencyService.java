package Lab.CarRentalSystem.service.interfaces;

import java.util.List;

import Lab.CarRentalSystem.dto.Agency.AgencyDTO;
import Lab.CarRentalSystem.models.Agency;

public interface IAgencyService {

    Agency register(AgencyDTO agencyDTO);

    List<Agency> getAllAgencies();

    Agency getAgencyById(Long id);

    Agency updateAgency(Long id, AgencyDTO agencyDTO);

    boolean deleteAgency(Long id);
}
