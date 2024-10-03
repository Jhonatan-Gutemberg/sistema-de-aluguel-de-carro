package Lab.CarRentalSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Lab.CarRentalSystem.controller.ApiResponse.ApiResponse;
import Lab.CarRentalSystem.dto.Bank.BankDTO;
import Lab.CarRentalSystem.models.Bank;
import Lab.CarRentalSystem.service.interfaces.IBankService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private IBankService bankService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Bank>> register(@RequestBody @Valid BankDTO bankDTO) {
        try {
            Bank result = bankService.register(bankDTO);
            ApiResponse<Bank> response = new ApiResponse<>(true, "Bank registered successfully", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Bank> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Bank>>> getAllBanks() {
        try {
            List<Bank> banks = bankService.getAllBanks();
            ApiResponse<List<Bank>> response = new ApiResponse<>(true, "All banks fetched successfully", banks);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Bank>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Bank>> getBankById(@PathVariable Long id) {
        try {
            Bank bank = bankService.getBankById(id);
            if (bank != null) {
                ApiResponse<Bank> response = new ApiResponse<>(true, "Bank found successfully", bank);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Bank> response = new ApiResponse<>(false, "Bank not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Bank> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Bank>> updateBank(@PathVariable Long id, @RequestBody @Valid BankDTO bankDTO) {
        try {
            Bank updatedBank = bankService.updateBank(id, bankDTO);
            if (updatedBank != null) {
                ApiResponse<Bank> response = new ApiResponse<>(true, "Bank updated successfully", updatedBank);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Bank> response = new ApiResponse<>(false, "Bank not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Bank> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBank(@PathVariable Long id) {
        try {
            bankService.deleteBank(id);
            ApiResponse<Void> response = new ApiResponse<>(true, "Bank deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
