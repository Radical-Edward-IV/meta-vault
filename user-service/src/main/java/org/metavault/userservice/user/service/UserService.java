package org.metavault.userservice.user.service;

import org.metavault.userservice.user.model.dto.UserDto;
import org.metavault.userservice.user.model.payload.request.SignUpRequest;

public interface UserService {

    UserDto addUser(SignUpRequest signUpRequest);
}
