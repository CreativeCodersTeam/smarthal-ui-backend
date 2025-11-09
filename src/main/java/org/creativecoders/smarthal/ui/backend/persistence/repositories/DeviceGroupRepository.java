package org.creativecoders.smarthal.ui.backend.persistence.repositories;

import java.util.UUID;
import org.creativecoders.smarthal.ui.backend.persistence.entities.DeviceGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceGroupRepository extends JpaRepository<DeviceGroupEntity, UUID> {
}
