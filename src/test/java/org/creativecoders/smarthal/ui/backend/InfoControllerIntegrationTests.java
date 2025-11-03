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
class InfoControllerIntegrationTests {

    @LocalServerPort
    private int port;

    private String baseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void getInfo_returns200AndVersion() throws Exception {
        // arrange
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl() + "/api/info"))
                .GET()
                .build();

        // act
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // assert
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).contains("version");
        assertThat(response.headers().firstValue("Content-Type").orElse("")).contains("application/json");
    }
}
