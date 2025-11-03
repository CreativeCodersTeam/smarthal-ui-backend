package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.smarthal.ui.backend.api.AuthApi;
import org.creativecoders.smarthal.ui.backend.model.LoginRequestV1;
import org.creativecoders.smarthal.ui.backend.model.LoginResponseV1;
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
    public ResponseEntity<LoginResponseV1> login(LoginRequestV1 loginRequest) {
        try {
            var loginResult = authService.login(loginRequest.getUsername(), loginRequest.getPassword());

            if (!loginResult.isSuccess()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            return ResponseEntity.ok(new LoginResponseV1(loginResult.getToken()));
        } catch (Exception _) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
