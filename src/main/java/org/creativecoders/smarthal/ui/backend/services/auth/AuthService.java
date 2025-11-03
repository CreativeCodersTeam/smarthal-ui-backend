package org.creativecoders.smarthal.ui.backend.services.auth;

public interface AuthService {
    public LoginResult login(String username, String password);
}
