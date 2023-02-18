package com.accounting.HardwareAccounting.hardware;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceDatesRepository extends JpaRepository<MaintenanceDate, UUID> {

}
