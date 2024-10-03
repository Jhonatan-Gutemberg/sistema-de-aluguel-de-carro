package Lab.CarRentalSystem.service.interfaces;

import java.util.List;

import Lab.CarRentalSystem.dto.Order.RegisterOrderDTO;
import Lab.CarRentalSystem.models.CreditContract;
import Lab.CarRentalSystem.models.Order;

public interface IOrderService {

    Order register(RegisterOrderDTO order);

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    // Order updateOrder(Long id, Order orderDetails);

    boolean deleteOrder(Long id);

     Order toggleOrderStatus(Long id);

     List<Order> getOrdersByUserId(Long userId);


}
