package com.accounting.HardwareAccounting.user;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final RoleRepository roleRepo;

    private final PasswordEncoder encoder;

    private final ModelMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public Set<Role> getRolesByUuid(UUID uuid) {
        Optional<User> user = repository.findById(uuid);
        if (user.isPresent()) return user.get().getRoles();

        return Collections.emptySet();
    }

    @Override
    public void addRoleById(UUID uuid, Long roleId) {
        repository.findById(uuid)
                .ifPresent(user -> {
                    roleRepo.findById(roleId)
                            .ifPresent(role -> {
                                Set<Role> userRoles = user.getRoles();
                                if (!userRoles.contains(role)) {
                                    userRoles.add(role);
                                    repository.save(user);
                                    logger.info(String.format(
                                            "The role '%s' has been added to the user '%s'.",
                                            role.getName(), user.getLogin()
                                    ));
                                } else {
                                    logger.warn(String.format(
                                            "The user '%s' already has the role '%s'.",
                                            role.getName(), user.getLogin()
                                    ));
                                }
                            });
                });
    }

    @Override
    public void deleteRoleById(UUID uuid, Long roleId) {
        repository.findById(uuid)
                .ifPresent(user -> {
                    roleRepo.findById(roleId)
                            .ifPresent(role -> {
                                if (role.getName().equals("ROLE_User")) {
                                    logger.warn(String.format(
                                            "The role 'User' for the user '%s' can't be deleted.",
                                            user.getLogin()
                                    ));
                                } else {
                                    user.getRoles().remove(role);
                                    repository.save(user);
                                    logger.info(String.format(
                                            "The role '%s' has been deleted from the user '%s.",
                                            role.getName(), user.getLogin()
                                    ));
                                }
                            });
                });
    }

    @Override
    public Set<Role> getAllRoles() {
        return Set.copyOf(roleRepo.findAll());
    }

    @Override
    public void create(UserDto dto) {
        User user = mapFromDto(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleRepo.findByName("ROLE_User")));

        logger.info(String.format(
                "A new user with login '%s' has been created.",
                user.getLogin()
        ));

        repository.save(user);
    }

    @Override
    public void update(UUID uuid, UserDto dto) {
        repository.findById(uuid)
                .map(user -> {
                            String oldLogin = user.getLogin();
                            if (StringUtils.hasText(dto.getLogin()))
                                user.setLogin(dto.getLogin());
                            logger.info(String.format(
                                    "The user '%s' has changed his login to '%s'.",
                                    oldLogin, user.getLogin()
                            ));

                            return user;
                        }
                ).ifPresent(repository::save);
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
        logger.info(String.format(
                "The user with UUID '%s' has been permanently deleted.",
                uuid
        ));
    }

    @Override
    @Transactional
    public void block(UUID uuid) {
        repository.findById(uuid)
                .ifPresent(user -> {
                    user.setBlocked(true);
                    repository.save(user);
                    logger.info(String.format(
                            "The user '%s' has been blocked.",
                            user.getLogin()
                    ));
                });
    }

    @Override
    public void unblock(UUID uuid) {
        repository.findById(uuid)
                .ifPresent(user -> {
                    user.setBlocked(false);
                    repository.save(user);
                    logger.info(String.format(
                            "The user '%s' has been unblocked.",
                            user.getLogin()
                    ));
                });
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
