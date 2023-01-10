package com.accounting.HardwareAccounting.user;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "roles")
    private Set<User> users;

  public Role(String name, Set<User> users) {
    this.name = name;
    this.users = users;
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
