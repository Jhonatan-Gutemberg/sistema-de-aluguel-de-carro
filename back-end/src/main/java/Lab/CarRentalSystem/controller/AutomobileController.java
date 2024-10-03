package Lab.CarRentalSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Lab.CarRentalSystem.controller.ApiResponse.ApiResponse;
import Lab.CarRentalSystem.dto.Automobile.AutomobileDTO;
import Lab.CarRentalSystem.models.Automobile;
import Lab.CarRentalSystem.service.interfaces.IAutomobileService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/automobile")
public class AutomobileController {

    @Autowired
    private IAutomobileService automobileService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Automobile>> register(
            @RequestBody @Valid AutomobileDTO automobileDTO) {
        try {
            Automobile result = automobileService.createAutomobile(automobileDTO);
            ApiResponse<Automobile> response = new ApiResponse<>(true, "Automobile created successfully", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Automobile> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Automobile>>> getAllAutomobiles() {
        try {
            List<Automobile> automobiles = automobileService.getAllAutomobiles();
            ApiResponse<List<Automobile>> response = new ApiResponse<>(true, "All automobiles fetched successfully",
                    automobiles);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Automobile>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Automobile>> getAutomobileById(@PathVariable Long id) {
        try {
            Automobile automobile = automobileService.getAutomobileById(id);
            if (automobile != null) {
                ApiResponse<Automobile> response = new ApiResponse<>(true, "Automobile found successfully", automobile);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Automobile> response = new ApiResponse<>(false, "Automobile not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Automobile> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Automobile>> updateAutomobile(
            @PathVariable Long id,
            @RequestBody @Valid AutomobileDTO automobileDTO) {
        try {
            Automobile updatedAutomobile = automobileService.updateAutomobile(id, automobileDTO);
            if (updatedAutomobile != null) {
                ApiResponse<Automobile> response = new ApiResponse<>(true, "Automobile updated successfully",
                        updatedAutomobile);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Automobile> response = new ApiResponse<>(false, "Automobile not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Automobile> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAutomobile(@PathVariable Long id) {
        try {
            automobileService.deleteAutomobile(id);
            ApiResponse<Void> response = new ApiResponse<>(true, "Automobile deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
