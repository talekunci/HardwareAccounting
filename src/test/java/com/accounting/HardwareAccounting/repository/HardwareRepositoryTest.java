package com.accounting.HardwareAccounting.repository;

import com.accounting.HardwareAccounting.hardware.Hardware;
import com.accounting.HardwareAccounting.hardware.HardwareRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class HardwareRepositoryTest {

    @Autowired
    private HardwareRepository hardwareRepository;

    @Autowired
    private TestEntityManager entityManager;

    private final GregorianCalendar calendar = new GregorianCalendar();

    @Test
    void hardwareRepository_save() {
        Hardware newHardware = new Hardware();
        newHardware.setManufacturer("Manufacturer");
        newHardware.setName("HardwareAccounting");
        newHardware.setSerialNumber("SR7293");
        newHardware.setManufacturingDate(new Date(calendar.getTimeInMillis()));

        Hardware saved = hardwareRepository.save(newHardware);

        assertThat(entityManager.find(Hardware.class, saved.getUuid())).isEqualTo(newHardware);
    }

    @Test
    void hardwareRepository_update() {
        Hardware newHardware = new Hardware();
        newHardware.setManufacturer("Manufacturer");
        newHardware.setName("HardwareAccounting");
        newHardware.setSerialNumber("SR7293");
        newHardware.setManufacturingDate(new Date(calendar.getTimeInMillis()));

        Hardware persisted = entityManager.persist(newHardware);
        newHardware.setSerialNumber("newSerial");
        hardwareRepository.save(newHardware);

        assertThat(entityManager.find(Hardware.class, persisted.getUuid())).isEqualTo(newHardware);
    }

    @Test
    void hardwareRepository_findBySerial() {
        Hardware newHardware = new Hardware();
        newHardware.setManufacturer("Manufacturer");
        newHardware.setName("HardwareAccounting");
        newHardware.setSerialNumber("SR7293");
        newHardware.setManufacturingDate(new Date(calendar.getTimeInMillis()));

        entityManager.persist(newHardware);

        Optional<Hardware> bySerialNumber = hardwareRepository.findBySerialNumber(newHardware.getSerialNumber());

        assertThat(bySerialNumber).contains(newHardware);
    }

    @Test
    void hardwareRepository_delete() {
        Hardware newHardware = new Hardware();
        newHardware.setManufacturer("Manufacturer");
        newHardware.setName("HardwareAccounting");
        newHardware.setSerialNumber("SR7293");
        newHardware.setManufacturingDate(new Date(calendar.getTimeInMillis()));

        entityManager.persist(newHardware);
        hardwareRepository.delete(newHardware);

        assertThat(hardwareRepository.findBySerialNumber(newHardware.getSerialNumber())).isEmpty();
    }

}
