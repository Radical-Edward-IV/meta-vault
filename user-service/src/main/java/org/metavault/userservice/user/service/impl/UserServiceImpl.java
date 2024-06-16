package org.metavault.userservice.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.metavault.userservice.role.model.entity.Role;
import org.metavault.userservice.role.repository.RoleRepository;
import org.metavault.userservice.user.mapper.UserMapper;
import org.metavault.userservice.user.model.dto.UserDto;
import org.metavault.userservice.user.model.entity.User;
import org.metavault.userservice.user.model.payload.request.SignUpRequest;
import org.metavault.userservice.user.repository.UserRepository;
import org.metavault.userservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${default.user.role:USER}")
    private String DEFAULT_USER_ROLE;

    @Override
    public UserDto addUser(SignUpRequest signUpRequest) {

        // 이메일 및 비밀번호 구성 정책은 Validation을 이용하여 검증함

        // 사용자 Email 중복 여부 검증 로직 필요

        String email     = signUpRequest.getEmail();
        String password  = passwordEncoder.encode(signUpRequest.getPassword());
        String firstName = signUpRequest.getFirstName();
        String lastName  = signUpRequest.getLastName();
        String phone     = signUpRequest.getPhone();

        Role role = roleRepository.findByName(DEFAULT_USER_ROLE)
                .orElseThrow(() -> new NoSuchElementException("Role not found"));

        User user = User.builder()
                .email(email).password(password)
                .firstName(firstName).lastName(lastName)
                .phone(phone)
                .role(role)
                .build();

        user = userRepository.save(user);

        UserDto userDto = UserMapper.INSTANCE.UserToUserDto(user);

        return userDto;
    }
}