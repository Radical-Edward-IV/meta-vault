package org.metavault.userservice.user.model.dto;

import lombok.Builder;
import lombok.Data;
import org.metavault.userservice.role.model.dto.RoleDto;
import org.metavault.userservice.user.model.UserCommon;

@Data
@Builder
public class UserDto implements UserCommon {

    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private RoleDto role;
}
