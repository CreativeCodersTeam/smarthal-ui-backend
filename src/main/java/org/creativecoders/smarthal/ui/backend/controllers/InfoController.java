package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.core.Ensure;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/info")
class InfoController {

    private final BuildProperties buildProperties;

    public InfoController(BuildProperties buildProperties) {
        this.buildProperties = Ensure.ensureNotNull(buildProperties, "buildProperties");
    }

    @GetMapping
    public AppInfo info() {
        return new AppInfo(this.buildProperties.getVersion());
    }

}
