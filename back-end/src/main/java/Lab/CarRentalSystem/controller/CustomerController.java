package Lab.CarRentalSystem.controller;

import java.math.BigDecimal;
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
import Lab.CarRentalSystem.dto.Customer.CustomerDTO;
import Lab.CarRentalSystem.models.Customer;
import Lab.CarRentalSystem.service.interfaces.ICustomerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Customer>> register(
            @RequestBody @Valid CustomerDTO customerRegisterDTO) {
        try {
            Customer result = customerService.register(customerRegisterDTO);
            ApiResponse<Customer> response = new ApiResponse<>(true, "Customer registered successfully", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Customer> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            ApiResponse<List<Customer>> response = new ApiResponse<>(true, "All customers fetched successfully",
                    customers);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Customer>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                ApiResponse<Customer> response = new ApiResponse<>(true, "Customer found successfully", customer);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Customer> response = new ApiResponse<>(false, "Customer not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Customer> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(
            @PathVariable Long id,
            @RequestBody @Valid CustomerDTO customerDTO) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customerDTO);
            if (updatedCustomer != null) {
                ApiResponse<Customer> response = new ApiResponse<>(true, "Customer updated successfully",
                        updatedCustomer);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Customer> response = new ApiResponse<>(false, "Customer not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Customer> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
        try {
            boolean isDeleted = customerService.deleteCustomer(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Customer deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Customer not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> changeStatus(@PathVariable Long id) {
        try {
            boolean isDeleted = customerService.deleteCustomer(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Customer deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Customer not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/pay/{customerId}/{orderId}/{value}")
    public ResponseEntity<ApiResponse<Void>> processPayment(
            @PathVariable Long customerId,
            @PathVariable Long orderId,
            @PathVariable BigDecimal value) {
        try {
            boolean paymentProcessed = customerService.processPayment(customerId, orderId, value);
            if (paymentProcessed) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Pagamento realizado com sucesso", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Saldo insuficiente ou ordem não encontrada",
                        null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/addBalance/{id}/{value}")
    public ResponseEntity<ApiResponse<Customer>> addBalance(
            @PathVariable Long id,
            @PathVariable double value) {
        try {
            Customer updatedCustomer = customerService.addBalance(id, value);
            if (updatedCustomer != null) {
                ApiResponse<Customer> response = new ApiResponse<>(true, "Balance updated successfully",
                        updatedCustomer);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Customer> response = new ApiResponse<>(false, "Customer not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Customer> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
