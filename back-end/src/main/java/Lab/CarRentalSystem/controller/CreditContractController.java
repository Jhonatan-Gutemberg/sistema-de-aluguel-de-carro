package Lab.CarRentalSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Lab.CarRentalSystem.controller.ApiResponse.ApiResponse;
import Lab.CarRentalSystem.dto.CreditContract.CreditContractDTO;
import Lab.CarRentalSystem.models.CreditContract;
import Lab.CarRentalSystem.service.interfaces.ICreditContractService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/creditContract")
public class CreditContractController {

    @Autowired
    private ICreditContractService creditContractService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<CreditContract>> register(
            @RequestBody @Valid CreditContractDTO creditContractDTO) {
        try {
            CreditContract creditContract = creditContractService.register(creditContractDTO);
            ApiResponse<CreditContract> response = new ApiResponse<>(true, "Credit contract registered successfully",
                    creditContract);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<CreditContract> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CreditContract>>> getAllCreditContracts() {
        try {
            List<CreditContract> creditContracts = creditContractService.getAllCreditContracts();
            ApiResponse<List<CreditContract>> response = new ApiResponse<>(true,
                    "All credit contracts fetched successfully", creditContracts);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<CreditContract>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CreditContract>> getCreditContractById(@PathVariable Long id) {
        try {
            CreditContract creditContract = creditContractService.getCreditContractById(id);
            if (creditContract != null) {
                ApiResponse<CreditContract> response = new ApiResponse<>(true, "Credit contract found successfully",
                        creditContract);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<CreditContract> response = new ApiResponse<>(false, "Credit contract not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<CreditContract> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<ApiResponse<CreditContract>>
    // updateCreditContract(@PathVariable Long id,
    // @RequestBody @Valid CreditContractDTO creditContractDTO) {
    // try {
    // CreditContract updatedCreditContract =
    // creditContractService.updateCreditContract(id, creditContractDTO);
    // if (updatedCreditContract != null) {
    // ApiResponse<CreditContract> response = new ApiResponse<>(true, "Credit
    // contract updated successfully",
    // updatedCreditContract);
    // return ResponseEntity.ok(response);
    // } else {
    // ApiResponse<CreditContract> response = new ApiResponse<>(false, "Credit
    // contract not found", null);
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    // }
    // } catch (Exception e) {
    // ApiResponse<CreditContract> errorResponse = new ApiResponse<>(false,
    // e.getMessage(), null);
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    // }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCreditContract(@PathVariable Long id) {
        try {
            boolean isDeleted = creditContractService.deleteCreditContract(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Credit contract deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Credit contract not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<CreditContract>>> getCreditContractsByUserId(@PathVariable Long userId) {
        try {
            List<CreditContract> creditContracts = creditContractService.getCreditContractsByUserId(userId);
            if (creditContracts != null && !creditContracts.isEmpty()) {
                ApiResponse<List<CreditContract>> response = new ApiResponse<>(true,
                        "Credit contracts fetched successfully", creditContracts);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<List<CreditContract>> response = new ApiResponse<>(false,
                        "No credit contracts found for the user", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<List<CreditContract>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<ApiResponse<CreditContract>> toggleApprovalStatus(@PathVariable Long id) {
        try {
            CreditContract updatedContract = creditContractService.toggleApprovalStatus(id);
            ApiResponse<CreditContract> response = new ApiResponse<>(true, "Approval status updated successfully",
                    updatedContract);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<CreditContract> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
