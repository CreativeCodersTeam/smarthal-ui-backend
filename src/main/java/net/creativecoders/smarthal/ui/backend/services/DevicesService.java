package net.creativecoders.smarthal.ui.backend.services;

import net.creativecoders.smarthal.ui.backend.persistence.entities.DeviceGroupEntity;

import java.util.List;
import java.util.UUID;

public interface DevicesService {
    DeviceGroupEntity createDeviceGroup(String name);

    List<DeviceGroupEntity> getAllDeviceGroups();

    boolean deleteDeviceGroup(UUID id);

    DeviceGroupEntity getDeviceGroupById(UUID id);
}
