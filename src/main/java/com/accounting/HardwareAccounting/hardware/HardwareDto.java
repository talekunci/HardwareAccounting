package com.accounting.HardwareAccounting.hardware;

import com.accounting.HardwareAccounting.validation.IsUnique;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
@IsUnique(
        model = Hardware.class,
        field = "serialNumber",
        message = "Entity with this serial number is already exist."
)
public class HardwareDto {

    private UUID uuid;

    @NotEmpty
    @Size(max = 50)
    private String serialNumber;

    @NotEmpty
    @Size(max = 72)
    private String manufacturer;

    @NotEmpty
    @Size(max = 72)
    private String name;

    @Size(max = 72)
    private String description;

    @NotNull
    private Date manufacturingDate;

    private Date installationDate;

    @Size(max = 72)
    private String installationAddress;

    @Size(max = 15)
    private String ownerPhoneNumber;

    @Size(max = 320)
    private String ownerEmail;

}
