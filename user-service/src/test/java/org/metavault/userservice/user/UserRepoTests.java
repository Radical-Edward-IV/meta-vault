package org.metavault.userservice.user;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.metavault.userservice.role.model.entity.Role;
import org.metavault.userservice.role.repository.RoleRepository;
import org.metavault.userservice.user.model.entity.User;
import org.metavault.userservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스를 사용을 원할 경우
public class UserRepoTests {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    // Fixtures
    private static List<User> users;

    @BeforeEach
    void setUp() {

        Role admin = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new NoSuchElementException("Role not found"));

        Role dba = roleRepository.findByName("DBA")
                .orElseThrow(() -> new NoSuchElementException("Role not found"));

        // Fixture
        User edward = User.builder()
                .email("edward@meta-vault.or.kr").password("!Q2w3e4r5t")
                .firstName("Edward").lastName("Kim")
                .phone("010-1234-5678")
                .role(admin)
                .build();

        User theodore = User.builder()
                .email("theodore@meta-vault.or.kr").password("!Q2w3e4r5t")
                .firstName("Theodore").lastName("Twombly")
                .phone("010-2222-3333")
                .role(dba)
                .build();

        users = Arrays.asList(edward, theodore);

        userRepository.saveAll(users);
    }

    @Test
    @DisplayName("사용자 추가")
    @Transactional
    @Rollback
    void testSaveUser() {

        Role developer = roleRepository.findByName("DEVELOPER")
                .orElseThrow(() -> new NoSuchElementException("Role not found"));

        User user = User.builder()
                .email("wade@meta-vault.org").password("!Q2w3e4r5t")
                .firstName("Wade").lastName("Ripple")
                .phone("010-3333-4444")
                .role(developer)
                .build();

        User newcomer = userRepository.save(user);

        Assertions.assertNotNull(newcomer.getId());
        Assertions.assertEquals(user.getEmail(), newcomer.getEmail());
        Assertions.assertEquals(user.getPassword(), newcomer.getPassword());
        Assertions.assertEquals(user.getFirstName(), newcomer.getFirstName());
        Assertions.assertEquals(user.getLastName(), newcomer.getLastName());
        Assertions.assertEquals(user.getPhone(), newcomer.getPhone());
        Assertions.assertEquals(user.getRole(), newcomer.getRole());
    }

    @Test
    @DisplayName("사용자 조회")
    @Transactional
    @Rollback
    void testFetchUser() {

        User user = userRepository.findByEmail("edward@meta-vault.or.kr")
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(users.get(0).getEmail(), user.getEmail());
        Assertions.assertEquals(users.get(0).getFirstName(), user.getFirstName());
        Assertions.assertEquals(users.get(0).getLastName(), user.getLastName());
        Assertions.assertEquals(users.get(0).getPhone(), user.getPhone());
        Assertions.assertEquals(users.get(0).getRole(), user.getRole());
    }
}
