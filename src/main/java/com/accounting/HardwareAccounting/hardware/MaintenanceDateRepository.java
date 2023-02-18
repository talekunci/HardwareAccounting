package com.accounting.HardwareAccounting.hardware;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaintenanceDateRepository extends JpaRepository<MaintenanceDate, UUID> {

}
