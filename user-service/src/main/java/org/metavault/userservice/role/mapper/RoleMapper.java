package org.metavault.userservice.role.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.metavault.userservice.role.model.dto.RoleDto;
import org.metavault.userservice.role.model.entity.Role;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    // Role -> RoleDto
    RoleDto RoleToRoleDto(Role role);

    // RoleDto -> Role
    Role RoleDtoToRole(RoleDto role);
}