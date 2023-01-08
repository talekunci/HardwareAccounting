package com.accounting.HardwareAccounting.user;

import java.util.Objects;
import jakarta.persistence.*;
import lombok.ToString;

@ToString
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role = (Role) o;
    return Objects.equals(id, role.id) && Objects.equals(name, role.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
