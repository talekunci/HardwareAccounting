package com.accounting.HardwareAccounting.hardware;

import com.accounting.HardwareAccounting.validation.IsUnique;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@IsUnique(
        model = Hardware.class,
        field = "serial_number",
        message = "Entity with this serial number is already exist."
)
public class HardwareDto {

    @JsonProperty(access = READ_ONLY)
    private UUID uuid;

    @NotEmpty
    @Size(max = 72)
    private String manufacturer;

    @NotEmpty
    @Size(max = 72)
    private String name;

    @NotEmpty
    @Max(50)
    private String serialNumber;

    @NotEmpty
    @Size(max = 72)
    private String description;

    @NotEmpty
    private Date manufacturingDate;

    private Date installationDate;

    @NotEmpty
    @Size(max = 72)
    private String installationAddress;

    @NotEmpty
    @Size(max = 15)
    private String ownerPhoneNumber;

    @NotEmpty
    @Size(max = 320)
    private String ownerEmail;

    private SortedSet<MaintenanceDateDto> maintenanceDates = new TreeSet<>(Comparator.comparing(MaintenanceDateDto::getDate).reversed());

}
