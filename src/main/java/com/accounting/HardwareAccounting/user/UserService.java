package com.accounting.HardwareAccounting.user;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserService {

    List<UserDto> getAll();

    Optional<UserDto> getByLogin(String login);

    Optional<UserDto> getByUuid(UUID uuid);

    Set<Role> getRolesByUuid(UUID uuid);

    void addRoleById(UUID uuid, Long roleId);

    void deleteRoleById(UUID uuid, Long roleId);

    Set<Role> getAllRoles();

    @Transactional
    void create(UserDto dto);

    @Transactional
    void update(UUID uuid, UserDto dto);

    void delete(UUID uuid);

    void block(UUID uuid);

    void unblock(UUID uuid);

    UserDto mapToDto(User user);

    User mapFromDto(UserDto dto);

}
