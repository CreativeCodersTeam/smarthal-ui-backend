package org.creativecoders.smarthal.ui.backend.services.auth;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
class DefaultAuthService implements AuthService {

    private final TokenService tokenService;

    public DefaultAuthService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public LoginResult login(String username, String password) {
        if (!validateCredentials(username, password)) {
            return new LoginResult(false, null);
        }

        var token = tokenService.createToken(username);

        return new LoginResult(true, token);
    }

    private boolean validateCredentials(String username, String password) {
        return Objects.equals(username, "frontend") && Objects.equals(password, "frontend1!");
    }
}
