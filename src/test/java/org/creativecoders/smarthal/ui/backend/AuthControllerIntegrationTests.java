package org.creativecoders.smarthal.ui.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerIntegrationTests {

    @LocalServerPort
    private int port;

    private String baseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void login_withValidCredentials_returns200AndToken() throws Exception {
        // arrange
        HttpResponse<String> response;
        try (var client = HttpClient.newHttpClient()) {
            var requestBody = "{\"username\":\"frontend\",\"password\":\"frontend1!\"}";
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl() + "/api/auth/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // act
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        // assert
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).contains("token");
    }

    @Test
    void login_withInvalidCredentials_returns401() throws Exception {
        // arrange
        HttpResponse<String> response;
        try (var client = HttpClient.newHttpClient()) {
            var requestBody = "{\"username\":\"wrong\",\"password\":\"wrong\"}";
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl() + "/api/auth/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // act
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        // assert
        assertThat(response.statusCode()).isEqualTo(401);
        assertThat(response.body()).isBlank();
    }
}
