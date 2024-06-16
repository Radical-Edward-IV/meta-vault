package org.metavault.userservice.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.metavault.userservice.common.payload.response.ApiResponse;
import org.metavault.userservice.user.model.dto.UserDto;
import org.metavault.userservice.user.model.payload.request.SignUpRequest;
import org.metavault.userservice.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        UserDto newcomer = userService.addUser(signUpRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/{id}")
                .buildAndExpand(newcomer.getId()).toUri();

        return ResponseEntity.created(location).body(ApiResponse.builder().success(true).message("User registerd successfully").build());
    }
}
