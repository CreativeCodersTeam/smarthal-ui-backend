package net.creativecoders.smarthal.ui.backend.controllers;

import net.creativecoders.smarthal.ui.backend.api.InfoApi;
import net.creativecoders.smarthal.ui.backend.api.model.AppInfoV1;
import net.creativecoders.smarthal.ui.backend.services.AppInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InfoController implements InfoApi {

    private final AppInfoService appInfoService;

    public InfoController(AppInfoService appInfoService) {
        this.appInfoService = appInfoService;
    }

    @Override
    public ResponseEntity<AppInfoV1> getInfo() {
        var model = new AppInfoV1();

        model.setVersion(appInfoService.getVersion());

        return ResponseEntity.ok(model);
    }
}
