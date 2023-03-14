package com.accounting.HardwareAccounting.hardware;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class MaintenanceDateDto implements Comparable<MaintenanceDateDto> {

  private UUID uuid;

  @NotNull
  private HardwareDto hardware;

  @NotNull
  private Date date;

  @NotEmpty
  @Size(max = 450)
  private String description;

  @Override
  public int compareTo(MaintenanceDateDto dateDto) {
    return getDate().compareTo(dateDto.getDate());
  }
}
