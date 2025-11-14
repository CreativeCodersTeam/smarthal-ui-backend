package net.creativecoders.smarthal.ui.backend.services;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

@Service
class DefaultAppInfoService implements AppInfoService {

    private final BuildProperties buildProperties;

    DefaultAppInfoService(ObjectProvider<BuildProperties> buildPropertiesProvider) {
        this.buildProperties = buildPropertiesProvider.getIfAvailable();
    }

    @Override
    public String getVersion() {
        return buildProperties != null ? buildProperties.getVersion() : "dev";
    }
}
