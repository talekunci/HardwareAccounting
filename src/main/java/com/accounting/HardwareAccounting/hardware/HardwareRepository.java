package com.accounting.HardwareAccounting.hardware;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface HardwareRepository extends JpaRepository<Hardware, UUID> {

    Optional<Hardware> findBySerialNumber(Long serialNumber);

}
