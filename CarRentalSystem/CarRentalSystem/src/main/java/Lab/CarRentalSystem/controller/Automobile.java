package Lab.CarRentalSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lab.CarRentalSystem.service.interfaces.IAutomobileService;

@RestController
@RequestMapping("/automobile")
public class Automobile {

    @Autowired
    private IAutomobileService automobileService;

}
