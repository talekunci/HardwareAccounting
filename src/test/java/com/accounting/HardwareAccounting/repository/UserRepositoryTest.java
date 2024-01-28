package com.accounting.HardwareAccounting.repository;

import com.accounting.HardwareAccounting.user.User;
import com.accounting.HardwareAccounting.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void userRepository_save() {
        User newUser = new User();
        newUser.setLogin("login1254");
        newUser.setPassword("qwerty");

        User insertedUser = userRepository.save(newUser);

        assertThat(entityManager.find(User.class, insertedUser.getUuid())).isEqualTo(newUser);
    }

    @Test
    void userRepository_update() {
        User newUser = new User();
        newUser.setLogin("login1254");
        newUser.setPassword("qwerty");

        User persisted = entityManager.persist(newUser);
        newUser.setLogin("1254");
        userRepository.save(newUser);

        assertThat(entityManager.find(User.class, persisted.getUuid())).isEqualTo(newUser);
    }

    @Test
    void userRepository_findByLogin() {
        User newUser = new User();
        newUser.setLogin("login1254");
        newUser.setPassword("qwerty");

        entityManager.persist(newUser);

        Optional<User> byLogin = userRepository.findByLogin(newUser.getLogin());

        assertThat(byLogin).contains(newUser);
    }

    @Test
    void userRepository_delete() {
        User newUser = new User();
        newUser.setLogin("login1254");
        newUser.setPassword("qwerty");

        entityManager.persist(newUser);
        userRepository.delete(newUser);

        assertThat(userRepository.findByLogin(newUser.getLogin())).isEmpty();
    }

}
