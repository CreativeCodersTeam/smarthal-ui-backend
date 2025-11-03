package org.creativecoders.smarthal.ui.backend;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeviceGroupsControllerIntegrationTests implements WithAssertions {

    @LocalServerPort
    private int port;

    private String baseUrl() {
        return "http://localhost:" + port;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void createDeviceGroup_returns201AndUuid() throws Exception {
        // arrange
        HttpResponse<String> response;
        try (var client = HttpClient.newHttpClient()) {
            var requestBody = "{\"name\":\"Living Room\"}";
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl() + "/api/v1/device-groups"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // act
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        // assert
        assertThat(response.statusCode()).isEqualTo(201);
        var body = response.body();
        assertThat(body).isNotBlank();
        // ensure it is a UUID
        assertThatCode(() -> UUID.fromString(body.replace("\"", "").trim()))
                .doesNotThrowAnyException();
    }
}
