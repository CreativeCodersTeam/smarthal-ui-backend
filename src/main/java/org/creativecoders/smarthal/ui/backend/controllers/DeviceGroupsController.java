package org.creativecoders.smarthal.ui.backend.controllers;

import org.creativecoders.smarthal.ui.backend.api.DeviceGroupsApi;
import org.creativecoders.smarthal.ui.backend.model.DeviceGroupCreationRequestV1;
import org.creativecoders.smarthal.ui.backend.model.DeviceGroupCreationResponseV1;
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
    public ResponseEntity<DeviceGroupCreationResponseV1> createDeviceGroup(DeviceGroupCreationRequestV1 deviceGroupCreationRequest) {
        var response = new DeviceGroupCreationResponseV1.Builder()
                .id(devicesService.createDeviceGroup(deviceGroupCreationRequest.getName()))
                .build();

        return ResponseEntity
                .status(201)
                .body(response);
    }
}
