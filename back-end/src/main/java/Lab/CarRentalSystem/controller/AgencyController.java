package Lab.CarRentalSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Lab.CarRentalSystem.controller.ApiResponse.ApiResponse;
import Lab.CarRentalSystem.dto.Agency.AgencyDTO;
import Lab.CarRentalSystem.models.Agency;
import Lab.CarRentalSystem.service.interfaces.IAgencyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/agency")
public class AgencyController {

    @Autowired
    private IAgencyService agencyService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Agency>> register(@RequestBody @Valid AgencyDTO agencyDTO) {
        try {
            Agency agency = agencyService.register(agencyDTO);
            ApiResponse<Agency> response = new ApiResponse<>(true, "Agency registered successfully", agency);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Agency> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Agency>>> getAllAgencies() {
        try {
            List<Agency> agencies = agencyService.getAllAgencies();
            ApiResponse<List<Agency>> response = new ApiResponse<>(true, "All agencies fetched successfully", agencies);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Agency>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Agency>> getAgencyById(@PathVariable Long id) {
        try {
            Agency agency = agencyService.getAgencyById(id);
            if (agency != null) {
                ApiResponse<Agency> response = new ApiResponse<>(true, "Agency found successfully", agency);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Agency> response = new ApiResponse<>(false, "Agency not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Agency> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Agency>> updateAgency(@PathVariable Long id,
            @RequestBody @Valid AgencyDTO agencyDTO) {
        try {
            Agency updatedAgency = agencyService.updateAgency(id, agencyDTO);
            if (updatedAgency != null) {
                ApiResponse<Agency> response = new ApiResponse<>(true, "Agency updated successfully", updatedAgency);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Agency> response = new ApiResponse<>(false, "Agency not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Agency> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAgency(@PathVariable Long id) {
        try {
            boolean isDeleted = agencyService.deleteAgency(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "Agency deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "Agency not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
