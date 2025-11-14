package net.creativecoders.smarthal.ui.backend.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Typed configuration for database settings.
 */
@Validated
@ConfigurationProperties(prefix = "smarthal.db")
public class DatabaseProperties {

    /**
     * Filesystem directory where the embedded H2 database files are stored.
     */
    @NotBlank
    private String h2Path;

    public String getH2Path() {
        return h2Path;
    }

    public void setH2Path(String h2Path) {
        this.h2Path = h2Path;
    }
}
