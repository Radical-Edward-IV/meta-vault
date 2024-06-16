package org.metavault.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles("dev")
class UserServiceApplicationTests {

    @Autowired
    Environment env;

    @Test
    void contextLoads() {
    }

    @Test
    void activeProfiles() {

        String[] profiles = env.getActiveProfiles();

        Arrays.stream(profiles).forEach(System.out::println);
    }
}
