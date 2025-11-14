package net.creativecoders.smarthal.ui.backend.services.auth;

import lombok.Data;

@Data
public class LoginResult {
    private final boolean success;

    private final String token;
}
