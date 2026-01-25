package net.creativecoders.smarthal.ui.backend.persistence.mappers;

import net.creativecoders.smarthal.ui.backend.api.model.DeviceGroupV1;
import net.creativecoders.smarthal.ui.backend.persistence.entities.DeviceGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceGroupMapper {

    DeviceGroupV1 toDto(DeviceGroupEntity entity);

    DeviceGroupEntity toEntity(DeviceGroupV1 dto);

    List<DeviceGroupV1> toDtoList(List<DeviceGroupEntity> entities);

    List<DeviceGroupEntity> toEntityList(List<DeviceGroupV1> dtos);

    /**
     * Update an existing entity from a DTO (useful for PATCH/PUT flows).
     * Null-checking behaviour follows MapStruct defaults; you can add
     *
     * @Mapping(target = "id", ignore = true) if you must protect the id.
     */
    void updateEntityFromDto(DeviceGroupV1 dto, @MappingTarget DeviceGroupEntity entity);
}
