package com.digitalkrapht.bloodbank.bloodbank.authentication.controller;

import com.digitalkrapht.bloodbank.bloodbank.authentication.request.LoginRequest;
import com.digitalkrapht.bloodbank.bloodbank.authentication.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

//
    @GetMapping("/resetPassword/{email}")
    @Operation(description="reset forgot password using email")
    public ResponseEntity<?> resetPasswordWithEmail(@Valid @PathVariable String email) {
        return authenticationService.resetPasswordWithEmail(email);
    }
    @PostMapping("/login")
    @Operation(description="authenticate and fetch access token")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticationService.Login(loginRequest);
    }
}
