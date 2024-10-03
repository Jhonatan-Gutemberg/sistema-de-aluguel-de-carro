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
import Lab.CarRentalSystem.dto.SystemUser.SystemUserLoginDTO;
import Lab.CarRentalSystem.dto.SystemUser.SystemUserRegisterDTO;
import Lab.CarRentalSystem.dto.SystemUser.UserReturnLoginDTO;
import Lab.CarRentalSystem.models.SystemUser;
import Lab.CarRentalSystem.service.interfaces.ISystemUserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/systemUser")
public class SystemUserController {

    @Autowired
    private ISystemUserService systemUserService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SystemUserLoginDTO userLoginDTO) {
        UserReturnLoginDTO result = systemUserService.login(userLoginDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<SystemUser>> register(
            @RequestBody @Valid SystemUserRegisterDTO systemUserRegisterDTO) {
        try {
            SystemUser result = systemUserService.register(systemUserRegisterDTO);
            ApiResponse<SystemUser> response = new ApiResponse<>(true, "User registered successfully", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<SystemUser> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SystemUser>>> getAllUsers() {
        try {
            List<SystemUser> users = systemUserService.getAllUsers();
            ApiResponse<List<SystemUser>> response = new ApiResponse<>(true, "All users fetched successfully", users);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<SystemUser>> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SystemUser>> getUserById(@PathVariable Long id) {
        try {
            SystemUser user = systemUserService.getUserById(id);
            if (user != null) {
                ApiResponse<SystemUser> response = new ApiResponse<>(true, "User found successfully", user);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<SystemUser> response = new ApiResponse<>(false, "User not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<SystemUser> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<SystemUser>> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid SystemUserRegisterDTO systemUserDTO) {
        try {
            SystemUser updatedUser = systemUserService.updateUser(id, systemUserDTO);
            if (updatedUser != null) {
                ApiResponse<SystemUser> response = new ApiResponse<>(true, "User updated successfully", updatedUser);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<SystemUser> response = new ApiResponse<>(false, "User not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<SystemUser> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        try {
            boolean isDeleted = systemUserService.deleteUser(id);
            if (isDeleted) {
                ApiResponse<Void> response = new ApiResponse<>(true, "User deleted successfully", null);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(false, "User not found", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
