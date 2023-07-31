package com.posyandu.data.controller;

import com.posyandu.data.model.request.AuthRequest;
import com.posyandu.data.model.response.CommonResponse;
import com.posyandu.data.model.response.LoginResponse;
import com.posyandu.data.model.response.RegisterResponse;
import com.posyandu.data.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v3/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        RegisterResponse register = authService.register(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully registered")
                .data(register)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/register-staff")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest request) {
        RegisterResponse register = authService.registerStaff(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully registered")
                .data(register)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        LoginResponse response = authService.login(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully login")
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }
}
