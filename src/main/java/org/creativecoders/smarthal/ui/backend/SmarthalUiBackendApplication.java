package org.creativecoders.smarthal.ui.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SmarthalUiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmarthalUiBackendApplication.class, args);
    }

}
