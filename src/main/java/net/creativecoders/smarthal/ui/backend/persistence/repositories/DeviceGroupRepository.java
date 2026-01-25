package net.creativecoders.smarthal.ui.backend.persistence.repositories;

import java.util.UUID;

import net.creativecoders.smarthal.ui.backend.persistence.entities.DeviceGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceGroupRepository extends JpaRepository<DeviceGroupEntity, UUID> {
    @Modifying
    @Query("delete from DeviceGroupEntity d where d.id = :id")
    int deleteByDeviceGroupId(@Param("id") UUID id);
}
