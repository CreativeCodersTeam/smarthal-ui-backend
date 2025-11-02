package org.creativecoders.smarthal.ui.backend.services;

import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

@Service
class DefaultAppInfoService implements AppInfoService {

    private final BuildProperties buildProperties;

    DefaultAppInfoService(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Override
    public String getVersion() {
        return buildProperties.getVersion();
    }
}
