package net.creativecoders.smarthal.ui.backend.services.auth;

public interface AuthService {
    LoginResult login(String username, String password);

    boolean logout(String token);
}
