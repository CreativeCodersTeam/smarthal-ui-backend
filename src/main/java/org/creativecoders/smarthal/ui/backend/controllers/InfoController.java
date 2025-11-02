package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.smarthal.ui.backend.services.AppInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/info")
class InfoController {

    private final AppInfoService appInfoService;

    public InfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @GetMapping
    public AppInfo info() {
        return new AppInfo(appInfoService.getVersion());
    }
}
