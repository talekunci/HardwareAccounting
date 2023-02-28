package com.accounting.HardwareAccounting.hardware;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maintenance_dates")
public class MaintenanceDate implements Comparable<MaintenanceDate> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "hardware_uuid", nullable = false)
    private Hardware hardware;

    @Column(name = "maintenance_date", nullable = false)
    private Date date;

    @Column(name = "description", nullable = false, length = 450)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaintenanceDate that = (MaintenanceDate) o;

        if (!getHardware().equals(that.getHardware())) return false;
        if (!getDate().equals(that.getDate())) return false;
        return getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getHardware().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }

    @Override
    public int compareTo(MaintenanceDate date) {
        return getDate().compareTo(date.getDate());
    }
}
