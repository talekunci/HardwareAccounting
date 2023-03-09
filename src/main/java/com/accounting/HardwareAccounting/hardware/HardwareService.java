package com.accounting.HardwareAccounting.hardware;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.UUID;

public interface HardwareService {

    List<HardwareDto> getAll();

    Optional<HardwareDto> getByUuid(UUID uuid);

    Optional<HardwareDto> getBySerialNumber(String serialNumber);

    @Transactional
    void create(HardwareDto dto);

    @Transactional
    void update(UUID uuid, HardwareDto dto);

    void delete(UUID uuid);

    SortedSet<MaintenanceDateDto> getMaintenanceDatesByHardwareUuid(UUID uuid);

    void addMaintenanceDateByUuid(UUID uuid, MaintenanceDateDto date);

    void updateMaintenanceDate(UUID uuid, MaintenanceDateDto date);

    void deleteMaintenanceDateByUuid(UUID hardwareUuid, UUID dateUuid);

    HardwareDto mapToDto(Hardware hardware);

    Hardware mapFromDto(HardwareDto dto);

    MaintenanceDateDto mapToDtoMaintenanceDate(MaintenanceDate date);

    MaintenanceDate mapFromDtoMaintenanceDate(MaintenanceDateDto dto);
}
