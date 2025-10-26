package org.creativecoders.smarthal.ui.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/info")
class InfoController {

    @GetMapping
    public AppInfo info() {
        return new AppInfo(getClass().getPackage().getImplementationVersion());
    }

}

