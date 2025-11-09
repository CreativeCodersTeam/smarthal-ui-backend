package org.creativecoders.smarthal.ui.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SmarthalUiBackendApplication {

    @SuppressWarnings("UnnecessaryModifier")
    public static void main(String[] args) {
        SpringApplication.run(SmarthalUiBackendApplication.class, args);
    }

}
