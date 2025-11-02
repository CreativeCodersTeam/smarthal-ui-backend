package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.smarthal.ui.backend.api.DeviceGroupsApi;
import org.creativecoders.smarthal.ui.backend.model.DeviceGroupCreationRequest;
import org.creativecoders.smarthal.ui.backend.services.DevicesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
class DeviceGroupsController implements DeviceGroupsApi {

    private final DevicesService devicesService;

    public DeviceGroupsController(DevicesService devicesService) {
        this.devicesService = devicesService;
    }

    @Override
    public ResponseEntity<UUID> createDeviceGroup(DeviceGroupCreationRequest deviceGroupCreationRequest) {
        return ResponseEntity
                .status(201)
                .body(devicesService.createDeviceGroup(deviceGroupCreationRequest.getName()));
    }
}
