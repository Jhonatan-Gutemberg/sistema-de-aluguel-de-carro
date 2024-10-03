package Lab.CarRentalSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lab.CarRentalSystem.controller.ApiResponse.ApiResponse;
import Lab.CarRentalSystem.dto.Order.RegisterOrderDTO;
import Lab.CarRentalSystem.models.Order;
import Lab.CarRentalSystem.service.interfaces.IOrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Order>> register(@RequestBody @Valid RegisterOrderDTO orderRequest) {
        try {
            Order result = orderService.register(orderRequest);
            ApiResponse<Order> response = new ApiResponse<>(true, "Order created successfully", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Order> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            ApiResponse<List<Order>> response = new ApiResponse<>(true, "All orders fetched successfully", orders);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Order>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderService.getOrderById(id);
            if (order != null) {
                ApiResponse<Order> response = new ApiResponse<>(true, "Order found successfully", order);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Order> response = new ApiResponse<>(false, "Order not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Order> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<ApiResponse<Order>> updateOrder(@PathVariable Long id,
    // @RequestBody @Valid Order order) {
    // try {
    // Order updatedOrder = orderService.updateOrder(id, order);
    // if (updatedOrder != null) {
    // ApiResponse<Order> response = new ApiResponse<>(true, "Order updated
    // successfully", updatedOrder);
    // return ResponseEntity.ok(response);
    // } else {
    // ApiResponse<Order> response = new ApiResponse<>(false, "Order not found",
    // null);
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    // }
    // } catch (Exception e) {
    // ApiResponse<Order> errorResponse = new ApiResponse<>(false, e.getMessage(),
    // null);
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    // }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable Long id) {
        try {
            boolean isDeleted = orderService.deleteOrder(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Order deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Order not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/toggleStatus/{id}")
    public ResponseEntity<ApiResponse<Order>> toggleOrderStatus(@PathVariable Long id) {
        try {
            Order updatedOrder = orderService.toggleOrderStatus(id);
            ApiResponse<Order> response = new ApiResponse<>(true, "Order status toggled successfully", updatedOrder);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Order> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        if (orders != null && !orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

}
