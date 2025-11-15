package net.creativecoders.smarthal.ui.backend.controllers;

import lombok.extern.slf4j.Slf4j;
import net.creativecoders.smarthal.ui.backend.api.DeviceGroupsApi;
import net.creativecoders.smarthal.ui.backend.api.model.DeviceGroupCreationRequestV1;
import net.creativecoders.smarthal.ui.backend.api.model.DeviceGroupCreationResponseV1;
import net.creativecoders.smarthal.ui.backend.api.model.DeviceGroupV1;
import net.creativecoders.smarthal.ui.backend.services.DevicesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
class DeviceGroupsController implements DeviceGroupsApi {

    private final DevicesService devicesService;

    public DeviceGroupsController(DevicesService devicesService) {
        this.devicesService = devicesService;
    }

    @Override
    public ResponseEntity<DeviceGroupCreationResponseV1> createDeviceGroup(DeviceGroupCreationRequestV1 deviceGroupCreationRequest) {
        try {
            var id = devicesService.createDeviceGroup(deviceGroupCreationRequest.getName());

            log.info("Device group created with id '{}'", id);

            var response = new DeviceGroupCreationResponseV1.Builder()
                    .id(id)
                    .build();

            return ResponseEntity
                    .status(201)
                    .body(response);

        } catch (Exception e) {
            log.error("Device group creation failed", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<List<DeviceGroupV1>> getDeviceGroups() {
        var deviceGroups = devicesService.getAllDeviceGroups();

        var response = deviceGroups.stream()
                .map(x ->
                        new DeviceGroupV1().id(x.getId()).name(x.getName()).createdAt(x.getCreatedAt()))
                .toList();

        return ResponseEntity.ok(response);
    }
}
