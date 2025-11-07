package org.creativecoders.smarthal.ui.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import org.assertj.core.api.WithAssertions;
import org.creativecoders.smarthal.ui.backend.model.DeviceGroupCreationResponseV1;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
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
        HttpResponse<DeviceGroupCreationResponseV1> responseObject;

        try (var client = HttpClient.newHttpClient()) {
            var requestBody = "{\"name\":\"Living Room\"}";
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl() + "/api/v1/device-groups"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // act
            responseObject = client.send(request, ofJson(new ObjectMapper()));
        }

        // assert
        assertThat(responseObject.statusCode()).isEqualTo(201);
        var creationResponse = responseObject.body();
        assertThat(creationResponse).isNotNull();
        // ensure it is a UUID
        assertThat(creationResponse.getId()).isNotNull();
        assertThat(creationResponse.getId()).isInstanceOf(UUID.class);

    }

    public static <T> HttpResponse.BodyHandler<T> ofJson(ObjectMapper mapper) {
        Objects.requireNonNull(mapper, "mapper");

        var resultTypeRef = new TypeReference<T>() {
        };

        return responseInfo -> HttpResponse.BodySubscribers.mapping(
                HttpResponse.BodySubscribers.ofByteArray(),
                bytes -> {
                    try {
                        return mapper.readValue(bytes, mapper.getTypeFactory().constructType(resultTypeRef));
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
        );
    }
}


