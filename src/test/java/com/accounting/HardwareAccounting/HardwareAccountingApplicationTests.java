package com.accounting.HardwareAccounting;

import com.accounting.HardwareAccounting.hardware.Hardware;
import com.accounting.HardwareAccounting.hardware.MaintenanceDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.*;

@SpringBootTest
class HardwareAccountingApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void hardwareSortedSetTest() {
        Calendar c = GregorianCalendar.getInstance();
        Hardware h = new Hardware();

        h.setUuid(UUID.randomUUID());
        h.setManufacturer("Google");
        h.setName("Searching engine");
        h.setSerialNumber("SN64556");
        h.setDescription("Most popular site");
        h.setManufacturingDate(new Date(c.getTimeInMillis()));
        h.setInstallationDate(new Date(c.getTimeInMillis()));
        h.setInstallationAddress("Dnipro");
        h.setOwnerPhoneNumber("+380123456789");
        h.setOwnerEmail("google@gmail.com");

        SortedSet<MaintenanceDate> d = h.getMaintenanceDates();
        d.addAll(Set.of(
                new MaintenanceDate(UUID.randomUUID(), h, new Date(c.getTimeInMillis()), String.valueOf(c.getTimeInMillis())),
                new MaintenanceDate(UUID.randomUUID(), h, new Date(c.getTimeInMillis() + 1000), String.valueOf(c.getTimeInMillis() + 1000)),
                new MaintenanceDate(UUID.randomUUID(), h, new Date(c.getTimeInMillis() + 2000), String.valueOf(c.getTimeInMillis() + 2000)),
                new MaintenanceDate(UUID.randomUUID(), h, new Date(c.getTimeInMillis() + 3000), String.valueOf(c.getTimeInMillis() + 3000)),
                new MaintenanceDate(UUID.randomUUID(), h, new Date(c.getTimeInMillis() + 4000), String.valueOf(c.getTimeInMillis() + 4000))
        ));

		long firstTime = d.first().getDate().getTime();
		long lastTime = d.last().getDate().getTime();
		Assert.isTrue(firstTime > lastTime, "Hardware SortedSet<MaintenanceDate> isn't sorted.");
    }

}

