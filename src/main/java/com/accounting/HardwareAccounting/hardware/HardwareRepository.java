package com.accounting.HardwareAccounting.hardware;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, UUID> {

    Optional<Hardware> findBySerialNumber(String serialNumber);

}
