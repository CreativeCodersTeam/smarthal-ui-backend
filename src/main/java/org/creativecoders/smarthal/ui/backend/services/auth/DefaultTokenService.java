package org.creativecoders.smarthal.ui.backend.services.auth;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class DefaultTokenService implements TokenService {

    @Override
    public String createToken(String username) {

        return UUID.randomUUID().toString();
    }
}
