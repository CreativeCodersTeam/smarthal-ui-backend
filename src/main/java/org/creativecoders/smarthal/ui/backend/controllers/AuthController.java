package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.smarthal.ui.backend.api.AuthApi;
import org.creativecoders.smarthal.ui.backend.model.LoginRequest;
import org.creativecoders.smarthal.ui.backend.model.LoginResponse;
import org.creativecoders.smarthal.ui.backend.services.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        try {
            var loginResult = authService.login(loginRequest.getUsername(), loginRequest.getPassword());

            if (!loginResult.isSuccess()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return ResponseEntity.ok(new LoginResponse(loginResult.getToken()));
        } catch (Exception _) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
