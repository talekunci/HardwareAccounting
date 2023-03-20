package com.accounting.HardwareAccounting.user;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final RoleRepository roleRepo;

    private final PasswordEncoder encoder;

    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository repository, RoleRepository roleRepo, PasswordEncoder encoder, ModelMapper mapper) {
        this.repository = repository;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
        this.mapper = mapper;
    }

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

                            return user;
                        }
                ).ifPresent(repository::save);
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
