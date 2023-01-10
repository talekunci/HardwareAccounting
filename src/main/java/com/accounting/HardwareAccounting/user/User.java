package com.accounting.HardwareAccounting.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "login", length = 50, nullable = false)
    private String login;

    @Column(name = "password", length = 72, nullable = false)
    private String password;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    public User(String login, String password, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return uuid != null && Objects.equals(uuid, user.uuid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

