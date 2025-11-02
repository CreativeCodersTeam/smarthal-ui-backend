package org.creativecoders.smarthal.ui.backend.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class DefaultDevicesService implements DevicesService {

    @Override
    public UUID createDeviceGroup(String name) {
        return UUID.randomUUID();
    }
}
