package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.smarthal.ui.backend.api.InfoApi;
import org.creativecoders.smarthal.ui.backend.model.AppInfo;
import org.creativecoders.smarthal.ui.backend.services.AppInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InfoController implements InfoApi {

    private final AppInfoService appInfoService;

    public InfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @Override
    public ResponseEntity<org.creativecoders.smarthal.ui.backend.model.AppInfo> getInfo() {
        var model = new AppInfo();

        model.setVersion(appInfoService.getVersion());

        return ResponseEntity.ok(model);
    }
}
