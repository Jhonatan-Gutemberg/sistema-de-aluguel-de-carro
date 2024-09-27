package Lab.CarRentalSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lab.CarRentalSystem.controller.ApiResponse.ApiResponse;
import Lab.CarRentalSystem.dto.SystemUser.SystemUserRegisterDTO;
import Lab.CarRentalSystem.models.SystemUser;
import Lab.CarRentalSystem.service.interfaces.ISystemUserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/systemUser")
public class SystemUserController {

    @Autowired
    private ISystemUserService systemUserService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<SystemUser>> register(@RequestBody @Valid SystemUserRegisterDTO systemUserRegisterDTO) {
        try {
            SystemUser result = systemUserService.register(systemUserRegisterDTO);
            ApiResponse<SystemUser> response = new ApiResponse<>(true, "User registered successfully", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<SystemUser> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
