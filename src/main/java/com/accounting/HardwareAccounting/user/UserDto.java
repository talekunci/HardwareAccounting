package com.accounting.HardwareAccounting.user;

import com.accounting.HardwareAccounting.validation.IsUnique;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
@IsUnique(
        model = User.class,
        field = "login",
        message = "User with this login is already exist."
)
public class UserDto {

    @JsonProperty(access = READ_ONLY)
    private UUID uuid;

    @NotEmpty
    private String login;

    @NotEmpty
    @JsonProperty(access = WRITE_ONLY)
    private String password;

    private Set<Role> roles;

}
