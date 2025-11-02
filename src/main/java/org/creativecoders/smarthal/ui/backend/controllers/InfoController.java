package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.smarthal.api.InfoApi;
import org.creativecoders.smarthal.api.dto.AppInfo;
import org.creativecoders.smarthal.ui.backend.services.AppInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/info")
class InfoController implements InfoApi {

    private final AppInfoService appInfoService;

    public InfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @Override
    public ResponseEntity<org.creativecoders.smarthal.api.dto.AppInfo> getInfo() {
        return ResponseEntity.ok(new AppInfo(appInfoService.getVersion()));
    }

//    @GetMapping
//    public AppInfo info() {
//        return new AppInfo(appInfoService.getVersion());
//    }
}
