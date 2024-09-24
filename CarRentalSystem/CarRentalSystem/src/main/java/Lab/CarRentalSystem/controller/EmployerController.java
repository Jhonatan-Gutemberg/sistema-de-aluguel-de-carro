package Lab.CarRentalSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import Lab.CarRentalSystem.service.interfaces.IEmployerService;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private IEmployerService IEmployerService;
}
