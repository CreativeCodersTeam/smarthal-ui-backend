package net.creativecoders.smarthal.ui.backend.services;

import java.util.List;
import java.util.UUID;

import net.creativecoders.smarthal.ui.backend.persistence.entities.DeviceGroupEntity;
import net.creativecoders.smarthal.ui.backend.persistence.repositories.DeviceGroupRepository;
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

        try {
            var saved = deviceGroupRepository.saveAndFlush(entity);

            return saved.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public List<DeviceGroupEntity> getAllDeviceGroups() {
        return deviceGroupRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deleteDeviceGroup(UUID id) {
        var deletedCount = deviceGroupRepository.deleteByDeviceGroupId(id);

        return deletedCount > 0;
    }

    @Override
    public DeviceGroupEntity getDeviceGroupById(UUID id) {
        var entity = deviceGroupRepository.findById(id);

        return entity.orElse(null);
    }
}
