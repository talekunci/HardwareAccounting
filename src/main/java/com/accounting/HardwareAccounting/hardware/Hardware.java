package com.accounting.HardwareAccounting.hardware;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hardware")
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name = "manufacturer", length = 72, nullable = false)
    private String manufacturer;

    @Column(name = "name", length = 72, nullable = false)
    private String name;

    @Column(name = "serial_number", nullable = false, length = 50)
    private String serialNumber;

    @Column(name = "description", length = 72)
    private String description;

    @Column(name = "manufacture_date", nullable = false)
    private Date manufacturingDate;

    @Column(name = "installation_date")
    private Date installationDate;

    @Column(name = "installation_address", length = 72)
    private String installationAddress;

    @Column(name = "owner_phone_number")
    private String ownerPhoneNumber;

    @Column(name = "owner_email", length = 320)
    private String ownerEmail;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "hardware",
            fetch = FetchType.EAGER
    )
    private SortedSet<MaintenanceDate> maintenanceDates = new TreeSet<>(Comparator.comparing(MaintenanceDate::getDate).reversed());

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hardware hardware = (Hardware) o;

        if (!getManufacturer().equals(hardware.getManufacturer())) return false;
        if (!getName().equals(hardware.getName())) return false;
        if (!getSerialNumber().equals(hardware.getSerialNumber())) return false;
        return getManufacturingDate().equals(hardware.getManufacturingDate());
    }

    @Override
    public int hashCode() {
        int result = getManufacturer().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getSerialNumber().hashCode();
        result = 31 * result + getManufacturingDate().hashCode();
        return result;
    }
}
