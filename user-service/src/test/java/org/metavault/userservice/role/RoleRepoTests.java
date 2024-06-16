package org.metavault.userservice.role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.metavault.userservice.role.model.entity.Role;
import org.metavault.userservice.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;

@DataJpaTest
public class RoleRepoTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("권한 조회")
    void testFetchRole() {

        Role role = roleRepository.findById(1L)
                .orElseThrow(() -> new NoSuchElementException("Role not found"));

        Assertions.assertNotNull(role.getId());
        Assertions.assertEquals("ADMIN", role.getName());
    }
}
