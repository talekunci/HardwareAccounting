package com.accounting.HardwareAccounting.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getByLogin(String login) {
        return repository.findByLogin(login)
                .map(this::mapToDto);
    }

    @Override
    public Optional<UserDto> getByUuid(UUID uuid) {
        return repository.findById(uuid)
                .map(this::mapToDto);
    }

    @Override
    public void create(UserDto dto) {
        User user = mapFromDto(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleRepo.findByName("User")));

        repository.save(mapFromDto(dto));
    }

    @Override
    public void update(UUID uuid, UserDto dto) {
        repository.findById(uuid)
                .map(user -> {
                            if (StringUtils.hasText(dto.getLogin()))
                                user.setLogin(dto.getLogin());

                            if (!dto.getRoles().isEmpty())
                                user.getRoles().addAll(dto.getRoles());

                            return user;
                        }
                ).ifPresent(user -> repository.save(user));
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public UserDto mapToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    @Override
    public User mapFromDto(UserDto dto) {
        return mapper.map(dto, User.class);
    }
}
