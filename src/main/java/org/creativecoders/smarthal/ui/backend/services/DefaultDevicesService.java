package org.creativecoders.smarthal.ui.backend.services;

import java.util.List;
import java.util.UUID;

import org.creativecoders.smarthal.ui.backend.persistence.entities.DeviceGroupEntity;
import org.creativecoders.smarthal.ui.backend.persistence.repositories.DeviceGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class DefaultDevicesService implements DevicesService {

    private final DeviceGroupRepository deviceGroupRepository;

    DefaultDevicesService(DeviceGroupRepository deviceGroupRepository) {
        this.deviceGroupRepository = deviceGroupRepository;
    }

    @Override
    @Transactional
    public UUID createDeviceGroup(String name) {
        var entity = new DeviceGroupEntity(null, name);
        var saved = deviceGroupRepository.save(entity);
        return saved.getId();
    }

    @Override
    public List<DeviceGroupEntity> getAllDeviceGroups() {
        return deviceGroupRepository.findAll();
    }
}
