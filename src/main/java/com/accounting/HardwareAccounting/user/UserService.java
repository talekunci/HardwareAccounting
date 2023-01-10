package com.accounting.HardwareAccounting.user;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {

    List<UserDto> getAll();

    Optional<UserDto> getByLogin(String login);

    Optional<UserDto> getByUuid(UUID uuid);

    @Transactional
    void create(UserDto dto);

    void update(UUID uuid, UserDto dto);

    void delete(UUID uuid);

    UserDto mapToDto(User user);

    User mapFromDto(UserDto dto);

}
