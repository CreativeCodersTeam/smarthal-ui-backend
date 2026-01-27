package net.creativecoders.smarthal.ui.backend.controllers;

import lombok.extern.slf4j.Slf4j;
import net.creativecoders.smarthal.ui.backend.api.DeviceGroupsApi;
import net.creativecoders.smarthal.ui.backend.api.model.DeviceGroupCreationRequestV1;
import net.creativecoders.smarthal.ui.backend.api.model.DeviceGroupV1;
import net.creativecoders.smarthal.ui.backend.persistence.mappers.DeviceGroupMapper;
import net.creativecoders.smarthal.ui.backend.services.DevicesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@Slf4j
class DeviceGroupsController implements DeviceGroupsApi {

    private final DevicesService devicesService;

    private final DeviceGroupMapper mapper;

    public DeviceGroupsController(DevicesService devicesService, DeviceGroupMapper mapper) {
        this.devicesService = devicesService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DeviceGroupV1> createDeviceGroup(DeviceGroupCreationRequestV1 deviceGroupCreationRequest) {
        try {
            var device = devicesService.createDeviceGroup(deviceGroupCreationRequest.getName());

            log.info("Device group created with id '{}'", device.getId());

            return ResponseEntity
                    .status(201)
                    .body(mapper.toDto(device));

        } catch (Exception e) {
            log.error("Device group creation failed", e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteDeviceGroupById(UUID id) {
        var groupWasFound = devicesService.deleteDeviceGroup(id);

        return groupWasFound ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<DeviceGroupV1> getDeviceGroupById(UUID id) {
        var device = devicesService.getDeviceGroupById(id);

        if (Objects.isNull(device)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(device));
    }

    @Override
    public ResponseEntity<List<DeviceGroupV1>> getDeviceGroups() {
        var deviceGroups = devicesService.getAllDeviceGroups();

        var response = deviceGroups.stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(response);
    }
}
