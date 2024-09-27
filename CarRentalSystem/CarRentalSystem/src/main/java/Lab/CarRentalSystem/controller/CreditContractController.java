package Lab.CarRentalSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lab.CarRentalSystem.service.interfaces.ICreditContractService;

@RestController
@RequestMapping("/creditContract")
public class CreditContractController {

    @Autowired
    private ICreditContractService creditContractService;

}
