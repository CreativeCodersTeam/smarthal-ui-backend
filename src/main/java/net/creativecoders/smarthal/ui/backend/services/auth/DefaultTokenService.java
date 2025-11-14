package net.creativecoders.smarthal.ui.backend.services.auth;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.SecureRandom;
import java.util.Base64;

@Service
class DefaultTokenService implements TokenService {

    // NOTE: For demo purposes. In production, inject this via configuration/secret manager.
    private static final String SECRET = generateRandomSecret();

    private static String generateRandomSecret() {
        byte[] bytes = new byte[32]; // 256 bits
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @Override
    public String createToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer("smarthal-backend")
                .withClaim("username", username)
                .sign(algorithm);


    }
}
