package org.metavault.userservice.role.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.metavault.userservice.role.model.dto.RoleDto;
import org.metavault.userservice.role.repository.RoleRepository;
import org.metavault.userservice.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleDto findByName(String name) {

        RoleDto roleDto = RoleDto.entityToDto(
                roleRepository.findByName(name)
                        .orElseThrow(() -> new NoSuchElementException("Role not found"))
        );

        return roleDto;
    }
}
