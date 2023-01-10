package com.accounting.HardwareAccounting.hardware;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "hardware")
public class Hardware {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "manufacturer", length = 72, nullable = false)
    private String manufacturer;
    @Column(name = "name", length = 72, nullable = false)
    private String name;
    @Column(name = "serial_number", nullable = false)
    private Long serialNumber;
    @Column(name = "description", length = 72)
    private String description;
    @Column(name = "manufacture_date", nullable = false)
    private Date manufacturingDate;
    @Column(name = "installation_date")
    private Date installationDate;
    @Column(name = "installation_address", length = 72)
    private String installationAddress;
    @Column(name = "owner_phone_number")
    private Integer ownerPhoneNumber;
    @Column(name = "owner_email", length = 320)
    private String ownerEmail;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "maintenance_dates",
            joinColumns = {@JoinColumn(name = "hardware_uuid")}
    )
    private Set<MaintenanceDates> maintenanceDates;


    public Hardware(
            String manufacturer,
            String name,
            Long serialNumber,
            String description,
            Date manufacturingDate,
            Date installationDate,
            String installationAddress,
            Integer ownerPhoneNumber,
            String ownerEmail,
            Set<MaintenanceDates> maintenanceDates
    ) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.serialNumber = serialNumber;
        this.description = description;
        this.manufacturingDate = manufacturingDate;
        this.installationDate = installationDate;
        this.installationAddress = installationAddress;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.ownerEmail = ownerEmail;
        this.maintenanceDates = maintenanceDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Hardware hardware = (Hardware) o;
        return uuid != null && Objects.equals(uuid, hardware.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
