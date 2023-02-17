package com.accounting.HardwareAccounting.hardware;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class MaintenanceDateDto {

  @JsonProperty(access = Access.READ_ONLY)
  private UUID uuid;
  @NotEmpty
  private UUID hardware_uuid;
  @NotEmpty
  private Date date;
  @NotEmpty
  @Size(max = 450)
  private String description;

}
