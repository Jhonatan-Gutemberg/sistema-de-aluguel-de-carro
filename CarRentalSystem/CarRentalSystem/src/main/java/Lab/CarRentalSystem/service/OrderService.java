package Lab.CarRentalSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Lab.CarRentalSystem.repository.OrderRepository;
import Lab.CarRentalSystem.service.interfaces.IOrderService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
}
