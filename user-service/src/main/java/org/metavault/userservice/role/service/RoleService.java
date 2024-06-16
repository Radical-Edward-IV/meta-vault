package org.metavault.userservice.role.service;

import org.metavault.userservice.role.model.dto.RoleDto;

public interface RoleService {

    RoleDto findByName(String name);
}
