package net.creativecoders.smarthal.ui.backend;

import net.creativecoders.smarthal.ui.backend.api.model.DeviceGroupV1;
import org.assertj.core.api.WithAssertions;
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

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.datasource.url=jdbc:h2:mem:smarthal;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
                "spring.datasource.driver-class-name=org.h2.Driver",
                "spring.datasource.username=sa",
                "spring.datasource.password=",
                "spring.jpa.hibernate.ddl-auto=create-drop",
                "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
                "spring.jpa.open-in-view=false"
        }
)
class DeviceGroupsControllerIntegrationTests implements WithAssertions {

    @LocalServerPort
    private int port;

    private String baseUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void createDeviceGroup_returns201AndUuid() throws Exception {
        // arrange
        HttpResponse<DeviceGroupV1> responseObject;

        try (var client = HttpClient.newHttpClient()) {
            var requestBody = "{\"name\":\"Living Room\"}";
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl() + "/api/v1/device-groups"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // act
            var mapper = new ObjectMapper().findAndRegisterModules();
            responseObject = client.send(request, ofJson(mapper, DeviceGroupV1.class));
        }

        // assert
        assertThat(responseObject.statusCode()).isEqualTo(201);
        var creationResponse = responseObject.body();
        assertThat(creationResponse).isNotNull();
        // ensure it is a UUID
        assertThat(creationResponse.getId()).isNotNull();
        assertThat(creationResponse.getId()).isInstanceOf(UUID.class);

    }

    public <T> HttpResponse.BodyHandler<T> ofJson(ObjectMapper mapper, Class<?> typeClass) throws UncheckedIOException {
        Objects.requireNonNull(mapper, "mapper");
        Objects.requireNonNull(typeClass, "typeRef");

        return _ -> HttpResponse.BodySubscribers.mapping(
                HttpResponse.BodySubscribers.ofByteArray(),
                bytes -> {
                    try {
                        return mapper.readValue(bytes, mapper.getTypeFactory().constructType(typeClass));
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
        );
    }
}


