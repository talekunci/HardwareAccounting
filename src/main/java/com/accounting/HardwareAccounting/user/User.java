package com.accounting.HardwareAccounting.user;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "uuid")
  private UUID uuid;
  @Column(name = "login", length = 50, nullable = false)
  private String login;
  @Column(name = "password", length = 72, nullable = false)
  private String password;
  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id")
  private Role role;

  public UUID getUuid() {
    return uuid;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(uuid, user.uuid) && Objects.equals(login, user.login)
        && Objects.equals(password, user.password) && Objects.equals(role,
        user.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, login, password, role);
  }

  @Override
  public String toString() {
    return "User{" +
        "uuid=" + uuid +
        ", login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", role=" + role +
        '}';
  }
}
