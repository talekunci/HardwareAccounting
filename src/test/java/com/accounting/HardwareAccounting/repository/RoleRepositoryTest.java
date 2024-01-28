package com.accounting.HardwareAccounting.repository;

import com.accounting.HardwareAccounting.user.Role;
import com.accounting.HardwareAccounting.user.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void roleRepositoryTest_create() {
        Role role = new Role();
        role.setName("Role1");

        Role saved = repository.save(role);

        assertThat(entityManager.find(Role.class, saved.getId())).isEqualTo(role);
    }

    @Test
    void roleRepositoryTest_update() {
        Role role = new Role();
        role.setName("Role1");

        Role persisted = entityManager.persist(role);
        role.setName("NewName");
        repository.save(role);

        assertThat(entityManager.find(Role.class, persisted.getId())).isEqualTo(role);
    }

    @Test
    void roleRepositoryTest_delete() {
        Role role = new Role();
        role.setName("Role1");

        Role persisted = entityManager.persist(role);
        repository.delete(role);

        assertThat(entityManager.find(Role.class, persisted.getId())).isNull();
    }

    @Test
    void roleRepositoryTest_findByName() {
        Role role = new Role();
        role.setName("Role1Name");

        entityManager.persist(role);
        Role byName = repository.findByName(role.getName());

        assertThat(byName).isEqualTo(role);
    }

}
