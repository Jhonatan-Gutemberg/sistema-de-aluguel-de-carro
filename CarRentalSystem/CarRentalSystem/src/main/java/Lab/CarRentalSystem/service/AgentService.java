package Lab.CarRentalSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.repository.AgentRepository;
import Lab.CarRentalSystem.service.interfaces.IAgentService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AgentService implements IAgentService {

    @Autowired
    private AgentRepository agentRepository;

}
