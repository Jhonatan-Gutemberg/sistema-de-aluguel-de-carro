package Lab.CarRentalSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.dto.Agency.AgencyDTO;
import Lab.CarRentalSystem.mappers.SystemUserMapper;
import Lab.CarRentalSystem.models.Agency;
import Lab.CarRentalSystem.repository.AgencyRepository;
import Lab.CarRentalSystem.service.interfaces.IAgencyService;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgencyService implements IAgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public Agency register(AgencyDTO agencyDTO) {
        Agency agency = SystemUserMapper.agencyDtoToModel(agencyDTO);
        agencyRepository.save(agency);
        return agency;
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }

    @Override
    public Agency getAgencyById(Long id) {
        Optional<Agency> agency = agencyRepository.findById(id);
        return agency.orElse(null);
    }

    @Override
    public Agency updateAgency(Long id, AgencyDTO agencyDTO) {
        Optional<Agency> optionalAgency = agencyRepository.findById(id);
        if (optionalAgency.isPresent()) {
            Agency existingAgency = optionalAgency.get();
            existingAgency.setName(agencyDTO.name());
            existingAgency.setAddress(agencyDTO.address());
            existingAgency.setType(agencyDTO.type());
            existingAgency.setStatus(agencyDTO.status());
            existingAgency.setPassword(agencyDTO.password());

            agencyRepository.save(existingAgency);
            return existingAgency;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteAgency(Long id) {
        Optional<Agency> optionalAgency = agencyRepository.findById(id);
        if (optionalAgency.isPresent()) {
            agencyRepository.delete(optionalAgency.get());
            return true;
        } else {
            return false;
        }
    }
}
