package Lab.CarRentalSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lab.CarRentalSystem.service.interfaces.IEmployerService;

@RestController
@RequestMapping("/employer")
public class EmploymentController {
    @Autowired
    private IEmployerService employerService;
}
