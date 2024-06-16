package org.metavault.userservice.role.model.dto;

import lombok.Builder;
import lombok.Data;
import org.metavault.userservice.role.model.RoleCommon;
import org.metavault.userservice.role.model.entity.Role;

@Data
@Builder
public class RoleDto implements RoleCommon {

    private Long id;

    private String name;

    public static RoleDto entityToDto(Role role) {

        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
