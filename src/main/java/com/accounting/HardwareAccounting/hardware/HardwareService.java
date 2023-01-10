package com.accounting.HardwareAccounting.hardware;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface HardwareService {

    List<HardwareDto> getAll();

    Optional<HardwareDto> getByUuid(UUID uuid);

    Optional<HardwareDto> getBySerialNumber(Long serialNumber);

    @Transactional
    void create(HardwareDto dto);

    void update(UUID uuid, HardwareDto dto);

    void delete(UUID uuid);

    HardwareDto mapToDto(Hardware hardware);

    Hardware mapFromDto(HardwareDto dto);

}
