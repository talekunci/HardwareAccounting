package com.accounting.HardwareAccounting.hardware;

import java.sql.Date;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "hardware")
public class Hardware {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "uuid")
  private UUID uuid;
  @Column(name = "manufacturer", length = 72, nullable = false)
  private String manufacturer;
  @Column(name = "name", length = 72, nullable = false)
  private String name;
  @Column(name = "serial_number", nullable = false)
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
  private String phoneNumber;
  @Column(name = "owner_email", length = 320)
  private String email;

  public UUID getUuid() {
    return uuid;
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
    return Objects.equals(uuid, hardware.uuid) && Objects.equals(manufacturer,
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
    return Objects.hash(uuid, manufacturer, name, serialNumber, description, manufacturingDate,
        installationDate, installationAddress, phoneNumber, email);
  }

  @Override
  public String toString() {
    return "Hardware{" +
        "id=" + uuid +
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
