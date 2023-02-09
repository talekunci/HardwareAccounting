package com.accounting.HardwareAccounting.hardware;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "maintenance_dates")
public class MaintenanceDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Transient
    private UUID uuid;

    @Column(name = "hardware_uuid", nullable = false)
    private UUID hardware_uuid;

    @Column(name = "maintenance_date", nullable = false)
    private Date date;

    @Column(name = "description", nullable = false, length = 450)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaintenanceDate that)) return false;
        return Objects.equals(uuid, that.uuid) && hardware_uuid.equals(that.hardware_uuid) && date.equals(that.date) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, hardware_uuid, date, description);
    }
}
