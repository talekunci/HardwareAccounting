package com.accounting.HardwareAccounting.hardware;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.sql.Date;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MaintenanceDatesDto {

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
