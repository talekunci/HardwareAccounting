package com.accounting.HardwareAccounting.hardware;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public interface MaintenanceDatesService {

  List<MaintenanceDatesDto> getAll();

  Optional<MaintenanceDatesDto> getByUuid(UUID uuid);

  @Transactional
  void create(MaintenanceDatesDto dto);

  void update(UUID uuid, MaintenanceDatesDto dto);

  void delete(UUID uuid);

  MaintenanceDatesDto mapToDto(MaintenanceDate dates);

  MaintenanceDate mapFromDto(MaintenanceDatesDto dto);

}
