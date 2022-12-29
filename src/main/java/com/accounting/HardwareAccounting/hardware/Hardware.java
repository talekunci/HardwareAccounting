package com.accounting.HardwareAccounting.hardware;

import java.sql.Date;
import javax.persistence.*;
import java.util.*;

@Entity
public class Hardware {

  @Id
  private UUID id;
  private String manufacturer;
  private String name;
  private String serialNumber;
  private String description;
  private Date manufacturingDate;
  private Date installationDate;
  private String installationAddress;
  private String phoneNumber;
  private String email;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getManufacturingDate() {
    return manufacturingDate;
  }

  public void setManufacturingDate(Date manufacturingDate) {
    this.manufacturingDate = manufacturingDate;
  }

  public Date getInstallationDate() {
    return installationDate;
  }

  public void setInstallationDate(Date installationDate) {
    this.installationDate = installationDate;
  }

  public String getInstallationAddress() {
    return installationAddress;
  }

  public void setInstallationAddress(String installationAddress) {
    this.installationAddress = installationAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hardware hardware = (Hardware) o;
    return Objects.equals(id, hardware.id) && Objects.equals(manufacturer,
        hardware.manufacturer) && Objects.equals(name, hardware.name)
        && Objects.equals(serialNumber, hardware.serialNumber) && Objects.equals(
        description, hardware.description) && Objects.equals(manufacturingDate,
        hardware.manufacturingDate) && Objects.equals(installationDate,
        hardware.installationDate) && Objects.equals(installationAddress,
        hardware.installationAddress) && Objects.equals(phoneNumber, hardware.phoneNumber)
        && Objects.equals(email, hardware.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, manufacturer, name, serialNumber, description, manufacturingDate,
        installationDate, installationAddress, phoneNumber, email);
  }

  @Override
  public String toString() {
    return "Hardware{" +
        "id=" + id +
        ", manufacturer='" + manufacturer + '\'' +
        ", name='" + name + '\'' +
        ", serialNumber='" + serialNumber + '\'' +
        ", description='" + description + '\'' +
        ", manufacturingDate=" + manufacturingDate +
        ", installationDate=" + installationDate +
        ", installationAddress='" + installationAddress + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
