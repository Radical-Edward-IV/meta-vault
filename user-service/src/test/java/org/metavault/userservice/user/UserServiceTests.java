package org.metavault.userservice.user;

import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.metavault.userservice.role.model.entity.Role;
import org.metavault.userservice.role.repository.RoleRepository;
import org.metavault.userservice.user.model.dto.UserDto;
import org.metavault.userservice.user.model.entity.User;
import org.metavault.userservice.user.model.payload.request.SignUpRequest;
import org.metavault.userservice.user.repository.UserRepository;
import org.metavault.userservice.user.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/*
⦿ @ExtendWith
    JUnit 5(Jupiter)와 Mockito를 통합하기 위해 사용되는 어노테이션입니다.
    MockitoAnnotations.openMocks(this)와 같은 코드를 직접 호출할 필요가 없어집니다.

⦿ @MockitoSettings
    Mockito의 동작 방식을 설정하기 위해 사용됩니다.
    strictness는 Mockito가 엄격한(strict) 모드 또는 관대한(lenient) 모드에서 동작할지 여부를 설정합니다.

    • LENIENT 모드: 엄격하지 않은 모드로, 테스트에서 사용되지 않는 목 객체나 스텁(stub)에 대해 경고하지 않습니다.
                    이는 초기 설정이나 테스트 준비 중에 자주 사용됩니다.
    • STRICT 모드: 엄격한 모드로, 테스트에서 사용되지 않는 목 객체나 스텁에 대해 경고합니다.
                   이는 테스트의 정확성을 높이고 불필요한 목 설정을 줄이는 데 도움이 됩니다.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTests {

    @Mock
    RoleRepository roleRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    // 테스트 데이터
    Role defaultUserRole;

    SignUpRequest signUpRequest;

    private final String ENCODED_PASSWORD = "encodedPassword";

    @Test
    @DisplayName("사용자 추가")
    void testAddUser() {

        UserDto user = userService.addUser(signUpRequest);

        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(signUpRequest.getEmail(), user.getEmail());
        Assertions.assertEquals(ENCODED_PASSWORD, user.getPassword());
        Assertions.assertEquals(signUpRequest.getFirstName(), user.getFirstName());
        Assertions.assertEquals(signUpRequest.getLastName(), user.getLastName());
        Assertions.assertEquals(signUpRequest.getPhone(), user.getPhone());
        Assertions.assertEquals(defaultUserRole.getName(), user.getRole().getName());
    }

    @BeforeEach
    void setUp() {

        ReflectionTestUtils.setField(userService, "DEFAULT_USER_ROLE", "USER");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        defaultUserRole = Role.builder()
                .id(1L)
                .name("USER")
                .build();

        signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("edward@meta-vault.or.kr");
        signUpRequest.setPassword("!Q2w3e4r5t");
        signUpRequest.setFirstName("Edward");
        signUpRequest.setLastName("Kim");
        signUpRequest.setPhone("010-1234-5678");

        if (!validator.validate(signUpRequest).isEmpty()) throw new ValidationException();

        when(roleRepository.findByName("USER")).thenReturn(Optional.of(defaultUserRole));

        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> {
            User savedUser = invocationOnMock.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });

        when(passwordEncoder.encode(anyString())).thenReturn(ENCODED_PASSWORD);
    }
}