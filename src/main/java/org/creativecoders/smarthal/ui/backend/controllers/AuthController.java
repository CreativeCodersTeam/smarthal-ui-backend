package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.smarthal.ui.backend.api.AuthApi;
import org.creativecoders.smarthal.ui.backend.model.LoginRequestV1;
import org.creativecoders.smarthal.ui.backend.model.LoginResponseV1;
import org.creativecoders.smarthal.ui.backend.model.LogoutRequestV1;
import org.creativecoders.smarthal.ui.backend.services.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.util.Objects;

@RestController
@RequestMapping("/")
class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = Objects.requireNonNull(authService);
    }

    @Override
    public ResponseEntity<LoginResponseV1> login(LoginRequestV1 loginRequest) {
        var loginResult = authService.login(loginRequest.getUsername(), loginRequest.getPassword());

        if (!loginResult.isSuccess()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var cookie = ResponseCookie.from("SESSION", loginResult.getToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("Lax")
                .maxAge(Duration.ofHours(1))
                .build();

        return ResponseEntity
                .ok()
                .header("Set-Cookie", cookie.toString())
                .body(new LoginResponseV1(loginResult.getToken()));
    }

    @Override
    public ResponseEntity<Void> logout(LogoutRequestV1 logoutRequest) {
        return authService.logout(logoutRequest.getToken())
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
