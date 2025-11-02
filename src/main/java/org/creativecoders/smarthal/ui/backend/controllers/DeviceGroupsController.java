package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.smarthal.ui.backend.api.DeviceGroupsApi;
import org.creativecoders.smarthal.ui.backend.model.DeviceGroupCreationRequest;
import org.creativecoders.smarthal.ui.backend.services.DevicesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DeviceGroupsController implements DeviceGroupsApi {

    private final DevicesService devicesService;

    public DeviceGroupsController(DevicesService devicesService) {
        this.devicesService = devicesService;
    }

    @Override
    public ResponseEntity<Void> createDeviceGroup(DeviceGroupCreationRequest deviceGroupCreationRequest) {
        // TODO: use devicesService to create the group when service is implemented
        return ResponseEntity.status(201).build();
    }
}
