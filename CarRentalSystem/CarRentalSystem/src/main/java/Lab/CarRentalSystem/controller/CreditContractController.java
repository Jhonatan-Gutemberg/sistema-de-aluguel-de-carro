package Lab.CarRentalSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import Lab.CarRentalSystem.service.interfaces.ICreditContractService;

@Controller
@RequestMapping("/creditContract")
public class CreditContractController {

    @Autowired
    private ICreditContractService creditContractService;

}
