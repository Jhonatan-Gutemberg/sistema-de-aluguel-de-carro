package Lab.CarRentalSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import Lab.CarRentalSystem.service.interfaces.IAgentService;

@Controller
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private IAgentService agentService;
}
